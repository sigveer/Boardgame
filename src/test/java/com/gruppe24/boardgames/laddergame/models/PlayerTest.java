package com.gruppe24.boardgames.laddergame.models;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    player = new Player("TestPlayer", 1);
  }

  /**
   * Tests the getter for player's name.
   */
  @Test
  void getName() {
    assertEquals("TestPlayer", player.getName());
  }

  /**
   * Tests the setter for player's name.
   */
  @Test
  void setName() {
    player.setName("NewName");
    assertEquals("NewName", player.getName());
  }

  /**
   * Tests the getter for player's position.
   */
  @Test
  void getPosition() {
    player.setPosition(8);
    assertEquals(8, player.getPosition());
  }

  /**
   * Tests the setter for player's position.
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
  void testPlayerPieceInitialization() {
    ImageView playerPiece = player.getPlayerPiece();
    assertNotNull(playerPiece);
    assertEquals(40, playerPiece.getFitWidth());
    assertEquals(40, playerPiece.getFitHeight());
  }

  /**
   * Tests the player's icon initialization.
   */
  @Test
  void testIconInitialization() {
    assertNotNull(player.getIcon());
    assertEquals("pictures/pngIcons/luigi.png", player.getIconPath());
  }

  /**
   * Tests the player's icon cycling functionality.
   */
  @Test
  void testCycleToNextIcon() {
    player.cycleToNextIcon();
    assertEquals("pictures/pngIcons/wario.png", player.getIconPath());
    player.cycleToNextIcon();
    assertEquals("pictures/pngIcons/waluigi.png", player.getIconPath());
  }

  /**
   * Tests the player's frozen state.
   */
  @Test
  void testFrozenState() {
    assertFalse(player.isFrozen());
    player.setFrozen(true);
    assertTrue(player.isFrozen());
  }

  /**
   * Tests the exception when creating a player with an invalid name.
   */
  @Test
  void testInvalidName() {
    assertThrows(IllegalArgumentException.class, () -> new Player("", 1));
  }

  /**
   * Tests the exception when creating a player with a negative icon index.
   */
  @Test
  void testInvalidIconIndex() {
    assertThrows(IllegalArgumentException.class, () -> new Player("TestPlayer", -1));
  }

  /**
   * Tests the initialization of the player piece with a custom image.
   */
  @Test
  void testInitializePlayerPiece() {
    Image customImage = new Image(getClass().getClassLoader().getResourceAsStream("pictures/pngIcons/mario.png"));
    player.initializePlayerPiece(customImage);
    assertNotNull(player.getPlayerPiece());
    assertEquals(customImage, player.getPlayerPiece().getImage());
  }
}