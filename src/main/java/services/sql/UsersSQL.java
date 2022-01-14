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
import java.util.Calendar;
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

	public static User checkLogin(Connection con, String userName, String password) {
		String sql = "SELECT ID, USER_NAME, USER_SURNAMES, DIRECTIVE, SIGN_IN FROM USERS WHERE USER_NAME like ? AND USER_PASSWORD LIKE ?";

		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			prepStmt.setString(2, password);
			ResultSet almacenador = prepStmt.executeQuery();
			User user;
			almacenador.next();
			
			user = new User(almacenador.getInt(1), almacenador.getString(2), almacenador.getString(3),
					almacenador.getBoolean(4));
			
			prepStmt.close();
			almacenador.close();
			
			return user;
			
		} catch (SQLException e) {
			return null;
		}	
	}

	public static boolean createUser(Connection con, String userName, String userSurnames, String password,
			boolean isDirective, String mail, String mailPassword) {
		String sql = "INSERT INTO users (user_name, user_surnames, user_password, directive, mail, mail_password, home_directory, sign_in) VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			prepStmt.setString(2, userSurnames);
			prepStmt.setString(3, password);
			prepStmt.setBoolean(4, isDirective);
			prepStmt.setString(5,mail);
			prepStmt.setString(6, mailPassword);
			prepStmt.setString(7, "pepe");
			prepStmt.setDate(8, new Date(Calendar.getInstance().getTime().getTime()));

			int result = prepStmt.executeUpdate();
			prepStmt.close();
			
			return true;
			
		} catch (SQLException e) {
			System.out.println("Patata");
			e.printStackTrace();
			return false;
		}

	}
	
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
	
	public static List<User> retrieveAllUsers(Connection con) {
		List<User> arrayUsers = new ArrayList<User>();
		try {
			PreparedStatement ps = con
					.prepareStatement("SELECT ID, USER_NAME, USER_SURNAMES, DIRECTIVE, SIGN_IN FROM USERS");
			ResultSet almacenador = ps.executeQuery();

			while (almacenador.next()) {
				User usuarios = new User(almacenador.getInt(1), almacenador.getString(2), almacenador.getString(3),
						almacenador.getBoolean(4));
				arrayUsers.add(usuarios);
			}
			
			ps.close();
			almacenador.close();
		} catch (SQLException e) {

		}
		return arrayUsers;
	}
}
