package ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 
 * @author Miguel Ruiz
 *
 */
public class FtpClient {

	public static final String HOST = "localhost";
	public static final int PORT = 21;
	public static final String USERNAME = "admin";
	public static final String PASSWORD = "admin";
	
	public static FTPClient client;

	public static void main(String[] args) {
		try {
			client = conectarFtp(HOST, PORT, USERNAME, PASSWORD);
			System.out.println(client.getReplyString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Conecta al servidor FTP con el usuario especificado
	 * 
	 * @param hostname - String - dirección del servidor FTP
	 * @param port - int - Número de puerto del servidor FTP
	 * @param nombre - String - de usuario FTP cuenta de inicio de sesión
	 * @param contraseña - String - Contraseña de inicio de sesión FTP
	 * @return cliente FTP si hace login correcto
	 * @throws IOException
	 */
	public static FTPClient conectarFtp(String hostname, int port, String username, String password)
			throws IOException {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect(hostname, port);
		ftpClient.login(username, password);
		int replyCode = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			return null;
		} else {
			return ftpClient;
		}
	}

	/**
	 * Subir archivo (disponible para la capa Acción / Controlador)
	 * 
	 * @param ftpClient - FTPClient - Cliente FTP que se conecta
	 * @param pathname - String - Servidor FTP guardar directorio
	 * @param fileName - String - Nombre del archivo después de subirlo al servidor FTP
	 * @param inputStream - InputStream - flujo de archivo de entrada
	 * @return
	 */
	public static boolean uploadFile(FTPClient ftpClient, String pathname, String fileName, InputStream inputStream) {
		boolean flag = false;
		ftpClient.setControlEncoding("UTF-8");
		try {
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(pathname);
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Subir archivo (puede cambiar el nombre del archivo)
	 * 
	 * @param ftpClient - FTPClient - Cliente FTP que se conecta
	 * @param pathname - String - Servidor FTP guardar directorio
	 * @param filename - String - Nombre del archivo después de subirlo al servidor FTP
	 * @param originfilename - String - el nombre del archivo a cargar (dirección absoluta)
	 * @return
	 */
	public static boolean uploadFileFromProduction(FTPClient ftpClient, String pathname, String filename,
			String originfilename) {
		boolean flag = false;
		try {
			InputStream inputStream = new FileInputStream(new File(originfilename));
			flag = uploadFile(ftpClient, pathname, filename, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Subir archivo (no se permite cambiar el nombre del archivo)
	 * 
	 * @param ftpClient - FTPClient - Cliente FTP que se conecta
	 * @param pathname - String - Servidor FTP guardar directorio
	 * @param originfilename - String - el nombre del archivo a cargar (dirección absoluta)
	 * @return
	 */
	public static boolean uploadFileFromProduction(FTPClient ftpClient, String pathname, String originfilename) {
		boolean flag = false;
		try {
			String fileName = new File(originfilename).getName();
			InputStream inputStream = new FileInputStream(new File(originfilename));
			flag = uploadFile(ftpClient, pathname, fileName, inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Eliminar archivos
	 * 
	 * @param ftpClient - FTPClient - Cliente FTP que se conecta
	 * @param pathname - String - Servidor FTP guardar directorio
	 * @param filename - String - el nombre del archivo a eliminar
	 * @return
	 */
	public static boolean deleteFile(FTPClient ftpClient, String pathname,
			String filename) {
		boolean flag = false;
		try {
			// Cambiar directorio FTP
			ftpClient.changeWorkingDirectory(pathname);
			ftpClient.dele(filename);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * Descargar archivo
	 * 
	 * @param ftpClient - FTPClient - Cliente FTP que se conecta
	 * @param pathname - String - Directorio de archivos del servidor FTP
	 * @param nombre - String - de archivo nombre de archivo
	 * @param localpath - String - Ruta del archivo después de la descarga
	 * @return
	 */
	public static boolean downloadFile(FTPClient ftpClient, String pathname,
			String filename, String localpath) {
		boolean flag = false;
		try {
			ftpClient.changeWorkingDirectory(pathname);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localpath + "/" + file.getName());
					OutputStream os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * Lista todos los ficheros del directorio actual
	 * @param ftpClient - FTPClient - cliente ftp usado
	 * @return lista de ficheros ftp
	 */
	public static List<FTPFile> listCurrentDirectory(FTPClient ftpClient) {
		try {
			return Arrays.asList(ftpClient.listFiles());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Lista todos los ficheros del directorio actual
	 * @param ftpClient - FTPClient - cliente ftp usado
	 * @param pathname - String - directorio en el que buscar
	 * @return lista de ficheros ftp
	 */
	public static List<FTPFile> listDirectory(FTPClient ftpClient, String pathname) {
		try {
			return Arrays.asList(ftpClient.listFiles(pathname));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
