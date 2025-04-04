package com.gruppe24.BoardGames.LadderGame.Models.Board;

import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.NormalTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderDownTile;
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
  protected HashMap<Integer, Integer> ladderUp = new HashMap<>();
  protected HashMap<Integer, Integer> ladderDown = new HashMap<>();
  private static final int Columns = 9;
  private static final int Rows = 10;
  protected Tile[] tiles;
  private String name;
  private String description;

  /**
   * Constructor that initializes the ladders and snakes.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public Board() {
    this.ladderUp = new HashMap<>();
    this.ladderDown = new HashMap<>();
    this.name = "Classic LadderGame";
    this.description = "A classic game of Ladders with 90 tiles.";
    initializeLaddersAndSnake();
    initializeTiles();
  }

  public Board(HashMap<Integer, Integer> ladderUp, HashMap<Integer, Integer> ladderDown,
      String name, String description) {
    this.ladderUp = ladderUp;
    this.ladderDown = ladderDown;
    this.name = name;
    this.description = description;
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
        tiles[i] = new LadderDownTile(i, ladderDown.get(i));
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


  public HashMap<Integer,Integer> getLadderUp(){
    return ladderUp;
  }
  public HashMap<Integer,Integer> getLadderDown(){
    return ladderDown;
  }


  public String getName() {
    return name;
  }


  public String getDescription() {
    return description;
  }
}
