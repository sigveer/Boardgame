package com.gruppe24.boardgames.laddergame.models;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code BoardTest} is a test class for the {@code Board} class.
 */
class BoardTest {

  private Board board;

  @BeforeEach
  public void beforeEach() {
    board = new Board();
  }

  /**
   * Tests the {@code getTile} method on a normal tile in the {@code Board} class.
   */
  @Test
  void getNormalTile() {
    Tile tile = board.getTile(2);
    assertInstanceOf(Tile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void getLadderUpTile() {
    Tile tile = board.getTile(1);
    assertInstanceOf(LadderUpTile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a snake tile in the {@code Board} class.
   */
  @Test
  void getLadderDownTile() {
    Tile tile = board.getTile(24);
    assertInstanceOf(LadderDownTile.class, tile);
  }
}