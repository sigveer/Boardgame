package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardFactory;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.boardgames.laddergame.models.board.tile.Tile;
import com.gruppe24.utils.Steps;

/**
 *
 */
public class GameController {

  private Board board;
  private final Dice dice;
  private static final int WinCondition = 90;
  private int checkTileType = 0;
  private int specialTilePosition;

  public GameController() {
    this(BoardType.CLASSIC); // Default to classic board
  }

  public GameController(BoardType boardType) {
    if(boardType == null){
      throw new IllegalArgumentException("Parameter boardType cannot be empty");
    }
    this.board = BoardFactory.createBoard(boardType);
    this.dice = new Dice(2);
  }

  public Board getBoard() {
    return board;
  }

  /**
   * Method that handles the player's turn.
   */
  public void handlePlayerTurn(Player player, int diceValue) {
    movePlayer(player, diceValue);
  }

  /**
   * Method that moves the player.
   *
   * @param sumDice the sum of the dice
   */
  public void movePlayer(Player player, int sumDice) {
    int newPosition = player.getPosition() + sumDice;
    newPosition = handleOvershoot(newPosition);
    player.setPosition(newPosition);
    handleTileAction(player, newPosition);
  }


  /**
   * Method that handles overshoot of the player.
   *
   * @param newPosition the new position of the player
   * @return the new position of the player
   */
  public int handleOvershoot(int newPosition) {
    if (newPosition > WinCondition) {
      int overshoot = newPosition - WinCondition;
      newPosition = WinCondition - overshoot;
    } return newPosition;
  }


  /**
   * Method that handles the action of a tile. Indirectly takes value checkTileType.
   * from abstract class Tile.
   *
   * @param player the player
   * @param newPosition the new position of the player
   */
  public void handleTileAction(Player player, int newPosition) {
    Tile tile = board.getTile(newPosition);
    tile.perform(player);
    checkTileType = tile.checkTileType;
    specialTilePosition = tile.getPosition();
  }

  /**
   * Method that checks if a player has won the game.
   *
   * @param newPosition the new position of the player
   * @return true if the player has won, false otherwise
   */
  public boolean checkAndHandleWin(int newPosition) {
    return newPosition == WinCondition;
  }

  public int getCheckTileType(){
    return checkTileType;
  }

  public int getSpecialTilePosition(){
    return specialTilePosition;
  }

  //----------------CONTROLLERS FOR TEXTBASED LADDERGAME--------------------//
  /**
   * Method that checks if a player has won the game.
   *
   * @param p the player
   * @param newPosition the new position of the player
   * @return true if the player has won, false otherwise
   */
  public boolean textBasedCheckAndHandleWin(Player p, int newPosition) {
    if (newPosition == WinCondition) {
      System.out.println(p.getColoredName() + " won the game!");
      return true;
    }
    return false;
  }

  /**
   * Method that handles the player's turn.
   */
  public void textBasedHandlePlayerTurn(Player player) {
    Steps.pressEnterToContinue();
    int sumDice = dice.rollSum();

    movePlayer(player, sumDice);

    System.out.println(player.getColoredName() + " rolled " + sumDice);
    if(getCheckTileType() == 1){
      System.out.println("A ladder!");
    }
    else if(getCheckTileType() == 2){
      System.out.println("A snake...");
    }
    System.out.println(player.getColoredName() + " is now on tile " + player.getPosition());
  }


}
