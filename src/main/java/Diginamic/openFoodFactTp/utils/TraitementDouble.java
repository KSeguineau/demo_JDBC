package Diginamic.openFoodFactTp.utils;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Classe permettant de traiter le parsing des String en Double
 * 
 * @author Kevin.s
 *
 */
public class TraitementDouble {

	private TraitementDouble() {

	}

	public static Double recuperDouble(String str) {
		if (NumberUtils.isCreatable(str)) {
			return Double.parseDouble(str);
		} else {
			return 0.0;
		}
	}

}
