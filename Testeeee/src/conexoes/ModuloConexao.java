package conexoes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ModuloConexao {
	
	public static Connection getConnection() {
		try {
			Properties prop = getProperties();
			
			final String url = prop.getProperty("banco.url");
	
			final String usuario = prop.getProperty("banco.usuario");
	
			final String senha = prop.getProperty("banco.senha");
			
			return DriverManager.getConnection(url, usuario, senha);
		} catch (SQLException | IOException e) {
			throw new RuntimeException(e);
		}	
	}
	
	private static Properties getProperties() throws IOException {
		Properties prop = new Properties();
		String currentUsersHomeDir = System.getProperty("user.home");
		String caminho = "/conexao.properties";
		
		prop.load(ModuloConexao.class.getResourceAsStream(caminho));
		return prop;
	}

}
