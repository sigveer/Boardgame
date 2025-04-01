package com.gruppe24.BoardGames.LadderGame.Models;

import static org.junit.jupiter.api.Assertions.*;

import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeDownTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.Tile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * {@code BoardTest} is a test class for the {@code Board} class.
 */
class BoardTest {

  private Board board;
  private Player testPlayer;

  @BeforeEach
  public void beforeEach(){
    board = new Board();
  }

  /**
   * Tests the {@code getTile} method on a normal tile in the {@code Board} class.
   */
  @Test
  void getNormalTile() {
    Tile tile = board.getTile(2);
    assertInstanceOf(Tile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a ladder tile in the {@code Board} class.
   */
  @Test
  void getLadderTile() {
    Tile tile = board.getTile(1);
    assertInstanceOf(LadderUpTile.class, tile);
  }


  /**
   * Tests the {@code getTile} method on a snake tile in the {@code Board} class.
   */
  @Test
  void getSnakeTile() {
    Tile tile = board.getTile(24);
    assertInstanceOf(SnakeDownTile.class, tile);
  }


}