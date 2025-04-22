package com.gruppe24.boardgames.laddergame.models.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for the {@code BoardFactory} class.
 * This class is responsible for testing the board factory implementation.
 */
class BoardFactoryTest {

  /**
   * Tests the creation of a classic board using the {@code BoardFactory}.
   * This test checks if the factory correctly creates an instance of {@code ClassicBoard}.
   */
  @Test
  void testCreateClassicBoard() {
    Board board = BoardFactory.createBoard(BoardType.CLASSIC);
    assertInstanceOf(ClassicBoard.class, board);
    assertEquals("Classic LadderGame", board.getName());
  }

  /**
   * Tests the creation of a special board using the {@code BoardFactory}.
   * This test checks if the factory correctly creates an instance of {@code SpecialBoard}.
   */
  @Test
  void testCreateSpecialBoard() {
    Board board = BoardFactory.createBoard(BoardType.SPECIAL);
    assertInstanceOf(SpecialBoard.class, board);
  }

  /**
   * Tests the creation of a board with an invalid type using the {@code BoardFactory}.
   * This test checks if the factory throws an exception when an invalid board type is provided.
   */
  @Test
  void testNullBoardType() {
    assertThrows(IllegalArgumentException.class, () -> {
      BoardFactory.createBoard(null);
    });
  }
}