package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Core.Tile;
import com.gruppe24.BoardGames.LadderGame.Core.TileAction;
import com.gruppe24.Utils.Steps;

/**
 * Class that represents players
  */
public class Player {

  //attributes
  private final String name;
  private final int ID;
  private static int nextID = 1;
  public int position;
  private static final Dice dice = new Dice(2);

  //constructor
  public Player(String name){
    this.name = name;
    this.position = 0;
    this.ID = nextID++; //Is this working?
  }


  //methods
  public void landOnTile(Tile tile) {
    if (tile instanceof TileAction actionTile) {
      actionTile.perform(this);
    }
  }

  /**
   * Method that handles the player's turn.
   *
   * @param p Player
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void handlePlayerTurn(Player p) {
    Steps.pressEnterToContinue();
    int sumDice = dice.rollSum();
    System.out.println(p.getName() + " rolled " + sumDice);
    movePlayer(p, sumDice);
    System.out.println(p.getName() + " is now on tile " + p.getPosition());
  }


  /**
   * Method that moves the player.
   *
   * @param p Player
   * @param sumDice the sum of the dice
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  private void movePlayer(Player p, int sumDice) {
    int newPosition = p.getPosition() + sumDice;
    p.setPosition(newPosition);
    Board.handleTileAction(this, newPosition);
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
   * Getter-method for ID
   *
   * @return ID-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public int getID(){
    return ID;
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
    return position;
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
