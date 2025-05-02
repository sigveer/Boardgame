package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardFactory;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.boardgames.laddergame.models.board.tiles.Tile;

/**
 * BoardController is a class that manages the game board interactions. It handles the game logic,
 * including player movement, tile actions, and win conditions.
 */
public class BoardController {

  private final Board currentBoard;
  private static final int WinCondition = 90;
  private int checkTileType = 0;
  private int specialTilePosition;

  /**
   * Constructor that initializes the game controller with a board type.
   */
  public BoardController() {
    this(BoardType.CLASSIC); // Default board
  }

  /**
   * Constructor that initializes the game controller with a board type.
   *
   * @param boardType the type of board to use
   */
  public BoardController(BoardType boardType) {
    if (boardType == null) {
      throw new IllegalArgumentException("Board type cannot be null");
    }
    this.currentBoard = BoardFactory.createBoard(boardType);
  }

  /**
   * Constructor that initializes the game controller with a custom board.
   *
   * @param customBoard the custom board to use
   */
  public BoardController(Board customBoard) {
    if (customBoard == null) {
      throw new IllegalArgumentException("Board cannot be null");
    }
    this.currentBoard = customBoard;
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
   * Method that handles the action of a tile. Indirectly takes value checkTileType. from abstract
   * class Tile.
   *
   * @param player      the player
   * @param newPosition the new position of the player
   */
  public void handleTileAction(Player player, int newPosition) {
    Tile tile = currentBoard.getTile(newPosition);
    tile.perform(player);
    checkTileType = tile.tileTypeNumber;
    specialTilePosition = tile.getPosition();
  }

  /**
   * Constructor that initializes the game controller with a board.
   *
   * @return board the board to use
   */
  public Board getBoard() {
    return currentBoard;
  }

  /**
   * Method that checks if a player has won the game.
   *
   * @param newPosition the new position of the player
   * @return true if the player has won, false otherwise
   */
  public boolean isWinningPosition(int newPosition) {
    return newPosition == WinCondition;
  }

  /**
   * Method that checks the type of tile the player is on.
   *
   * @return the type tile number.
   */
  public int getCheckTileType() {
    return checkTileType;
  }

  /**
   * Method that checks the position of the special tile.
   *
   * @return the position of the special tile
   */
  public int getSpecialTilePosition() {
    return specialTilePosition;
  }
}