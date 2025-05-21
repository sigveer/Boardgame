package com.gruppe24.boardgames.laddergame.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class PlayerTest {

  private Player player;


  @BeforeEach
  void setUp() {
    player = new Player("TestPlayer", 1);
  }


  @Test
  void getName() {
    assertEquals("TestPlayer", player.getName());
  }

  @Test
  void setName() {
    player.setName("NewName");
    assertEquals("NewName", player.getName());
  }


  @Test
  void getPosition() {
    player.setPosition(8);
    assertEquals(8, player.getPosition());
  }


  @Test
  void setPosition() {
    player.setPosition(10);
    assertEquals(10, player.getPosition());
  }


  @Test
  void testPlayerPieceInitialization() {
    ImageView playerPiece = player.getPlayerPiece();
    assertNotNull(playerPiece);
    assertEquals(40, playerPiece.getFitWidth());
    assertEquals(40, playerPiece.getFitHeight());
  }


  @Test
  void testIconInitialization() {
    assertNotNull(player.getIcon());
    assertEquals("pictures/pngIcons/luigi.png", player.getIconPath());
  }


  @Test
  void testCycleToNextIcon() {
    player.cycleToNextIcon();
    assertEquals("pictures/pngIcons/wario.png", player.getIconPath());
    player.cycleToNextIcon();
    assertEquals("pictures/pngIcons/waluigi.png", player.getIconPath());
  }


  @Test
  void testFrozenState() {
    assertFalse(player.isFrozen());
    player.setFrozen(true);
    assertTrue(player.isFrozen());
  }

//  /**
//   * Tests the exception when creating a player with an invalid name.
//   */
//  @Test
//  void testInvalidName() {
//    assertThrows(IllegalArgumentException.class, () -> new Player("", 1));
//  }
//
//  /**
//   * Tests the exception when creating a player with a negative icon index.
//   */
//  @Test
//  void testInvalidIconIndex() {
//    assertThrows(IllegalArgumentException.class, () -> new Player("TestPlayer", -1));
//  }


  @Test
  void testInitializePlayerPiece() {
    int iconIndex = 2;
    player.initializePlayerIcon(iconIndex);
    assertNotNull(player.getPlayerPiece());
  }
}