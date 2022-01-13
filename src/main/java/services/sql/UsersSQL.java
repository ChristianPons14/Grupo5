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
 * Contiene las consultas con los usuarios de la aplicacion
 * @author Grupo 5
 * 
 */
public class UsersSQL {

	/**
	 * Hace login en la base de datos, si es correcto, devuelve true si hace login correctamente, false si no
	 * @param con - Connection - Conexion MySQL usada
	 * @param userName - String - usuario que inicia sesion
	 * @param password - String - contraseña que se usa para iniciar
	 * @return true si el login es correcto, o false si no es correcto o hay error
	 */
	public static boolean checkLogin(Connection con, String userName, String password) {
		String sql = "SELECT * FROM users WHERE user_name like ? and user_password like ?";

		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			prepStmt.setString(2, password);
			ResultSet almacenador = prepStmt.executeQuery();

			boolean check = almacenador.next();
			
			prepStmt.close();
			almacenador.close();
			
			return check;
			
		} catch (SQLException e) {
			
		}
		
		return false;
	}

	/**
	 * Crea un usuario, y devuelve un boolean en funcion de si el usuario se ha creado con exito
	 * @param con - Connection - Conexion MySQL usada
	 * @param userName - String - nombre de usuario creado
	 * @param userSurnames - String - apellido del usuario creado
	 * @param password - String - contraseña del usuario creado
	 * @param isDirective - boolean - si es true, el usuario sera directivo y tendra permisos especiales
	 * @return true si crea con exito, false si no puede crear bien
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
	 * Devuelve la lista de todos los usuarios de la base de datos
	 * @param Connection con - Conexion usada para MySQL
	 * @return Lista con TODOS los usuarios de la base de datos
	 */
	public static List<User> retrieveAllUsers(Connection con) {
		List<User> arrayUsers = new ArrayList<User>();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT ID, USER_NAME, USER_SURNAMES, DIRECTIVE, SIGN_IN FROM USERS");
			ResultSet almacenador = ps.executeQuery();

			while (almacenador.next()) {
				User usuarios = new User(almacenador.getInt(1), almacenador.getString(2), almacenador.getString(3),
						almacenador.getInt(4), almacenador.getDate(5));
				arrayUsers.add(usuarios);
			}
			
			ps.close();
			almacenador.close();
		} catch (SQLException e) {

		}
		return arrayUsers;
	}
}
