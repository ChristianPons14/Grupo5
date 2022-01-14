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
	private boolean directive;
}
