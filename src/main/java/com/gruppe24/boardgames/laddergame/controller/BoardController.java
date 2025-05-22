package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.board.Board;
import com.gruppe24.boardgames.laddergame.models.board.BoardFactory;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.exeptions.InvalidBoardException;

/**
 * Controller class that manages the game board interactions in ladderGame. It handles:
 * <li>game logic</li>
 * <li>player movment</li>
 * <li>tile actions</li>
 * <li>win conditions</li>
 */
public class BoardController {

  private final Board board;

  /**
   * Constructor that initializes the game controller with a board type.
   *
   * @param boardType the type of board to use
   */
  public BoardController(BoardType boardType) {
    if (boardType == null) {
      throw new InvalidBoardException();
    }
    this.board = BoardFactory.createBoard(boardType);
  }

  /**
   * JSON-Constructor that initializes the game controller with a custom board.
   *
   * @param customBoard the custom board to use
   */
  public BoardController(Board customBoard) {
    if (customBoard == null) {
      throw new InvalidBoardException();
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
}