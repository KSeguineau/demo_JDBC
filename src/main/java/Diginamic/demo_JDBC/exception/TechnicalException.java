package Diginamic.demo_JDBC.exception;

/**
 * exception renvoyée lorsqu'une sql exception est lancée lors de la connection
 * à la BDD
 * 
 * @author Kevin.s
 *
 */
public class TechnicalException extends RuntimeException {

	public TechnicalException() {
	}

	public TechnicalException(String arg0) {
		super(arg0);
	}

	public TechnicalException(Throwable arg0) {
		super(arg0);
	}

	public TechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public TechnicalException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
