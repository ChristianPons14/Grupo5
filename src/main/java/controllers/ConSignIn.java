package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JComponent;

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
	List<JComponent> fields;

	public static void main(String[] args) {
		new ConSignIn();
	}

	public ConSignIn() {
		prepareWindow();
		window = new VsignIn();
		window.getEnter().addActionListener(this);
		window.setVisible(true);
	}

	/**
	 * Invoca a la ventana, graba todas los campos a rellenar en una lista y la
	 * muestra.
	 */
	private void prepareWindow() {
		window = new VsignIn();
		window.getEnter().addActionListener(this);
		window.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean filledFields = checkFilledFields();
	}

	private boolean checkFilledFields() {
		boolean allFilled = true;

		if (window.getUserName().getText().isBlank()) {
			window.getUserName();
		} else {

		}
		if (window.getUserSurnames().getText().isBlank()) {

		} else {

		}

		if (window.getPassword().getText().isBlank()) {

		} else {

		}

		if (window.getConfPassword().getText().isBlank()) {

		} else {

		}
		return false;
	}
}
