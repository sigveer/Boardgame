package com.gruppe24.boardgames.laddergame.models.board;

/**
 * Factory class for creating different types of game boards.
 */
public class BoardFactory {

  /**
   * Creates a board based on the specified board type.
   *
   * @param boardType The type of board to create
   * @return A new board of the specified type
   */
  public static Board createBoard(BoardType boardType) {
    if (boardType == null) {
      throw new IllegalArgumentException("Parameter boardType cannot be empty");
    }
    return switch (boardType) {
      case CLASSIC -> createClassicBoard();
      case SPECIAL -> createSpecialBoard();
    };
  }

  /**
   * Creates a classic Snakes and Ladders board.
   *
   */
  private static Board createClassicBoard() {
    Board board = new Board();
    board.initializeLadders();
    board.initializeWinningTile();
    board.initializeTiles();
    return board;
  }

  /**
   * Creates a special board with additional tile types.
   *
   */
  private static Board createSpecialBoard() {
    Board board = new Board();
    board.initializeLadders();
    board.initializeSpecialTiles();
    board.initializeWinningTile();
    board.initializeTiles();
    return board;
  }
}