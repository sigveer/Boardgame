package com.gruppe24.boardgames.laddergame.models.Tile;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.boardgames.laddergame.models.board.tile.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code LadderTileTest} is a test class for the {@code LadderTile} class.
 */
class LadderUpTileTest {

  private LadderUpTile ladderUpTile;
  private Player player;


  /**
   * Sets up the test fixture by creating a new ladder tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    ladderUpTile = new LadderUpTile(5, 10);
    player = new Player("TestPlayer", Color.BLUE);
    player.setPosition(5);
  }


  /**
   * Tests the perform method in the {@code LadderTile} class.
   */
  @Test
  void perform() {
    ladderUpTile.perform(player);
    assertEquals(10, player.getPosition());
  }
}