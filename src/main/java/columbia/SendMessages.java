package columbia;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JTextField;

import lombok.Getter;
import models.GmailVariables;

@Getter
public class SendMessages {

	Properties propsSmtp;
	GmailVariables var;

	public SendMessages(GmailVariables var, ArrayList<JTextField> textfields) {
		this.var = var;
		var.setSender(textfields.get(0).getText());
		var.setPassword(textfields.get(1).getText());
		var.setDestination(textfields.get(2).getText());
		var.setSubject(textfields.get(3).getText());
		var.setText(textfields.get(4).getText());
		propsSmtp = new Properties();
		propertiesStmp();
		enviarMensaje();
	}

	private void enviarMensaje() {
		Session sesionEnviar = Session.getDefaultInstance(propsSmtp);
		MimeMessage mensaje = new MimeMessage(sesionEnviar);
		try {

			mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(var.getDestination()));
			mensaje.setSubject(var.getSubject());
			mensaje.setText(var.getText());

			Transport transporte = sesionEnviar.getTransport("smtp");
			transporte.connect("smtp.gmail.com", var.getDestination(), var.getPassword());
			transporte.sendMessage(mensaje, mensaje.getAllRecipients());
			System.out.println("Mensaje enviado");
			transporte.close();

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("El mensaje no se ha podido enviar");
		}

	}

	private void propertiesStmp() {

		propsSmtp.put("mail.smtp.host", "smtp.gmail.com");
		propsSmtp.put("mail.smtp.port", "587");
		propsSmtp.put("mail.smtp.auth", "true");
		propsSmtp.put("mail.smtp.starttls.enable", "true");
		propsSmtp.put("mail.smtp.user", var.getDestination());
		propsSmtp.put("mail.smtp.clave", var.getPassword());
	}
}
