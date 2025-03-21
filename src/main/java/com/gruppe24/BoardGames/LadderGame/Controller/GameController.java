package com.gruppe24.BoardGames.LadderGame.Controller;

import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.TileAction;
import com.gruppe24.Utils.Steps;

public class GameController {

  private Board board;
  private final Dice dice;
  private static final int WinCondition = 90;

  public GameController() {
    this.board = new Board();
    this.dice = new Dice(1);
  }




  /**
   * Method that handles the player's turn.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void handlePlayerTurn(Player player) {
    int sumDice = dice.rollSum();
    movePlayer(player, sumDice);
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
    } return newPosition;
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
    TileAction tile = board.getTile(newPosition);
    tile.perform(player);
  }


  /**
   * Method that checks if a player has won the game.
   *
   * @param newPosition the new position of the player
   * @return true if the player has won, false otherwise
   *
   * @Author Sigveer, Ingve
   * @Date: 12.02.2025
   * @Version: 1.0
   */
  public boolean checkAndHandleWin(int newPosition) {
    return newPosition == WinCondition;
  }


  //----------------CONTROLLERS FOR TEXTBASED LADDERGAME--------------------
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
  public boolean textBasedCheckAndHandleWin(Player p, int newPosition) {
    if (newPosition == WinCondition) {
      System.out.println(p.getColoredName() + " won the game!");
      return true;
    }
    return false;
  }

  /**
   * Method that handles the player's turn.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void textBasedHandlePlayerTurn(Player player) {
    Steps.pressEnterToContinue();
    int sumDice = dice.rollSum();

    movePlayer(player, sumDice);

    System.out.println(player.getColoredName() + " rolled " + sumDice);
    System.out.println(player.getColoredName() + " is now on tile " + player.getPosition());
  }


}
