package com.company.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
           try {
               Properties properties = loadProperties();
               String url = properties.getProperty("dburl");
               connection = DriverManager.getConnection(url, properties); // Conecta no banco de dados e retorna o objeto de conexão
           } catch (SQLException exception) {
               throw new DBException(exception.getMessage());
           }
        }

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
           try {
               connection.close();
           } catch (SQLException exception) {
               throw new DBException(exception.getMessage());
           }
        }
    }

    private static Properties loadProperties() {
        try (FileInputStream inputStream = new FileInputStream("src/com/company/db.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream); // Ler os dados do arquivo db.properties apontado pelo inputStream
            return properties;
        } catch (IOException exception) {
            throw new DBException(exception.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException exception) {
                throw new DBException(exception.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
       if (resultSet != null) {
           try {
                resultSet.close();
           } catch (SQLException exception) {
                throw new DBException(exception.getMessage());
           }
       }
    }
}
