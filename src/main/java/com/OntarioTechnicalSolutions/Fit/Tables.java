package com.OntarioTechnicalSolutions.Fit;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {

    public static void runTables() {
//        Creating the database
        try {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS fitness_app");
            st.executeUpdate("Use OntarioTechnicalSolutions");
        } catch (Exception e) {
            System.out.println("Error in creating the Ontario Technical solutions Database");
        }

        //Creating the user Table
        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS Users (" +
                    "User_PK serial PRIMARY KEY," +
                    "name VARCHAR(50)," +
                    "email VARCHAR(50)," +
                    "username VARCHAR(50)," +
                    "password VARCHAR(20))");
            System.out.println("success, the user table is created");
        } catch (SQLException e) {
            System.out.println("error");
            System.err.println("Error establishing database connection: " + e.getMessage());
        }

        try{
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS Workouts(" +
                    "WorkOut_PK serial PRIMARY KEY," +
                    "Name VARCHAR(50)," +
                    "Description VARCHAR(1000)," +
                    "Photo VARCHAR(300)," +
                    "Video VARCHAR(300)," +
                    "Category VARCHAR(50)," +
                    "Muscle VARCHAR(50))");
            System.out.println("Workout table is created");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR",JOptionPane.WARNING_MESSAGE);
        }
    }
    public static void main(String []args){
        Tables.runTables();
    }

    public static void createFavTable(String name){
        String sql = "CREATE TABLE IF NOT EXISTS " + name + " ("
                + "WorkOut_PK serial PRIMARY KEY, "
                + "Name VARCHAR(50), "
                + "Description VARCHAR(1000), "
                + "Photo VARCHAR(300), "
                + "Video VARCHAR(300), " +
                "Category VARCHAR(50),"
                + "Muscle VARCHAR(50))";

        try (Connection con = ConnectionProvider.getCon();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.executeUpdate();
            System.out.println(name+" table has been created");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
