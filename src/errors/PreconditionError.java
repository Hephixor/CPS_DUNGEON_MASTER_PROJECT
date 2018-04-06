package errors;

public class PreconditionError extends Error{
	
	private static final long serialVersionUID = 1L;
	
	public PreconditionError(String e) {
		super(e);
	}

}
