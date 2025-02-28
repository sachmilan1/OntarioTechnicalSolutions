package com.OntarioTechnicalSolutions.Fit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionProvider {
    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");

            // First, check if the database exists
            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\" +  "src\\main\\java\\com\\OntarioTechnicalSolutions\\Fit\\database\\OntarioTechnicalSolutions.db";
            con = DriverManager.getConnection(url);
            String sql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)";

            Statement st = con.createStatement();
            System.out.println("Connected to the database");
            st.executeUpdate(sql);
            st.close();
//            con.close(); // Close the connection to reconnect

            // Now connect to the newly created database
            con = DriverManager.getConnection(url, "root", "root");
        } catch (ClassNotFoundException e) {
            System.out.println("error in class");
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("error in sql");
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
        return con;
    }

//    public static void main(String []args){
//        ConnectionProvider cp = new ConnectionProvider();
//        cp.getCon();
//    }
}

