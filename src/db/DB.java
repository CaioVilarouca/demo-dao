package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DB {
	private static Properties loadProperties() { // Carregar
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		}
		catch (IOException e ) {
			throw new DbException("Não foi possivel carregar o arquivo. "+e.getMessage());
		}
	}
}
