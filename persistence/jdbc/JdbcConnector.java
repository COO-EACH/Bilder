package persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import persistence.PersistenceException;
import util.Log;

public class JdbcConnector {

	protected enum DB {
		MYSQL, POSTGRES, HSQL;
	}

	private static final String PASSWORD = "123@mudar";
	private static final String USER = "root";
	private static final String HOST = "localhost";
	protected static  String DB_NAME = "coo2018_outro";

	// MySQL
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql";

	// Postgres
	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	private static final String POSTGRES_URL = "jdbc:postgresql";

	// HSQL
	private static final String HSQL_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String HSQL_URL = "jdbc:hsqldb:mem:";

	private String dbDriver;
	private String dbURL;

	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	protected JdbcConnector(DB db) throws PersistenceException {
		switch (db) {
		case HSQL:
			dbDriver = HSQL_DRIVER;
			dbURL = HSQL_URL;
			break;

		case MYSQL:
			dbDriver = MYSQL_DRIVER;
			dbURL = MYSQL_URL;
			break;

		case POSTGRES:
			dbDriver = POSTGRES_DRIVER;
			dbURL = POSTGRES_URL;
			break;

		default:
			break;
		}

		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	public JdbcConnector() throws PersistenceException {
		this(DB.MYSQL);
	}

	protected void openConnection() throws PersistenceException {
		try {
			con = DriverManager.getConnection(dbURL + "://" + getDbHost() + "/"
					+ getDbName(), getUser(), getPassword());
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	protected void closeConnection() throws PersistenceException {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}

			if (con != null) {
				con.close();
				con = null;
			}

		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Problemas no acesso ao banco de dados.");
		}

	}

	protected void prepareSqlCommand(String sql) throws PersistenceException {
		try {
			pstmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			Log.gravaLog(e);
			throw new PersistenceException(
					"Problemas no acesso ao banco de dados.");
		}
	}

	private String getUser() {
		return USER;
	}

	private String getPassword() {
		return PASSWORD;
	}

	private String getDbHost() {
		return HOST;
	}

	private String getDbName() {
		return DB_NAME;
	}

}
