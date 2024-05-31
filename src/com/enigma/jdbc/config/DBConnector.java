package com.enigma.jdbc.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection connection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/purba_resto";
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");
            return DriverManager.getConnection(url, username, password);

        }catch ( SQLException e){
            throw new RuntimeException("error while connecting to database" + e);

        }
    }
}
