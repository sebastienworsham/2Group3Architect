package main;

import org.junit.jupiter.api.Test;
import main.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

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
        String[] expected = {"User1", "0"};
        assertArrayEquals(expected, result);
    }
}
