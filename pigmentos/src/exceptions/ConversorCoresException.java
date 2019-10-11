package exceptions;

public class ConversorCoresException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConversorCoresException() {
		super();
	}
	
	public ConversorCoresException (String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	public ConversorCoresException (String arg0) {
		super(arg0);
	}
	
	public ConversorCoresException (Throwable arg0) {
		super(arg0);
	}

}
