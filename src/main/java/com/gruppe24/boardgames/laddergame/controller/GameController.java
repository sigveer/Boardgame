//package com.gruppe24.boardgames.laddergame.controller;
//
//import com.gruppe24.boardgames.laddergame.models.Dice;
//import com.gruppe24.boardgames.laddergame.models.board.Board;
//import com.gruppe24.boardgames.laddergame.models.board.BoardFactory;
//import com.gruppe24.boardgames.laddergame.models.board.BoardType;
//
///**
// * Class that handles the game logic and player turns.
// */
//public class GameController {
//
//  private final Board board;
//  private final Dice dice;
//  private static final int WinCondition = 90;
//  private int checkTileType = 0;
//  private int specialTilePosition;
//
//
//  /**
//   * Constructor that initializes the game controller with a board type.
//   *
//   * @param boardType the type of board to use
//   */
//  public GameController(BoardType boardType) {
//    if (boardType == null) {
//      throw new IllegalArgumentException("Parameter boardType cannot be empty");
//    }
//    this.board = BoardFactory.createBoard(boardType);
//    this.dice = new Dice(2);
//  }
//
//  /**
//   * Constructor that initializes the game controller with a custom board.
//   *
//   * @param customBoard the custom board to use
//   */
//  public GameController(Board customBoard) {
//    if (customBoard == null) {
//      throw new IllegalArgumentException("Parameter customBoard cannot be empty");
//    }
//    this.board = customBoard;
//    this.dice = new Dice(2);
//  }
//}
