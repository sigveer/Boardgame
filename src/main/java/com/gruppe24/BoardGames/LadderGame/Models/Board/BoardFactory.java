package com.gruppe24.BoardGames.LadderGame.Models.Board;

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
    return switch (boardType) {
      case CLASSIC -> createClassicBoard();
      case SPECIAL -> createSpecialBoard();
      default -> throw new IllegalArgumentException("Unknown board type: " + boardType);
    };
  }

  /**
   * Creates a classic Snakes and Ladders board.
   *
   * @return A classic snakes and ladders board
   */
  private static Board createClassicBoard() {
    return new ClassicBoard();
  }

  /**
   * Creates a special board with additional tile types.
   *
   * @return A board with special tiles
   */
  private static Board createSpecialBoard() {
    return new SpecialBoard();
  }
}