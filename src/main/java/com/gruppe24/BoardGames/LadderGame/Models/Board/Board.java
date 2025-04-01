package com.gruppe24.BoardGames.LadderGame.Models.Board;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.NormalTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.SnakeDownTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.Tile;
import java.util.HashMap;

/**
 * Represets Snakes and Ladder board.
 *
 * @author Ingve, Sigveer.
 * @date 06.02.2025
 * @version 1.0.0
 */
public class Board {

  //Attributes
  protected final HashMap<Integer, Integer> ladderUp = new HashMap<>();
  protected final HashMap<Integer, Integer> ladderDown = new HashMap<>();
  private static final int Columns = 9;
  private static final int Rows = 10;
  protected Tile[] tiles;

  /**
   * Constructor that initializes the ladders and snakes.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public Board(){
    initializeLaddersAndSnake();
    initializeTiles();
  }

  //methods

  /**
   * Method that puts ladders at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public void initializeLaddersAndSnake(){

  }


  /**
   * Method that initializes the tiles.
   *
   * @Author Sigveer, Ingve
   * @Date: 19.02.2025
   * @Version: 1.0
   */
  protected void initializeTiles() {
    tiles = new Tile[Columns * Rows];
    for (int i = 0; i < Columns * Rows; i++) {
      if (ladderUp.containsKey(i)) {
        tiles[i] = new LadderUpTile(i, ladderUp.get(i));
      } else if (ladderDown.containsKey(i)) {
        tiles[i] = new SnakeDownTile(i, ladderDown.get(i));
      } else {
        tiles[i] = new NormalTile(i);
      }
    }
  }


/**
   * Method that gets the tile.
   *
   * @param position the position of the tile
   * @return the tile
   *
   * @Author Sigveer, Ingve
   * @Date: 20.02.2025
   * @Version: 1.0
   */
  public Tile getTile(int position) {
    if (position >= 0 && position < tiles.length) {
      return tiles[position];
    }
    return new NormalTile(position);
  }
}
