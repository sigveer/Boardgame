package com.gruppe24.boardgames.laddergame.models.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the {@code SpecialBoard} class.
 * This class is responsible for testing the special board implementation.
 */
class SpecialBoardTest {

  private SpecialBoard specialBoard;

  /**
   * Sets up the test environment by initializing a new instance of {@code SpecialBoard}.
   */
  @BeforeEach
  void setUp() {
    specialBoard = new SpecialBoard();
  }

  /**
   * Tests the initialization of the special board.
   * This test checks if the special board is initialized correctly with specific ladder
   * configurations.
   */
  @Test
  void testSpecialBoardInitialization() {
    assertInstanceOf(FrozenTile.class, specialBoard.getTile(34));
    assertInstanceOf(FrozenTile.class, specialBoard.getTile(78));
    assertInstanceOf(RandomTeleportTile.class, specialBoard.getTile(50));
  }

  @Test
  void testLaddersInitialization() {
    // Test ladder up tiles
    assertInstanceOf(LadderUpTile.class, specialBoard.getTile(2));
    assertEquals(40, ((LadderUpTile) specialBoard.getTile(2)).getDestination());

    assertInstanceOf(LadderUpTile.class, specialBoard.getTile(49));
    assertEquals(79, ((LadderUpTile) specialBoard.getTile(49)).getDestination());

    assertInstanceOf(LadderUpTile.class, specialBoard.getTile(68));
    assertEquals(85, ((LadderUpTile) specialBoard.getTile(68)).getDestination());

    // Test ladder down tiles
    assertInstanceOf(LadderDownTile.class, specialBoard.getTile(24));
    assertEquals(5, ((LadderDownTile) specialBoard.getTile(24)).getDestination());

    assertInstanceOf(LadderDownTile.class, specialBoard.getTile(64));
    assertEquals(27, ((LadderDownTile) specialBoard.getTile(64)).getDestination());

    assertInstanceOf(LadderDownTile.class, specialBoard.getTile(87));
    assertEquals(70, ((LadderDownTile) specialBoard.getTile(87)).getDestination());
  }
}