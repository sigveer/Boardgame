package com.gruppe24.boardgames.laddergame.models.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.NormalTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code BoardTest} is a test class for the {@code Board} class.
 */
class BoardTest {

  private Board board;

  @BeforeEach
  public void setUp() {
    board = new Board();
  }

  @Test
  void testBoardInitialization() {
    assertEquals(90, board.tiles.length);
  }

  /**
   * Tests the {@code getTile} method on a normal tile in the {@code Board} class.
   */
  @Test
  void getNormalTile() {
    Tile tile = board.getTile(1);
    assertInstanceOf(NormalTile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void getLadderUpTile() {
    Tile tile = board.getTile(2);
    assertInstanceOf(LadderUpTile.class, tile);
    LadderUpTile ladderTile = (LadderUpTile) tile;
    assertEquals(40, ladderTile.getDestination());
  }


  /**
   * Tests the {@code getTile} method on a snake tile in the {@code Board} class.
   */
  @Test
  void getLadderDownTile() {
    Tile tile = board.getTile(24);
    assertInstanceOf(LadderDownTile.class, tile);
    LadderDownTile snakeTile = (LadderDownTile) tile;
    assertEquals(5, snakeTile.getDestination());
  }
}