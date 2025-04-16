package com.gruppe24.boardgames.laddergame.models.board;

/**
 * Classic Snakes and Ladders board implementation.
 */
class ClassicBoard extends Board {

  public ClassicBoard() {
    initializeLadders();
    initializeTiles();
  }
}