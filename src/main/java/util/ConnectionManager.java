package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.xmldb.api.base.Database;

public class ConnectionManager {
	
	private static final String URL_KEY = "url";
	private static final String USER_KEY = "user";
	private static final String PWD_KEY = "pwd";
	private static final String DRIVER_KEY = "driver";
	private static final String COL_DEPTS_KEY = "coleccionDepartamentos";

	private static MyDataSource datasource = null;
	

	private ConnectionManager() {

	}

	public static MyDataSource getDataSource(String ruta) {
		if (datasource == null) {
			Properties properties = new Properties();
			try (FileInputStream fis = new FileInputStream(ruta)) {
				properties.load(fis);
				String url = properties.getProperty(URL_KEY);
				String user = properties.getProperty(USER_KEY);
				String pwd = properties.getProperty(PWD_KEY);
				String driver = properties.getProperty(DRIVER_KEY);
				String col_depts = properties.getProperty(COL_DEPTS_KEY);

				datasource = new MyDataSource(user, pwd, url, driver);
				
				datasource.setColeccion(col_depts);
				



			} catch (FileNotFoundException e) {
				System.err.println("Ha ocurrido una excepción FileNotFound: " + e.getMessage());
			} catch (IOException e) {
				System.err.println("Ha ocurrido una excepción IOE: " + e.getMessage());
			} catch (Exception e) {
				System.err.println("Ha ocurrido una excepción: " + e.getMessage());

			}

		}

		return datasource;

	}

	
	
	

}
