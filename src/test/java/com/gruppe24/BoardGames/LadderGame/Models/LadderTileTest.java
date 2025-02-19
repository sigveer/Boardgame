package com.gruppe24.BoardGames.LadderGame.Models;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LadderTileTest {

  private LadderTile ladderTile;
  private Player player;

  @BeforeEach
  void setUp() {
    ladderTile = new LadderTile(5, 10);
    player = new Player("TestPlayer");
    player.setPosition(5);
  }

  @Test
  void perform() {
    ladderTile.perform(player);
    assertEquals(10, player.getPosition());
  }
}