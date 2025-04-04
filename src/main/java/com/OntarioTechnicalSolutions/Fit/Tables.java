package com.OntarioTechnicalSolutions.Fit;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {

    public void runTables() {
        //Creating the database
//        try {
//            Connection con = ConnectionProvider.getCon();
//            Statement st = con.createStatement();
//            st.executeUpdate("CREATE DATABASE IF NOT EXISTS OntarioTechnicalSolutions");
//            st.executeUpdate("Use OntarioTechnicalSolutions");
//        } catch (Exception e) {
//            System.out.println("Error in creating the Ontario Technical solutions Database");
//        }

        //Creating the user Table
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS Users (" +
                    "User_PK Int AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "email VARCHAR(50)," +
                    "username VARCHAR(50)," +
                    "password VARCHAR(20))");
            System.out.println("success, the user table is created");
        } catch (SQLException e) {
            System.out.println("error");
            System.err.println("Error establishing database connection: " + e.getMessage());
        }
    }
    public static void main(String []args){
        System.out.println(System.getProperty("user.dir"));

    }
}
