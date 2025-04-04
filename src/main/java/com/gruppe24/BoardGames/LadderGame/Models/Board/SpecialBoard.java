package com.gruppe24.BoardGames.LadderGame.Models.Board;

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