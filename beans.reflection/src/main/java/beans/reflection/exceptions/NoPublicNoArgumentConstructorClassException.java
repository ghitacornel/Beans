package beans.reflection.exceptions;

@SuppressWarnings("serial")
public class NoPublicNoArgumentConstructorClassException extends
        RuntimeException {

    public NoPublicNoArgumentConstructorClassException(Class<?> clazz) {
        super("Cannot instantiate class " + clazz
                + ", no public no argument constructor found");
    }

}
