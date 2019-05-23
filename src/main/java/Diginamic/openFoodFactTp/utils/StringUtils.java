package Diginamic.openFoodFactTp.utils;

/**
 * classe outils permettant de spliter chaque ligne du fichier
 * 
 * @author Kevin.s
 *
 */
public class StringUtils {

	private StringUtils() {

	}

	/**
	 * split les 11 premieres données de la ligne
	 * 
	 * @param produit
	 * @return tableau de String
	 */
	public static String[] recupererElemProduit(String produit) {
		String[] ligne = produit.split("\\|", 11);
		ligne[10] = ligne[10].split("\\|")[0];
		return ligne;
	}
}
