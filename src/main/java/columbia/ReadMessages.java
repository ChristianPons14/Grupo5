package columbia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.util.MimeMessageParser;

import lombok.Getter;
import models.GmailVariables;
import services.conector.Conector;
import services.sql.ReadMessagesSQL;
import services.sql.User;

@Getter
public class ReadMessages {

	Connection con;
	Properties propsPop;
	GmailVariables var;
	User user;
	ReadMessagesSQL sql;
	
	public ReadMessages(User user, Connection con) {
		this.con = con;
		this.user = user;
		sql = new ReadMessagesSQL();
		propsPop = new Properties();
		propertiesPop();
		leerMensaje();
	}

	private void leerMensaje() {
		String name = user.getUserName();
		String sender = sql.getMail(con, name);;
		String password = sql.getPassword(con, name);
		Session sesionLeer = Session.getDefaultInstance(propsPop);
		try {
			Store store = sesionLeer.getStore("pop3s");
			store.connect("pop.gmail.com", sender, password);

			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);
			Message[] mensajes = emailFolder.getMessages();
			var.setMessageCount(emailFolder.getMessageCount());
			
			for (int i = 1; i < var.getMessageCount(); i++) {
				String subject = mensajes[emailFolder.getMessageCount() - i].getSubject();
				String from = mensajes[emailFolder.getMessageCount() - i].getFrom()[0].toString();
				String text = readPlainContent((MimeMessage) mensajes[emailFolder.getMessageCount() - i]);
				sql.createMessage(con, subject, from, text);
			}
			emailFolder.close(true);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String readPlainContent(MimeMessage message) throws Exception {
		return new MimeMessageParser(message).parse().getPlainContent();
	}

	private void propertiesPop() {

		propsPop.put("mail.smtp.host", "pop.gmail.com");
		propsPop.put("mail.smtp.port", "995");
		propsPop.put("mail.smtp.auth", "true");
		propsPop.put("mail.smtp.starttls.enable", "true");
		propsPop.put("mail.smtp.user", var.getSender());
		propsPop.put("mail.smtp.clave", var.getPassword());
	}
}
