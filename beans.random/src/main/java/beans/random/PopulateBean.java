package beans.random;

import beans.random.configuration.Configuration;
import beans.random.configuration.ConfigurationUtil;
import beans.random.configuration.InjectionStrategy;
import beans.random.exceptions.NonPublicClassEncounteredException;
import beans.random.generators.Generator;
import beans.reflection.ReflectionUtils;
import beans.reflection.ResultHolder;
import beans.reflection.utils.ClassUtils;
import beans.reflection.utils.FieldUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.*;

final class PopulateBean {

    final private Configuration configuration;
    final private PredefinedGenerators predefinedGenerators;

    public PopulateBean(Configuration configuration) {
        this.configuration = configuration;
        predefinedGenerators = new PredefinedGenerators(this.configuration);
    }

    public void populate(Object instance) {
        if (configuration.getInjectionStrategy() == InjectionStrategy.FIELD) {
            populateInstance(instance, 0);
        } else if (configuration.getInjectionStrategy() == InjectionStrategy.SETTER) {
            populateInstance(instance, 0);
        } else {
            throw new RuntimeException("unsupported strategy "
                    + configuration.getInjectionStrategy());
        }
    }

    private void populateInstance(Object instance, int level) {
        List<Field> fields = ClassUtils.getAllFields(instance.getClass());
        for (Field field : fields) {
            if (isAcceptedField(field)) {
                populateField(instance, field, level);
            }
        }
    }

    /**
     * @param field
     * @return true if field is to be processed, false otherwise
     */
    private boolean isAcceptedField(Field field) {

        if (field.isSynthetic()) {
            return false;
        }
        if (Modifier.isStatic(field.getModifiers())) {
            return false;
        }
        if (Modifier.isFinal(field.getModifiers())) {
            if (configuration.getIgnoredFields().isIgnoreFinals()) {
                return false;
            }
        }

        // verify ignored
        {
            if (!(field.getType().isPrimitive() || field.getType().isArray())
                    && configuration.getIgnoredPackages().getPackages()
                    .contains(field.getType().getPackage().getName())) {
                return false;
            }
            if (configuration.getIgnoredClasses().getClasses()
                    .contains(field.getType().getCanonicalName())) {
                return false;
            }
            if (configuration.getIgnoredFields().getFields()
                    .contains(field.getName())) {
                return false;
            }
            Set<String> ignoredClassFields = configuration.getIgnoredFields()
                    .getClassFields()
                    .get(field.getDeclaringClass().getCanonicalName());
            if (ignoredClassFields != null) {
                if (ignoredClassFields.contains(field.getName())) {
                    return false;
                }
            }
        }

        return true;
    }

    private void populateField(Object instance, Field field, int level) {

        Object value = populateWithOverwrites(instance, field, configuration);

        if (value == null) {
            value = populateFieldWithPredefined(instance, field,
                    predefinedGenerators);
        }

        // objects ? => check depth level
        if (configuration.getContainmentDepthLevel() <= level) {
            return;
        }

        // try with collections
        Object collection = populateFieldWithCollection(instance, field,
                configuration.getCollectionsSize());
        if (collection != null) {
            populateCollectionFieldWithElements(field, level, collection);
            return;
        }

        populateFieldWithSimpleBean(instance, field, level + 1);
    }

    /**
     * @param instance
     * @param field
     * @return
     */
    private static Object populateWithOverwrites(Object instance, Field field,
                                                 Configuration configuration) {

        // per class generator ?
        {
            Generator<?> generator = configuration.getOverwriteClass()
                    .getGenerators().get(field.getType().getCanonicalName());
            if (generator != null) {
                Object value = generator.getValue();
                FieldUtils.setFieldValue(field, value, instance);
                return value;
            }
        }

        // per field generator ?
        {
            Generator<?> generator = configuration.getOverwriteFields()
                    .getFieldGenerators().get(field.getName());
            if (generator != null) {
                Object value = generator.getValue();
                FieldUtils.setFieldValue(field, value, instance);
                return value;
            }
        }

        // per class field generator ?
        {
            Map<String, Generator<?>> classFieldGenerators = configuration
                    .getOverwriteFields().getClassFieldsGenerators()
                    .get(instance.getClass().getCanonicalName());
            if (classFieldGenerators != null) {
                Generator<?> generator = classFieldGenerators.get(field
                        .getName());
                if (generator != null) {
                    Object value = generator.getValue();
                    FieldUtils.setFieldValue(field, value, instance);
                    return value;
                }
            }
        }

        return null;
    }

    private static Object populateFieldWithPredefined(Object instance,
                                                      Field field, PredefinedGenerators predefinedGenerators) {
        Object value = predefinedGenerators.generate(field.getType());
        if (value != null) {
            FieldUtils.setFieldValue(field, value, instance);
        }
        return value;
    }

    private static Object populateFieldWithCollection(Object instance,
                                                      Field field, int size) {

        Class<?> clazz = field.getType();

        // TODO find a way to handle Maps
        if (Map.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("Maps content cannot be generated");
        }

        // collections already instantiated are not re-created
        if (Collection.class.isAssignableFrom(clazz)) {
            Object object = FieldUtils.getFieldValue(field, instance);
            if (object != null) {
                return object;
            }
        }

        Object object = CollectionGenerator.generate(clazz, size);
        if (object != null) {
            FieldUtils.setFieldValue(field, object, instance);
        }

        return object;
    }

    private void populateCollectionFieldWithElements(Field field, int level,
                                                     Object collection) {

        // get collection element types
        Class<?> clazz;
        if (collection.getClass().isArray()) {
            clazz = collection.getClass().getComponentType();
        } else {
            ParameterizedType parameterizedType = (ParameterizedType) field
                    .getGenericType();
            clazz = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        }

        // list of elements to be added to the collection
        List<Object> elements = new ArrayList<>();

        // generate elements for collection
        for (int i = 0; i < configuration.getCollectionsSize(); i++) {

            // try to create a new collection item
            Object element;

            // try with primitives
            element = predefinedGenerators.generate(clazz);

            // try with simple bean finally
            if (element == null) {
                ResultHolder<?> resultHolder = ReflectionUtils.createInstance(clazz);
                if (resultHolder.instance != null) {
                    element = resultHolder.instance;

                    // populate recurrent
                    populateInstance(element, level + 1);

                    elements.add(element);

                } else {

                    // process failure
                    ConfigurationUtil.process(resultHolder, configuration);

                }
            }

        }

        // add created elements to collection
        if (collection.getClass().isArray()) {
            int i = 0;
            for (Object element : elements) {
                Array.set(collection, i, element);
                i++;
            }
        } else {
            @SuppressWarnings("unchecked")
            Collection<Object> temp = ((Collection<Object>) collection);
            temp.addAll(elements);
        }

    }

    private void populateFieldWithSimpleBean(Object instance, Field field,
                                             int level) {

        // non public classes are not instantiated
        if (!ClassUtils.isPublic(field.getType())) {
            if (configuration.raiseExceptionWhenNonPublicClassEncountered()) {
                throw new NonPublicClassEncounteredException(field.getType());
            } else {
                return;
            }
        }

        ResultHolder<?> resultHolder;

        // inner class ?
        if (field.getType().getEnclosingClass() != null) {
            if (Modifier.isStatic(field.getType().getModifiers())) {
                resultHolder = ReflectionUtils.createInstance(field.getType());
            } else {
                resultHolder = ReflectionUtils.createInnerInstance(instance,
                        field.getType());
            }
        } else {
            resultHolder = ReflectionUtils.createInstance(field.getType());
        }

        if (resultHolder.instance != null) {
            FieldUtils.setFieldValue(field, resultHolder.instance, instance);
            populateInstance(resultHolder.instance, level);
        } else {
            ConfigurationUtil.process(resultHolder, configuration);
        }

    }

}
