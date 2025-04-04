package com.gruppe24.BoardGames.LadderGame.Models.Board;

/**
 * Classic Snakes and Ladders board implementation
 */
class ClassicBoard extends Board {

  public ClassicBoard() {
    initializeLadders();
    initializeTiles();
  }
}