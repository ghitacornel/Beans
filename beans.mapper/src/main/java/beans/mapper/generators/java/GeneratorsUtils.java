package beans.mapper.generators.java;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

final public class GeneratorsUtils {

	private GeneratorsUtils() {
		// helper
	}

	/**
	 * 
	 * @param map
	 * @param getters
	 * @param source
	 *            source parameter name
	 * @param target
	 *            destination parameter name
	 * @return
	 */
	public static String buildConversionMethodContent(Map<Method, Method> map,
			String source, String target) {

		StringBuilder builder = new StringBuilder();

		for (Method getter : sortByName(map.keySet())) {
			Method setter = map.get(getter);
			Class<?> getterReturnType = getter.getReturnType();
			Class<?> setterParameterType = setter.getParameterTypes()[0];

			builder.append("\n");
			if (setterParameterType.isPrimitive()) {
				if (getterReturnType.isPrimitive()) {
					simpleToSimple(source, target, builder, getter, setter);
				} else {

					// primitive = class
					builder.append("if(" + source + "." + getter.getName()
							+ "()==null)");
					builder.append(target + ".");
					builder.append(setter.getName());
					if (setterParameterType.equals(boolean.class))
						builder.append("(false);");
					else if (setterParameterType.equals(byte.class))
						builder.append("((byte)0);");
					else if (setterParameterType.equals(short.class))
						builder.append("((short)0);");
					else if (setterParameterType.equals(long.class))
						builder.append("((long)0);");
					else if (setterParameterType.equals(float.class))
						builder.append("((float)0);");
					else if (setterParameterType.equals(double.class))
						builder.append("((double)0);");
					else
						builder.append("(0);");

					builder.append("else ");
					builder.append(target + ".");
					builder.append(setter.getName());
					builder.append("(");
					builder.append(source + ".");
					builder.append(getter.getName());
					builder.append("()." + setterParameterType.getName()
							+ "Value()");
					builder.append(");");

				}
			} else {
				if (getterReturnType.isPrimitive()) {

					// class = primitive
					if (setterParameterType.equals(Float.class)) {
						builder.append(target + ".");
						builder.append(setter.getName());
						builder.append("(new " + setterParameterType.getName()
								+ "(");
						builder.append(source + ".");
						builder.append(getter.getName());
						builder.append("())");
						builder.append(");");
					} else if (setterParameterType.equals(Double.class)) {
						builder.append(target + ".");
						builder.append(setter.getName());
						builder.append("(new " + setterParameterType.getName()
								+ "(");
						builder.append(source + ".");
						builder.append(getter.getName());
						builder.append("())");
						builder.append(");");
					} else {
						builder.append(target + ".");
						builder.append(setter.getName());
						builder.append("(" + setterParameterType.getName()
								+ ".valueOf(");
						builder.append(source + ".");
						builder.append(getter.getName());
						builder.append("())");
						builder.append(");");
					}
				} else {

					// class = class

					// check first for compatible but different classes of
					// date/time category
					if (getterReturnType.isAssignableFrom(Date.class)
							&& !getterReturnType.equals(setterParameterType)) {

						builder.append("if(" + source + "." + getter.getName()
								+ "()==null)");
						builder.append(target + ".");
						builder.append(setter.getName());
						builder.append("(null);");
						builder.append("else ");
						builder.append(target + ".");
						builder.append(setter.getName());
						builder.append("(new "
								+ setterParameterType.getCanonicalName() + "(");
						builder.append(source + "." + getter.getName()
								+ "().getTime()");
						builder.append("));");

					} else {
						simpleToSimple(source, target, builder, getter, setter);
					}

				}
			}
		}

		return builder.toString();
	}

	private static void simpleToSimple(String source, String target,
			StringBuilder builder, Method getter, Method setter) {
		builder.append(target);
		builder.append(".");
		builder.append(setter.getName());
		builder.append("(");
		builder.append(source);
		builder.append(".");
		builder.append(getter.getName());
		builder.append("()");
		builder.append(");");
	}

	private static List<Method> sortByName(Set<Method> set) {
		List<Method> list = new ArrayList<Method>(set);
		Collections.sort(list, new Comparator<Method>() {

			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});
		return list;
	}
}
