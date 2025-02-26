package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code LadderTileTest} is a test class for the {@code LadderTile} class.
 */
class LadderTileTest {

  private LadderTile ladderTile;
  private Player player;


  /**
   * Sets up the test fixture by creating a new ladder tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    ladderTile = new LadderTile(5, 10);
    player = new Player("TestPlayer");
    player.setPosition(5);
  }


  /**
   * Tests the perform method in the {@code LadderTile} class.
   */
  @Test
  void perform() {
    ladderTile.perform(player);
    assertEquals(10, player.getPosition());
  }
}