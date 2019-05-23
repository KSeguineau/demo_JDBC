package Diginamic.openFoodFactTp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Diginamic.demo_JDBC.exception.TechnicalException;
import Diginamic.openFoodFactTp.utils.ConnectionUtils;

/**
 * Dao gérant les catégories
 * 
 * @author Kevin.s
 *
 */
public class CategorieDao {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(CategorieDao.class);

	/**
	 * Constructeur
	 * 
	 */
	public CategorieDao() {
		super();
	}

	/**
	 * ajoute toute les catégories à la base
	 * 
	 * @param listeCategorie
	 *            hashSet des catégories
	 */
	public void ajouterAllCategorie(HashSet<String> listeCategorie) {

		PreparedStatement insertCategorie = null;
		try {
			insertCategorie = ConnectionUtils.getInstance().prepareStatement(
					"insert into categorie (CTG_NOM) values (?)");
			for (String categorie : listeCategorie) {
				insertCategorie.setString(1, categorie);
				insertCategorie.executeUpdate();
			}
			ConnectionUtils.doCommit();
		} catch (SQLException e) {
			ConnectionUtils.doRollback();
			throw new TechnicalException("probleme d'insertion en base de données", e);
		} finally {
			if (insertCategorie != null) {
				try {
					insertCategorie.close();
				} catch (SQLException e) {
					throw new TechnicalException("impossible de fermer le preparedStatement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}

	/**
	 * récupère l'id d'une catégorie par rapport à son nom
	 * 
	 * @param nom
	 *            nom de la catégorie
	 * @return l'identifiant de la catégorie
	 */
	public Integer recupererIdCategorie(String nom) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Integer id = null;

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select CTG_ID from categorie where CTG_NOM=?");
			preparedStatement.setString(1, nom);
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			if (resultSet.next()) {
				id = resultSet.getInt("CTG_ID");
			}
			return id;
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

	/**
	 * recupere l'ensemble de la table catégorie et créér une hashmap avec le
	 * nom en clé et l'identifiant en valeur
	 * 
	 * @return HashMap<String,Integer>
	 */
	public HashMap<String, Integer> recupererAllId() {

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		HashMap<String, Integer> listeCategorie = new HashMap<>();

		try {
			preparedStatement = ConnectionUtils.getInstance().prepareStatement(
					"select * from categorie");
			resultSet = preparedStatement.executeQuery();
			ConnectionUtils.doCommit();
			while (resultSet.next()) {
				Integer id = resultSet.getInt("CTG_ID");
				String nom = resultSet.getString("CTG_NOM");
				listeCategorie.put(nom, id);
			}
			return listeCategorie;
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
