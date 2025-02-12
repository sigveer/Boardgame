package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.Core.Tile;
import com.gruppe24.BoardGames.LadderGame.Core.TileAction;

/**
 * Class that represents players
  */
public class Player {

  //attributes
  private final String name;
  private final int ID;
  public int position;

  //constructor
  public Player(String name){
    this.name = name;
    this.position = 0;
    int nextID = 1;
    this.ID = nextID++; //Is this working?
  }


  //methods
  public void landOnTile(Tile tile) {
    if (tile instanceof TileAction actionTile) {
      actionTile.perform(this);
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
}
