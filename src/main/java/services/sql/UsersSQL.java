/**
 * 
 */
package services.sql;

import java.sql.ResultSet;

import services.conector.Conector;

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
}
