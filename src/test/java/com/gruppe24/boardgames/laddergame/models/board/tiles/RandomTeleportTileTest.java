package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gruppe24.boardgames.laddergame.models.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@code RandomTeleportTileTest} is a test class for the {@code RandomTeleportTile} class.
 */
class RandomTeleportTileTest {

  private RandomTeleportTile randomTeleportTile;
  private Player player;

  /**
   * Sets up the test fixture by creating a new random teleport tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    randomTeleportTile = new RandomTeleportTile(10);
    player = new Player("TestPlayer", Color.BLUE);
    player.setPosition(10);
  }

  /**
   * Tests the perform method in the {@code RandomTeleportTile} class.
   */
  @Test
  void perform() {
    int initialPosition = player.getPosition();
    randomTeleportTile.perform(player);

    assertNotEquals(initialPosition, player.getPosition(),
        "Player position should change after teleportation");

    int newPosition = player.getPosition();
    assertTrue(newPosition >= 1 && newPosition <= 89,
        "Teleported position should be between 1 and 89");

    assertEquals(player.getPosition(), randomTeleportTile.getPosition(),
        "Tile position should match player's new position");

    assertEquals(3, randomTeleportTile.checkTileType,
        "checkTileType should be 3 for RandomTeleportTile");
  }
}