package services.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.mail.Address;

public class ReadMessagesSQL {
	public static String getMail(Connection con, String name) {
		String mail = "";
		
		try {
			String sql = "Select mail from users,messages where user_name like 'name' ";
			PreparedStatement prepStmt = con.prepareStatement(sql);
			ResultSet almacenador = prepStmt.executeQuery();
			
			if(almacenador.next()) {
				mail = almacenador.getString(1);
			}
		}catch (SQLException e) {

		}
		
		return mail;	
	}
	public static String getPassword(Connection con, String name) {
		String password = "";

		try {
			String sql = "Select mail_password from users,messages where user_name like 'name'";
			PreparedStatement prepStmt = con.prepareStatement(sql);
			ResultSet almacenador = prepStmt.executeQuery();

			if (almacenador.next()) {
				password = almacenador.getString(1);
			}
		} catch (SQLException e) {

		}

		return password;
	}
	public static void createMessage(Connection con, String subject, String from, String text) {
		String sql = "insert into messages('message_subject','message_from','message_text') "
				+ "values ('subject','from','text"; 
		
		try {
			PreparedStatement prepStmt = con.prepareStatement(sql);			
			int result = prepStmt.executeUpdate();
			prepStmt.close();
		
		} catch (SQLException e) {
		}

	}
}
