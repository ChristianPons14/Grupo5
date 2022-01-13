package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import com.google.protobuf.Extension.MessageType;

import models.LoginVariables;
import services.conector.Conector;
import services.sql.User;
import services.sql.UsersSQL;
import views.Vlogin;

/**
 * 
 * @author Grupo 5
 * 
 *         Llama a la ventana Vlogin, la muestra y permite iniciar sesión en el
 *         programa si los datos son correctos.
 *
 */
public class ConLogin implements ActionListener {

	private Vlogin window;
	private LoginVariables var;

	public static void main(String[] args) {
		new ConLogin();
	}

	public ConLogin() {
		var = new LoginVariables();
		prepareLoginWindow();
	}

	private void prepareLoginWindow() {
		window = new Vlogin(var);
		window.getEnter().addActionListener(this);
		window.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean filledFields = checkFields();

		if (filledFields) {
			
			try {
				Connection con = new Conector().getMySQLConnection();
				User user = UsersSQL.checkLogin(con, window.getUserName().getText(),
						window.getPassword().getPassword().toString());

				if (user == null) {
					JOptionPane.showMessageDialog(window,
							"No se ha encontrado un usuario con el nombre y la contraseña introducidos",
							"El usuario introducido no existe", JOptionPane.ERROR_MESSAGE);
					window.getUserName().setText("");
					window.getPassword().setText("");
					checkFields();
					
				}else {
					new ConMenu();
					window.dispose();
				}
				con.close();

			} catch (Exception ex) {	
			}
		}
	}

	/**
	 * Comprueba que los datos campos hayan sido rellenados
	 * 
	 * @return
	 */
	private boolean checkFields() {
		boolean allFilled = true;

		if (window.getUserName().getText().isBlank()) {
			window.getUserName().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getUserName().setBorder(new LineBorder(null));
		}

		if (window.getPassword().getPassword().length <= 0) {
			window.getPassword().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getUserName().setBorder(new LineBorder(null));
		}
		return allFilled;
	}

	// Pendiente de desarrollar.
	/*
	 * private boolean checkDb() {
	 * 
	 * return null; }
	 */
}
