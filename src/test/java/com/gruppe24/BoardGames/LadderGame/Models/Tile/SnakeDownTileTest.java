package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderDownTile;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code SnakeTileTest} is a test class for the {@code SnakeTile} class.
 */
class SnakeDownTileTest {

  private SnakeDownTile snakeDownTile;
  private Player player;


  /**
   * Sets up the test fixture by creating a new snake tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    snakeDownTile = new SnakeDownTile(10, 5);
    player = new Player("TestPlayer", Color.BLUE);
    player.setPosition(10);
  }


  /**
   * Tests the perform method in the {@code SnakeTile} class.
   */
  @Test
  void perform() {
    snakeDownTile.perform(player);
    assertEquals(5, player.getPosition());
  }
}