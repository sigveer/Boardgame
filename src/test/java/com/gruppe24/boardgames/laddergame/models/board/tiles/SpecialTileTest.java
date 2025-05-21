package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialTileTest {

  private Player player;
  private SpecialTile specialTileWithoutDestination;
  private SpecialTile specialTileWithDestination;

  @BeforeEach
  void setUp() {
    player = new Player("TestPlayer", 1);
    specialTileWithoutDestination = new SpecialTile(10);
    specialTileWithDestination = new SpecialTile(15, 20);
  }

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

  @Test
  void getDestination() {
    assertEquals(0, specialTileWithoutDestination.getDestination(),
        "Destination should be 0 for special tile without destination");
    assertEquals(20, specialTileWithDestination.getDestination(),
        "Destination should be 20 for special tile with destination");
  }

  @Test
  void testNegativeDestination() {
    try {
      new SpecialTile(10, -5);
    } catch (IllegalArgumentException e) {
      assertEquals("Parameter destination cannot be less than 0", e.getMessage());
    }
  }

  @Test
  void testGetTileType() {
    assertEquals(-1,
        specialTileWithoutDestination.getTileType());
  }
}