package com.gruppe24.BoardGames.LadderGame.Models;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.Tile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.TileAction;
import com.gruppe24.BoardGames.LadderGame.Controller.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code BoardTest} is a test class for the {@code Board} class.
 */
class BoardTest {


  private Board board;
  private Player testPlayer;


  /**
   * Sets up the test fixture by creating a new board and player before each test.
   */
  @BeforeEach
  void setUp() {
    board = new Board();
    testPlayer = new Player("TestPlayer");
  }


  /**
   * Tests the {@code getTile} method on a normal tile in the {@code Board} class.
   */
  @Test
  void getNormalTile() {
    TileAction tile = board.getTile(1);
    assertInstanceOf(Tile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void getLadderTile() {
    TileAction tile = board.getTile(2);
    assertInstanceOf(LadderTile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a snake tile in the {@code Board} class.
   */
  @Test
  void getSnakeTile() {
    TileAction tile = board.getTile(16);
    assertInstanceOf(SnakeTile.class, tile);
  }


  /**
   * Tests the {@code handleTileAction} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void testHandleLadderTileAction() {
    int oldPosition = testPlayer.getPosition();
    board.handleTileAction(testPlayer, 2);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(39, testPlayer.getPosition());
  }


  /**
   * Tests the {@code handleTileAction} method on a snake tile in the {@code Board} class.
   */
  @Test
  void testHandleSnakeTileAction() {
    int oldPosition = testPlayer.getPosition();
    board.handleTileAction(testPlayer, 16);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(6, testPlayer.getPosition());
  }


  /**
   * Tests the {@code checkAndHandleWin} method when the player wins in the {@code Board} class.
   */
  @Test
  void testCheckAndHandleWin_Win() {
    testPlayer.setPosition(100);
    assertTrue(board.checkAndHandleWin(testPlayer, 100));
  }


  /**
   * Tests the {@code checkAndHandleWin} method when the player does not win in the {@code Board} class.
   */
  @Test
  void testCheckAndHandleWin_NotWin() {
    testPlayer.setPosition(99);
    assertFalse(board.checkAndHandleWin(testPlayer, 99));
  }


  /**
   * Tests the {@code handleOvershoot} method when the player overshoots in the {@code Board} class.
   */
  @Test
  void testHandleOvershoot_Overshoot() {
    int newPosition = board.handleOvershoot(105);
    assertEquals(95, newPosition);
  }


  /**
   * Tests the {@code handleOvershoot} method when the player does not overshoot in the {@code Board} class.
   */
  @Test
  void testHandleOvershoot_NotOvershoot() {
    int newPosition = board.handleOvershoot(95);
    assertEquals(95, newPosition);
  }
}