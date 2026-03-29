package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
	private static Connection connection = null;
	private static Connection getConnection() {
		if (connection == null) {
			try {
				Properties properties = loadProperties();
				String url = properties.getProperty("url");
				connection = DriverManager.getConnection(url, properties);
			} 
			catch (SQLException e) {
				throw new DbException("Não foi possivel fazer a conexão com o banco. Erro: "+e.getMessage());
			}
		}
		return connection;
	}
	private static Properties loadProperties() { // Carregar
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		}
		catch (IOException e ) {
			throw new DbException("Não foi possivel carregar o arquivo. Erro: "+e.getMessage());
		}
	}
}
