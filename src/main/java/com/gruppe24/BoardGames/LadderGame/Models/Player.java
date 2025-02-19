package com.gruppe24.BoardGames.LadderGame.Models;

/**
 * Class that represents players
  */
public class Player {

  //attributes
  private final String name;
  public int position;

  //constructor
  public Player(String name){
    this.name = name;
    this.position = 0;
  }


  //methods
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
