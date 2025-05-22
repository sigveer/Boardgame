package com.gruppe24.boardgames.monopolylite.model;

import com.gruppe24.boardgames.commonclasses.CommonPlayer;

/**
 * Class representing a player in monopoly game.
 */
public class Player extends CommonPlayer {

  private int money = 1500;

  /**
   * Constructor for the common player.
   *
   * @param name      name of the player
   * @param iconIndex index of the icon
   */
  public Player(String name, int iconIndex) {
    super(name, iconIndex);
  }

  /**
   * Method to add money into players account.
   *
   * @param amount amount of money added.
   */
  public void addMoney(int amount) {
    this.money += amount;
  }

  /**
   * Method to subtract money from players account as well as give confirmation if amount was
   * legal.
   *
   * @param amount amount of money subtracted.
   * @return true if the money was subtracted, false if not enough money.
   */
  public boolean subtractMoney(int amount) {
    if (this.money - amount < 0) {
      return false;
    }
    this.money -= amount;
    return true;
  }

  /**
   * Getter method to get amount.
   *
   * @return amount of meoney.
   */
  public int getMoney() {
    return money;
  }
}
