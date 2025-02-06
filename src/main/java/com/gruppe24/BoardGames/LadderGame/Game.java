package com.gruppe24.BoardGames.LadderGame;

import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import java.util.List;
import java.util.Scanner;

public class Game {

  Scanner myScanner = new Scanner(System.in);

  //attributes
  private final Board board;
  private final Dice dice;
  private final List<Player> players;


  public Game(Board board, Dice dice, List<Player> players){
    this.board = board;
    this.dice = dice;
    this.players = players;
  }

  //methods
  public void playerSetUp () {
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
      players.add(new Player(name));
    }

    System.out.println("Players are ready to play!");
  }


}
