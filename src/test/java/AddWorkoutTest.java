import com.OntarioTechnicalSolutions.Fit.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddWorkoutTest {

    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Mock ConnectionProvider
        ConnectionProvider.setConnection(mockConnection);
    }

    @Test
    void testAddToDatabase() throws Exception {
        // Mock executeUpdate() for insert operation
        when(mockStatement.executeUpdate()).thenReturn(1);

        byte[] imageData = new byte[]{1, 2, 3};  // Sample image data

        AddWorkout.addToDatabase("Push-ups", "Arms", "100", "Great upper body workout", "https://video.com", imageData);

        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void testRetrieveWorkout() throws Exception {
        when(mockResultSet.next()).thenReturn(true, false);  // Only one workout
        when(mockResultSet.getInt("workout_PK")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Push-ups");
        when(mockResultSet.getString("category")).thenReturn("Arms");
        when(mockResultSet.getString("calorie_burn")).thenReturn("100");
        when(mockResultSet.getString("Description")).thenReturn("Great upper body workout");
        when(mockResultSet.getString("video_url")).thenReturn("https://video.com");

        List<Map<String, String>> workouts = AddWorkout.retrieveWorkout();
        assertEquals(1, workouts.size());
        assertEquals("Push-ups", workouts.get(0).get("name"));
    }

    @Test
    void testRemoveWorkout() throws Exception {
        // Mock executeUpdate() for delete operation
        when(mockStatement.executeUpdate()).thenReturn(1);

        AddWorkout.removeWorkout("Push-ups");

        verify(mockStatement, times(1)).executeUpdate();
    }
    @Test
    void testEditAdminAccess() throws Exception {
        when(mockStatement.executeUpdate()).thenReturn(1);

        EditAdminAccess.editAdminAccess("testuser", true);

        verify(mockStatement, times(1)).setBoolean(1, true);
        verify(mockStatement, times(1)).setString(2, "testuser");
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void testRetrieveUsers() throws Exception {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("name")).thenReturn("Alice", "Bob");

        List<String> users = EditAdminAccess.retrieveUsers();

        assertEquals(2, users.size());
        assertTrue(users.contains("Alice"));
        assertTrue(users.contains("Bob"));
    }
}
