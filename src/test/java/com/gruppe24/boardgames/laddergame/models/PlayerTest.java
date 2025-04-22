package com.gruppe24.boardgames.laddergame.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * {@code PlayerTest} is a test class for the {@code Player} class.
 */
class PlayerTest {

  private Player player;

  /**
   * Sets up the test fixture by creating a new player before each test.
   */
  @BeforeEach
  void setUp() {
    player = new Player("TestPlayer", Color.RED);
  }

  /**
   * Tests the getter for players name.
   */
  @Test
  void getName() {
    assertEquals("TestPlayer", player.getName());
  }

  /**
   * Tests the getter for players position.
   */
  @Test
  void getPosition() {
    player.setPosition(8);
    assertEquals(8, player.getPosition());
  }

  /**
   * Tests the setter for players position.
   */
  @Test
  void setPosition() {
    player.setPosition(10);
    assertEquals(10, player.getPosition());
  }

  /**
   * Tests the player piece is properly initialized.
   */
  @Test
  void testPlayerPiece() {
    assertNotNull(player.getPlayerPiece());
    assertEquals(25, player.getPlayerPiece().getRadius());
    assertEquals(Color.RED, player.getPlayerPiece().getFill());
  }
}