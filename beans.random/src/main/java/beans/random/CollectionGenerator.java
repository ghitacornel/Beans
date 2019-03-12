package beans.random;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import beans.reflection.ReflectionUtils;
import beans.reflection.ResultHolder;

public final class CollectionGenerator {

	private CollectionGenerator() {
		// helper only
	}

	public static Object generate(Class<?> clazz, int size) {

		// static array
		if (clazz.isArray()) {
			return Array.newInstance(clazz.getComponentType(), size);
		}

		// only collections are generated
		// maps are not processed
		// TODO find a way to handle them
		if (!Collection.class.isAssignableFrom(clazz)) {
			return null;
		}

		// fast checks
		if (List.class.isAssignableFrom(clazz)) {
			return new ArrayList<>();
		}

		if (Set.class.isAssignableFrom(clazz)) {
			return new HashSet<>();
		}

		if (Queue.class.isAssignableFrom(clazz)) {
			return new ArrayDeque<>();
		}

		// for any other types of concrete collections assume a default
		// constructor exists or throw exceptions otherwise
		ResultHolder<?> resultHolder = ReflectionUtils.createInstance(clazz);
		if (resultHolder.instance != null) {
			return resultHolder.instance;
		}

		throw new RuntimeException("Cannot create collection instance of type "
				+ clazz);
	}

}
