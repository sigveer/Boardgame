package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderDownTile;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code SnakeTileTest} is a test class for the {@code SnakeTile} class.
 */
class LadderDownTileTest {

  private LadderDownTile ladderDownTile;
  private Player player;


  /**
   * Sets up the test fixture by creating a new snake tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    ladderDownTile = new LadderDownTile(10, 5);
    player = new Player("TestPlayer", Color.BLUE);
    player.setPosition(10);
  }


  /**
   * Tests the perform method in the {@code SnakeTile} class.
   */
  @Test
  void perform() {
    ladderDownTile.perform(player);
    assertEquals(5, player.getPosition());
  }
}