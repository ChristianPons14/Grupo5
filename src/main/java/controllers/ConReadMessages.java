package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import columbia.ReadMessages;
import models.GmailVariables;
import services.conector.Conector;
import services.sql.User;
import views.VreadMessages;

public class ConReadMessages {
	private VreadMessages window;
	private GmailVariables var;
	
	public ConReadMessages(User user) {
		var = new GmailVariables();
		Connection con = new Conector().getMySQLConnection();
		ReadMessages read = new  ReadMessages(user,con);
		prepareLoginWindow();
	}

	private void prepareLoginWindow() {
		window = new VreadMessages(var);
		window.setVisible(true);
	}


}
