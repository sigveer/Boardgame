package com.gruppe24.BoardGames.LadderGame.Controller;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import java.io.ByteArrayInputStream;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameControllerTest {

  private Player testPlayer;
  private GameController GC;

  /**
   * Sets up the test fixture by creating a new board and player before each test.
   */
  @BeforeEach
  void setUp() {
    testPlayer = new Player("TestPlayer", Color.RED);
    GC = new GameController();
  }


  /**
   *
   */
  @Test
  void handleTileAction() {
    testPlayer.setPosition(1);
    GC.handleTileAction(testPlayer, 1);
    assertEquals(40, testPlayer.getPosition());

    testPlayer.setPosition(24);
    GC.handleTileAction(testPlayer, 24);
    assertEquals(5, testPlayer.getPosition());
  }

  /**
   *
   */
  @Test
  void testHandleLadderTileAction() {
    int oldPosition = testPlayer.getPosition();
    GC.handleTileAction(testPlayer, 1);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(40, testPlayer.getPosition());
  }


  /**
   *
   */
  @Test
  void testHandleSnakeTileAction() {
    int oldPosition = testPlayer.getPosition();
    GC.handleTileAction(testPlayer, 24);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(5, testPlayer.getPosition());
  }


  /**
   *
   */
  @Test
  void testCheckAndHandleWin_Win() {
    testPlayer.setPosition(90);
    assertTrue(GC.checkAndHandleWin(90));
  }


  /**
   *
   */
  @Test
  void testCheckAndHandleWin_NotWin() {
    testPlayer.setPosition(89);
    assertFalse(GC.checkAndHandleWin(89));
  }


  /**
   *
   */
  @Test
  void testHandleOvershoot_Overshoot() {
    int newPosition = GC.handleOvershoot(95);
    assertEquals(85, newPosition);
  }


  /**
   *
   */
  @Test
  void testHandleOvershoot_NotOvershoot() {
    int newPosition = GC.handleOvershoot(95);
    assertEquals(85, newPosition);
  }


  /**
   *
   */
  @Test
  void handlePlayerTurn() {
    System.setIn(new ByteArrayInputStream("\n".getBytes()));
    int initialPosition = testPlayer.getPosition();
    GC.handlePlayerTurn(testPlayer, 4);
    assertTrue(testPlayer.getPosition() > initialPosition);
  }


  /**
   *
   */
  @Test
  void movePlayer() {
    testPlayer.setPosition(5);
    int sumDice = 2;
    GC.movePlayer(testPlayer, sumDice);
    assertEquals(7, testPlayer.getPosition());
  }

  @Test
  void testIsFrozen(){
    assertTrue(GC.isFrozen(32));
    assertTrue(GC.isFrozen(59));
    assertFalse(GC.isFrozen(1));
  }

  @Test
  public void testGetCheckTile(){
   //Positive test
    GC.handlePlayerTurn(testPlayer, 1);
    assertEquals(1,GC.getCheckTileType()); //1 means ladderUp
    GC.handlePlayerTurn(testPlayer, 24);
    assertEquals(2, GC.getCheckTileType()); //2 means ladderDown
    GC.handlePlayerTurn(testPlayer,3);
    assertEquals(0, GC.getCheckTileType()); //0 just a normal tile

    //Negativ test
    assertNotEquals(1, GC.getCheckTileType());
  }

  @Test
  public void testGetSpecialTilePosition(){
    //Positive test
    GC.handlePlayerTurn(testPlayer,1);
    assertEquals(1,GC.getSpecialTilePosition());
    //Negative test
    assertNotEquals(2,GC.getSpecialTilePosition());
  }


  @Test
  public void testTextBasedCheckAndHandleWin(){
    //Positive test
    int WinCondition = 90;
    assertTrue(GC.textBasedCheckAndHandleWin(testPlayer,WinCondition));
    //Negative test
    WinCondition = 80;
    assertFalse(GC.textBasedCheckAndHandleWin(testPlayer,WinCondition));
  }
}