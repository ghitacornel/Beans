package beans.random.exceptions;

@SuppressWarnings("serial")
public class WrappedInstantiationException extends RuntimeException {

    public WrappedInstantiationException(Exception e) {
        super(e);
    }

}
