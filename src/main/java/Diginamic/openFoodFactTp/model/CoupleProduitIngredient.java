package Diginamic.openFoodFactTp.model;

/**
 * Classe representant un couple d'identifiant
 * 
 * @author Kevin.s
 *
 */
public class CoupleProduitIngredient {

	/** produit : Integer */
	private Integer produit;
	/** ingredient : Integer */
	private Integer ingredient;

	/**
	 * Constructeur
	 * 
	 * @param produit
	 *            identifiant du produit
	 * @param ingredient
	 *            identifiant de l'ingredient
	 */
	public CoupleProduitIngredient(Integer produit, Integer ingredient) {
		super();
		this.produit = produit;
		this.ingredient = ingredient;
	}

	/**
	 * Getter
	 * 
	 * @return the produit
	 */
	public Integer getProduit() {
		return produit;
	}

	/**
	 * Setters
	 * 
	 * @param produit
	 *            the produit to set
	 */
	public void setProduit(Integer produit) {
		this.produit = produit;
	}

	/**
	 * Getter
	 * 
	 * @return the ingredient
	 */
	public Integer getIngredient() {
		return ingredient;
	}

	/**
	 * Setters
	 * 
	 * @param ingredient
	 *            the ingredient to set
	 */
	public void setIngredient(Integer ingredient) {
		this.ingredient = ingredient;
	}

}
