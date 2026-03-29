package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	private static Connection connection = null;
	public static Connection getConnection() { // Conectar com o banco
		if (connection == null) {
			try {
				Properties properties = loadProperties();
				String url = properties.getProperty("url");
				connection = DriverManager.getConnection(url, properties);
			} 
			catch (SQLException e) {
				throw new DbException("Não foi possivel fazer a conexão com o banco.");
			}
		}
		return connection;
	}
	public static void closeConnection() { // Fechar a conexão
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DbException("Não foi possivel fechar a conexão com o banco.");
			}
		}
	}
	public static void closeStatement(Statement statement) { // Fechar declaração
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DbException("Não foi possivel fechar Statement.");
			}
		}
	}
	public static void closeStatement(ResultSet resultSet) { // Resultado a inserir
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DbException("Não foi possivel fechar ResultSet.");
			}
		}
	}
	private static Properties loadProperties() { // Carregar a conexão
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		}
		catch (IOException e ) {
			throw new DbException("Não foi possivel carregar o arquivo.");
		}
	}
}
