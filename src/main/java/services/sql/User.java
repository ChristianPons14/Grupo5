package services.sql;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class User {
	private int id;
	private String userName;
	private String userSurName;
	private String password;
	private boolean directive;
	private Date signIn;
	
	/**
	 * 
	 * @param id - int - id usuario
	 * @param userName - String - nombre usuario
	 * @param userSurName - String - apellido usuario
	 * @param directive - boolean - si es directivo
	 * @param signIn - Date - ultimo logueo
	 */
	public User(int id, String userName, String userSurName, boolean directive, Date signIn) {
		this.id = id;
		this.userName = userName;
		this.userSurName = userSurName;
		this.directive = directive;
		this.signIn = signIn;
	}
	
	
}
