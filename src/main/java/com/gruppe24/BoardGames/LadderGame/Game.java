package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.Utils.Steps;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Class that represents the game as a whole
 *
 * @author Ingve V., Sigveer
 * @date 06.02.2025
 * @version 1.0.0
 */
public class Game {

  Scanner myScanner = new Scanner(System.in);

  //attributes
  private final Board board;
  private final Dice dice;
  private final List<Player> players;

  //constructor
  public Game() {
    this.board = new Board();
    this.dice = new Dice(2);
    this.players = new ArrayList<>();
  }

  //methods

  /**
   * Method that sets up the game.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void setUp() {
    int numberOfPlayers = 0;
    while (numberOfPlayers < 1 || numberOfPlayers > 4) {
      System.out.println("How many players? (1-4)");
      numberOfPlayers = myScanner.nextInt();
      if (numberOfPlayers < 1 || numberOfPlayers > 4) {
        System.out.println("ERROR: Number of players must be between 1 and 4.");
      }
    }

    myScanner.nextLine();
    for (int i = 1; i <= numberOfPlayers; i++) {
      System.out.println("Name of player " + i + ": ");
      String name = myScanner.nextLine();
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
      for (Player p : players) {
        System.out.println(p.getName() + " is on tile " + p.getPosition());

        Steps.pressEnterToContinue();

        int sumDice = dice.rollSum();
        System.out.println(p.getName() + " rolled " + sumDice);
        int newPosition = p.getPosition() + sumDice;
        p.setPosition(newPosition);

        Tile currentTile = board.getTile(newPosition);
        if (currentTile != null) {
          currentTile.perform(p);
        }

        if (newPosition >= 100) {
          gameOver = true;
          System.out.println(p.getName() + " won the game!");

          Steps.pressEnterToContinue();
          break;
        }
      }
    }
  }
}
