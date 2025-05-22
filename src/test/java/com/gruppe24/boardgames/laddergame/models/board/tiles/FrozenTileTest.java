package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrozenTileTest {

  private FrozenTile frozenTile;
  private Player player;

  @BeforeEach
  void setUp() {
    frozenTile = new FrozenTile(10);
    player = new Player("TestPlayer", 1);
    player.setPosition(10);
  }

  @Test
  void perform() {
    assertFalse(player.isFrozen());
    frozenTile.perform(player);
    assertTrue(player.isFrozen());
    assertEquals(10, player.getPosition());
  }

  @Test
  void testGetTileType() {
    assertEquals(4, frozenTile.getTileType());
  }
}