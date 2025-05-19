package com.gruppe24.boardgames.commonclasses;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.boardgames.laddergame.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonGameControllerTest {

  private CommonGameController gameController;
  private Player testPlayer;

  @BeforeEach
  void setUp() {
  }

  @Test
  void initializeGame() {
  }

  @Test
  void createPlayer() {
  }

  @Test
  void getMaxPlayers() {
  }

  @Test
  void addPlayer() {
  }

  @Test
  void removePlayer() {
  }

  @Test
  void getPlayers() {
  }

  @Test
  void setPlayers() {
  }

  @Test
  void cyclePlayerIcon() {
  }

  @Test
  void movePlayer() {
  }

  @Test
  void handlePlayerTurn() {
  }

  @Test
  void testHandleOvershootWithinLimit() {
    int position = 85;
    assertEquals(85, gameController.handleOvershoot(position));
  }

  @Test
  void testHandleOvershootExactWin() {
    int position = 90;
    assertEquals(90, gameController.handleOvershoot(position));
  }

  @Test
  void testHandleOvershootBeyondLimit() {
    int position = 95;
    assertEquals(85, gameController.handleOvershoot(position));
  }

  @Test
  void testHandleOvershootNegativePosition() {
    assertThrows(IllegalArgumentException.class, () -> gameController.handleOvershoot(-5));
  }

  @Test
  void testHandleTileAction() {
    testPlayer.setPosition(2);
    gameController.handleTileAction(testPlayer, 2);
    assertEquals(40, testPlayer.getPosition());
  }

  @Test
  void testHandleTileActionNullPlayer() {
    assertThrows(IllegalArgumentException.class, () -> gameController.handleTileAction(null, 5));
  }

  @Test
  void testHandleTileActionNegativePosition() {
    assertThrows(IllegalArgumentException.class,
        () -> gameController.handleTileAction(testPlayer, -5));
  }
}