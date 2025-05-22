package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}