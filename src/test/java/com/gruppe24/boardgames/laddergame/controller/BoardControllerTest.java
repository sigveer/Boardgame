package com.gruppe24.boardgames.laddergame.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
  void testBoardControllerConstructor() {
    BoardController boardType = new BoardController(BoardType.CLASSIC);

    Board board = boardType.getBoard();

    assertNotNull(board);
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

}