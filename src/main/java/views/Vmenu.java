package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import lombok.Getter;
import models.MenuVariables;

/**
 * 
 * @author Grupo 5
 *
 */
@Getter
public class Vmenu extends JFrame {

	// ATRIBUTOS

	private JTree files;
	private List<JButton> buttons;

	public Vmenu(MenuVariables var, boolean isDirective) {
		prepareWindow(var, isDirective);
	}

	// MÉTODOS

	/**
	 * Crea y prepara la ventana del menú .
	 * 
	 * @param var         Datos para crear la ventana.
	 * @param isDirective Indica si el usuario es un directivo o no.
	 */
	private void prepareWindow(MenuVariables var, boolean isDirective) {

		// Propiedades de las ventanas.
		this.setTitle(var.getTitle());
		this.setSize(var.getWindowWidth(), var.getWindowHeight());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		// Panel principal
		JPanel window = new JPanel();
		window.setLayout(new BorderLayout());
		this.add(window);

		// Secciones.
		createHeaderSection(window, var.getText());
		createFileSection(window);
		createButtonSection(window, var.getButtonTexts(), isDirective);
	}

	/**
	 * Crea el panel con el encabezado de la ventana.
	 * 
	 * @param window     Contenedor que contiene al resto de paneles.
	 * @param headerText Texto del encabezado.
	 */
	private void createHeaderSection(JPanel window, String headerText) {
		JPanel panel = new JPanel();
		panel.add(new JLabel(headerText));
		panel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
		window.add(panel, BorderLayout.NORTH);
	}

	/**
	 * Crea la sección para ver y seleccionar las carpetas y ficheros.
	 * 
	 * @param window Contenedor que contiene al resto de paneles.
	 */
	private void createFileSection(JPanel window) {
		JScrollPane scroll = new JScrollPane();
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(Color.white);
		files = new JTree();
		panel.add(files);
		scroll.setViewportView(panel);
		window.add(scroll, BorderLayout.CENTER);
	}

	/**
	 * Crea la sección con los botones para seleccionar la opción a realizar. Si es
	 * usuario es un directivo tendrá una opción extra que es la de gestionar los
	 * usuarios.
	 * 
	 * @param window      Contenedor que contiene al resto de paneles.
	 * @param buttonTexts Lista con todos los textos de los botones.
	 * @param isDirective Indica si es un directivo o no.
	 */
	private void createButtonSection(JPanel window, List<String> buttonTexts, boolean isDirective) {
		buttons = new ArrayList<>();
		short buttonCount = (short) buttonTexts.size();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JButton button;

		if (!isDirective) {
			buttonCount -= 1;
		}

		for (short i = 0; i < buttonCount; i++) {
			button = new JButton(buttonTexts.get(i));
			button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
			button.setName("" + i);
			buttons.add(button);
			panel.add(button);
		}

		window.add(panel, BorderLayout.EAST);
	}

}
