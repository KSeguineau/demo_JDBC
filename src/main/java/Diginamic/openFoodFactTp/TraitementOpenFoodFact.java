package Diginamic.openFoodFactTp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Diginamic.demo_JDBC.exception.TechnicalException;
import Diginamic.openFoodFactTp.dao.CategorieDao;
import Diginamic.openFoodFactTp.dao.IngredientDao;
import Diginamic.openFoodFactTp.dao.MarqueDao;
import Diginamic.openFoodFactTp.model.Produit;
import Diginamic.openFoodFactTp.service.ProduitIngredientService;
import Diginamic.openFoodFactTp.service.ProduitService;
import Diginamic.openFoodFactTp.utils.ConnectionUtils;
import Diginamic.openFoodFactTp.utils.StringUtils;

/**
 * point d'entrée du traitement
 * 
 * @author Kevin.s
 *
 */
public class TraitementOpenFoodFact {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(TraitementOpenFoodFact.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		long debut = System.currentTimeMillis();

		// initialisation du driver de la base de données
		try {
			String driverName = ConnectionUtils.getDriverName();
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			SERVICE_LOG.error("impossible de charger le driver", e1);
			throw new TechnicalException("impossible de charger le driver", e1);
		}

		HashSet<String> listeCategorie = new HashSet<>();
		HashSet<String> listeMarque = new HashSet<>();
		HashSet<String> listeIngrédients = new HashSet<>();
		List<Produit> listeProduit = new ArrayList<>();

		CategorieDao categorieDao = new CategorieDao();
		MarqueDao marqueDao = new MarqueDao();
		IngredientDao ingredientDao = new IngredientDao();
		ProduitService produitService = new ProduitService();
		ProduitIngredientService produitIngredientService = new ProduitIngredientService();

		// récupération du fichier à mettre en base de données
		File file = new File("data/openFoodFacts.csv");
		List<String> listeLigne;
		try {
			listeLigne = FileUtils.readLines(file, "utf8");

		} catch (IOException e) {
			SERVICE_LOG.error("impossible de charger le fichier de données", e);
			throw new TechnicalException("impossible de charger le fichier de données", e);
		}

		// longueur tableau 11
		// 0 catégorie ajout dans hashset catégorie
		// 1 marque ajout dans hashset marque
		// 2 nom
		// 3 grade
		// 4 ingredient split sur les virgules puit ajout dans hashset
		// ingrédient
		// 5 energie
		// 6 graisse
		// 7 sucre
		// 8 fibre
		// 9 protéine
		// 10 sel
		// Parsing du fichier
		for (int i = 1; i < listeLigne.size(); i++) {

			String[] ligne = StringUtils.recupererElemProduit(listeLigne.get(i));
			List<String> ingredients = Arrays.asList(ligne[4].split(",|-|;"));

			listeCategorie.add(ligne[0]);
			listeMarque.add(ligne[1]);
			listeIngrédients.addAll(ingredients);

			Produit produit = new Produit(ligne, ingredients);
			listeProduit.add(produit);

		}

		// élimination des produits en double
		HashSet<Produit> produitSansDoublon = new HashSet<>();
		produitSansDoublon.addAll(listeProduit);
		listeProduit = new ArrayList<>(produitSansDoublon);

		// remplissage des tables categorie,marque,ingredients
		categorieDao.ajouterAllCategorie(listeCategorie);
		marqueDao.ajouterAllMarque(listeMarque);
		ingredientDao.ajouterAllIngredient(listeIngrédients);

		// traitement des produits
		produitService.traitementAllProduit(listeProduit);
		// traitement de la jointure
		produitIngredientService.traitementJointureProduitIngredient(listeProduit);

		long fin = System.currentTimeMillis();
		System.out.println("temps de traitement: " + ((fin - debut) / 1000));
	}

}
