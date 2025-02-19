package com.gruppe24.BoardGames.LadderGame.Controllers;

import com.gruppe24.BoardGames.LadderGame.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.Utils.Steps;

public class PlayerController {

  private final Board board;
  private final Dice dice;

  public PlayerController(){
    this.board = new Board();
    this.dice = new Dice(2);
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
    newPosition = board.handleOvershoot(newPosition);
    player.setPosition(newPosition);
    board.handleTileAction(player, newPosition);
  }

}
