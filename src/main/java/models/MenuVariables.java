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
 * Contiene los datos necesarios para la ventana del menu
 *
 */
@Getter @NoArgsConstructor
public class MenuVariables {

	private short windowWidth = 500;
	private short windowHeight = 400;
	private String title = "Menu";
	private String text = "Selecciona la opci�n a realizar";
	private String deleteFiles = "Eliminar";
	private String addFiles = "A�adir";
	private String download = "Descargar";
	private String changeName = "Cambiar nombre";
	private String addUser = "A�adir un nuevo usuario";
	private String messages = "Mensajes";
	private List<String> buttonTexts = new ArrayList<>(Arrays.asList(addFiles,download,changeName,deleteFiles, messages, addUser));
}
