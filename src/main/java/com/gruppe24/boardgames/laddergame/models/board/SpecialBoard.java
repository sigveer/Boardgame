package com.gruppe24.boardgames.laddergame.models.board;

/**
 * Special board implementation with different ladder and snake configurations
 */
class SpecialBoard extends Board {

  public SpecialBoard() {
    initializeLadders();
    initializeSpecialTiles();
    initializeTiles();
  }
}