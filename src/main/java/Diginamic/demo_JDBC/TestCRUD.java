package Diginamic.demo_JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Diginamic.demo_JDBC.exception.TechnicalException;

/**
 * classe permettant de tester différente requetes vers la base de données
 * 
 * @author Kevin.s
 *
 */
public class TestCRUD {

	/** SERVICE_LOG : Logger */
	private static final Logger SERVICE_LOG = LoggerFactory.getLogger(TestCRUD.class);

	/**
	 * Constructeur
	 * 
	 */
	public TestCRUD() {
		super();
	}

	/**
	 * initialise la base avec 4 articles
	 * 
	 * @param conn
	 *            connection à la base
	 */
	public void initialisationArticleBdd(Connection conn) {
		Statement myStatement = null;
		try {
			myStatement = conn.createStatement();
			myStatement.executeUpdate(
					"insert into article (ART_DESIGNATION,ART_FOURNISSEUR,ART_PRIX) values ('biscuit','lu',10.00);");
			myStatement.executeUpdate(
					"insert into article (ART_DESIGNATION,ART_FOURNISSEUR,ART_PRIX) values ('legume','monJardin',0.50);");
			myStatement.executeUpdate(
					"insert into article (ART_DESIGNATION,ART_FOURNISSEUR,ART_PRIX) values ('fruit','monPommier',25.00);");
			myStatement.executeUpdate(
					"insert into article (ART_DESIGNATION,ART_FOURNISSEUR,ART_PRIX) values ('viand','chezRobert',15.00);");

		} catch (SQLException e) {
			SERVICE_LOG.error("probleme d'insertion en base", e);
			throw new TechnicalException("probleme d'insertion en base", e);
		} finally {
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
		}
	}

	/**
	 * augmente de 25% le prix des articles de plus de 10€
	 * 
	 * @param conn
	 *            connection à la base
	 */
	public void augmentationTarif(Connection conn) {
		Statement myStatement = null;
		try {
			myStatement = conn.createStatement();
			myStatement.executeUpdate("update article set ART_PRIX=ART_PRIX*1.25 where ART_PRIX>10;");
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme d'update en base", e);
			throw new TechnicalException("probleme d'update en base", e);
		} finally {
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
		}

	}

	/**
	 * affiche les articles en base de données
	 * 
	 * @param conn
	 *            connection à la base
	 */
	public void afficherArticle(Connection conn) {
		Statement myStatement = null;
		StringBuilder sBuild = new StringBuilder();
		ResultSet resultSet = null;
		try {
			myStatement = conn.createStatement();
			resultSet = myStatement.executeQuery("select * from article;");
			while (resultSet.next()) {
				sBuild.append("Id: ").append(resultSet.getInt("ART_ID"));
				sBuild.append(", Designation: ").append(resultSet.getString("ART_DESIGNATION"));
				sBuild.append(", Fournisseur: ").append(resultSet.getString("ART_FOURNISSEUR"));
				sBuild.append(", Prix: ").append(resultSet.getDouble("ART_Prix"));
				sBuild.append("\n");
			}
			System.out.println(sBuild);
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);

		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("probleme de selection en base", e);
					throw new TechnicalException("probleme de selection en base", e);
				}
			}
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
		}
	}

	/**
	 * affiche la moyenne des prix des articles en base de données
	 * 
	 * @param conn
	 *            connection à la base
	 */
	public void afficherMoyArticle(Connection conn) {
		Statement myStatement = null;
		ResultSet resultSet = null;

		try {
			myStatement = conn.createStatement();
			resultSet = myStatement.executeQuery("select avg(ART_PRIX) as MOYENNE_PRIX from article;");
			if (resultSet.next()) {
				System.out.println("moyenne des prix" + resultSet.getDouble("MOYENNE_PRIX"));
			}

		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de selection en base", e);
			throw new TechnicalException("probleme de selection en base", e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le resultset", e);
					throw new TechnicalException("impossible de fermer le resultset", e);
				}
			}
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
		}
	}

	/**
	 * execute un truncate sur la table article de la base
	 * 
	 * @param conn
	 *            connection à la base
	 */
	public void resetBdd(Connection conn) {
		Statement myStatement = null;

		try {
			myStatement = conn.createStatement();
			myStatement.executeUpdate("truncate table article;");
		} catch (SQLException e) {
			SERVICE_LOG.error("probleme de supression en base", e);
			throw new TechnicalException("probleme de supression en base", e);
		} finally {
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e) {
					SERVICE_LOG.error("impossible de fermer le statement", e);
					throw new TechnicalException("impossible de fermer le statement", e);
				}
			}
		}
	}

}
