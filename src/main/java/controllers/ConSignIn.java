package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import views.VsignIn;

public class ConSignIn implements ActionListener {

	public ConSignIn() {
		VsignIn window = new VsignIn();
		window.getEnter().addActionListener(this);
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
}
