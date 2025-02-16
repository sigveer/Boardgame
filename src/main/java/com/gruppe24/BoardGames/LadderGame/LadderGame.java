package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.Utils.Steps;
import com.gruppe24.Utils.Validators;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that represents the game as a whole
 *
 * @author Ingve V., Sigveer
 * @date 06.02.2025
 * @version 1.0.0
 */
public class LadderGame {

  //attributes
  private final List<Player> players;
  private final Controller controller;

  //constructor
  public LadderGame() {
    this.players = new ArrayList<>();
    this.controller = new Controller();
  }

  /**
   * Method that sets up the game.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void setUpPlayers() {
    int numberOfPlayers = 0;
    while (numberOfPlayers < 1 || numberOfPlayers > 4) {
      System.out.println("How many players? (1-4)");
      numberOfPlayers = Validators.promptInt("");
      if (numberOfPlayers < 1 || numberOfPlayers > 4) {
        System.out.println("ERROR: Number of players must be between 1 and 4.");
      }
    }

    for (int i = 1; i <= numberOfPlayers; i++) {
      System.out.println("Name of player " + i + ": ");
      String name = Validators.scannerString();
      players.add(new Player(name)); //AI-assisted
    }

    System.out.println("Players are ready to play!");
  }

  /**
   * Method that starts the game.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void play() {
    boolean gameOver = false;
    while (!gameOver) {
      for (Player player : players) {
        controller.handlePlayerTurn(player);
        if (controller.checkAndHandleWin(player, player.getPosition())) {
          gameOver = true;
          Steps.pressEnterToContinue();
          break;
        }
      }
    }
  }
}
