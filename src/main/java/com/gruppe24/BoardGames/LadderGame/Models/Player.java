package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Core.NormalTile;
import com.gruppe24.BoardGames.LadderGame.Core.TileAction;
import com.gruppe24.Utils.Steps;

/**
 * Class that represents players
  */
public class Player {

  Board board = new Board();

  //attributes
  private final String name;
  private final int ID;
  private static int nextID = 1;
  public int position;
  private final Dice dice;

  //constructor
  public Player(String name){
    this.name = name;
    this.position = 0;
    this.ID = nextID++; //Is this working?
    this.dice = new Dice(2);
  }


  //methods
  public void landOnTile(NormalTile normalTile) {
    if (normalTile instanceof TileAction actionTile) {
      actionTile.perform(this);
    }
  }

  /**
   * Method that handles the player's turn.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void handlePlayerTurn() {
    Steps.pressEnterToContinue();
    int sumDice = dice.rollSum();

    movePlayer(sumDice);

    System.out.println(this.getName() + " rolled " + sumDice);
    System.out.println(this.getName() + " is now on tile " + this.getPosition());
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
  private void movePlayer(int sumDice) {
    int newPosition = this.getPosition() + sumDice;
    newPosition = board.handleOvershoot(newPosition);
    this.setPosition(newPosition);
    board.handleTileAction(this, newPosition);
  }


  /**
   * Getter-method for Name
   *
   * @return name-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public String getName(){
    return name;
  }



  /**
   * Getter-method for Position
   *
   * @return position-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public int getPosition(){
    return this.position;
  }


  /**
   * Setter-method for Position
   *
   * @param position-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void setPosition(int position){
    this.position = position;
  }
}
