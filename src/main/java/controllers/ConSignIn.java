package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.border.LineBorder;

import models.SignInVariables;
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
	 * Comprueba que los campos est�n rellenos, que la contrase�a sea correcta y lo
	 * graba en la base de datos.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		boolean filledFields = checkFilledFields();

		if (filledFields) {
			boolean okPassword = checkPassword();
			UsersSQL u = new UsersSQL();
			

			if (okPassword) {
				// Pendiente de los managers
			}

		} else {
			window.getError().setText(var.getErrorEmpty());
		}
	}

	/**
	 * Comprueba si las 2 contrase�as introducidas son iguales.
	 * 
	 * @return
	 *         <ul>
	 *         <li>Verdadero si son iguales</li>
	 *         <li>Falso si no son iguales</li>
	 *         </ul>
	 */
	private boolean checkPassword() {
		if (!window.getPassword().getText().equals(window.getConfPassword().getText())) {
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
	 *         <li>Verdadero si todos los campos est�n rellenos</li>
	 *         <li>Falso si alg�n campo est� vac�o</li>
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
		return allFilled;
	}
}
