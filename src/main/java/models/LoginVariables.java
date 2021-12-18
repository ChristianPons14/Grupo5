package models;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Christian Pons Hernández
 *
 * Contiene todos los datos de las ventanas
 */
@Getter @NoArgsConstructor
public class LoginVariables {

	// Varaibles de VLogin
	private String loginTitle = "Iniciar sesión";
	private short vloginWidth = 400;
	private short vloginHeight = 300;
	private String loginText = "Introduce los datos para iniciar sesión:";
	private String name = "Nombre:";
	private String password = "Contraseña:";
	private String enter = "Introducir";
	private String errorEmpty = "Por favor \nrellene los campos";
	private String errorNotFound = "El nombre o la contraseña son incorrectos";
}
