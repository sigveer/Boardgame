package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NormalTileTest {

  private NormalTile normalTile;
  private Player player;

  @BeforeEach
  void setUp() {
    normalTile = new NormalTile(10);
    player = new Player("TestPlayer", 1);
    player.setPosition(10);
  }


  @Test
  void testNormalTileBehavior() {
    assertEquals(10, player.getPosition());

    normalTile.perform(player);

    assertEquals(10, player.getPosition());
  }

  @Test
  void testGetPosition() {
    assertEquals(10, normalTile.getPosition());
  }

  @Test
  void testGetTileType() {
    assertEquals(0, normalTile.getTileType());
  }
}