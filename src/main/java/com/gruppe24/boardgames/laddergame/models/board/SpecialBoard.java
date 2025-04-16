package com.gruppe24.boardgames.laddergame.models.board;

/**
 * Special board implementation with different ladder configurations.
 */
class SpecialBoard extends Board {

  /**
   * Initializes the special board with specific ladder configurations.
   */
  public SpecialBoard() {
    initializeLadders();
    initializeSpecialTiles();
    initializeTiles();
  }
}