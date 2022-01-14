package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import columbia.SendMessages;
import models.GmailVariables;
import views.VreadMessages;
import views.VsendMessages;

public class EventSendMessages implements ActionListener{
		GmailVariables var;
		ArrayList<JTextField> textfields;
		public EventSendMessages(GmailVariables var2, ArrayList<JTextField> textfields) {
			var = var2;
			this.textfields = textfields;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			new SendMessages(var,textfields);
			}
		}
