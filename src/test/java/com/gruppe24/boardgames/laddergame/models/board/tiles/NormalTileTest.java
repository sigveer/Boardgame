package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@code NormalTileTest} is a test class for the {@code NormalTile} class.
 */
class NormalTileTest {

  private NormalTile normalTile;
  private Player player;

  /**
   * Sets up the test fixture by creating a new normal tile and player before each test.
   */
  @BeforeEach
  void setUp() {
    normalTile = new NormalTile(10);
    player = new Player("TestPlayer", 1);
    player.setPosition(10);
  }

  /**
   * Tests the perform method in the {@code NormalTile} class.
   */
  @Test
  void testNormalTileBehavior() {
    assertEquals(10, player.getPosition());

    normalTile.perform(player);

    assertEquals(10, player.getPosition());
  }

  /**
   * Tests the getPosition method in the {@code NormalTile} class.
   */
  @Test
  void testGetPosition() {
    assertEquals(10, normalTile.getPosition());
  }
}