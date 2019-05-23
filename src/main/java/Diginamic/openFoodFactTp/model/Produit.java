package Diginamic.openFoodFactTp.model;

import java.util.List;

import Diginamic.openFoodFactTp.utils.TraitementDouble;

/**
 * classe representant un produit
 * 
 * @author Kevin.s
 *
 */
public class Produit {

	/** id : Integer */
	private Integer id;
	/** nom : String */
	private String nom;
	/** categorie : String */
	private String categorie;
	/** IdCategorie : Integer */
	private Integer IdCategorie;
	/** marque : String */
	private String marque;
	/** IdMarque : Integer */
	private Integer IdMarque;
	/** grade : String */
	private String grade;
	/** ingredients : List<String> */
	List<String> ingredients;
	/** energie : Double */
	private Double energie;
	/** graisse : Double */
	private Double graisse;
	/** surcre : Double */
	private Double surcre;
	/** fibre : Double */
	private Double fibre;
	/** proteine : Double */
	private Double proteine;
	/** sel : Double */
	private Double sel;

	/**
	 * Constructeur
	 * 
	 * @param ligne
	 *            ligne provenant du fichier à inserer en base de données
	 * @param ingredients
	 *            liste des ingrédients du produit
	 */
	public Produit(String[] ligne, List<String> ingredients) {
		super();
		this.nom = ligne[2];
		this.categorie = ligne[0];
		this.marque = ligne[1];
		this.grade = ligne[3];
		this.ingredients = ingredients;
		this.energie = TraitementDouble.recuperDouble(ligne[5]);
		this.graisse = TraitementDouble.recuperDouble(ligne[6]);
		this.surcre = TraitementDouble.recuperDouble(ligne[7]);
		this.fibre = TraitementDouble.recuperDouble(ligne[8]);
		this.proteine = TraitementDouble.recuperDouble(ligne[9]);
		this.sel = TraitementDouble.recuperDouble(ligne[10]);
	}

	/**
	 * Getter
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setters
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter
	 * 
	 * @return the categorie
	 */
	public String getCategorie() {
		return categorie;
	}

	/**
	 * Setters
	 * 
	 * @param categorie
	 *            the categorie to set
	 */
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * Getter
	 * 
	 * @return the idCategorie
	 */
	public Integer getIdCategorie() {
		return IdCategorie;
	}

	/**
	 * Setters
	 * 
	 * @param idCategorie
	 *            the idCategorie to set
	 */
	public void setIdCategorie(Integer idCategorie) {
		IdCategorie = idCategorie;
	}

	/**
	 * Getter
	 * 
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}

	/**
	 * Setters
	 * 
	 * @param marque
	 *            the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}

	/**
	 * Getter
	 * 
	 * @return the idMarque
	 */
	public Integer getIdMarque() {
		return IdMarque;
	}

	/**
	 * Setters
	 * 
	 * @param idMarque
	 *            the idMarque to set
	 */
	public void setIdMarque(Integer idMarque) {
		IdMarque = idMarque;
	}

	/**
	 * Getter
	 * 
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * Setters
	 * 
	 * @param grade
	 *            the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * Getter
	 * 
	 * @return the ingredients
	 */
	public List<String> getIngredients() {
		return ingredients;
	}

	/**
	 * Setters
	 * 
	 * @param ingredients
	 *            the ingredients to set
	 */
	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * Getter
	 * 
	 * @return the energie
	 */
	public Double getEnergie() {
		return energie;
	}

	/**
	 * Setters
	 * 
	 * @param energie
	 *            the energie to set
	 */
	public void setEnergie(Double energie) {
		this.energie = energie;
	}

	/**
	 * Getter
	 * 
	 * @return the graisse
	 */
	public Double getGraisse() {
		return graisse;
	}

	/**
	 * Setters
	 * 
	 * @param graisse
	 *            the graisse to set
	 */
	public void setGraisse(Double graisse) {
		this.graisse = graisse;
	}

	/**
	 * Getter
	 * 
	 * @return the surcre
	 */
	public Double getSurcre() {
		return surcre;
	}

	/**
	 * Setters
	 * 
	 * @param surcre
	 *            the surcre to set
	 */
	public void setSurcre(Double surcre) {
		this.surcre = surcre;
	}

	/**
	 * Getter
	 * 
	 * @return the fibre
	 */
	public Double getFibre() {
		return fibre;
	}

	/**
	 * Setters
	 * 
	 * @param fibre
	 *            the fibre to set
	 */
	public void setFibre(Double fibre) {
		this.fibre = fibre;
	}

	/**
	 * Getter
	 * 
	 * @return the proteine
	 */
	public Double getProteine() {
		return proteine;
	}

	/**
	 * Setters
	 * 
	 * @param proteine
	 *            the proteine to set
	 */
	public void setProteine(Double proteine) {
		this.proteine = proteine;
	}

	/**
	 * Getter
	 * 
	 * @return the sel
	 */
	public Double getSel() {
		return sel;
	}

	/**
	 * Setters
	 * 
	 * @param sel
	 *            the sel to set
	 */
	public void setSel(Double sel) {
		this.sel = sel;
	}

	/**
	 * Getter
	 * 
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Setters
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IdCategorie == null) ? 0 : IdCategorie.hashCode());
		result = prime * result + ((IdMarque == null) ? 0 : IdMarque.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (IdCategorie == null) {
			if (other.IdCategorie != null)
				return false;
		} else if (!IdCategorie.equals(other.IdCategorie))
			return false;
		if (IdMarque == null) {
			if (other.IdMarque != null)
				return false;
		} else if (!IdMarque.equals(other.IdMarque))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
