package views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controllers.EventReadMessages;
import controllers.EventSendMessages;
import models.GmailVariables;

public class VsendMessages extends JFrame{

	// ATRIBUTOS
		private int yLocation = 73;
		GmailVariables var;
		private JTextField sender;
		private JTextField password;
		private JTextField destination;
		private JTextField subject;
		private JTextField text;
		private ArrayList<JTextField> textfields;
		private JButton send;
		
		public VsendMessages(GmailVariables var) {
			textfields = new ArrayList<>();
			sender = new JTextField();
			password = new JTextField();
			destination = new JTextField();
			subject = new JTextField();
			text = new JTextField();
			send = new JButton(var.getSend());
			textfields.add(sender);
			textfields.add(password);
			textfields.add(destination);
			textfields.add(subject);
			textfields.add(text);
			send.addActionListener(new EventSendMessages(var,textfields));
			this.var = var;
			prepareWindow();
			
		}

		private void putComponets(JPanel window) {
			window.add(sender);
			window.add(password);
			window.add(destination);
			window.add(subject);
			window.add(text);
			window.add(send);
			
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
			
			putComponets(window);
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
