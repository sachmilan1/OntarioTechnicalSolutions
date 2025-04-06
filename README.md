**Documentation-**

_AddToFavourites.java_

This file handles the logic for adding a workout to a user’s list of favourites in the database.

It connects to the database, prepares an INSERT statement to add a row to the user _ favorites table, and executes it. If the user has already added that workout before, it catches the duplicate entry error and shows a message saying the workout is already in their favourites.

In short, this class makes sure a user can favourite a workout — and get a friendly message if it’s already been added.

_AddWorkout.java_

This class handles all workout-related interactions with the database. It includes methods for adding new workouts, editing or removing existing ones, retrieving workouts and their images, and searching workouts by category.

It helps keep our code clean by separating the database logic from the UI, making it easier to maintain and reuse across the app.

_ConnectionProvider.java_

This class sets up the connection to our PostgreSQL database. Since we're packaging the database inside the JAR (in /resources/database/OntarioTechnicalSolutions.db), this class takes care of extracting it, copying it to a temporary file, and connecting to it using JDBC.

I also made sure that it only copies the database once and reuses the temporary file to avoid doing that every time. There’s even a fallback for mock connections in case we want to inject a test database.

The connection includes a quick check to make sure the users table exists — it creates it if it doesn't. The getCon() method returns the live connection, and setConnection() lets us inject a different one manually (helpful for testing).

_CurrentUser.java_

This class helps us manage the currently logged-in user across the application. We're using the Singleton pattern, which ensures that only one instance of CurrentUser exists during runtime — making it easier to access user info from anywhere in the app.

We use it to store and access:

The user's ID and name

Whether the user is a guest

Whether the user has admin privileges

If no one is logged in, the userID defaults to -1, and the username stays empty. This makes it easy to check whether someone is logged in, and what type of access they have (e.g., admin vs guest).

Overall, this class helps us control role-based access and personalize the user experience throughout the app.


_EditAdminAccess.java_

This class handles admin access control in our app. It allows us to promote or demote users to and from admin status by updating their isAdmin field in the database.

-editAdminAccess(String username, boolean accessLevel)
Updates the admin access for a given username. If accessLevel is true, the user becomes an admin; if false, admin rights are removed.
A confirmation message is shown once the change is made.

-retrieveUsers()
Returns a list of all usernames from the database. We use this to populate user lists in the UI (e.g., dropdowns or tables for managing access).

_LoginScreenFX.java_

This class is the entry point for our JavaFX-based login screen. It extends Application and is responsible for loading and displaying the LoginScreen.fxml file when the app starts.

-Loads the FXML file for the login screen from the resources/FXML/ directory.
-Sets the window title and icon.
-Initializes and displays the main login window using JavaFX.

_Main_

Entry point of the application

_ProcessImage.java_

This class helps us manage image files for workouts. We’re using it to take an image from a file path (set by the user), read it, and return it as a byte array so it can be stored in the database.

-It follows the singleton pattern, so we only ever have one active instance.

-We call setPathToImage() when the user selects or uploads an image.

-Then we use getImage() to convert that image into bytes, which we save in the database with the workout info.

_RepetitionChecker.java_

This class helps us avoid duplicate user accounts during sign-up. It checks whether a given username or email already exists in the database.

-nameChecker(String userName)
Looks up the database to see if the username is already taken.

-emailChecker(String email)
Checks if the email is already registered.

_SceneController.java_

This class is the main controller for handling page navigation, user interactions, admin actions, and dynamic workout content throughout our fitness app.

It connects all the FXML views with their backend logic and is responsible for switching between user roles (regular, guest, admin), managing user sessions, loading workouts, and handling database-driven features like favourites, search, and admin controls.

_Tables.java_


This class is responsible for creating essential database tables when the app starts — specifically, the Users table. It checks if the table already exists, and only creates it if needed.

-Connects to the SQLite database using our ConnectionProvider.

-Creates the Users table with fields like name, email, username, and password.

-Automatically sets up the User _ PK as the primary key with auto-increment.