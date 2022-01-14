package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import models.SignInVariables;
import services.conector.Conector;
import services.sql.UsersSQL;
import views.VsignIn;

/**
 * 
 * @author Grupo 5
 * 
 *         Muestra la ventana de registro y se asegura de que los datos
 *         introducidos sean correctos
 *
 */
public class ConSignIn implements ActionListener {

	// ATRIBUTOS
	VsignIn window;
	SignInVariables var;
	List<JComponent> fields;

	public static void main(String[] args) {
		new ConSignIn();
	}

	public ConSignIn() {
		var = new SignInVariables();
		window = new VsignIn(var);
		window.getEnter().addActionListener(this);
		window.setVisible(true);
	}

	/**
	 * Comprueba que los campos estï¿½n rellenos, que la contraseï¿½a sea correcta
	 * y, si todo lo anterior se cumple, lo graba en la base de datos.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean filledFields = checkFilledFields();

		if (filledFields) {
			boolean okPassword = checkPassword();

			if (okPassword) {
				try {
					Connection con = new Conector().getMySQLConnection();
					boolean userCreated = UsersSQL.createUser(con, window.getUserName().getText(),
							window.getUserSurnames().getText(), new String(window.getPassword().getPassword()),
							window.getDirective().getState(), window.getUserMail().getText(),
							new String(window.getMailPassword().getPassword()));

					if (userCreated) {
						JOptionPane.showMessageDialog(window, "Se han añadido los datos del usuario corréctamente",
								"Insertado realizado", JOptionPane.INFORMATION_MESSAGE);
						window.dispose();

					} else {
						JOptionPane.showMessageDialog(window, "Ya existe un usuario con los datos introducidos",
								"El usuario ya existe", JOptionPane.ERROR_MESSAGE);
						window.getUserName().setText("");
						window.getUserSurnames().setText("");
						window.getPassword().setText("");
						window.getConfPassword().setText("");
						window.getDirective().setState(false);
					}
					con.close();
				} catch (Exception e2) {
				}

			}

		} else {
			window.getError().setText(var.getErrorEmpty());
		}
	}

	/**
	 * Comprueba si las 2 contraseñas introducidas son iguales.
	 * 
	 * @return
	 *         <ul>
	 *         <li>Verdadero si son iguales</li>
	 *         <li>Falso si no son iguales</li>
	 *         </ul>
	 */
	private boolean checkPassword() {

		if (!new String(window.getPassword().getPassword())
				.equals(new String(window.getConfPassword().getPassword()))) {
			System.out.println(window.getPassword().getPassword().toString());
			System.out.println(window.getConfPassword().getPassword().toString());
			window.getError().setText(var.getErrorPassword());
			window.getPassword().setBorder(new LineBorder(Color.red));
			window.getPassword().setText("");
			window.getConfPassword().setBorder(new LineBorder(Color.red));
			window.getConfPassword().setText("");

			return false;

		} else {
			return true;
		}
	}

	/**
	 * Comprueba que todos los campos hayan sido rellenados y marca los que no.
	 * 
	 * @return
	 *         <ul>
	 *         <li>Verdadero si todos los campos estï¿½n rellenos</li>
	 *         <li>Falso si algï¿½n campo estï¿½ vacï¿½o</li>
	 *         </ul>
	 */
	private boolean checkFilledFields() {
		boolean allFilled = true;

		if (window.getUserName().getText().isBlank()) {
			window.getUserName().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getUserName().setBorder(new LineBorder(null));
		}

		if (window.getUserSurnames().getText().isBlank()) {
			window.getUserSurnames().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getUserSurnames().setBorder(new LineBorder(null));
		}

		if (window.getPassword().getPassword().length <= 0) {
			window.getPassword().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getPassword().setBorder(new LineBorder(null));
		}

		if (window.getConfPassword().getPassword().length <= 0) {
			window.getConfPassword().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getConfPassword().setBorder(new LineBorder(null));
		}

		if (window.getUserMail().getText().isBlank()) {
			window.getUserMail().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getUserMail().setBorder(new LineBorder(null));
		}

		if (window.getMailPassword().getPassword().length <= 0) {
			window.getMailPassword().setBorder(new LineBorder(Color.red));
			allFilled = false;

		} else {
			window.getMailPassword().setBorder(new LineBorder(null));
		}
		return allFilled;
	}
}
