package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.TileAction;

/**
 * Class that represents players
  */
public class Player {

  //attributes
  private String name;
  private int ID;
  public int position;
  public TileAction action;

  //constructor
  public Player(String name){
    this.name = name;
    this.position = 0;
    this.ID++; //Is this working?
  }


  //methods
  /**
   * Method that activates the action of the tile.
   *
   * @param player The player that lands on the tile.
   *
   * @Author Sigveer, Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void activate(Player player) {
    if (action != null) {
      action.perform(player);
    }
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


  /**
   * Setter-method for Action
   *
   * @param action-variable
   *
   * @Author Ingve
   * @Date: 06.02.2025
   * @Version: 1.0
   */
  public void setAction(TileAction action) {
    this.action = action;
  }

}
