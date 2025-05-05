package com.gruppe24.boardgames.monopolylite.controller;

import com.gruppe24.boardgames.commonclasses.CommonGameController;
import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import com.gruppe24.observerpattern.GameSubject;

public class MonopolyController extends CommonGameController {


  protected MonopolyController(int numDice, GameSubject gameSubject) {
    super(numDice, gameSubject);
  }

  @Override
  protected CommonPlayer createPlayer(String name, int iconIndex) {
    return null;
  }

  @Override
  protected int getMaxPlayers() {
    return 4;
  }
}
