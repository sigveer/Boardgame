package com.gruppe24.boardgames.monopolylite.model;

public class Property {

  private String name;
  private String color;
  private int price;
  private int rent;
  private int position;

  public Property(String name, String color, int price, int rent, int position) {
    this.name = name;
    this.color = color;
    this.price = price;
    this.rent = rent;
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public String getColor() {
    return color;
  }

  public int getPrice() {
    return price;
  }

  public int getRent() {
    return rent;
  }

  public int getPosition() {
    return position;
  }
}