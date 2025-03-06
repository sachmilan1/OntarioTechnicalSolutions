package com.OntarioTechnicalSolutions.Fit;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.OntarioTechnicalSolutions.Fit.*;
import java.sql.Statement;
//
//public class ConnectionProvider {
//    public static Connection getCon() {
//        Connection con = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//
//            // First, check if the database exists
//            String url = "jdbc:sqlite:" +  System.getProperty("user.dir") + "/src/main/java/com/OntarioTechnicalSolutions/Fit/database/OntarioTechnicalSolutions.db";
//            con = DriverManager.getConnection(url);
//            String sql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, username TEXT, password TEXT, isAdmin BOOLEAN)";
//
//            Statement st = con.createStatement();
//            System.out.println("Connected to the database");
//            st.executeUpdate(sql);
//            st.close();
////            con.close(); // Close the connection to reconnect
//
//            // Now connect to the newly created database
//            con = DriverManager.getConnection(url, "root", "root");
//        } catch (ClassNotFoundException e) {
//            System.out.println("error in class");
//            System.err.println("MySQL JDBC Driver not found: " + e.getMessage());
//        } catch (SQLException e) {
//            System.out.println("error in sql");
//            System.err.println("Error establishing database connection: " + e.getMessage());
//        }
//        return con;
//    }
//
//
//    public static Connection getConWorkoutDB() {
//        Connection con = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/java/com/OntarioTechnicalSolutions/Fit/database/OntarioTechnicalSolutions.db";
//            con = DriverManager.getConnection(url);
//            String sql = "CREATE TABLE IF NOT EXISTS workouts (id INTEGER PRIMARY KEY, name TEXT, category TEXT, calorie_burn TEXT, Description TEXT, video_url TEXT, image_url TEXT)";
//
//            Statement st = con.createStatement();
//            System.out.println("Connected to the workout database");
//            st.executeUpdate(sql);
//
//
//            con = DriverManager.getConnection(url, "root", "root");
//
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return con;
//    }
////    public static void main(String []args){
////        ConnectionProvider cp = new ConnectionProvider();
////        cp.getCon();
////    }
//}
//

//public class ConnectionProvider {
//    private static String URL = "jdbc:postgresql://localhost:5432/Fit";
//    private static String userName = System.getenv("POSTGRES_USER");
//    private static String password =System.getenv("POSTGRES_PASSWORD")
//}
//package com.OntarioTechnicalSolutions.Fit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionProvider {
    private static final String URL = System.getProperty("DB_URL", "jdbc:postgresql://localhost:5432/fitness_app");
    private static final String USER = System.getProperty("DB_USER", "postgres");
    private static final String PASSWORD = System.getProperty("DB_PASSWORD", "1313");

    public static void main(String[] args) {
        startDocker();
        setEnvironmentVariables();
        getCon();
    }

    // 🚀 Start Docker & PostgreSQL automatically
    private static void startDocker() {
        try {
            Process checkDocker = new ProcessBuilder("docker", "ps").start();
            checkDocker.waitFor();

            if (checkDocker.exitValue() != 0) {
                System.out.println("Docker is not running. Starting Docker...");
                Process startDocker = new ProcessBuilder("docker", "start").start();
                startDocker.waitFor();
            }

            System.out.println("Starting PostgreSQL with Docker...");
            Process dockerCompose = new ProcessBuilder("docker-compose", "up", "-d").start();
            dockerCompose.waitFor();

            // Check logs to confirm DB is ready
            Process checkLogs = new ProcessBuilder("docker", "logs", "fitness_db").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(checkLogs.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("database system is ready to accept connections")) {
                    System.out.println("PostgreSQL is ready!");
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error starting Docker: " + e.getMessage());
        }
    }

    //  Set database environment variables
    private static void setEnvironmentVariables() {
        System.out.println("Setting environment variables...");
        System.setProperty("DB_URL", "jdbc:postgresql://localhost:5432/fitness_app");
        System.setProperty("DB_USER", "postgres");
        System.setProperty("DB_PASSWORD", "password");  // Change as needed
        System.out.println("Environment variables set.");
    }

    //  Get database connection
    public static Connection getCon() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to PostgreSQL database successfully.");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
        return conn;
    }

    //  Create necessary tables in the database
}
