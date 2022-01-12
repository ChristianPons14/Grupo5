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

/**
 * @author Grupo 5
 *
 */
public class UsersSQL {
	/*
	 * private String userName; private String userSurName; private String password;
	 * private boolean isDirective;
	 */

	public UsersSQL() {

		/*
		 * esto no estar�a mal si solo tuvi�ramos la ventana de registrarse as� que lo
		 * pondr� para que lo reciba el m�todo. userName =
		 * window.getUserName().getText(); userSurName =
		 * window.getUserSurnames().getText(); password =
		 * window.getPassword().getPassword().toString(); /*
		 * 
		 * /* Esto se deber�a llamar por m�todo, no al llamar la clase en s�
		 * checkExistUser(); if (checkExistUser() == false) { createUser(); }
		 */

	}

	public static boolean checkExistUser(Connection con, String userName, String password) {
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

			if (almacenador.first()) {
				return true;

			} else {
				return false;
			}

		} catch (SQLException e) {
			return false;
		}
	}

	public static short createUser(Connection con, String userName, String userSurnames, String password,
			boolean isDirective) {
		// la id ya es autoincremental de base, no hace falta obtener la id
		/*
		 * int lastId = 0; LocalDate todaysDate = LocalDate.now();
		 */
		// almacenador = c.crearSQL("SELECT * FROM Users WHERE id=(SELECT max(id) FROM
		// Users)");
		String sql = "INSERT INTO users VALUES (?,?,?,?,?)";

		if (!checkExistUser(con, userName, password)) {
			try {
				PreparedStatement prepStmt = con.prepareStatement(sql);
				prepStmt.setString(1, userName);
				prepStmt.setString(2, userSurnames);
				prepStmt.setString(3, password);
				prepStmt.setBoolean(4, isDirective);
				try {
					// No estoy seguro de si esto est� bien
					prepStmt.setDate(5,
							(Date) new SimpleDateFormat("dd/MM/yyyy").parse(new java.util.Date().toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}

				prepStmt.executeUpdate();
				return 1;
				/*
				 * if (almacenador.next()) { //lastId = almacenador.getInt(1) + 1;
				 * 
				 * }
				 */
			} catch (SQLException e) {
				e.printStackTrace();
				return 2;
			}

		} else {
			return 3;
		}
	}
}
