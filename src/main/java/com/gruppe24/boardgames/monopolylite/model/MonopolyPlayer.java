package com.gruppe24.boardgames.monopolylite.model;

import com.gruppe24.boardgames.commonclasses.AbstractPlayer;
import java.util.ArrayList;
import java.util.List;

public class MonopolyPlayer extends AbstractPlayer {

  private int money;
  private final List<Property> ownedProperties;
  private boolean inJail;

  private MonopolyPlayer(String name, int iconIndex, int startingMoney) {
    super(name, iconIndex);
    this.money = startingMoney;
    this.ownedProperties = new ArrayList<>();
    this.inJail = false;
  }

  public int getMoney() {
    return money;
  }

  public void addMoney(int amount) {
    this.money += amount;
  }

  public void removeMoney(int amount) {
    this.money -= amount;
  }

  public void addProperty(Property property) {
    ownedProperties.add(property);
  }

  public List<Property> getOwnedProperties() {
    return ownedProperties;
  }

  public void removeProperty(Property property) {
    ownedProperties.remove(property);
  }

  public void setJailStatus(boolean inJail) {
    this.inJail = inJail;
  }

  public boolean getJailStatus() {
    return inJail;
  }
}
