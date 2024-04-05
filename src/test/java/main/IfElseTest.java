package main;

import entities.Player;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IfElseTest {
    @Test
    void testIfPlayerCanJump() {
        // Create a pane for playerRoot
        Pane playerRoot = new Pane();

        // Create a player instance
        Player player = new Player(playerRoot);
        Game.triggerJumpAction();

        //Check that the player can jump
        Assertions.assertTrue(Boolean.parseBoolean(player.canPlayerJump()));
    }
}
