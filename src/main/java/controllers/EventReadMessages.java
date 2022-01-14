package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import columbia.SendMessages;
import models.GmailVariables;
import views.VreadMessages;

public class EventReadMessages implements ActionListener{
	VreadMessages parentFrame;
	GmailVariables var;
	
	public EventReadMessages(VreadMessages vreadMessages) {
		var = new GmailVariables();
		parentFrame = vreadMessages;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(((JButton) e.getSource()).getText().equals(var.getSend())){
		 new ConSendMessages();
		}else {
		JLabel label = new JLabel(var.getText());
		JPanel panel = new JPanel();
		final JDialog frame = new JDialog(parentFrame, true);
		frame.getContentPane().add(panel);
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
		}
	}
	
}
