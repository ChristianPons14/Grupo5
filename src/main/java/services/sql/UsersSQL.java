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

import services.conector.Conector;
import views.VsignIn;

/**
 * @author ezequ
 *
 */
public class UsersSQL {
	Conector c = new Conector();
	ResultSet almacenador;
	/*
	private String userName;
	private String userSurName;
	private String password;
	private boolean isDirective;
	*/

	public UsersSQL(VsignIn window) {

		/* esto no estaría mal si solo tuviéramos la ventana de registrarse así que lo pondré para que lo reciba el método.
		userName = window.getUserName().getText();
		userSurName = window.getUserSurnames().getText();
		password = window.getPassword().getPassword().toString();
		/*
		
		/* Esto se debería llamar por método, no al llamar la clase en sí
		checkExistUser();
		if (checkExistUser() == false) {
			createUser();
		}*/

	}

	private boolean checkExistUser(Connection con, String userName, String password) {
		/*almacenador = c.crearSQL("SELECT * FROM USERS WHERE user_name like '" + userName + "' and user_password like '"
				+ password + "';");*/
		String sql = "SELECT * FROM users WHERE user_name like ? and user_password like ?";
		
		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, userName);
			prepStmt.setString(2, password);
			almacenador = prepStmt.executeQuery();
			
			if(almacenador.first()) {
				return true;
				
			}else {
				return false;
			}		
			
		} catch (SQLException e) {
			return false;
		}		
	}
	
	private short createUser(Connection con, String userName, String userSurnames, String password, boolean isDirective) {
		//la id ya es autoincremental de base, no hace falta obtener la id
		/*
		int lastId = 0;
		LocalDate todaysDate = LocalDate.now();
		*/
		//almacenador = c.crearSQL("SELECT * FROM Users WHERE id=(SELECT max(id) FROM Users)");
		String sql = "INSERT INTO users VALUES (?,?,?,?,?)";
		
		if(!checkExistUser(con, userName, password)) {
			try {
				PreparedStatement prepStmt = con.prepareStatement(sql);
				prepStmt.setString(1, userName);
				prepStmt.setString(2, userSurnames);
				prepStmt.setString(3, password);
				prepStmt.setBoolean(4, isDirective);
				try {
					// No estoy seguro de si esto está bien
					prepStmt.setDate(5, (Date) new SimpleDateFormat("dd/MM/yyyy").parse(new java.util.Date().toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				prepStmt.executeUpdate();
				return 1;
				/*
				if (almacenador.next()) {
					//lastId = almacenador.getInt(1) + 1;
					
				}*/
			} catch (SQLException e) {
				e.printStackTrace();
				return 2;	
			}
			
		}else {
			return 3;
		}
	}
}
