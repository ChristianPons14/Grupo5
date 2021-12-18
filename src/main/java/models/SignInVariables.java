package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Grupo 5
 * 
 *         Contiene las variables que se uilizar�n en la ventana de registro
 *
 */
@Getter @NoArgsConstructor
public class SignInVariables {
	private String windowTitle = "Registrar usuario";
	private String text = "Introduce los datos del usuario:";
	private String errorEmpty = "Uno de los campos est� vac�o, por favor introduce los datos";
	private String name = "Nombre:";
	private String surnames = "Apellidos:";
	private String password = "Contrase�a:";
	private String confirm = "confirmar contrase�a";
	private String directive = "Directivo";
	private String enter = "Introducir datos";
	private short windowWidth = 500;
	private short windowHeight = 400;
	private List <String> labelTexts = new ArrayList<>(Arrays.asList(name,surnames, password, confirm));
}
