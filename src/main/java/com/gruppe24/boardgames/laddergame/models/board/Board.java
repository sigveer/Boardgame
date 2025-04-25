package com.gruppe24.boardgames.laddergame.models.board;

import com.gruppe24.boardgames.laddergame.models.board.tiles.FrozenTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderDownTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.LadderUpTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.NormalTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.RandomTeleportTile;
import com.gruppe24.boardgames.laddergame.models.board.tiles.Tile;
import java.util.HashMap;

/**
 * Represents the game board for the LadderGame.
 */
public class Board {

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
   * Constructor for the Board class.
   * Initializes the ladders and tiles on the board.
   */
  public Board() {
    this.ladderUp = new HashMap<>();
    this.ladderDown = new HashMap<>();
    this.name = "Classic LadderGame";
    this.description = "A classic game of Ladders with 90 tiles.";
    initializeLadders();
    initializeTiles();
  }

  /**
   * Constructor for the Board class with custom ladders and special tiles.
   *
   * @AI_Assisted Constructor is completed with a logical answer from AI.
   *
   * @param ladderUp HashMap of ladders going up
   * @param ladderDown HashMap of ladders going down
   * @param frozenTiles HashMap of frozen tiles
   * @param randomTeleportTiles HashMap of random teleport tiles
   * @param name Name of the board
   * @param description Description of the board
   */
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

  /**
   * Method that puts ladders at certain indexes in ladders-hashMap.
   */
  public void initializeLadders() {
    initializeStandardLadders(ladderUp, ladderDown);
  }

  /**
   * Method that puts special tiles at certain indexes in specialTiles-hashMap.
   */
  public void initializeSpecialTiles() {
    initializeStandardSpecialTiles(frozenTiles, randomTeleportTiles);
  }

  /**
   * Method that initializes the standard ladders.
   *
   * @param ladderUp HashMap of ladders going up
   * @param ladderDown HashMap of ladders going down
   */
  public static void initializeStandardLadders(HashMap<Integer, Integer> ladderUp, HashMap<Integer,
      Integer> ladderDown) {
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

  /**
   * Method that initializes the standard special tiles.
   *
   * @AI_Based How to initialize the special tiles .put([x], true/false) is based on AI generated
   * code.
   *
   * @param frozenTiles HashMap of frozen tiles
   * @param randomTeleportTiles HashMap of random teleport tiles
   */
  public static void initializeStandardSpecialTiles(HashMap<Integer, Boolean> frozenTiles,
      HashMap<Integer, Boolean> randomTeleportTiles) {
    frozenTiles.put(34, true);
    frozenTiles.put(78, true);
    randomTeleportTiles.put(50, true);
  }

  /**
   * Method that initializes the tiles.
   *
   * @AI_Based Logic as to how to initialize the tiles is based on AI generated code.
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
   */
  public Tile getTile(int position) {
    if (position >= 0 && position < tiles.length) {
      return tiles[position];
    }
    return new NormalTile(position);
  }

  /**
   * Getter for the ladderUp hashmaps tiles.
   *
   * @return HashMap of ladderUp tiles.
   */
  public HashMap<Integer, Integer> getLadderUp() {
    return ladderUp;
  }

  /**
   * Getter for the ladderDown hashmaps tiles.
   *
   * @return HashMap of ladderDown tiles.
   */
  public HashMap<Integer, Integer> getLadderDown() {
    return ladderDown;
  }

  /**
   * Getter for board names.
   *
   * @return the name of the board.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter for board description.
   *
   * @return the description of the board.
   */
  public String getDescription() {
    return description;
  }
}
