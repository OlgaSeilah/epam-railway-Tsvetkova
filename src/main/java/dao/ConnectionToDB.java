package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

class ConnectionToDB {
    String driver = "org.postgresql.Driver";
    String url = "jdbc:postgresql://localhost:5432/railway_test";
    Properties properties = getPropertiesForDAO();

    Connection conn;

    private Properties getPropertiesForDAO() {
        Properties property = new Properties();

        try {
            FileInputStream file = new FileInputStream("src/main/resources/daoConfig.properties");
            property.load(file);
        } catch (
                IOException e) {
            System.err.println("ОШИБКА: Отсуствует конфигурационный файл для доступа к базе данных");
        }
        return property;
    }

}