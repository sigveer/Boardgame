package com.gruppe24.boardgames.monopolylite.controller;

import com.gruppe24.boardgames.commonclasses.AbstractGameController;
import com.gruppe24.boardgames.commonclasses.AbstractPlayer;
import com.gruppe24.observerpattern.GameSubject;

public class MonopolyController extends AbstractGameController {


  protected MonopolyController(int numDice, GameSubject gameSubject) {
    super(numDice, gameSubject);
  }

  @Override
  protected AbstractPlayer createPlayer(String name, int iconIndex) {
    return null;
  }

  @Override
  protected int getMaxPlayers() {
    return 4;
  }
}
