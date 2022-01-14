package views;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controllers.EventReadMessages;
import lombok.Getter;
import models.GmailVariables;
import models.SignInVariables;
import javax.swing.BoxLayout;

/**
 * 
 * @author Grupo 5
 * 
 *         Contiene los métodos para crear la venana de registro
 *
 */
@Getter
public class VreadMessages extends JFrame {

	// ATRIBUTOS
	private int yLocation = 73;
	GmailVariables var;
	private ArrayList<JButton> buttons;
	
	public VreadMessages(GmailVariables var) {
		buttons = new ArrayList<>();
		this.var = var;
		prepareWindow();
	}

	// MÉTODOS

	private void prepareWindow() {


		// Propiedades de la ventana
		this.setTitle(var.getWindowTitle());
		this.setSize(var.getWindowWidth(), var.getWindowHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		// Contenedor principal
		JPanel window = new JPanel();
		getContentPane().add(window);
		window.setLayout(null);
		
		// Sección para crear los botones
		createButtonSection(window, var.getWindowWidth());

	}

	/**
	 * Crea en panel que contiene la sección con el botón para introducir los datos
	 * 
	 * @param window      Contenedor que contiene al resto de paneles.
	 * @param windowWidth Anchura de la ventana.
	 */
	private void createButtonSection(JPanel window, short windowWidth) {
		JPanel panel = getPanel(windowWidth);
		for(int i=0;i<var.getMessageCount();i++) {
			JButton button = new JButton(String.valueOf(i));
			button.addActionListener(new EventReadMessages(this));
			buttons.add(button);
			panel.add(button);
		}
		JButton  button= new JButton(var.getSend());
		panel.add(button);
		window.add(panel);

	}

	/**
	 * Crea un panel genérico para las secciones
	 * 
	 * @param windowWidith Anchura de la ventana.
	 * @return panel Genérico.
	 */
	private JPanel getPanel(int windowWidith) {
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setSize(new Dimension(windowWidith -100,var.getWindowHeight()-100));
		panel.setLocation(0, yLocation);
		yLocation += 40;
		return panel;
	}

}
