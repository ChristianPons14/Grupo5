package services.conector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Conector {

	Properties prop = new Properties();
	Connection conexion;
	Statement sentencia;
	ResultSet rs;

	public Conector() {
		try {
			// Loads all the properties of file "config.properties".
			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * try { Class.forName("com.mysql.jdbc.Driver"); conexion =
		 * DriverManager.getConnection("jdbc:mysql://localhost/columbia_1", "root", "");
		 * crearState(); } catch (Exception e) { e.printStackTrace(); }
		 */
	}

	public Connection getMySQLConnection() {
		try {
			// Indicates which driver is going to be used.
			Class.forName(prop.getProperty(MySQLConstants.DRIVER));

			try {
				// Creates the connection based on the obtained URL.
				conexion = DriverManager.getConnection(getURL());
				return conexion;

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getURL() {
		return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
				.append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
				.append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
				.append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
				.append(prop.getProperty(MySQLConstants.USER)).append("&password=")
				.append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
				.append(prop.getProperty(MySQLConstants.URL_SSL)).toString();

	}
}

// ---
// jdbc:mysql://localhost:3305/world?user=cPons&password=contraseña&userSSL=false

/*
 * Esto no deber�a estar aqu�. Puedes crear un statment sin necesidad de darle
 * nada public void crearState() { try { sentencia =
 * conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
 * ResultSet.CONCUR_READ_ONLY); } catch (Exception e) { e.printStackTrace(); } }
 * 
 * public ResultSet crearSQL(String sql) { try { rs =
 * sentencia.executeQuery(sql); } catch (Exception e) { e.printStackTrace(); }
 * return rs;
 * 
 * }
 * 
 * public boolean insertarEliminarBD(String sql) { try {
 * sentencia.executeUpdate(sql); return true; } catch (Exception e) {
 * e.printStackTrace(); } return false; }
 * 
 * public void cerrarSQL() { try { rs.close(); sentencia.close();
 * conexion.close();
 * 
 * } catch (Exception e) { e.printStackTrace(); }
 * 
 * } }
 */