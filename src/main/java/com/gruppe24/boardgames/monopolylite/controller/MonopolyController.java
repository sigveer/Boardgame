package com.gruppe24.boardgames.monopolylite.controller;

import com.gruppe24.boardgames.commonclasses.CommonDice;
import com.gruppe24.boardgames.monopolylite.model.Cards;
import com.gruppe24.boardgames.monopolylite.model.Cards.ChanceCard;
import com.gruppe24.boardgames.monopolylite.model.Player;
import com.gruppe24.boardgames.monopolylite.model.Property;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for monopoly game logic.
 */
public class MonopolyController {

  private final List<Player> players;
  private final CommonDice dice;
  private final List<Property> properties;
  private final Cards cards;
  private int currentPlayerIndex = 0;
  private int currentCardIndex = 0;
  private List<Player> bankruptPlayers = new ArrayList<>();

  /**
   * Constructor for MonopolyController.
   */
  public MonopolyController() {
    this.players = new ArrayList<>();
    this.dice = new CommonDice(1);
    this.properties = Property.createProperties();
    this.cards = new Cards(properties);
  }

  /**
   * Marks a player as bankrupt and removes them from active players.
   *
   * @param player the player to bankrupt
   */
  public void bankruptPlayer(Player player) {
    if (players.contains(player)) {
      bankruptPlayers.add(player);
      players.remove(player);

      for (Property property : properties) {
        if (property.getOwner() == player) {
          property.setOwner(null);
        }
      }

      if (players.isEmpty()) {
        return;
      } else if (currentPlayerIndex >= players.size()) {
        currentPlayerIndex = 0;
      }
    }
  }

  /**
   * Pay rent for a property.
   *
   * @param property the property in question.
   * @param player the player on the property
   * @return true or false if the player can afford to rent.
   */
  public boolean payRent(Property property, Player player) {
    if (property.getOwner() == player || !property.isPurchased()) {
      return true; // No rent to pay
    }

    int rentAmount = property.getRent();
    boolean canPay = player.subtractMoney(rentAmount);

    if (canPay) {
      property.getOwner().addMoney(rentAmount);
      return true;
    } else {
      bankruptPlayer(player);
      return false;
    }
  }

  /**
   * Adds a player to the game.
   *
   * @param player player to add
   */
  public void addPlayer(Player player) {
    players.add(player);
  }

  /**
   * Gets the list of players.
   *
   * @return list of players
   */
  public List<Player> getPlayers() {
    return players;
  }

  /**
   * Gets the current player.
   *
   * @return current player
   */
  public Player getCurrentPlayer() {
    if (players.isEmpty()) {
      return null;
    }
    return players.get(currentPlayerIndex);
  }

  /**
   * Gets the properties.
   *
   * @return list of properties
   */
  public List<Property> getProperties() {
    return new ArrayList<>(properties);
  }

  /**
   * Gets the cards object.
   *
   * @return cards
   */
  public Cards getCards() {
    return cards;
  }

  /**
   * Gets the next chance card.
   *
   * @return next chance card
   */
  public ChanceCard getNextChanceCard() {
    List<ChanceCard> chanceCards = cards.getChanceCards();
    if (chanceCards.isEmpty()) {
      return null;
    }

    currentCardIndex = (currentCardIndex + 1) % chanceCards.size();
    return chanceCards.get(currentCardIndex);
  }

  /**
   * Gets the previous chance card.
   *
   * @return previous chance card
   */
  public ChanceCard getPreviousChanceCard() {
    List<ChanceCard> chanceCards = cards.getChanceCards();
    if (chanceCards.isEmpty()) {
      return null;
    }

    currentCardIndex = (currentCardIndex - 1 + chanceCards.size()) % chanceCards.size();
    return chanceCards.get(currentCardIndex);
  }

  /**
   * Gets the current chance card.
   *
   * @return current chance card
   */
  public ChanceCard getCurrentChanceCard() {
    List<ChanceCard> chanceCards = cards.getChanceCards();
    if (chanceCards.isEmpty()) {
      return null;
    }

    return chanceCards.get(currentCardIndex);
  }

  /**
   * Rolls the dice and moves the current player.
   *
   * @return the dice roll value
   */
  public int rollDiceAndMovePlayer() {
    int diceValue = dice.rollSum();
    Player currentPlayer = getCurrentPlayer();
    if (currentPlayer != null) {
      movePlayer(currentPlayer, diceValue);
    }
    return diceValue;
  }

  /**
   * Gets the dice object.
   *
   * @return dice
   */
  public CommonDice getDice() {
    return dice;
  }

  /**
   * Moves the player by the specified number of spaces.
   *
   * @param player the player to move
   * @param steps number of steps to move
   * @return the property landed on
   */
  public Property movePlayer(Player player, int steps) {
    int fromPosition = player.getPosition();
    int toPosition = (fromPosition + steps) % 24;

    // Check if player passed GO
    if (toPosition < fromPosition && toPosition != 0) {
      player.addMoney(200);
    }

    player.setPosition(toPosition);

    // Find the property at this position
    return getPropertyAtPosition(toPosition);
  }

  /**
   * Gets the property at the specified position.
   *
   * @param position position on the board
   * @return property at that position
   */
  public Property getPropertyAtPosition(int position) {
    for (Property property : properties) {
      if (property.getPosition() == position) {
        return property;
      }
    }
    return null;
  }

  /**
   * Sends a player to jail.
   *
   * @param player player to send to jail
   */
  public void sendPlayerToJail(Player player) {
    player.setPosition(6); // Jail position
  }

  /**
   * Attempts to purchase a property for the current player.
   *
   * @param property property to purchase
   * @return true if purchase was successful, false otherwise
   */
  public boolean purchaseProperty(Property property) {
    Player currentPlayer = getCurrentPlayer();
    if (currentPlayer == null || property.isPurchased()
        || !currentPlayer.subtractMoney(property.getPrice())) {
      return false;
    }

    property.setOwner(currentPlayer);
    return true;
  }

  /**
   * Advances to the next player.
   */
  public void advanceToNextPlayer() {
    if (!players.isEmpty()) {
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
  }


}