package ftp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import services.conector.Conector;
import services.sql.User;
import services.sql.UsersSQL;

/**
 * 
 * @author Miguel Ruiz
 *
 */
public class FTPServer {

	public static final int PORT = 21;
	public static final String directory = "C:\\Users\\mig\\Desktop\\ftpcolumbia";

	private static FtpServer ftpServer;

	public static void main(String[] args) {
		initServer();
	}

	public static void initServer() {
		try {
			// utilizado para crear servidor
			FtpServerFactory serverFactory = new FtpServerFactory();
			// Información de configuración, como configurar el puerto de escucha y el
			// filtro IP
			ListenerFactory listenerFactory = new ListenerFactory();
			// establece el puerto
			listenerFactory.setPort(PORT);
			// Cerrar la conexión si no se han realizado operaciones durante cinco minutos
			// listenerFactory.setIdleTimeout(5*60*1000);

			List<Authority> authorities = new ArrayList<Authority>();
			authorities.add(new WritePermission());

			// Establecer nombre de usuario y contraseña

			List<User> usuarios = UsersSQL.retrieveAllUsersPassword(new Conector().getMySQLConnection());
			usuarios.forEach(user -> {
				BaseUser userFtp = new BaseUser();
				userFtp.setName(user.getUserName());
				userFtp.setPassword(user.getPassword());

				if (!new File(directory + "/" + user.getUserName()).exists()) {
					new File(directory + "/" + user.getUserName()).mkdirs();
				}

				if (user.isDirective()) {
					userFtp.setHomeDirectory(directory);
				} else {
					userFtp.setHomeDirectory(directory + "/" + user.getUserName());
				}

				userFtp.setAuthorities(authorities);
				try {
					serverFactory.getUserManager().save(userFtp);
				} catch (FtpException e) {
					e.printStackTrace();
				}
			});

			/*
			 * DbUserManagerFactory userFactory = new DbUserManagerFactory();
			 * userFactory.setDataSource(new Conector().getDataSource());
			 * userFactory.setAdminName("admin"); userFactory.
			 * setSqlUserAdmin("select user_name from users where user_name='{userid}' AND user_name='admin'"
			 * );
			 * userFactory.setSqlUserDelete("delete from users where user_name='{userid}'");
			 * userFactory.setSqlUserSelect("select * from users where user_name='{userid}'"
			 * ); userFactory.setSqlUserSelectAll("select * from users"); userFactory.
			 * setSqlUserAuthenticate("select user_name, user_password from users where user_name='{userid}"
			 * ); userFactory.setSqlUserInsert(
			 * "insert into users(userid, userpassword, homedirectory) values('{userid}', '{userpassword}', '{homedirectory}')"
			 * ); userFactory.setSqlUserUpdate(
			 * "update users set user_password='{userpassword}', home_directory='{homedirectory}', enableflag={enableflag}, write_permission={writepermission}, idletime={idletime}, uploadrate={uploadrate}, downloadrate={downloadrate}, maxlogin_number={maxloginnumber}, maxlogin_perip={maxloginperip} WHERE userid='{userid}'"
			 * );
			 * 
			 * UserManager userManager = userFactory.createUserManager();
			 * 
			 * serverFactory.setUserManager(userManager);
			 */

			serverFactory.addListener("default", listenerFactory.createListener());

			ftpServer = serverFactory.createServer();
			ftpServer.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
