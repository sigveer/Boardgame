package com.gruppe24.boardgames.laddergame.models.board;

import static com.gruppe24.boardgames.laddergame.models.board.BoardFactory.createBoard;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.gruppe24.boardgames.commonclasses.CommonTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.NormalTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.WinningTile;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BoardTest {

  private Board board;

  @BeforeEach
  public void setUp() {
    board = createBoard(BoardType.SPECIAL);
  }

  @Test
  void testBoardInitialization() {
    assertEquals(91, board.getTiles().length); // Includes tile 0
    assertEquals("Classic LadderGame", board.getName());
    assertEquals("A classic game of Ladders with 90 tiles.", board.getDescription());
  }

  @Test
  void testGetNormalTile() {
    CommonTile tile = board.getTile(1);
    assertInstanceOf(NormalTile.class, tile);
    assertEquals(1, tile.getPosition());
  }

  @Test
  void testGetLadderUpTile() {
    CommonTile tile = board.getTile(2);
    assertInstanceOf(LadderUpTile.class, tile);
    LadderUpTile ladderTile = (LadderUpTile) tile;
    assertEquals(40, ladderTile.getDestination());
    assertEquals(2, ladderTile.getPosition());
  }

  @Test
  void testGetLadderDownTile() {
    CommonTile tile = board.getTile(24);
    assertInstanceOf(LadderDownTile.class, tile);
    LadderDownTile ladderTile = (LadderDownTile) tile;
    assertEquals(5, ladderTile.getDestination());
    assertEquals(24, ladderTile.getPosition());
  }

  @Test
  void testGetWinningTile() {
    CommonTile tile = board.getTile(90);
    assertInstanceOf(WinningTile.class, tile);
    assertEquals(90, tile.getPosition());
  }

  @Test
  void testGetFrozenTile() {
    CommonTile tile = board.getTile(34);
    assertInstanceOf(FrozenTile.class, tile);
    assertEquals(34, tile.getPosition());
  }

  @Test
  void testGetRandomTeleportTile() {
    CommonTile tile = board.getTile(11);
    assertInstanceOf(RandomTeleportTile.class, tile);
    assertEquals(11, tile.getPosition());
  }

  @Test
  void testGetTileTypeForDifferentTiles() {
    //LadderUpTile
    assertEquals(1, board.getTileType(2));

    //LadderDownTile
    assertEquals(2, board.getTileType(24));

    //NormalTile
    assertEquals(0, board.getTileType(1));

    //FrozenTile
    assertEquals(4, board.getTileType(34));

    //RandomTeleportTile
    assertEquals(3, board.getTileType(11));

    //WinningTile
    assertEquals(-3, board.getTileType(90));
  }

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

  @Test
  void testCustomBoardInitialization() {
    HashMap<Integer, Integer> ladderUp = new HashMap<>();
    HashMap<Integer, Integer> ladderDown = new HashMap<>();
    HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
    HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();
    HashMap<Integer, Boolean> winningTile = new HashMap<>();

    ladderUp.put(1, 10);
    ladderDown.put(20, 5);
    frozenTiles.put(15, true);
    randomTeleportTiles.put(25, true);
    winningTile.put(30, true);

    Board customBoard = new Board(ladderUp, ladderDown, winningTile, frozenTiles,
        randomTeleportTiles, "Custom Board", "Custom Description");

    assertEquals("Custom Board", customBoard.getName());
    assertEquals("Custom Description", customBoard.getDescription());
    assertInstanceOf(LadderUpTile.class, customBoard.getTile(1));
    assertInstanceOf(LadderDownTile.class, customBoard.getTile(20));
    assertInstanceOf(FrozenTile.class, customBoard.getTile(15));
    assertInstanceOf(RandomTeleportTile.class, customBoard.getTile(25));
    assertInstanceOf(WinningTile.class, customBoard.getTile(30));
  }

  @Test
  void testInvalidCustomBoardInitialization() {
    assertThrows(IllegalArgumentException.class, () -> new Board(null, new HashMap<>(),
        new HashMap<>(), new HashMap<>(), new HashMap<>(), "Name", "Description"));
    assertThrows(IllegalArgumentException.class, () -> new Board(new HashMap<>(), null,
        new HashMap<>(), new HashMap<>(), new HashMap<>(), "Name", "Description"));
    assertThrows(IllegalArgumentException.class, () -> new Board(new HashMap<>(), new HashMap<>(),
        null, new HashMap<>(), new HashMap<>(), "Name", "Description"));
    assertThrows(IllegalArgumentException.class, () -> new Board(new HashMap<>(), new HashMap<>(),
        new HashMap<>(), null, new HashMap<>(), "Name", "Description"));
    assertThrows(IllegalArgumentException.class, () -> new Board(new HashMap<>(), new HashMap<>(),
        new HashMap<>(), new HashMap<>(), null, "Name",
        "Description"));
    assertThrows(IllegalArgumentException.class, () -> new Board(new HashMap<>(), new HashMap<>(),
        new HashMap<>(), new HashMap<>(), new HashMap<>(), "", "Description"));
    assertThrows(IllegalArgumentException.class, () -> new Board(new HashMap<>(), new HashMap<>(),
        new HashMap<>(), new HashMap<>(), new HashMap<>(), "Name", ""));
  }
}