package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import models.MenuVariables;
import services.sql.User;
import views.Vmenu;

/**
 * 
 * @author Grupo 5
 * 
 *         Llama, controla y muestra la ventana del menu
 *
 */
public class ConMenu implements ActionListener {

	private Vmenu window;
	private MenuVariables var;
	private User user;

	public static void main(String[] args) {
		new ConMenu(new User(1,"Pepe", "Villuelas",true));
	}

	// Debe cambiarse en el futuro
	public ConMenu(User user) {
		this.user = user;
		var = new MenuVariables();
		window = new Vmenu(var, true);
		window.getButtons().forEach(button -> button.addActionListener(this));
		window.setVisible(true);
	}

	// Pendiente de hacer

	/**
	 * Realiza la función del boton pulsado.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (((JButton) e.getSource()).getName()) {

		// Añadir archivos
		case "0":
			break;
		// Descargar archivos
		case "1":
			break;
		// Cambiar nombre de archivo
		case "2":
			break;
			
		// Eliminar archivo
		case "3":
			break;
		
		// Ventana con los mensajes
		case "4":
			
			break;
		
		// Ventana para añadir ususario
		case "5":
			new ConSignIn();
			break;
		}
	}
}
