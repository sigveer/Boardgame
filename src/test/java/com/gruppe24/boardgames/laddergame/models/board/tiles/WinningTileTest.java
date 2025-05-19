package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WinningTileTest {

  private WinningTile winningTile;

  @BeforeEach
  void setUp() {
    winningTile = new WinningTile(90);
  }

  @Test
  void getTileType() {
    assertEquals(-3, winningTile.getTileType());
  }

  @Test
  void addActionToJson() {
    JsonObject tileJson = new JsonObject();
    winningTile.addActionToJson(tileJson, 90);

    JsonObject actionJson = tileJson.getAsJsonObject("action");
    assertNotNull(actionJson);
    assertEquals("WinningAction", actionJson.get("type").getAsString());
    assertEquals("Player wins the game on tile 90", actionJson.get("description").getAsString());
  }
}