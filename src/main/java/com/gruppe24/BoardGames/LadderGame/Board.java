package com.gruppe24.BoardGames.LadderGame;

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
  private static final int Columns = 10;
  private static final int Rows = 9;
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
    this.dice = new Dice(2);
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
    ladders.put(3, 22);
    ladders.put(8, 30);
    ladders.put(28, 84);
    ladders.put(58, 77);
    ladders.put(75, 86);

    snakes.put(17, 7);
    snakes.put(54, 34);
    snakes.put(62, 19);
    snakes.put(64, 60);
    snakes.put(87, 24);
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

    for (int i = 1; i < Columns * Rows; i++) {
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
    TileAction tileAction = checkTileTypeAtPosition(newPosition);
    tileAction.perform(player);
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
