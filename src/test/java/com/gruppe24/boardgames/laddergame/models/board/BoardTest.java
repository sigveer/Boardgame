package com.gruppe24.boardgames.laddergame.models.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.NormalTile;
import com.gruppe24.boardgames.commonclasses.AbstractTile;
import java.util.HashMap;
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
    assertEquals(90, board.abstractTiles.length);
    assertEquals("Classic LadderGame", board.getName());
    assertEquals("A classic game of Ladders with 90 tiles.", board.getDescription());
  }

  /**
   * Tests the {@code getTile} method on a normal tile in the {@code Board} class.
   */
  @Test
  void getNormalTile() {
    AbstractTile abstractTile = board.getTile(1);
    assertInstanceOf(NormalTile.class, abstractTile);
    assertEquals(1, abstractTile.getPosition());
  }

  /**
   * Tests the {@code getTile} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void getLadderUpTile() {
    AbstractTile abstractTile = board.getTile(2);
    assertInstanceOf(LadderUpTile.class, abstractTile);
    LadderUpTile ladderTile = (LadderUpTile) abstractTile;
    assertEquals(40, ladderTile.getDestination());
    assertEquals(2, ladderTile.getPosition());
  }

  /**
   * Tests the {@code getTile} method on a snake tile in the {@code Board} class.
   */
  @Test
  void getLadderDownTile() {
    AbstractTile abstractTile = board.getTile(24);
    assertInstanceOf(LadderDownTile.class, abstractTile);
    LadderDownTile snakeTile = (LadderDownTile) abstractTile;
    assertEquals(5, snakeTile.getDestination());
    assertEquals(24, snakeTile.getPosition());
  }

  /**
   * Tests the {@code getLadderUp} and {@code getLadderDown} methods.
   */
  @Test
  void testGetLadders() {
    HashMap<Integer, Integer> ladderUp = board.getLadderUp();
    HashMap<Integer, Integer> ladderDown = board.getLadderDown();

    assertNotNull(ladderUp);
    assertNotNull(ladderDown);

    // Test some standard ladder up positions
    assertEquals(40, ladderUp.get(2));
    assertEquals(10, ladderUp.get(8));
    assertEquals(79, ladderUp.get(49));
    assertEquals(85, ladderUp.get(68));

    // Test some standard ladder down positions
    assertEquals(5, ladderDown.get(24));
    assertEquals(27, ladderDown.get(64));
    assertEquals(70, ladderDown.get(87));
  }
}