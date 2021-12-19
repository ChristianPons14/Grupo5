package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import models.MenuVariables;
import views.Vmenu;

/**
 * 
 * @author Grupo 5
 * 
 * Llama, controla y muestra la ventana del menu
 *
 */
public class ConMenu implements ActionListener{
	
	private Vmenu window;
	private MenuVariables var;
	
	public static void main(String[] args) {
		new ConMenu();
	}

	// Debe cambiarse en el futuro
	public ConMenu() {
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
		switch(((JButton)e.getSource()).getName()) {
		
		case "0":
			break;
			
		case "1":
			break;
			
		case "2":
			break;
			
		case "3":
			break;
			
		case "4":
			break;
		}
	}
}
