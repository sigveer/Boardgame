package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.TileAction;

/**
 * Class that represents players
 *
 * @author Ingve V., Sigve W.
 * @version 1.0.0
 * @since 1.0.0
 */
public class Player {

  //attributes
  private String name;
  private int ID;
  public int position;
  public TileAction action;

  /**
   * Constructor that initializes name, position and ID of player.
   * @author Ingve
   * @date 06.02.2025
   */
  public Player(String name){
    this.name = name;
    this.position = 0;
    this.ID++; //Is this working?
  }


  //methods
  /**
   * Method that performs an action for chosen player.
   * @author Ingve
   * @date 06.02.2025
   */
  public void activate(Player player) {
    if (action != null) {
      action.perform(player);
    }
  }

  /**
   * Getter-method
   * @return name-variable
   * @author Ingve
   * @date 06.02.2025
   */
  public String getName(){
    return name;
  }

  /**
   * Getter-method
   * @return ID-variable
   * @author Ingve
   * @date 06.02.2025
   */
  public int getID(){
    return ID;
  }

  /**
   * Getter-method
   * @return position-variable
   * @author Ingve
   * @date 06.02.2025
   */
  public int getPosition(){
    return position;
  }

  /**
   * Setter-method setting the position-variable
   *
   * @param position of the player
   */
  public void setPosition(int position){
    this.position = position;
  }

  /**
   * Setter-method setting the action-variable
   * @param action of the player
   */
  public void setAction(TileAction action) {
    this.action = action;
  }

}
