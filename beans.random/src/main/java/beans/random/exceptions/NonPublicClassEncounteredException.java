package beans.random.exceptions;

@SuppressWarnings("serial")
public class NonPublicClassEncounteredException extends RuntimeException {

    public NonPublicClassEncounteredException(Class<?> clazz) {
        super("Cannot instantiate non public " + clazz);
    }

}
