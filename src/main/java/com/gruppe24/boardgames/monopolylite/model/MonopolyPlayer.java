package com.gruppe24.boardgames.monopolylite.model;

import com.gruppe24.boardgames.commonclasses.CommonPlayer;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a player in the Monopoly game and extends common player.
 */
public class MonopolyPlayer extends CommonPlayer {

  private int money;
  private final List<Property> ownedProperties;
  private boolean inJail;

  /**
   * Constructor for MonopolyPlayer.
   *
   * @param name          the name of the player
   * @param iconIndex     the index of the player's icon
   * @param startingMoney the starting money for the player
   */
  private MonopolyPlayer(String name, int iconIndex, int startingMoney) {
    super(name, iconIndex);
    this.money = startingMoney;
    this.ownedProperties = new ArrayList<>();
    this.inJail = false;
  }

  /**
   * Getter for the player's name.
   *
   * @return the name of the player
   */
  public int getMoney() {
    return money;
  }

  /**
   * Adds money to the player's balance.
   *
   * @param amount the amount of money to add
   */
  public void addMoney(int amount) {
    this.money += amount;
  }

  /**
   * Removes money from the player's balance.
   *
   * @param amount the amount of money to remove
   */
  public void removeMoney(int amount) {
    this.money -= amount;
  }

  /**
   * Spends money from the player's balance if money is more than cost of property.
   *
   * @param amount the amount of money to spend
   * @return true if the transaction was successful, false otherwise
   */
  public boolean spendMoney(int amount) {
    if (money >= amount) {
      money -= amount;
      return true;
    }
    return false;
  }

  /**
   * Adds a property to the player's owned properties.
   *
   * @param property the property to add.
   */
  public void addProperty(Property property) {
    ownedProperties.add(property);
  }

  /**
   * Getter for the player's owned properties.
   *
   * @return the list of properties owned by the player
   */
  public List<Property> getOwnedProperties() {
    return ownedProperties;
  }

  /**
   * Removes a property from the player's owned properties.
   *
   * @param property the property to remove
   */
  public void removeProperty(Property property) {
    ownedProperties.remove(property);
  }

  /**
   * Sets the player's jail status.
   *
   * @param inJail true if the player is in jail, false otherwise
   */
  public void setJailStatus(boolean inJail) {
    this.inJail = inJail;
  }

  /**
   * Getter for the player's jail status.
   *
   * @return true if the player is in jail, false otherwise.
   */
  public boolean getJailStatus() {
    return inJail;
  }
}
