package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardFactory;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.boardgames.laddergame.models.board.tiles.Tile;

/**
 * Class that handles the game logic and player turns.
 */
public class GameController {

  private final Board board;
  private static final int WinCondition = 90;
  private int checkTileType = 0;
  private int specialTilePosition;

  /**
   * Constructor that initializes the game controller with a board type.
   */
  public GameController() {
    this(BoardType.CLASSIC); // Default to classic board
  }

  /**
   * Constructor that initializes the game controller with a board type.
   *
   * @param boardType the type of board to use
   */
  public GameController(BoardType boardType) {
    if (boardType == null) {
      throw new IllegalArgumentException("Parameter boardType cannot be empty");
    }
    this.board = BoardFactory.createBoard(boardType);
  }

  /**
   * Constructor that initializes the game controller with a board.
   *
   * @return board the board to use
   */
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
    }
    return newPosition;
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

  public int getCheckTileType() {
    return checkTileType;
  }

  public int getSpecialTilePosition() {
    return specialTilePosition;
  }

}
