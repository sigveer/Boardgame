package com.gruppe24.boardgames.laddergame.models.board.tiles;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gruppe24.boardgames.laddergame.models.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@code SpecialTileTest} is a test class for the {@code SpecialTile} class.
 */
class SpecialTileTest {

  private Player player;
  private SpecialTile specialTileWithoutDestination;
  private SpecialTile specialTileWithDestination;

  /**
   * Sets up the test fixture by creating a new player and special tiles before each test.
   */
  @BeforeEach
  void setUp() {
    player = new Player("TestPlayer", Color.BLUE);
    specialTileWithoutDestination = new SpecialTile(10);
    specialTileWithDestination = new SpecialTile(15, 20);
  }

  /**
   * Tests the behavior of the perform method in the {@code SpecialTile} class.
   */
  @Test
  void testSpecialTileBehaviour() {
    player.setPosition(specialTileWithoutDestination.getPosition());

    int initialPosition = player.getPosition();
    specialTileWithoutDestination.perform(player);
    assertEquals(initialPosition, player.getPosition(),
        "Special tile without destination should not change player position");

    player.setPosition(specialTileWithDestination.getPosition());
    initialPosition = player.getPosition();
    specialTileWithDestination.perform(player);

    assertEquals(initialPosition, player.getPosition(),
        "Base SpecialTile implementation should not change player position");

    assertEquals(20, specialTileWithDestination.getDestination(),
        "Destination should remain unchanged after perform()");
  }

  /**
   * Tests the getDestination method in the {@code SpecialTile} class.
   */
  @Test
  void getDestination() {
    assertEquals(0, specialTileWithoutDestination.getDestination(),
        "Destination should be 0 for special tile without destination");
    assertEquals(20, specialTileWithDestination.getDestination(),
        "Destination should be 20 for special tile with destination");
  }

  /**
   * Tests the getDestination method in the {@code SpecialTile} class with a negative destination.
   */
  @Test
  void testNegativeDestination() {
    try {
      new SpecialTile(10, -5);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameter destination cannot be less than 0", e.getMessage());
    }
  }
}