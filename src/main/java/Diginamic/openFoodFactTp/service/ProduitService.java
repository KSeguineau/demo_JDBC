package Diginamic.openFoodFactTp.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Diginamic.demo_JDBC.exception.TechnicalException;
import Diginamic.openFoodFactTp.dao.CategorieDao;
import Diginamic.openFoodFactTp.dao.MarqueDao;
import Diginamic.openFoodFactTp.dao.ProduitDao;
import Diginamic.openFoodFactTp.model.Produit;

/**
 * Classe gérant le traitement des produits
 * 
 * @author Kevin.s
 *
 */
public class ProduitService {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ProduitService.class);

	/**
	 * Constructeur
	 * 
	 */
	public ProduitService() {
		super();
	}

	/**
	 * renseigne les identifiants de la marque et de la catégorie pour chaque
	 * produit et appelle le dao pour les ajouter à la base
	 * 
	 * @param listeProduit
	 *            liste des produits
	 */
	public void traitementAllProduit(List<Produit> listeProduit) {
		CategorieDao categorieDao = new CategorieDao();
		MarqueDao marqueDao = new MarqueDao();
		ProduitDao produitDao = new ProduitDao();

		HashMap<String, Integer> listeCategorie = categorieDao.recupererAllId();
		HashMap<String, Integer> listeMarque = marqueDao.recupererAllId();

		for (Produit produit : listeProduit) {
			produit.setIdCategorie(listeCategorie.get(produit.getCategorie()));
			produit.setIdMarque(listeMarque.get(produit.getMarque()));
			if (produit.getIdCategorie() == null || produit.getIdMarque() == null) {
				SERVICE_LOG.error("une clé étrangère du produit est manquante");
				throw new TechnicalException("une clé étrangère du produit est manquante");
			}
		}

		produitDao.ajouterAllProduit(listeProduit);
		enregistrerIdProduit(listeProduit);

	}

	/**
	 * renseigne l'identifiant donné par la base pour chaque produit
	 * 
	 * @param listeProduit
	 *            liste des produits
	 */
	public void enregistrerIdProduit(List<Produit> listeProduit) {
		ProduitDao produitDao = new ProduitDao();
		HashMap<String, Integer> hashmapProduit = produitDao.recupererAllId();
		// nom + marque + categorie
		for (Produit produit : listeProduit) {
			produit.setId(hashmapProduit.get(produit.getNom() + produit.getIdMarque() + produit.getIdCategorie()));
		}
	}

}
