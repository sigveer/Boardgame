package com.gruppe24.boardgames.laddergame.view;

import com.gruppe24.boardgames.laddergame.controller.GameController;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.utils.Steps;
import com.gruppe24.utils.Validators;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.scene.paint.Color;


/**
 * Class that represents the game as a whole.
 *
 * @author Ingve V., Sigveer
 * @date 06.02.2025
 * @version 1.0.0
 */
public class TextBasedLadderGame {
  private final List<Player> players;
  private final GameController gameController;

  /**
   * Constructor for the TextBasedLadderGame class.
   */
  public TextBasedLadderGame() {
    this.players = new ArrayList<>();
    this.gameController = new GameController();
    Validators.getLogger().log(Level.INFO, "Text based ladde game started");
  }

  /**
   * Method that sets up the game.
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
      System.out.println("Color of player" + i + "\n(R = Red, B = blue, G = green):");
      Color color = Validators.colorChoice(Validators.scannerString());
      players.add(new Player(name, color)); //AI-assisted
    }

    System.out.println("Players are ready to play!");

    play();
  }

  /**
   * Method that starts the game.
   */
  public void play() {
    boolean gameOver = false;
    while (!gameOver) {
      for (Player player : players) {
        gameController.textBasedHandlePlayerTurn(player);
        if (gameController.textBasedCheckAndHandleWin(player, player.getPosition())) {
          gameOver = true;
          Steps.pressEnterToContinue();
          break;
        }
      }
    }
  }
}
