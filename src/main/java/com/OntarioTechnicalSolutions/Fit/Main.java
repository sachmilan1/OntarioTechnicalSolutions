//package com.OntarioTechnicalSolutions.Fit;
//
//import javafx.application.Application;
//
//import java.io.File;
//import java.net.URL;
//
//
//public class Main {
//    public static void main(String[] args) {
//        //new LoginScreen().login();
//        URL url = Main.class.getResource("/FXML/RegisterScreen.fxml");
//        System.out.println(url);
//        Application.launch(LoginScreenFX.class, args);
//
//    }
//
//}
package com.OntarioTechnicalSolutions.Fit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        startDocker();
        setEnvironmentVariables();
        System.out.println("Everything is working till now");
    }

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

    private static void setEnvironmentVariables() {
        System.out.println("Setting environment variables...");
        System.setProperty("DB_URL", "jdbc:postgresql://localhost:5432/fitness_app");
        System.setProperty("DB_USER", "postgres");
        System.setProperty("DB_PASSWORD", "password");  // Change as needed
        System.out.println("Environment variables set.");
    }
}
