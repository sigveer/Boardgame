package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardFactory;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;

/**
 * BoardController is a class that manages the game board interactions. It handles the game logic,
 * including player movement, tile actions, and win conditions.
 */
public class BoardController {

  private final Board board;

  /**
   * Constructor that initializes the game controller with a board type.
   */
  public BoardController() {
    this(BoardType.CLASSIC);
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
    this.board = BoardFactory.createBoard(boardType);
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
    this.board = customBoard;
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
   * Checks if a position is a winning position.
   *
   * @param position the position to check
   * @return true if the position is winning, false otherwise
   */
  public boolean isWinningPosition(int position) {
    return position == 90;
  }

}