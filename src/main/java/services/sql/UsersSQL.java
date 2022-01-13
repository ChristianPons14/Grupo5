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
 * @author Grupo 5
 *
 */
public class UsersSQL {
	/*
	 * private String userName; private String userSurName; private String password;
	 * private boolean isDirective;
	 */

	public static boolean checkLogin(Connection con, String userName, String password) {
		/*
		 * almacenador = c.crearSQL("SELECT * FROM USERS WHERE user_name like '" +
		 * userName + "' and user_password like '" + password + "';");
		 */
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

	public static boolean createUser(Connection con, String userName, String userSurnames, String password,
			boolean isDirective) {
		// la id ya es autoincremental de base, no hace falta obtener la id
		/*
		 * int lastId = 0; LocalDate todaysDate = LocalDate.now();
		 */
		// almacenador = c.crearSQL("SELECT * FROM Users WHERE id=(SELECT max(id) FROM
		// Users)");
		String sql = "INSERT INTO users VALUES (?,?,?,?,?)";
		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			prepStmt.setString(2, userSurnames);
			prepStmt.setString(3, password);
			prepStmt.setBoolean(4, isDirective);
			try {
				// No estoy seguro de si esto est� bien
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
