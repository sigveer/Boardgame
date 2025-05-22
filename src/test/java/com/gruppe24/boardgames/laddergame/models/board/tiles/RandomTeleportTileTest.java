package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RandomTeleportTileTest {

  private RandomTeleportTile randomTeleportTile;
  private Player player;

  @BeforeEach
  void setUp() {
    randomTeleportTile = new RandomTeleportTile(10);
    player = new Player("TestPlayer", 1);
    player.setPosition(10);
  }

  @Test
  void perform() {
    int initialPosition = player.getPosition();
    randomTeleportTile.perform(player);

    assertNotEquals(initialPosition, player.getPosition(),
        "Player position should change after teleportation");

    int newPosition = player.getPosition();
    assertTrue(newPosition >= 1 && newPosition <= 89,
        "Teleported position should be between 1 and 89");
  }

  @Test
  void testGetTileType() {
    assertEquals(3, randomTeleportTile.getTileType());
  }
}