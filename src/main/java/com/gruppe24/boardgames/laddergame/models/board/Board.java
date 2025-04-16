package com.gruppe24.boardgames.laddergame.models.board;

import com.gruppe24.boardgames.laddergame.models.board.tile.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tile.NormalTile;
import com.gruppe24.boardgames.laddergame.models.board.tile.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tile.RandomTeleportTile;
import com.gruppe24.boardgames.laddergame.models.board.tile.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tile.Tile;
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
  protected HashMap<Integer, Integer> ladderUp;
  protected HashMap<Integer, Integer> ladderDown;
  protected HashMap<Integer, Boolean> frozenTiles = new HashMap<>();
  protected HashMap<Integer, Boolean> randomTeleportTiles = new HashMap<>();
  private static final int Columns = 9;
  private static final int Rows = 10;
  protected Tile[] tiles;
  private final String name;
  private final String description;

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
    initializeLadders();
    initializeTiles();
  }

  public Board(HashMap<Integer, Integer> ladderUp, HashMap<Integer, Integer> ladderDown,
      HashMap<Integer, Boolean> frozenTiles, HashMap<Integer, Boolean> randomTeleportTiles,
      String name, String description) {
    this.ladderUp = ladderUp;
    this.ladderDown = ladderDown;
    this.frozenTiles = frozenTiles;
    this.randomTeleportTiles = randomTeleportTiles;
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
  public void initializeLadders(){
    initializeStandardLadders(ladderUp, ladderDown);
  }


  public void initializeSpecialTiles(){
    initializeStandardSpecialTiles(frozenTiles, randomTeleportTiles);
  }


  public static void initializeStandardLadders(HashMap<Integer, Integer> ladderUp, HashMap<Integer, Integer> ladderDown) {
    ladderUp.put(2, 40);
    ladderUp.put(8, 10);
    ladderUp.put(36, 52);
    ladderUp.put(43, 62);
    ladderUp.put(49, 79);
    ladderUp.put(65, 82);
    ladderUp.put(68, 85);

    ladderDown.put(24, 5);
    ladderDown.put(33, 3);
    ladderDown.put(42, 30);
    ladderDown.put(56, 37);
    ladderDown.put(64, 27);
    ladderDown.put(74, 12);
    ladderDown.put(87, 70);
  }


  public static void initializeStandardSpecialTiles(HashMap<Integer, Boolean> frozenTiles, HashMap<Integer, Boolean> randomTeleportTiles) {
    frozenTiles.put(34, true);
    frozenTiles.put(78, true);
    randomTeleportTiles.put(50, true);
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
      } else if (frozenTiles != null && frozenTiles.containsKey(i)) {
        tiles[i] = new FrozenTile(i);
      } else if (randomTeleportTiles != null && randomTeleportTiles.containsKey(i)) {
        tiles[i] = new RandomTeleportTile(i);
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
