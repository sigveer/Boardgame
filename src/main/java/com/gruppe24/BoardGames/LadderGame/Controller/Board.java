package com.gruppe24.BoardGames.LadderGame.Controller;

import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.Tile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.TileAction;
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
  private static final int WinCondition = 90;
  private static final int Columns = 9;
  private static final int Rows = 10;
  private TileAction[] tiles;
  private final Dice dice;

  /**
   * Constructor that initializes the ladders and snakes.
   *
   * @author Ingve, Sigveer
   * @date 06.02.2025
   * @Version 1.0
   */
  public Board(){
    this.dice = new Dice(1);
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
    ladders.put(1, 40);
    ladders.put(8, 10);
    ladders.put(36, 52);
    ladders.put(43, 62);
    ladders.put(49, 79);
    ladders.put(65, 82);
    ladders.put(68, 85);

    snakes.put(24, 5);
    snakes.put(33, 3);
    snakes.put(42, 30);
    snakes.put(56, 37);
    snakes.put(64, 27);
    snakes.put(74, 12);
    snakes.put(87, 70);
  }


  /**
   * Method that initializes the tiles.
   *
   * @Author Sigveer, Ingve
   * @Date: 19.02.2025
   * @Version: 1.0
   */
  private void initializeTiles() {
    tiles = new TileAction[Columns * Rows];
    for (int i = 0; i < Columns * Rows; i++) {
      if (ladders.containsKey(i)) {
        tiles[i] = new LadderTile(i, ladders.get(i));
      } else if (snakes.containsKey(i)) {
        tiles[i] = new SnakeTile(i, snakes.get(i));
      } else {
        tiles[i] = new Tile(i);
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
  public TileAction getTile(int position) {
    if (position >= 0 && position < tiles.length) {
      return tiles[position];
    }
    return new Tile(position);
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
    TileAction tile = getTile(newPosition);
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
    if (newPosition == WinCondition) {
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
    if (newPosition > WinCondition) {
      int overshoot = newPosition - WinCondition;
      newPosition = WinCondition - overshoot;
    }
    return newPosition;
  }


  /**
   * Method that handles the player's turn.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void handlePlayerTurn(Player player) {
    Steps.pressEnterToContinue();
    int sumDice = dice.rollSum();

    movePlayer(player, sumDice);

    System.out.println(player.getName() + " rolled " + sumDice);
    System.out.println(player.getName() + " is now on tile " + player.getPosition());
  }


  /**
   * Method that moves the player.
   *
   * @param sumDice the sum of the dice
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void movePlayer(Player player, int sumDice) {
    int newPosition = player.getPosition() + sumDice;
    newPosition = handleOvershoot(newPosition);
    player.setPosition(newPosition);
    handleTileAction(player, newPosition);
  }
}
