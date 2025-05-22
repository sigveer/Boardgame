package com.gruppe24.boardgames.monopolylite.view;

import com.gruppe24.boardgames.monopolylite.model.Property;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represent the cards in monopoly lite.
 */
public class Cards {

  private final List<Node> propertyCards = new ArrayList<>();
  private final List<Node> chanceCards = new ArrayList<>();

  /**
   * Constructor for monopoly cards.
   *
   * @param properties the properties in the game.
   */
  public Cards(List<Property> properties) {
    createPropertyCards(properties);
    createChanceCard();
  }

  /**
   * Adds property cards into a list propertyCards.
   *
   * @param properties the properties in monopoly.
   */
  private void createPropertyCards(List<Property> properties) {
    for (Property property : properties) {
      if (property.getPrice() > 0) {
        propertyCards.add(createPropertyCardNode(property));
      }
    }
  }

  /**
   * PART OF createPropertyCards-method.
   */
  private Node createPropertyCardNode(Property property) {
    StackPane cardPane = new StackPane();
    cardPane.setMaxWidth(325);
    cardPane.setMaxHeight(600);
    cardPane.setStyle(
        "-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");

    VBox content = new VBox(15);
    content.setAlignment(Pos.TOP_CENTER);
    content.setPadding(new Insets(20));

    Rectangle colorBar = new Rectangle(260, 70);
    colorBar.setFill(Color.web(property.getColor()));
    colorBar.setStroke(Color.BLACK);

    VBox headerText = new VBox(5);
    headerText.setAlignment(Pos.CENTER);

    Label titleLabel = new Label("TITLE DEED");
    titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: black;");

    Label nameLabel = new Label(property.getName());
    nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black;");

    // Create color header with stacked text
    StackPane colorHeader = new StackPane();
    headerText.getChildren().addAll(titleLabel, nameLabel);
    colorHeader.getChildren().addAll(colorBar, headerText);

    Label priceLabel = new Label("PRICE kr " + property.getPrice());
    priceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    Label rentLabel = new Label("RENT kr " + property.getRent());
    rentLabel.setStyle("-fx-font-size: 16px;");

    content.getChildren().addAll(colorHeader, priceLabel, rentLabel);
    cardPane.getChildren().add(content);

    return cardPane;
  }

  /**
   * Adds a chance card into the list chanceCards.
   */
  public void createChanceCard() {
    chanceCards.add(createChanceCardNode("Advance to GO", "Collect kr 200"));
    chanceCards.add(createChanceCardNode("Bank pays you dividend", "Receive kr 50"));
    chanceCards.add(createChanceCardNode("Pay school fees", "Pay kr 150"));
    chanceCards.add(createChanceCardNode("Speeding fine", "Pay kr 100"));
    chanceCards.add(createChanceCardNode("Go to Jail", "Do not pass GO, do not collect kr 200"));
    chanceCards.add(createChanceCardNode("Building loan matures", "Receive kr 150"));
  }

  /**
   * PART OF createChanceCard-method.
   */
  private Node createChanceCardNode(String title, String description) {
    StackPane cardPane = new StackPane();
    cardPane.setMaxWidth(600);
    cardPane.setMaxHeight(325);
    cardPane.setStyle(
        "-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 2px;");

    VBox content = new VBox(20);
    content.setAlignment(Pos.CENTER);
    content.setPadding(new Insets(20));

    Label titleLabel = new Label("CHANCE");
    titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

    Label actionLabel = new Label(title);
    actionLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    actionLabel.setWrapText(true);
    actionLabel.setAlignment(Pos.CENTER);

    Label descriptionLabel = new Label(description);
    descriptionLabel.setStyle("-fx-font-size: 16px;");
    descriptionLabel.setWrapText(true);
    descriptionLabel.setAlignment(Pos.CENTER);

    content.getChildren().addAll(titleLabel, actionLabel, descriptionLabel);
    cardPane.getChildren().add(content);

    return cardPane;
  }

  /**
   * Get all cards from proprtyCards list.
   *
   * @return new list allCards.
   */
  public List<Node> getAllCards() {
    List<Node> allCards = new ArrayList<>(propertyCards);
    allCards.addAll(chanceCards);
    return allCards;
  }
}
