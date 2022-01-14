package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.net.ftp.FTPClient;

import ftp.FtpClient;
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
	
	public static void main(String[] args) throws IOException {
		new ConMenu();
	}
	
	/**
	 * Muestra en el jtree todos los archivos del directorio actual
	 * 
	 * @param ftpClient - FTPClient - cliente que se conecta al ftp
	 * @return DefaultMutableTreeNode que se usara para meterle al jtree 
	 */
	public static DefaultMutableTreeNode listAllFiles(FTPClient ftpClient) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("Root");
		FtpClient.listCurrentDirectory(ftpClient).forEach(file -> {
			if(file.isDirectory()) {
				node.add(listFiles(ftpClient, "", file.getName()));
			} else {
				node.add(new DefaultMutableTreeNode(file.getName()));
			}
		});
		return node;
	}
	
	/**
	 * Muestra en el jtree todos los archivos del directorio actual
	 * 
	 * @param ftpClient - FTPClient - cliente que se conecta al ftp
	 * @param path - String - directorio qu
	 * @param file
	 * @return DefaultMutableTreeNode que se usara para meterle al jtree 
	 */
	public static DefaultMutableTreeNode listFiles(FTPClient ftpClient, String path, String file) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(file);
		FtpClient.listDirectory(ftpClient, path + "/" + file).forEach(currentFile -> {
			if(currentFile.isDirectory()) {
				node.add(listFiles(ftpClient, path, currentFile.getName()));
			} else {
				node.add(new DefaultMutableTreeNode(currentFile.getName()));
			}
		});
		return node;
	}

	// Debe cambiarse en el futuro
	public ConMenu() throws IOException {
		var = new MenuVariables();
		window = new Vmenu(var, true);
		//Asi recibe los archivos a mostrar
		window.getModel().setRoot(ConMenu.listAllFiles(FtpClient.conectarFtp("localhost", 21, "asdf", "12345")));
		window.getButtons().forEach(button -> button.addActionListener(this));
		window.setVisible(true);
		
	}

	// Pendiente de hacer
	
	/**
	 * Realiza la funci�n del boton pulsado.
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
