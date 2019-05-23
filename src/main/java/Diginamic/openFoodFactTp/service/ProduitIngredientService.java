package Diginamic.openFoodFactTp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import Diginamic.openFoodFactTp.dao.IngredientDao;
import Diginamic.openFoodFactTp.dao.ProduitIngredientDao;
import Diginamic.openFoodFactTp.model.CoupleProduitIngredient;
import Diginamic.openFoodFactTp.model.Produit;

/**
 * Classe gerant le traitement nécessaire pour l'ajout dans la table de jointure
 * entre produit et ingrédient
 * 
 * @author Kevin.s
 *
 */
public class ProduitIngredientService {

	/**
	 * Constructeur
	 * 
	 */
	public ProduitIngredientService() {
		super();
	}

	/**
	 * méthode qui fait le lien entre l'identifiant du produit et l'identifiant
	 * de ses ingrédients puis appelle le dao pour les insérer en base
	 * 
	 * @param listeProduit
	 *            liste des produits
	 */
	public void traitementJointureProduitIngredient(List<Produit> listeProduit) {

		IngredientDao ingredientDao = new IngredientDao();
		ProduitIngredientDao produitIngredientDao = new ProduitIngredientDao();
		HashMap<String, Integer> listeIngredient = ingredientDao.recupererAllId();
		List<CoupleProduitIngredient> listeCouple = new ArrayList<>();

		for (Produit produit : listeProduit) {
			// élimination des ingrédients en double dans un même produits
			HashSet<String> ingredients = new HashSet<>();
			ingredients.addAll(produit.getIngredients());

			for (String str : ingredients) {
				listeCouple.add(new CoupleProduitIngredient(produit.getId(), listeIngredient.get(str)));
			}
		}
		produitIngredientDao.ajouterAllCoupleProduitIngredient(listeCouple);

	}

}
