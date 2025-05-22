package com.gruppe24.boardgames.monopolylite.model;

/**
 * Reporesents a property in monopoly game.
 */
public class Property {

  private final String name;
  private final String color;
  private final int price;
  private final int rent;
  private final int position;
  private Player owner;
  private boolean purchased;

  /**
   * Constructor for property.
   *
   * @param name name of the property.
   * @param color the colour of the property.
   * @param price the price of the property
   * @param rent the rent
   * @param position the position of the porperty on board
   */
  public Property(String name, String color, int price, int rent, int position) {
    this.name = name;
    this.color = color;
    this.price = price;
    this.rent = rent;
    this.position = position;
    this.owner = null;
    this.purchased = false;
  }

  /**
   * Boolean method to check if the instance of property is purchased.
   *
   * @return true if purchased, false if not.
   */
  public boolean isPurchased() {
    return purchased;
  }

  /**
   * Getter method for property name.
   *
   * @return the name.
   */
  public String getName() {
    return name;
  }

  /**
   * Getter method for colour of property.
   *
   * @return the property colour.
   */
  public String getColor() {
    return color;
  }

  /**
   * Getter method for price of property.
   *
   * @return the property price.
   */
  public int getPrice() {
    return price;
  }

  /**
   * Getter method for rent of proerty.
   *
   * @return the properties rent.
   */
  public int getRent() {
    return rent;
  }

  /**
   * Getter method for position of property.
   *
   * @return the position of the proerty.
   */
  public int getPosition() {
    return position;
  }

  /**
   * Getter method for owner of property.
   *
   * @return the owner of the property.
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * Setter method for owner of property.
   *
   * @param owner the owner of the property.
   */
  public void setOwner(Player owner) {
    this.owner = owner;
    this.purchased = true;
  }
}