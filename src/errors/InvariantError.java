package errors;

public class InvariantError extends Error {
	
	private static final long serialVersionUID = 1L;
	
	public InvariantError(String e) {
		super(e);
	}

}