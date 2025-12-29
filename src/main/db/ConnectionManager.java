package main.db;

import java.sql.*;

public class ConnectionManager {

    private String connectionUrl;
    private Connection con = null;

    public ConnectionManager(String connectionUrl) {
        if(connectionUrl == null || connectionUrl.isEmpty()) {
            throw new IllegalArgumentException("Connection URL cannot be null or empty");
        }

        this.connectionUrl = connectionUrl;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public Connection createConnection() {
        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void closeConnection() {
        try {
            this.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}