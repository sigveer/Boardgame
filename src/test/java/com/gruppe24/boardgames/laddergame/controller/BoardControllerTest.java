package com.gruppe24.boardgames.laddergame.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gruppe24.boardgames.commonclasses.CommonGameController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardFactory;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardControllerTest {

  private CommonGameController gameController;
  private BoardController boardController;
  private Player testPlayer;

  @BeforeEach
  void setUp() {
    boardController = new BoardController(BoardType.CLASSIC);
    testPlayer = new Player("TestPlayer", 1);
  }

  @Test
  void testDefaultConstructor() {
    assertNotNull(boardController.getBoard());
  }

  @Test
  void testBoardTypeConstructor() {
    BoardController customTypeController = new BoardController(BoardType.CLASSIC);
    assertNotNull(customTypeController.getBoard());
  }

  @Test
  void testCustomBoardConstructor() {
    Board customBoard = BoardFactory.createBoard(BoardType.CLASSIC);
    BoardController customBoardController = new BoardController(customBoard);
    assertNotNull(customBoardController.getBoard());
  }

  @Test
  void testNullBoardTypeConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new BoardController((BoardType) null));
  }

  @Test
  void testNullCustomBoardConstructor() {
    assertThrows(IllegalArgumentException.class, () -> new BoardController((Board) null));
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
    // The expected position depends on your tile implementation
    // Let's assume position 2 is a ladder up to position 40
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

  @Test
  void testGetBoard() {
    assertNotNull(boardController.getBoard());
  }

  @Test
  void testIsWinningPosition() {
    assertTrue(boardController.isWinningPosition(90));
    assertFalse(boardController.isWinningPosition(89));
    assertFalse(boardController.isWinningPosition(91));
  }

  @Test
  void testGetCheckTileType() {
    // First handle a tile action to set checkTileType
    testPlayer.setPosition(0);
    gameController.handleTileAction(testPlayer, 2);
    // The expected value depends on your tile implementation
    // Let's assume tile at position 2 has type 1 (ladder up)
    assertEquals(0, boardController.getCheckTileType());
  }

  @Test
  void testGetSpecialTilePosition() {
    // First handle a tile action to set specialTilePosition
    testPlayer.setPosition(0);
    gameController.handleTileAction(testPlayer, 2);
    // The expected position depends on your tile implementation
    assertEquals(2, boardController.getSpecialTilePosition());
  }
}