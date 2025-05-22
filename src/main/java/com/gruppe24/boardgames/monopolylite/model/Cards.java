package com.gruppe24.boardgames.monopolylite.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;

/**
 * Represent the cards in monopoly lite.
 */
public class Cards {

  private final List<Property> propertyCards = new ArrayList<>();
  private final List<ChanceCard> chanceCards = new ArrayList<>();

  /**
   * Constructor for monopoly cards.
   *
   * @param properties the properties in the game.
   */
  public Cards(List<Property> properties) {
    createPropertyCards(properties);
    createChanceCards();
  }

  /**
   * Adds property cards into a list propertyCards.
   *
   * @param properties the properties in monopoly.
   */
  private void createPropertyCards(List<Property> properties) {
    for (Property property : properties) {
      if (property.getPrice() > 0) {
        propertyCards.add(property);
      }
    }
  }

  /**
   * Adds chance cards into the list chanceCards.
   */
  private void createChanceCards() {
    chanceCards.add(new ChanceCard("Advance to GO", "Collect kr 200"));
    chanceCards.add(new ChanceCard("Bank pays you dividend", "Receive kr 50"));
    chanceCards.add(new ChanceCard("Pay school fees", "Pay kr 150"));
    chanceCards.add(new ChanceCard("Speeding fine", "Pay kr 100"));
    chanceCards.add(new ChanceCard("Go to Jail", "Do not pass GO, do not collect kr 200"));
    chanceCards.add(new ChanceCard("Building loan matures", "Receive kr 150"));
  }

  /**
   * Get all property cards.
   *
   * @return list of property cards.
   */
  public List<Property> getPropertyCards() {
    return new ArrayList<>(propertyCards);
  }

  /**
   * Get all chance cards.
   *
   * @return list of chance cards.
   */
  public List<ChanceCard> getChanceCards() {
    return new ArrayList<>(chanceCards);
  }

  /**
   * Inner class to represent a chance card.
   */
  public static class ChanceCard extends Node {
    private final String title;
    private final String description;

    public ChanceCard(String title, String description) {
      this.title = title;
      this.description = description;
    }

    public String getTitle() {
      return title;
    }

    public String getDescription() {
      return description;
    }
  }
}