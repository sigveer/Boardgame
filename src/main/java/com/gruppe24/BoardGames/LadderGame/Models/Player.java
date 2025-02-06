package com.gruppe24.BoardGames.LadderGame.Models;

import com.gruppe24.BoardGames.LadderGame.TileAction;

public class Player {

  //attributes
  private String name;
  private int ID;
  public int position;
  public TileAction action;

  public Player(String name){
    this.name = name;
    this.position = 0;
    this.ID++; //Is this working?
  }


  //methods
  public void setAction(TileAction action) {
    this.action = action;
  }

  public void activate(Player player) {
    if (action != null) {
      action.perform(player);
    }
  }

  public String getName(){
    return name;
  }

  public int getID(){
    return ID;
  }

  public int getPosition(){
    return position;
  }

  public void setPosition(int position){
    this.position = position;
  }


  public void move(int steps) {
    this.position += steps;
  }
}
