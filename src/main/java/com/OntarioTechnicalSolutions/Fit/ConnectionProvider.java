package com.OntarioTechnicalSolutions.Fit;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionProvider {
    private static Connection mockConnection;
    private static final String DATABASE_PATH_IN_JAR = "/database/OntarioTechnicalSolutions.db";
    private static Path tempDbFile;

    public static Connection getCon() {
        if (mockConnection != null) {
            return mockConnection;
        }
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");

            // Get the database file from resources as an InputStream
            InputStream dbStream = ConnectionProvider.class.getResourceAsStream(DATABASE_PATH_IN_JAR);

            if (dbStream == null) {
                System.err.println("Error: Database file '" + DATABASE_PATH_IN_JAR + "' not found in resources.");
                return null;
            }

            // Create a temporary file to copy the database to (only if not already created)
            if (tempDbFile == null) {
                tempDbFile = Files.createTempFile("temp_db", ".db");
                Files.copy(dbStream, tempDbFile, StandardCopyOption.REPLACE_EXISTING);
                dbStream.close();
            }

            // Construct the database URL using the temporary file path
            String url = "jdbc:sqlite:" + tempDbFile.toAbsolutePath().toString();

            con = DriverManager.getConnection(url);
            String sql = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT)";

            Statement st = con.createStatement();
            System.out.println("Connected to the database");
            st.executeUpdate(sql);
            st.close();

            // We don't close the connection here as it might be needed immediately after

        } catch (ClassNotFoundException e) {
            System.out.println("error in class");
            System.err.println("SQLite JDBC Driver not found: " + e.getMessage()); // Corrected driver name
        } catch (SQLException e) {
            System.out.println("error in sql");
            System.err.println("Error establishing database connection: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error accessing or copying database from resources: " + e.getMessage());
        }
        return con;
    }

    public static void setConnection(Connection connection) {
        mockConnection = connection;
    }
}