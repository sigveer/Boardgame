package com.gruppe24.BoardGames.LadderGame.Models.Tile;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code SnakeTileTest} is a test class for the {@code SnakeTile} class.
 */
class SnakeTileTest {

  private SnakeTile snakeTile;
  private Player player;


  /**
   * Sets up the test fixture by creating a new snake tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    snakeTile = new SnakeTile(10, 5);
    player = new Player("TestPlayer");
    player.setPosition(10);
  }


  /**
   * Tests the perform method in the {@code SnakeTile} class.
   */
  @Test
  void perform() {
    snakeTile.perform(player);
    assertEquals(5, player.getPosition());
  }
}