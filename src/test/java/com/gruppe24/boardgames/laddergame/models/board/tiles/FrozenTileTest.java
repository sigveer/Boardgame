package com.gruppe24.boardgames.laddergame.models.board.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gruppe24.boardgames.laddergame.models.Player;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@code frozenTileTest} is a test class for the {@code frozenTile} class.
 */
class FrozenTileTest {

  private FrozenTile frozenTile;
  private Player player;

  @BeforeEach
  void setUp() {
    frozenTile = new FrozenTile(10);
    player = new Player("TestPlayer", Color.BLUE);
    player.setPosition(10);
  }

  @Test
  void perform() {
    assertFalse(player.isFrozen());
    frozenTile.perform(player);
    assertTrue(player.isFrozen());
    assertEquals(10, player.getPosition());
  }
}