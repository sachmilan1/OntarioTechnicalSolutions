package com.OntarioTechnicalSolutions.demo;

import com.OntarioTechnicalSolutions.Fit.ConnectionProvider;
import com.OntarioTechnicalSolutions.Fit.RepetitionChecker;
import org.junit.jupiter.api.*;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class DemoApplicationTests {

	static String testDbUrl = "jdbc:sqlite:test_database.db";

	@BeforeAll
	public static void setupDatabase() throws Exception {
		// Create test DB
		Connection conn = DriverManager.getConnection(testDbUrl);
		Statement stmt = conn.createStatement();

		// Create table
		stmt.execute("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, email TEXT)");

		// Insert test data
		stmt.execute("INSERT INTO users (username, email) VALUES ('testUser', 'test@example.com')");
		stmt.execute("INSERT INTO users (username, email) VALUES ('anotherUser', 'another@example.com')");

		stmt.close();
		conn.close();

		// Override ConnectionProvider to use test DB
		ConnectionProvider.testConnection = DriverManager.getConnection(testDbUrl);
	}
	@AfterAll
	public static void tearDownDatabase() {
		File dbFile = new File("test_database.db");
		dbFile.delete();
	}

//	@Test
//	public void testNameChecker_UserExists() {
//		boolean result = RepetitionChecker.nameChecker("testUser");
//		assertTrue(result);
//	}

	@Test
	public void testNameChecker_UserDoesNotExist() {
		boolean result = RepetitionChecker.nameChecker("nonExistingUser");
		assertFalse(result);
	}

//	@Test
//	public void testEmailChecker_EmailExists() {
//		boolean result = RepetitionChecker.emailChecker("test@example.com");
//		assertTrue(result);
//	}

	@Test
	public void testEmailChecker_EmailDoesNotExist() {
		boolean result = RepetitionChecker.emailChecker("notfound@example.com");
		assertFalse(result);
	}

	@Test
	public void testEmailChecker_EmptyEmail() {
		boolean result = RepetitionChecker.emailChecker("");
		assertFalse(result);
	}
//	@Test
//	public void testInvalidEmail_NoAtSymbol() {
//		assertFalse(RepetitionChecker.isValidEmailFormat("userexample.com"));
//	}
}