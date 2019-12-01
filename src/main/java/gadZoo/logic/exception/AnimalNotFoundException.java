package gadZoo.logic.exception;

public class AnimalNotFoundException extends Exception {
	
	private static final long serialVersionUID = 8684210995274777697L;

	public AnimalNotFoundException() {
		super();
	}

	public AnimalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public AnimalNotFoundException(String message) {
		super(message);
	}

	public AnimalNotFoundException(Throwable cause) {
		super(cause);
	}
}
