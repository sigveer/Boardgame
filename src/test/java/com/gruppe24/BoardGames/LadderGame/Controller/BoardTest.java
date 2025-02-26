package com.gruppe24.BoardGames.LadderGame.Controller;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.Tile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.TileAction;
import java.io.ByteArrayInputStream;
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
   * Tests the {@code handleTileAction} method in the {@code Board} class.
   */
  @Test
  void handleTileAction() {
    testPlayer.setPosition(1);
    board.handleTileAction(testPlayer, 1);
    assertEquals(40, testPlayer.getPosition());

    testPlayer.setPosition(24);
    board.handleTileAction(testPlayer, 24);
    assertEquals(5, testPlayer.getPosition());
  }


  /**
   * Tests the {@code getTile} method on a normal tile in the {@code Board} class.
   */
  @Test
  void getNormalTile() {
    TileAction tile = board.getTile(2);
    assertInstanceOf(Tile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void getLadderTile() {
    TileAction tile = board.getTile(1);
    assertInstanceOf(LadderTile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a snake tile in the {@code Board} class.
   */
  @Test
  void getSnakeTile() {
    TileAction tile = board.getTile(24);
    assertInstanceOf(SnakeTile.class, tile);
  }


  /**
   * Tests the {@code handleTileAction} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void testHandleLadderTileAction() {
    int oldPosition = testPlayer.getPosition();
    board.handleTileAction(testPlayer, 1);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(40, testPlayer.getPosition());
  }


  /**
   * Tests the {@code handleTileAction} method on a snake tile in the {@code Board} class.
   */
  @Test
  void testHandleSnakeTileAction() {
    int oldPosition = testPlayer.getPosition();
    board.handleTileAction(testPlayer, 24);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(5, testPlayer.getPosition());
  }


  /**
   * Tests the {@code checkAndHandleWin} method when the player wins in the {@code Board} class.
   */
  @Test
  void testCheckAndHandleWin_Win() {
    testPlayer.setPosition(90);
    assertTrue(board.checkAndHandleWin(testPlayer, 90));
  }


  /**
   * Tests the {@code checkAndHandleWin} method when the player does not win in the {@code Board} class.
   */
  @Test
  void testCheckAndHandleWin_NotWin() {
    testPlayer.setPosition(89);
    assertFalse(board.checkAndHandleWin(testPlayer, 89));
  }


  /**
   * Tests the {@code handleOvershoot} method when the player overshoots in the {@code Board} class.
   */
  @Test
  void testHandleOvershoot_Overshoot() {
    int newPosition = board.handleOvershoot(95);
    assertEquals(85, newPosition);
  }


  /**
   * Tests the {@code handleOvershoot} method when the player does not overshoot in the {@code Board} class.
   */
  @Test
  void testHandleOvershoot_NotOvershoot() {
    int newPosition = board.handleOvershoot(95);
    assertEquals(85, newPosition);
  }


  /**
   * Tests the {@code handlePlayerTurn} method in the {@code Board} class.
   */
  @Test
  void handlePlayerTurn() {
    System.setIn(new ByteArrayInputStream("\n".getBytes()));
    int initialPosition = testPlayer.getPosition();
    board.handlePlayerTurn(testPlayer);
    assertTrue(testPlayer.getPosition() > initialPosition);
  }


  /**
   * Tests the {@code movePlayer} method in the {@code Board} class.
   */
  @Test
  void movePlayer() {
    testPlayer.setPosition(5);
    int sumDice = 2;
    board.movePlayer(testPlayer, sumDice);
    assertEquals(7, testPlayer.getPosition());
  }
}