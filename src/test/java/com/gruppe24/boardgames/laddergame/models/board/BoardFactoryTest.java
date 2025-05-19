package com.gruppe24.boardgames.laddergame.models.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import org.junit.jupiter.api.Test;

/**
 * Test class for the {@code BoardFactory} class. This class is responsible for testing the board
 * factory implementation.
 */
class BoardFactoryTest {

  /**
   * Tests the creation of a classic board using the {@code BoardFactory}. This test checks if the
   * factory correctly creates an instance of {@code Board}.
   */
  @Test
  void testCreateClassicBoard() {
    Board board = BoardFactory.createBoard(BoardType.CLASSIC);
    assertNotNull(board);
    assertEquals("Classic LadderGame", board.getName());
    assertEquals("A classic game of Ladders with 90 tiles.", board.getDescription());
    assertEquals(91, board.getTiles().length);
  }

  /**
   * Tests the creation of a special board using the {@code BoardFactory}. This test checks if the
   * factory correctly creates an instance of {@code Board}.
   */
  @Test
  void testCreateSpecialBoard() {
    Board board = BoardFactory.createBoard(BoardType.SPECIAL);
    assertNotNull(board);
    assertEquals("Classic LadderGame", board.getName());
    assertEquals("A classic game of Ladders with 90 tiles.", board.getDescription());
    assertEquals(91, board.getTiles().length);

    // Verify special tiles are initialized
    assertInstanceOf(FrozenTile.class, board.getTile(34));
    assertInstanceOf(RandomTeleportTile.class, board.getTile(11));
  }

  /**
   * Tests the creation of a board with a null board type. This test checks if the factory throws an
   * exception when a null board type is provided.
   */
  @Test
  void testNullBoardType() {
    assertThrows(IllegalArgumentException.class, () -> BoardFactory.createBoard(null),
        "Parameter boardType cannot be empty");
  }
}