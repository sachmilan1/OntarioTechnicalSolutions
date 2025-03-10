# Fitlink by OntarioTechnicalSolutions

# Fitness App - How to Run

This guide will walk you through running the **Fitness App** on your computer.

---

## 1. Install Required Software
Before running the application, ensure you have the following installed:

- **Java 11 or later** 
- **Apache Maven** (for managing dependencies)
- **PostgreSQL** (for database storage) 
  *(Alternatively, you can use Docker to set up the database)*
- **JavaFX SDK** (for running the graphical user interface)
- **Docker (Optional, Recommended for database setup)** 

---

## 2. Download the Project
You can **clone** the repository or **download** it manually.

### Clone via Git (Recommended)
Open a terminal or command prompt and run:
```sh
git clone https://github.com/your-repo/fitness-app.git
cd fitness-app
```
## 3. Set Up the Database
### Using PostgreSQL Manually

Open **PostgreSQL** and create a new database:

```sql
CREATE DATABASE fitness_app;
```
Open the file **`ConnectionProvider.java`** and update the database credentials:

```java
private static final String URL = "jdbc:postgresql://localhost:5432/fitness_app";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```
## 4. Run the Application

### Run from an IDE (IntelliJ, VS Code, Eclipse)

1. Open the project in your IDE.
2. Set the **Main Class** to:
   ```sh
   com.OntarioTechnicalSolutions.Fit.Main
   ```
3. Click **Run**.
4. 
After that you will be able to Register and Account and login with it.
Then given that you are an admin you will be able to add, remove and edit a workout.
