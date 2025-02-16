package com.gruppe24.BoardGames.LadderGame.Models;

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
    ladders.put(5, 25);
    ladders.put(12, 28);
    ladders.put(22, 77);
    ladders.put(35, 55);
    ladders.put(45, 90);
    ladders.put(50, 70);
    ladders.put(65, 85);
  }

  /**
   * Method that puts snakes at certain indexes in ladders-hashMap.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public void initializeSnakes(){
    snakes.put(16, 6);
    snakes.put(33, 20);
    snakes.put(42, 21);
    snakes.put(47, 30);
    snakes.put(60, 10);
    snakes.put(68, 52);
    snakes.put(75, 25);
    snakes.put(88, 72);
    snakes.put(99, 40);
  }


  /**
   * Method that checks the tile type at a certain position.
   *
   *
   * @param position the position to check
   * @return the tile action at the position
   *
   * @Author Ingve, Sigveer
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public TileAction checkTileTypeAtPosition(int position) {
    if (ladders.containsKey(position)) {
      return new LadderTile(position, ladders.get(position));
    } else if (snakes.containsKey(position)) {
      return new SnakeTile(position, snakes.get(position));
    } else {
      return new Tile(position); // Normal tile
    }
  }


  /**
   * Method that handles the action of a tile.
   *
   * @param player the player
   * @param newPosition the new position of the player
   *
   * @Author Sigveer, Ingve
   * @Date: 16.02.2025
   * @Version: 1.0
   */
  public void handleTileAction(Player player, int newPosition) {
   TileAction tile = checkTileTypeAtPosition(newPosition);
   tile.perform(player);
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
    if (newPosition == 100) {
      System.out.println(p.getName() + " won the game!");
      return true;
    }
    return false;
  }


  /**
   * Method that handles overshoot of the player.
   *
   * @param newPosition the new position of the player
   * @return the new position of the player
   *
   * @Author Sigveer
   * @Date: 12.02.2025
   * @Version: 1.0
   */
  public int handleOvershoot(int newPosition) {
    if (newPosition > 100) {
      int overshoot = newPosition - 100;
      newPosition = 100 - overshoot;
    }
    return newPosition;
  }

}
