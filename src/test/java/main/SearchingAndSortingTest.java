package main;

import org.junit.jupiter.api.Test;
import main.*;
import static org.junit.Assert.assertEquals;

public class SearchingAndSortingTest {
    String playerName = "User1";
    User user = new User("User1", 0);

    @Test
    public void testgetUserLevelByLinearsearch() {
        String playerName = "User1";
        //assertEquals(user.getUserLevelByLinearSearch(playerName), 0);
    }
    @Test
    public void testgetPlayerInformation() {
        String playerName = "User1";
        //assertEquals(user.getPlayerInformation(playerName), "User1");
    }
}
