package Diginamic.openFoodFactTp.exception;

/**
 * exception technique lors de l'interaction avec la base de données
 * 
 * @author Kevin.s
 *
 */
public class TechnicalException extends RuntimeException {

	/**
	 * Constructeur
	 * 
	 */
	public TechnicalException() {
	}

	/**
	 * Constructeur
	 * 
	 * @param arg0
	 */
	public TechnicalException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructeur
	 * 
	 * @param arg0
	 */
	public TechnicalException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * Constructeur
	 * 
	 * @param arg0
	 * @param arg1
	 */
	public TechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * Constructeur
	 * 
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public TechnicalException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
