package services.conector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Conector {

	Properties prop = new Properties();
	
	public Conector() {
		try {
			//Loads all the properties of file "config.properties".
			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return Conexion con MySQL a partir del data source
	 */
	public Connection getMySQLConnection() {
		try {
			return getDataSource().getConnection();
		} catch (SQLException e) {
			
		}
		return null;
	}
	
	/**
	 * @return data source a partir de la url
	 */
	public DataSource getDataSource() {
		MysqlDataSource source = new MysqlDataSource();
		source.setUrl(getURL());
		return source;
	}
	
	/**
	 * @return URL del archivo properties
	 */
	private String getURL() {
		return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
		.append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
		.append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
		.append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
		.append(prop.getProperty(MySQLConstants.USER)).append("&password=")
		.append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
		.append(prop.getProperty(MySQLConstants.URL_SSL)).toString();
		
		
		// --- jdbc:mysql://localhost:3305/world?user=cPons&password=contraseña&userSSL=false
		
		}
}