/**
 * 
 */
package services.sql;

import java.util.Date;

/**
 * @author ezequ
 *
 */
public class Users {
	/*"SELECT ID, USER_NAME, USER_SURNAMES, DIRECTIVE, SIGN_IN FROM USERS"*/
	//Properties
	private int id;
	private String userName;
	private String userSurName;
	private int directive;
	private Date signIn;
	
	public Users(int id, String userName, String userSurName, int directive, Date signIn) {
		super();
		this.id = id;
		this.userName = userName;
		this.userSurName = userSurName;
		this.directive = directive;
		this.signIn = signIn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurName() {
		return userSurName;
	}

	public void setUserSurName(String userSurName) {
		this.userSurName = userSurName;
	}

	public int getDirective() {
		return directive;
	}

	public void setDirective(int directive) {
		this.directive = directive;
	}

	public Date getSignIn() {
		return signIn;
	}

	public void setSignIn(Date signIn) {
		this.signIn = signIn;
	}
}
