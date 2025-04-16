package com.gruppe24.boardgames.laddergame.models.board;

/**
 * Classic Snakes and Ladders board implementation.
 */
class ClassicBoard extends Board {

  /**
   * Initializes the ladders on the board.
   */
  public ClassicBoard() {
    initializeLadders();
    initializeTiles();
  }
}