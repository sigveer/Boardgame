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
}