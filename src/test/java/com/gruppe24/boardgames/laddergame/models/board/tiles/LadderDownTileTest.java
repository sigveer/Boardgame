package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gruppe24.boardgames.laddergame.models.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code ladderDownTileTest} is a test class for the {@code ladderDownTile} class.
 */
class LadderDownTileTest {

  private LadderDownTile ladderDownTile;
  private Player player;


  /**
   * Sets up the test fixture by creating a new ladderDown tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    ladderDownTile = new LadderDownTile(10, 5);
    player = new Player("TestPlayer", Color.BLUE);
    player.setPosition(10);
  }


  /**
   * Tests the perform method in the {@code ladderDownTile} class.
   */
  @Test
  void perform() {
    ladderDownTile.perform(player);
    assertEquals(5, player.getPosition());
  }
}