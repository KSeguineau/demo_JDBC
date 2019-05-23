package Diginamic.openFoodFactTp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Diginamic.demo_JDBC.exception.TechnicalException;
import Diginamic.openFoodFactTp.model.Produit;
import Diginamic.openFoodFactTp.utils.ConnectionUtils;

/**
 * Dao gérant les produits
 * 
 * @author Kevin.s
 *
 */
public class ProduitDao {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ProduitDao.class);

	/**
	 * Constructeur
	 * 
	 */
	public ProduitDao() {
		super();
	}

	/**
	 * ajoute tous les produits à la base de données
	 * 
	 * @param listeProduit
	 *            liste des produits
	 */
	public void ajouterAllProduit(List<Produit> listeProduit) {

		PreparedStatement insertProduit = null;
		try {
			insertProduit = ConnectionUtils.getInstance().prepareStatement(
					"insert into produit (PDT_NOM,PDT_CATEGORIE,PDT_MARQUE,PDT_NUTRITIONGRADE,PDT_ENERGIE,PDT_GRAISSE,PDT_SUCRE,PDT_FIBRE,PDT_PROTEINE,PDT_SEL) values (?,?,?,?,?,?,?,?,?,?)");
			for (Produit produit : listeProduit) {
				insertProduit.setString(1, produit.getNom());
				insertProduit.setInt(2, produit.getIdCategorie());
				insertProduit.setInt(3, produit.getIdMarque());
				insertProduit.setString(4, produit.getGrade());
				insertProduit.setDouble(5, produit.getEnergie());
				insertProduit.setDouble(6, produit.getGraisse());
				insertProduit.setDouble(7, produit.getSurcre());
				insertProduit.setDouble(8, produit.getFibre());
				insertProduit.setDouble(9, produit.getProteine());
				insertProduit.setDouble(10, produit.getSel());
				insertProduit.executeUpdate();
			}
			ConnectionUtils.doCommit();
		} catch (SQLException e) {
			ConnectionUtils.doRollback();
			throw new TechnicalException("probleme d'insertion en base de données", e);
		} finally {
			if (insertProduit != null) {
				try {
					insertProduit.close();
				} catch (SQLException e) {
					throw new TechnicalException("impossible de fermer le preparedStatement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}

	/**
	 * récupère la table de produits et la stock dans une hashMap où la clé est
	 * la concaténation du nom, de la marque et de la catégorie
	 * 
	 * @return HashMap<String, Integer>
	 */
	public HashMap<String, Integer> recupererAllId() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		HashMap<String, Integer> listeProduit = new HashMap<>();

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select PDT_ID,PDT_NOM,PDT_MARQUE,PDT_CATEGORIE from produit");
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("PDT_ID");
				Integer marque = resultSet.getInt("PDT_MARQUE");
				Integer categorie = resultSet.getInt("PDT_CATEGORIE");
				String nom = resultSet.getString("PDT_NOM");
				listeProduit.put(nom + marque + categorie, id);
			}
			return listeProduit;
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le resultSet", e);
					throw new TechnicalException("impossible de fermer le resultSet", e);
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}
}
