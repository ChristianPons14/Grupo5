package controllers;

import java.sql.Connection;

import columbia.ReadMessages;
import columbia.SendMessages;
import models.GmailVariables;
import services.conector.Conector;
import services.sql.User;
import views.VreadMessages;
import views.VsendMessages;

public class ConSendMessages {
	private VsendMessages window;
	private GmailVariables var;
	public ConSendMessages() {
	var = new GmailVariables();
	Connection con = new Conector().getMySQLConnection();
	prepareLoginWindow();
}

private void prepareLoginWindow() {
	window = new VsendMessages(var);
	window.setVisible(true);
}


}

