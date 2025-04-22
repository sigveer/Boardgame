package com.gruppe24.boardgames.laddergame.models.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.NormalTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the {@code ClassicBoard} class.
 * This class is responsible for testing the classic board implementation.
 */
class ClassicBoardTest {

  private ClassicBoard classicBoard;

  /**
   * Sets up the test environment by initializing a new instance of {@code ClassicBoard}.
   */
  @BeforeEach
  void setUp() {
    classicBoard = new ClassicBoard();
  }

  /**
   * Tests that the classic board is initialized correctly.
   */
  @Test
  void testClassicBoardInitialization() {
    assertEquals("Classic LadderGame", classicBoard.getName());
    assertEquals(90, classicBoard.tiles.length);

    // Verify no special tiles exist
    assertFalse(classicBoard.getTile(34) instanceof FrozenTile);
    assertFalse(classicBoard.getTile(50) instanceof RandomTeleportTile);
    assertInstanceOf(NormalTile.class, classicBoard.getTile(34));
  }

  /**
   * Tests the ladders initialization in the classic board.
   */
  @Test
  void testLaddersInitialization() {
    // Test ladder up tiles
    assertInstanceOf(LadderUpTile.class, classicBoard.getTile(2));
    assertEquals(40, ((LadderUpTile) classicBoard.getTile(2)).getDestination());

    assertInstanceOf(LadderUpTile.class, classicBoard.getTile(49));
    assertEquals(79, ((LadderUpTile) classicBoard.getTile(49)).getDestination());

    assertInstanceOf(LadderUpTile.class, classicBoard.getTile(68));
    assertEquals(85, ((LadderUpTile) classicBoard.getTile(68)).getDestination());

    // Test ladder down tiles
    assertInstanceOf(LadderDownTile.class, classicBoard.getTile(24));
    assertEquals(5, ((LadderDownTile) classicBoard.getTile(24)).getDestination());

    assertInstanceOf(LadderDownTile.class, classicBoard.getTile(64));
    assertEquals(27, ((LadderDownTile) classicBoard.getTile(64)).getDestination());

    assertInstanceOf(LadderDownTile.class, classicBoard.getTile(87));
    assertEquals(70, ((LadderDownTile) classicBoard.getTile(87)).getDestination());
  }
}