# Fitlink by OntarioTechnicalSolutions

## Install Required Software
## Before running the app, ensure the following are installed:

Java Development Kit (JDK 11+) – Download JDK
Apache Maven – Install Maven
PostgreSQL Database (or use Docker to set up the database) – Download PostgreSQL
JavaFX SDK (Required for the graphical user interface) – Download JavaFX
Docker (Optional but recommended for database setup) – Install Docker

## Download the Project
You can clone the repository using Git
git clone https://github.com/your-repo/fitness-app.git

## Set Up the Database
Open PostgreSQL and create a new database: sql
CREATE DATABASE fitness_app;

Open the file ConnectionProvider.java and update the database credentials
private static final String URL = "jdbc:postgresql://localhost:5432/fitness_app";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";

Then run the application.

After that you will be able to Register and Account and login with it.
Then given that you are an admin you will be able to add, remove and edit a workout.
