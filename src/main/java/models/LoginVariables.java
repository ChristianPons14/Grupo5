package models;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Christian Pons Hern�ndez
 *
 * Contiene todos los datos de las ventanas
 */
@Getter @NoArgsConstructor
public class LoginVariables {

	// Varaibles de VLogin
	private String loginTitle = "Iniciar sesi�n";
	private short vloginWidth = 400;
	private short vloginHeight = 300;
	private String loginText = "Introduce los datos para iniciar sesi�n:";
	private String name = "Nombre:";
	private String password = "Contrase�a:";
	private String enter = "Introducir";
	private String errorEmpty = "Por favor \nrellene los campos";
	private String errorNotFound = "El nombre o la contrase�a son incorrectos";
}
