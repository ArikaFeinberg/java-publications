package lab.model.dao.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {

    private static final String PROPERTIES_FILE_NAME = "/home/ivan/IdeaProjects/java-publications/src/main/resources/db.properties";
    private static volatile DataSource dataSource;

    private ConnectionPool() {
        //getDataSource();
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            Path propertiesPath = null;
            propertiesPath = Paths.get(PROPERTIES_FILE_NAME);

            synchronized (ConnectionPool.class) {
                if (dataSource == null) {
                    try (FileReader reader = new FileReader(propertiesPath.toFile())) {
                        Properties properties = new Properties();
                        properties.load(reader);
                        BasicDataSource ds = new BasicDataSource();
                        ds.setUrl(properties.getProperty("url"));
                        ds.setDriverClassName("org.postgresql.Driver");
                        ds.setUsername(properties.getProperty("username"));
                        ds.setPassword(properties.getProperty("password"));
                        ds.setMinIdle(5);
                        ds.setMaxIdle(20);
                        ds.setMaxOpenPreparedStatements(100);
                        dataSource = ds;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            //Class.forName("org.postgresql.Driver");
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
