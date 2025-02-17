package com.gruppe24.BoardGames.LadderGame.Controllers;

import com.gruppe24.BoardGames.LadderGame.Models.Tile.TileAction;
import com.gruppe24.BoardGames.LadderGame.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.Tile;
import com.gruppe24.Utils.Steps;

public class Controller {


  private final Board board;
  private final Dice dice;

  public Controller(){
    this.board = new Board();
    this.dice = new Dice(2);
  }

  //methods
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
    if (board.getLadder().containsKey(position)) {
      return new LadderTile(position, board.getLadder().get(position));
    } else if (board.getSnakes().containsKey(position)) {
      return new SnakeTile(position, board.getSnakes().get(position));
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
