/**
 * 
 */
package services.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import services.conector.Conector;
import views.VsignIn;

/**
 * @author ezequ
 *
 */
public class UsersSQL {
	Conector c = new Conector();
	ResultSet almacenador;
	private String userName;
	private String userSurName;
	private String password;
	private String confPassword;

	public UsersSQL(VsignIn window) {

		userName = window.getUserName().getText();
		userSurName = window.getUserSurnames().getText();
		password = window.getPassword().getText();
		confPassword = window.getConfPassword().getText();
		checkExistUser();
		if (checkExistUser() == false) {
			createUser();
		}

	}

	private boolean checkExistUser() {
		almacenador = c.crearSQL("SELECT * FROM USERS WHERE user_name like '" + userName + "' and user_password like '"
				+ password + "';");

		try {
			if (almacenador.next()) {
				System.out.println("Existe");
				return true;
			} else {
				System.out.println("No existe");
				return false;
			}
		} catch (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }

		}
		return false;

	}
	
	private void createUser() {
		//Sacamos el último id y le añadimos uno, ya que de esta forma lo haremos auto-incremental.
		int lastId = 0;
		LocalDate todaysDate = LocalDate.now();
		almacenador = c.crearSQL("SELECT * FROM Users WHERE id=(SELECT max(id) FROM Users)");
		try {
			if (almacenador.next()) {
				lastId = almacenador.getInt(1) + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		c.insertarEliminarBD("INSERT INTO USERS VALUES ('"+lastId+"','"+userName+"','"+userSurName+"','"+password+"','1','"+todaysDate+"')");
		System.out.println("Se ha insertado correctamente");
	}
}
