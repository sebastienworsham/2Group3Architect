package main;

import entities.Coin;
import entities.Player;
import javafx.scene.layout.Pane;
import level.LevelController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class OOPTest {
    Pane playerRoot = new Pane();

    // Create a player instance

    @Test
    void TestOOP() {
        Pane levelRoot = new Pane();

        // Define initial coordinates for the coin
        int initialX = 100;
        int initialY = 200;

        // Create a Coin object using the constructor with initial coordinates
        Coin coin = new Coin(levelRoot, initialX, initialY);

        // Verify that the initial coordinates are set correctly
        assertEquals(initialX, coin.getXAxis(), "Initial X coordinate should be set correctly");
        assertEquals(initialY, coin.getYAxis(), "Initial Y coordinate should be set correctly");
    }
}