package views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import lombok.Getter;
import models.LoginVariables;
import java.awt.FlowLayout;

/**
 * 
 * @author Grupo 5
 * 
 *	Contiene los métodos para crear la ventana para iniciar sesión.
 */
@Getter
public class Vlogin extends JFrame {

	// ATRIBUTOS

	private JLabel error;
	private JTextField userName;
	private JPasswordField password;
	private JButton enter;

	public Vlogin() {
		// Iniciamos las variables.
		userName = new JTextField(17);
		error = new JLabel();
		password = new JPasswordField(17);
		enter = new JButton();

		// Preparamos la ventana y la mostramos
		prepareWindow();
		this.setVisible(true);
	}

	// MÉTODOS

	/**
	 * Introduce las propiedades de la ventana y lla a los métodos que crean las
	 * partes de a misma
	 */
	private void prepareWindow() {
		LoginVariables var = new LoginVariables();

		// Propiedades de la ventana
		this.setTitle(var.getLoginTitle());
		this.setSize(var.getVloginWidth(), var.getVloginHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		JPanel window = new JPanel();
		getContentPane().add(window);
		window.setLayout(null);

		// Secciones de la ventana

		// Encabezado
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 384, 30);
		window.add(panel);
		{
			JLabel label = new JLabel("Introduce los datos para iniciar sesi\u00F3n:");
			panel.add(label);
			panel.add(error);
			error.setForeground(Color.red);
		}
		// Petición nombre
		createNameSection(var.getName(), var.getVloginWidth(), window);

		// Petición Contraseña
		createPasswordSection(var.getPassword(), var.getVloginWidth(), window);

		// Añadir Botón de confirmación
		createButtonSection(var.getEnter(), window);

	}

	/**
	 * Crea el panel que contiene el campo nombre a introducir y lo añade a la
	 * ventana.
	 * 
	 * @param name         Texto que indica que en el espacio siguiente se debe
	 *                     escribir el nombre.
	 * @param windowWidith Anchura de la ventana.
	 * @param window       Panel que contiene al resto en la ventana.
	 */
	private void createNameSection(String name, int windowWidith, JPanel window) {
		JPanel panel = getPanel(windowWidith);
		panel.add(new JLabel(name));
		panel.add(userName);
		panel.setLocation(0, 53);
		window.add(panel);
	}

	/**
	 * Crea el panel que contiene el campo contraseña a introducir y lo añade a la
	 * ventana.
	 * 
	 * @param text         Texto que indica que en el espacio siguiente se debe
	 *                     introducir la contraseña.
	 * @param windowWidith Anchira de la ventana.
	 * @param window       Panel que contiene al resto en la ventana.
	 */
	private void createPasswordSection(String text, int windowWidith, JPanel window) {
		JPanel panel = getPanel(windowWidith);
		panel.add(new JLabel(text));
		panel.add(password);
		panel.setLocation(0, 103);
		window.add(panel);
	}

	/**
	 * Crea el panel que contiene el botón para introducir los datos y que sean
	 * confirmados.
	 * 
	 * @param enterText Texto del botón.
	 * @param window    Panel que contiene al resto en la ventana.
	 */
	private void createButtonSection(String enterText, JPanel window) {
		JPanel panel = new JPanel();
		panel.setBounds(0, 143, 374, 30);
		enter.setText(enterText);
		panel.add(enter);
		window.add(panel);

	}

	/**
	 * Crea un panel genérico para las secciones del nombre y la contraseña de la
	 * ventana.
	 * 
	 * @param windowWidith Anchura de la ventana.
	 * @return panel genérico.
	 */
	private JPanel getPanel(int windowWidith) {
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setSize(new Dimension(windowWidith - (windowWidith / 2) / 2, 35));
		return panel;
	}
}
