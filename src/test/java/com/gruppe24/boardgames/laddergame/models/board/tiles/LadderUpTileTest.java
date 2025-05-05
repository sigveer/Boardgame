package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code LadderUpTileTest} is a test class for the {@code LadderUpTile} class.
 */
class LadderUpTileTest {

  private LadderUpTile ladderUpTile;
  private Player player;


  /**
   * Sets up the test fixture by creating a new ladderUp tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    ladderUpTile = new LadderUpTile(5, 10);
    player = new Player("TestPlayer", 1);
    player.setPosition(5);
  }


  /**
   * Tests the perform method in the {@code LadderUpTile} class.
   */
  @Test
  void perform() {
    ladderUpTile.perform(player);
    assertEquals(10, player.getPosition());
  }
}