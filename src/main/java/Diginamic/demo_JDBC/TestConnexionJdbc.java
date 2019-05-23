package Diginamic.demo_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Diginamic.demo_JDBC.exception.TechnicalException;

/**
 * classe permettant de se connecter à la base de données
 * 
 * @author Kevin.s
 *
 */
public class TestConnexionJdbc {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(TestConnexionJdbc.class);

	public static void main(String[] args) {

		Connection conn = null;
		ResourceBundle bddConf = ResourceBundle.getBundle("connection");

		try {
			String driverName = bddConf.getString("database.driver");
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			SERVICE_LOG.error("impossible de charger le driver", e1);
			throw new TechnicalException("impossible de charger le driver", e1);
		}

		try {
			TestCRUD testCRUD = new TestCRUD();
			String bddUrl = bddConf.getString("database.url");
			String bddUser = bddConf.getString("database.user");
			String bddPassword = bddConf.getString("database.password");

			conn = DriverManager.getConnection(bddUrl, bddUser, bddPassword);
			testCRUD.initialisationArticleBdd(conn);
			testCRUD.augmentationTarif(conn);
			testCRUD.afficherArticle(conn);
			testCRUD.afficherMoyArticle(conn);
			testCRUD.resetBdd(conn);

		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de dialogue avec la base", e);
			throw new TechnicalException("probleme de dialogue avec la base", e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer la connection avec la base", e);
					throw new TechnicalException("impossible de fermer la connection avec la base", e);
				}
			}
		}

	}

}
