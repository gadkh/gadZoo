package gadZoo.logic.exception;

public class AnimalAlreadyExistException extends Exception {
	
	private static final long serialVersionUID = -8976965045966680590L;

	public AnimalAlreadyExistException() {
		super();
	}

	public AnimalAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnimalAlreadyExistException(String message) {
		super(message);
	}

	public AnimalAlreadyExistException(Throwable cause) {
		super(cause);
	}
}
