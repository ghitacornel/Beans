package beans.mapper.generators.java;

import beans.mapper.Mapper;

public class JavaGenerator {

    private static Class<?> getGeneratedClass(Class<?> sourceClass, Class<?> destinationClass) {

        SourceClass source = new SourceClass(sourceClass, destinationClass);
        SourceFile sourceFile = new SourceFile(source.getCanonicalClassName(), source.getContent());
        MemoryClassLoader memoryClassLoader = new MemoryClassLoader(sourceFile);
        Thread.currentThread().setContextClassLoader(memoryClassLoader);

        try {
            return memoryClassLoader.findClass(source.getCanonicalClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @SuppressWarnings("unchecked")
    public static <S, D> Mapper<S, D> buildConvertor(Class<S> source, Class<D> destination) {
        try {
            return (Mapper<S, D>) getGeneratedClass(source, destination).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
