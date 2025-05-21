package com.gruppe24.boardgames.laddergame.models.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import org.junit.jupiter.api.Test;


class BoardFactoryTest {


  @Test
  void testCreateClassicBoard() {
    Board board = BoardFactory.createBoard(BoardType.CLASSIC);
    assertNotNull(board);
    assertEquals("Classic LadderGame", board.getName());
    assertEquals("A classic game of Ladders with 90 tiles.", board.getDescription());
    assertEquals(91, board.getTiles().length);
  }


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

  @Test
  void testNullBoardType() {
    assertThrows(IllegalArgumentException.class, () -> BoardFactory.createBoard(null),
        "Parameter boardType cannot be empty");
  }
}