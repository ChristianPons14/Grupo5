/**
 * 
 */
package services.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Contiene los métodos de acceso a la tabla users de la base de datos MySQL
 * @author Grupo 5
 */
public class UsersSQL {

	/**
	 * Comprueba el login, y devuelve true si ha iniciado sesion correctamente
	 * 
	 * @param con - Connection - Conexion MySQL
	 * @param userName - String - Nombre del usuario
	 * @param password - String - Contraseñadel usuario a iniciar sesion
	 * @return true si inicia correctamente, false si no inicia, o hay error
	 */
	public static User checkLogin(Connection con, String userName, String password) {
		String sql = "SELECT ID, USER_NAME, USER_SURNAMES, DIRECTIVE, SIGN_IN FROM USERS WHERE USER_NAME like ? AND USER_PASSWORD LIKE ?";

		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			prepStmt.setString(2, password);
			ResultSet almacenador = prepStmt.executeQuery();
			User user;
			if(!almacenador.next()) {
				throw new SQLException();
			}
			user = new User(almacenador.getInt(1), almacenador.getString(2), almacenador.getString(3),
					almacenador.getBoolean(4), almacenador.getDate(5));
			
			prepStmt.close();
			almacenador.close();
			
			return user;
			
		} catch (SQLException e) {
			return null;
		}	
	}

	/**
	 * Crea un usuario, con los parametros especificados
	 * 
	 * @param con - Connection - Conexion con MySQL
	 * @param userName - String - nombre de usuario
	 * @param userSurnames - String - apellidos de l usuario
	 * @param password - String - contraseña del usuario
	 * @param isDirective - boolean - es true si es directivo y tiene permisos especiales
	 * @return true si crea con exito, o false si no tiene exito
	 */
	public static boolean createUser(Connection con, String userName, String userSurnames, String password,
			boolean isDirective) {
		String sql = "INSERT INTO users VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			prepStmt.setString(2, userSurnames);
			prepStmt.setString(3, password);
			prepStmt.setBoolean(4, isDirective);
			
			try {
				prepStmt.setDate(5, (Date) new SimpleDateFormat("dd/MM/yyyy").parse(new java.util.Date().toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			int result = prepStmt.executeUpdate();
			prepStmt.close();
			
			return result > 0;
			
		} catch (SQLException e) {
			return false;
		}

	}
	
	/**
	 * Devuelve la contraseña del usuario especificado
	 * @param con - Connection - Conexion con MySQL
	 * @param userName - String - usuario a buscar
	 * @return String - contraseña del usuario
	 */
	public static String getUserPassword(Connection con, String userName) {
		String password = "";
		
		try {
			String sql = "SELECT USER_PASSWORD FROM USERS WHERE USER_NAME = ?";
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			ResultSet almacenador = prepStmt.executeQuery();
			
			if(almacenador.next()) {
				password = almacenador.getString(1);
			}
		}catch (SQLException e) {

		}
		
		return password;	
	}
	
	/**
	 * Devuelve a todos los usuarios y sus datos, incluyendo la contraseña
	 * 
	 * @param con - Connection - Conexion MySQL
	 * @return lista con todos los usuarios
	 */
	public static List<User> retrieveAllUsersPassword(Connection con) {
		List<User> arrayUsers = new ArrayList<User>();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT ID, USER_NAME, USER_SURNAMES, USER_PASSWORD, DIRECTIVE, SIGN_IN FROM USERS");
			ResultSet almacenador = ps.executeQuery();

			while (almacenador.next()) {
				User usuarios = new User(almacenador.getInt(1), almacenador.getString(2), almacenador.getString(3),
						almacenador.getString(4), almacenador.getBoolean(5), almacenador.getDate(6));
				arrayUsers.add(usuarios);
			}
			
			ps.close();
			almacenador.close();
		} catch (SQLException e) {

		}
		return arrayUsers;
	}
	
	/**
	 * Devuelve todos los datos de todos los usuarios, quitando la contraseña
	 * 
	 * @param con - Connection - Conexion MySQL
	 * @return Lista con todos los usuarios
	 */
	public static List<User> retrieveAllUsers(Connection con) {
		List<User> arrayUsers = new ArrayList<User>();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT ID, USER_NAME, USER_SURNAMES, DIRECTIVE, SIGN_IN FROM USERS");
			ResultSet almacenador = ps.executeQuery();
			
			while (almacenador.next()) {
				User usuarios = new User(almacenador.getInt(1), almacenador.getString(2), almacenador.getString(3),
						almacenador.getBoolean(4), almacenador.getDate(5));
				arrayUsers.add(usuarios);
			}
			
			ps.close();
			almacenador.close();
		} catch (SQLException e) {

		}
		return arrayUsers;
	}
}
