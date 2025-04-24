package com.gruppe24.boardgames.laddergame.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameControllerTest {

  private Player testPlayer;
  private GameController gameController;

  /**
   * Sets up the test fixture by creating a new board and player before each test.
   */
  @BeforeEach
  void setUp() {
    testPlayer = new Player("TestPlayer", Color.RED);
    gameController = new GameController();
  }

  /**
   * Tests the getBoard method returns a non-null board.
   */
  @Test
  void testGetBoard() {
    assertNotNull(gameController.getBoard());
  }

  /**
   * Tests the handleTileAction method of the GameController class.
   */
  @Test
  void handleTileAction() {
    testPlayer.setPosition(2);
    gameController.handleTileAction(testPlayer, 2);
    assertEquals(40, testPlayer.getPosition());

    testPlayer.setPosition(24);
    gameController.handleTileAction(testPlayer, 24);
    assertEquals(5, testPlayer.getPosition());
  }

  /**
   * Tests the handleTileAction method of the GameController class with ladder up tile.
   */
  @Test
  void testHandleLadderUpTileAction() {
    int oldPosition = testPlayer.getPosition();
    gameController.handleTileAction(testPlayer, 2);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(40, testPlayer.getPosition());
  }

  /**
   * Tests the handleTileAction method of the GameController class with ladder down tile.
   */
  @Test
  void testHandleLadderDownTileAction() {
    int oldPosition = testPlayer.getPosition();
    gameController.handleTileAction(testPlayer, 24);
    assertNotEquals(oldPosition, testPlayer.getPosition());
    assertEquals(5, testPlayer.getPosition());
  }

  /**
   * Tests the checkAndHandleWin method of the GameController class when a player wins.
   */
  @Test
  void testCheckAndHandleWin() {
    testPlayer.setPosition(90);
    assertTrue(gameController.checkAndHandleWin(90));
  }

  /**
   * Tests the checkAndHandleWin method of the GameController class when a player does not win.
   */
  @Test
  void testCheckAndHandleNotWin() {
    testPlayer.setPosition(89);
    assertFalse(gameController.checkAndHandleWin(89));
  }

  /**
   * Tests the handleOvershoot method of the GameController class when a player overshoots.
   */
  @Test
  void testHandleOvershootWithOvershoot() {
    int newPosition = gameController.handleOvershoot(95);
    assertEquals(85, newPosition);
  }

  /**
   * Tests the handleOvershoot method of the GameController class when a player does not overshoot.
   */
  @Test
  void testHandleOvershootWithNotOvershoot() {
    int newPosition = gameController.handleOvershoot(95);
    assertEquals(85, newPosition);
  }

  /**
   * Tests the handlePlayerTurn method of the GameController class when a player rolls a 4.
   */
  @Test
  void handlePlayerTurn() {
    System.setIn(new ByteArrayInputStream("\n".getBytes()));
    int initialPosition = testPlayer.getPosition();
    gameController.handlePlayerTurn(testPlayer, 4);
    assertTrue(testPlayer.getPosition() > initialPosition);
  }

  /**
   * Tests the movePlayer method of the GameController class.
   */
  @Test
  void movePlayer() {
    testPlayer.setPosition(5);
    int sumDice = 2;
    gameController.movePlayer(testPlayer, sumDice);
    assertEquals(7, testPlayer.getPosition());
  }

  /**
   * Tests the getCheckTileType method of the GameController class.
   */
  @Test
  public void testGetCheckTileType() {
    // Test ladder up tile
    testPlayer.setPosition(0);
    gameController.handlePlayerTurn(testPlayer, 2);
    assertEquals(1, gameController.getCheckTileType());

    // Test ladder down tile
    testPlayer.setPosition(20);
    gameController.handlePlayerTurn(testPlayer, 4);
    assertEquals(2, gameController.getCheckTileType());

    // Test normal tile
    testPlayer.setPosition(3);
    gameController.handlePlayerTurn(testPlayer, 3);
    assertEquals(0, gameController.getCheckTileType());

    // Negative test - verify not a ladder up
    testPlayer.setPosition(3);
    gameController.handlePlayerTurn(testPlayer, 3);
    assertNotEquals(1, gameController.getCheckTileType());
  }

  /**
   * Tests the getSpecialTilePosition method of the GameController class.
   */
  @Test
  public void testGetSpecialTilePosition() {
    //Positive test
    gameController.handlePlayerTurn(testPlayer, 1);
    assertEquals(1, gameController.getSpecialTilePosition());
    //Negative test
    assertNotEquals(2, gameController.getSpecialTilePosition());
  }

  /**
   * Tests the textBasedCheckAndHandleWin method of the GameController class.
   */
  @Test
  public void testTextBasedCheckAndHandleWin() {
    //Positive test
    int winCondition = 90;
    assertTrue(gameController.textBasedCheckAndHandleWin(testPlayer, winCondition));
    //Negative test
    winCondition = 80;
    assertFalse(gameController.textBasedCheckAndHandleWin(testPlayer, winCondition));
  }

  /**
   * Tests the textBasedHandlePlayerTurn method.
   * This test uses a ByteArrayInputStream to simulate user input.
   */
  @Test
  void testTextBasedHandlePlayerTurn() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    System.setIn(new ByteArrayInputStream("\n".getBytes()));

    int initialPosition = testPlayer.getPosition();
    gameController.textBasedHandlePlayerTurn(testPlayer);

    assertTrue(testPlayer.getPosition() >= initialPosition);

    String output = outContent.toString();
    assertTrue(output.contains("rolled"));

    System.setOut(System.out);
    System.setIn(System.in);
  }

  /**
   * Tests the handlePlayerTurn method with specific dice values.
   */
  @Test
  void testHandlePlayerTurnWithSpecificValue() {
    testPlayer.setPosition(0);

    // Test with a value that should move to a ladder up tile
    gameController.handlePlayerTurn(testPlayer, 2);
    assertEquals(40, testPlayer.getPosition());

    // Reset and test with a value that moves to a ladder down tile
    testPlayer.setPosition(20);
    gameController.handlePlayerTurn(testPlayer, 4);
    assertEquals(5, testPlayer.getPosition());
  }

  /**
   * Tests the handleOvershoot method with exact win condition.
   */
  @Test
  void testHandleOvershootWithExactWin() {
    int newPosition = gameController.handleOvershoot(90);
    assertEquals(90, newPosition);
  }
}