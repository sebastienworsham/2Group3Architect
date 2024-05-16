package main;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import main.LoadScreen;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SearchingAndSortingTest {
    String playerName = "User1";
    User user = new User("User1", 0);

    @Test
    public void testgetUserLevelByLinearsearch() {
        LoadScreen loadScreen = new LoadScreen(null, null, null, null);
        String playerName = "User1";
        String result = loadScreen.getUserLevelByLinearSearch(playerName);
        assertEquals(result, "0");
    }
    @Test

    public void testgetPlayerInformation() {
        LoadScreen loadScreen = new LoadScreen(null, null, null, null);
        String playerName = "User1";
        String[] result = loadScreen.getPlayerInformation(playerName);
        String[] expected = {"User1", "0"}; // Assuming "User1" and "0" are the expected results
        assertArrayEquals(expected, result);
    }
}
