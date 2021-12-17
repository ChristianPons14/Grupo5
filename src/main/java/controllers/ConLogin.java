package controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.LineBorder;

import models.ViewsVariables;
import views.Vlogin;

/**
 * 
 * @author Grupo 5
 * 
 *         Llama a la ventana Vlogin y permite iniciar sesi�n en el programa si
 *         los datos son correctos.
 *
 */
public class ConLogin implements ActionListener{
	
	private Vlogin window;
	private ViewsVariables var;

	public ConLogin() {
		prepareLoginWindow();
	}

	private void prepareLoginWindow() {
		window = new Vlogin();
		var = new ViewsVariables();
		window.getEnter().addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean filledFields = checkFields();
		
		if(filledFields) {
			//User user = checkDb();
			
		}
	}

	private boolean checkFields() {
		try {
			if(window.getUserName().getText().isBlank()) {
				window.getUserName().setBorder(new LineBorder(Color.red));
				throw new Exception();
				
			}else {
				window.getUserName().setBorder(null);
			}
			
			if(window.getPassword().getPassword().toString().isBlank()) {
				window.getPassword().setBorder(new LineBorder(Color.red));
				throw new Exception();
				
			}else {
				window.getUserName().setBorder(null);
			}
			return true;
			
		} catch (Exception e) {
			window.getError().setText(var.getErrorEmpty());
			return false;
		}
	}
	
	// Pendiente de desarrollar.
	/*
	private boolean checkDb() {
		
		return null;
	}*/
}