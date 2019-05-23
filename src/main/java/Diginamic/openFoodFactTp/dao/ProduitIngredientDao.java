package Diginamic.openFoodFactTp.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Diginamic.demo_JDBC.exception.TechnicalException;
import Diginamic.openFoodFactTp.model.CoupleProduitIngredient;
import Diginamic.openFoodFactTp.utils.ConnectionUtils;

/**
 * dao gérant la table de jointure entre
 * 
 * @author Kevin.s
 *
 */
public class ProduitIngredientDao {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(ProduitIngredientDao.class);

	/**
	 * Constructeur
	 * 
	 */
	public ProduitIngredientDao() {
		super();
	}

	/**
	 * ajoute toutes les couples id_produit/id_ingrédient dans la table de
	 * jointure correspondante
	 * 
	 * @param listeCouple
	 */
	public void ajouterAllCoupleProduitIngredient(List<CoupleProduitIngredient> listeCouple) {

		PreparedStatement insertCouple = null;
		try {
			insertCouple = ConnectionUtils.getInstance().prepareStatement(
					"insert into produit_ingredient (PI_IDPRODUIT,PI_IDINGREDIENT) values (?,?)");
			for (CoupleProduitIngredient couple : listeCouple) {
				insertCouple.setInt(1, couple.getProduit());
				insertCouple.setInt(2, couple.getIngredient());
				insertCouple.executeUpdate();
			}
			ConnectionUtils.doCommit();

		} catch (SQLException e) {
			ConnectionUtils.doRollback();
			throw new TechnicalException("probleme d'insertion en base de données", e);
		} finally {
			if (insertCouple != null) {
				try {
					insertCouple.close();
				} catch (SQLException e) {
					throw new TechnicalException("impossible de fermer le preparedStatement", e);
				}
			}
			ConnectionUtils.doClose();
		}

	}

}
