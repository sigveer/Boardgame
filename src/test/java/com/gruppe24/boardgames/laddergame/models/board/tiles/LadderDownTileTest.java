package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.google.gson.JsonObject;
import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LadderDownTileTest {

  private LadderDownTile ladderDownTile;
  private Player player;

  @BeforeEach
  void setUp() {
    ladderDownTile = new LadderDownTile(10, 5);
    player = new Player("TestPlayer", 1);
    player.setPosition(10);
  }

  @Test
  void perform() {
    ladderDownTile.perform(player);
    assertEquals(5, player.getPosition());
  }

  @Test
  void testGetTileType() {
    assertEquals(2, ladderDownTile.getTileType());
  }
}