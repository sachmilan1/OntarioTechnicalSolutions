package com.OntarioTechnicalSolutions.demo;

import java.sql.*;

public class ConnectionProvider {
    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // First, check if the database exists
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true", "root", "1313");
            Statement st = con.createStatement();
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS OntarioTechnicalSolutions");
            con.close(); // Close the connection to reconnect

            // Now connect to the newly created database
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/OntarioTechnicalSolutions?useSSL=false&allowPublicKeyRetrieval=true", "root", "1313");
        } catch (ClassNotFoundException e) {
            System.out.println("error in class");
            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("error in sql");
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
        return con;
    }
}

