package main.java.com.wiserweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{
    protected static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "wiserweb_db";
		String get_params = "?useUnicode=true&characterEncoding=utf-8";
        String dbUsername = "root";
        String dbPassword = "";

        Class.forName(dbDriver);

        return DriverManager.getConnection(dbURL + dbName + get_params, dbUsername, dbPassword);
    }
}
