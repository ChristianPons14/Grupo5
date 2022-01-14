package models;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter	@NoArgsConstructor
public class GmailVariables {

		private String sender = "";
		private String password = "";
		private String destination = "";
		private String subject = "";
		private String text = "";
		private String send = "enviar email";
		private int messageCount;
		
		private String windowTitle = "Registrar usuario";
		private short windowWidth = 500;
		private short windowHeight = 400;
}
