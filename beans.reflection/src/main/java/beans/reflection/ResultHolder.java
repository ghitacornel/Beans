package beans.reflection;

/**
 * Encapsulates the result of a class instance creation<br>
 * Can be either an instance or an exception
 *
 * @param <T>
 */
public class ResultHolder<T> {

    /**
     * class to be instantiated
     */
    final public Class<T> clazz;

    /**
     * result
     */
    public T instance;

    /**
     * exception
     */
    public Exception exception;

    public ResultHolder(Class<T> clazz) {
        this.clazz = clazz;
    }

}
