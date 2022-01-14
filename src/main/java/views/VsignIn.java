package views;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import lombok.Getter;
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
public class VsignIn extends JFrame {

	// ATRIBUTOS
	
	private JTextField userName;
	private JTextField userSurnames;
	private JLabel error;
	private JPasswordField password;
	private JPasswordField confPassword;
	private JTextField userMail;
	private JPasswordField mailPassword;
	private Checkbox directive;
	private JButton enter;
	private int yLocation = 73;

	public VsignIn(SignInVariables var) {
		prepareWindow(var);
	}
	
	// MÉTODOS

	private void prepareWindow(SignInVariables var) {
		// Inicializamos las variables
		userName = new JTextField(17);
		userSurnames = new JTextField(17);
		password = new JPasswordField(17);
		confPassword = new JPasswordField(17);
		userMail = new JTextField(17);
		mailPassword = new JPasswordField(17);
		directive = new Checkbox(var.getDirective());
		enter = new JButton(var.getEnter());
		error = new JLabel();

		// Propiedades de la ventana
		this.setTitle(var.getWindowTitle());
		this.setSize(var.getWindowWidth(), var.getWindowHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		// Contenedor principal
		JPanel window = new JPanel();
		getContentPane().add(window);
		window.setLayout(null);

		// Secciones

		// Encabezado
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, var.getWindowWidth(), 50);
		window.add(panel);
		panel.setBorder(new EmptyBorder(10,20,0,0));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		{
			JLabel label = new JLabel(var.getText());
			panel.add(label);
			panel.add(error);
			error.setForeground(Color.red);
		}

		// Secciones con un campo a rellenar
		createFieldSections(window, var.getWindowWidth(), var.getLabelTexts());

		// Sección para indicar si es un directivo
		createDirectiveSection(window, var.getWindowWidth());

		// Sección para introducir los datos
		createButtonSection(window, var.getWindowWidth());

	}

	/**
	 * Crea los paneles que contienen los campos a rellenar y los añade a la
	 * ventana.
	 * 
	 * @param window      contenedor principal que contiene al resto de paneles.
	 * @param windowWidth Anchira de la ventana.
	 * @param labelTexts  Textos de las etiquetas que indican que se debe introducir
	 *                    en los campos
	 */
	private void createFieldSections(JPanel window, short windowWidth, List<String> labelTexts) {
		List<JComponent> fields = new ArrayList<>();
		fields.add(userName);
		fields.add(userSurnames);
		fields.add(password);
		fields.add(confPassword);
		fields.add(userMail);
		fields.add(mailPassword);

		for (int i = 0; i < fields.size(); i++) {
			JPanel panel = getPanel(windowWidth);
			panel.add(new JLabel(labelTexts.get(i)));
			panel.add(fields.get(i));
			window.add(panel);
		}

	}

	/**
	 * Crea en panel que contiene la sección para indicar si se trata de un
	 * directivo o no
	 * 
	 * @param window      Contenedor que contiene al resto de paneles.
	 * @param windowWidth Anchura de la ventana.
	 */
	private void createDirectiveSection(JPanel window, short windowWidth) {
		JPanel panel = getPanel(windowWidth);
		panel.add(directive);
		panel.setSize(new Dimension(windowWidth/2, 35));
		window.add(panel);
	}

	/**
	 * Crea en panel que contiene la sección con el botón para introducir los datos
	 * 
	 * @param window      Contenedor que contiene al resto de paneles.
	 * @param windowWidth Anchura de la ventana.
	 */
	private void createButtonSection(JPanel window, short windowWidth) {
		JPanel panel = getPanel(windowWidth);
		panel.add(enter);
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
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setSize(new Dimension(windowWidith - (windowWidith / 2) / 2, 35));
		panel.setLocation(0, yLocation);
		yLocation += 40;
		return panel;
	}
}
