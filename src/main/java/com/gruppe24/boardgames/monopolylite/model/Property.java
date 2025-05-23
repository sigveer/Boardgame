package com.gruppe24.boardgames.monopolylite.model;

import java.util.ArrayList;
import java.util.List;

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
   * Creates the properties for the game.
   *
   * @return list of properties
   */
  public static List<Property> createProperties() {
    List<Property> properties = new ArrayList<>();

    properties.add(new Property("START", "#FFFFFF", 0, 0, 0));
    properties.add(new Property("Aker Brygge", "#955436", 60, 2, 1));
    properties.add(new Property("Grünerløkka", "#955436", 60, 4, 2));
    properties.add(new Property("Majorstuen", "#aae0fa", 100, 6, 3));
    properties.add(new Property("Frogner", "#aae0fa", 120, 8, 4));
    properties.add(new Property("Bygdøy Allé", "#d93a96", 140, 10, 5));
    properties.add(new Property("Jail", "#FFFFFF", 0, 0, 6));
    properties.add(new Property("Bogstadveien", "#d93a96", 140, 10, 7));
    properties.add(new Property("Karl Johan", "#d93a96", 160, 12, 8));
    properties.add(new Property("Chance", "#FFFFFF", 0, 0, 9));
    properties.add(new Property("Bryggen", "#f7941d", 180, 14, 10));
    properties.add(new Property("Fløyen", "#f7941d", 200, 16, 11));
    properties.add(new Property("Gratis Parkering", "#FFFFFF", 0, 0, 12));
    properties.add(new Property("Trondheim Torg", "#ed1b24", 220, 18, 13));
    properties.add(new Property("Nidarosdomen", "#ed1b24", 220, 18, 14));
    properties.add(new Property("Solsiden", "#ed1b24", 240, 20, 15));
    properties.add(new Property("Nordlys", "#fef200", 260, 22, 16));
    properties.add(new Property("Ishavskatedralen", "#fef200", 280, 24, 17));
    properties.add(new Property("Go to Jail", "#FFFFFF", 0, 0, 18));
    properties.add(new Property("Preikestolen", "#0072bb", 300, 26, 19));
    properties.add(new Property("Chance", "#FFFFFF", 0, 0, 20));
    properties.add(new Property("Vardø Festning", "#0072bb", 320, 28, 21));
    properties.add(new Property("Holmenkollen", "#5e3c6c", 350, 35, 22));
    properties.add(new Property("Slottet", "#5e3c6c", 400, 50, 23));

    return properties;
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