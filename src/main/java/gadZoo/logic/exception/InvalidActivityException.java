package gadZoo.logic.exception;

public class InvalidActivityException extends Exception {

	private static final long serialVersionUID = 1576304007783673092L;

	public InvalidActivityException() {
		super();
	}

	public InvalidActivityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidActivityException(String message) {
		super(message);
	}

	public InvalidActivityException(Throwable cause) {
		super(cause);
	}

}
