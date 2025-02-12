package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Core.NormalTile;
import com.gruppe24.BoardGames.LadderGame.Core.Tile;
import com.gruppe24.BoardGames.LadderGame.Core.TileAction;
import com.gruppe24.Utils.Steps;
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
  private final HashMap<Integer, Integer> ladders = new HashMap<>();
  private final HashMap<Integer, Integer> snakes = new HashMap<>();

  /**
   * Constructor that initializes the ladders and snakes.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public Board(){
    initializeLadders();
    initializeSnakes();
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
    ladders.put(2, 39);
    ladders.put(3, 11);
    ladders.put(21, 49);
    ladders.put(30, 60);
    ladders.put(83, 85);
  }

  /**
   * Method that puts snakes at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public void initializeSnakes(){
    snakes.put(98, 80);
    snakes.put(44, 36);
    snakes.put(42, 21);
    snakes.put(14, 4);
    snakes.put(38, 1);
  }

  /**
   * Method that returns the tile at a certain position.
   *
   * @param position of the tile
   * @return the tile at the position
   *
   * @author Ingve
   * @date 06.02.2025
   * @Version 1.0
   */
  public Tile getTile(int position) {
    if (ladders.containsKey(position)) {
      return new LadderTile(position, ladders.get(position));
    } else if (snakes.containsKey(position)) {
      return new SnakeTile(position, snakes.get(position));
    }
    return new NormalTile(position);
  }


  /**
   * Method that handles the action of a tile.
   *
   * @param player the player
   * @param newPosition the new position of the player
   *
   * @Author Sigveer, Ingve
   * @Date: 12.02.2025
   * @Version: 1.0
   */
  public static void handleTileAction(Player player, int newPosition) {
    Board board = new Board(); // new board instance to access non-static methods
    Tile currentTile = board.getTile(newPosition);
    if (currentTile instanceof TileAction) {
      ((TileAction) currentTile).perform(player);
    }
  }


  /**
   * Method that checks if a player has won the game.
   *
   * @param p the player
   * @param newPosition the new position of the player
   * @return true if the player has won, false otherwise
   *
   * @Author Sigveer, Ingve
   * @Date: 12.02.2025
   * @Version: 1.0
   */
  public boolean checkAndHandleWin(Player p, int newPosition) {
    if (newPosition > 100) {
      int overshoot = newPosition - 100;
      newPosition = 100 - overshoot;
      p.setPosition(newPosition);
    }

    if (newPosition == 100) {
      System.out.println(p.getName() + " won the game!");
      Steps.pressEnterToContinue();
      return true;
    }
    return false;
  }


}
