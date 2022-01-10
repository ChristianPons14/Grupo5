/**
 * 
 */
package services.sql;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import services.conector.Conector;
import views.Vlogin;

/**
 * @author ezequ
 *
 */
public class UsersSQL {

	Conector c = new Conector();
	ResultSet almacenador;
	private String userName;
	private String password;

	public UsersSQL(Vlogin window) {

		userName = window.getUserName().getText();
		password = window.getPassword().getText();
		System.out.println(checkLogin());
		consultAllUsers();

	}

	private boolean checkLogin() {
		almacenador = c.crearSQL(
				"SELECT * FROM USERS WHERE user_name like '" + userName + "' and user_password like '" + password + "';");

		try {
			if (almacenador.next()) {
				System.out.println("El usuario y password son correctos");
				return true;
			} else {
				System.out.println("El usuario y password son incorrectos");
				return false;
			}
		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }

		}
		return false;
	}
	
	private ArrayList<Users> consultAllUsers() {
		ArrayList<Users> arrayUsers = new ArrayList<Users>();
		almacenador = c.crearSQL("SELECT ID, USER_NAME, USER_SURNAMES, DIRECTIVE, SIGN_IN FROM USERS");
		try {
			while (almacenador.next()) {
				Users usuarios = new Users(almacenador.getInt(1),almacenador.getString(2),almacenador.getString(3),almacenador.getInt(4),almacenador.getDate(5));
				arrayUsers.add(usuarios);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayUsers;
		}
}
