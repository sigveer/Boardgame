package com.gruppe24.boardgames.monopolylite.view;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.boardgames.commonclasses.CommonDice;
import com.gruppe24.boardgames.monopolylite.model.Cards;
import com.gruppe24.boardgames.monopolylite.model.Cards.ChanceCard;
import com.gruppe24.boardgames.monopolylite.model.Player;
import com.gruppe24.boardgames.monopolylite.model.Property;
import com.gruppe24.utils.StyleUtils;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * View class for monopoly game.
 */
public class View {

  private final Stage primaryStage;
  private final List<Player> players;
  private final CommonDice dice = new CommonDice(1);
  private Cards cards;
  private int currentCardIndex = 0;
  private List<Property> properties;
  private Label diceResultLabel;
  private Label playerInfoLabel;
  private Label currentPlayerLabel;

  /**
   * Constructor for View class.
   *
   * @param primaryStage the primary stage.
   */
  public View(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.players = new ArrayList<>();

    Player tempPlayer = new Player("Player 1", 0);
    players.add(tempPlayer);
  }

  /**
   * Initializeser for view.
   */
  public void initializeView() {
    primaryStage.setTitle("Monopoly Lite");

    BorderPane mainLayout = new BorderPane();
    mainLayout.setStyle("-fx-background-color: #3a5ad7;");

    StackPane boardContainer = new StackPane();

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);

    boardContainer.getChildren().addAll(gridPane);

    // Control panel (right section)
    VBox controlPanel = new VBox(15);
    controlPanel.setAlignment(Pos.TOP_CENTER);
    controlPanel.setPrefWidth(250);
    controlPanel.setMinWidth(200);
    StyleUtils.stylePanel(controlPanel);

    currentPlayerLabel = new Label("Current Player: " + players.getFirst().getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
    currentPlayerLabel.setWrapText(true);
    currentPlayerLabel.setMaxWidth(230);

    playerInfoLabel = new Label(
        players.getFirst().getName() + " - kr " + players.getFirst().getMoney());
    playerInfoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
    playerInfoLabel.setWrapText(true);
    playerInfoLabel.setMaxWidth(230);

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    Pane dicePane = new Pane();
    dicePane.setPrefHeight(70);

    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, dicePane));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new DashboardGui().start(primaryStage));
    StyleUtils.styleNormalButton(backToMenu);

    Button checkPropertyButton = new Button("Check Property");
    StyleUtils.styleNormalButton(checkPropertyButton);
    checkPropertyButton.setOnAction(event -> {
    });

    Button viewCardsButton = new Button("Show A Chance Cards");
    StyleUtils.styleNormalButton(viewCardsButton);
    viewCardsButton.setOnAction(event -> showCardViewer());

    controlPanel.getChildren().addAll(
        backToMenu,
        currentPlayerLabel,
        playerInfoLabel,
        dicePane,
        diceResultLabel,
        diceRoll,
        checkPropertyButton,
        viewCardsButton //midlertidig løsning
    );

    mainLayout.setCenter(boardContainer);
    mainLayout.setRight(controlPanel);

    Scene scene = new Scene(mainLayout, 1200, 900);

    drawBoard(gridPane);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Displays the card viewer.
   */
  private void showCardViewer() {
    Stage cardViewerStage = new Stage();
    cardViewerStage.initOwner(primaryStage);
    cardViewerStage.setTitle("Card Viewer");

    cardViewerStage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
    cardViewerStage.setAlwaysOnTop(true); // Keep the card viewer on top
    cardViewerStage.setResizable(false); // Prevent resizing

    BorderPane layout = new BorderPane();
    layout.setStyle("-fx-background-color: #efefef; -fx-padding: 20;");

    List<ChanceCard> chanceCardsList = cards.getChanceCards();
    if (chanceCardsList.isEmpty()) {
      return;  // No cards to display
    }

    StackPane cardContainer = new StackPane();
    cardContainer.getChildren().add(createChanceCardNode(chanceCardsList.get(currentCardIndex)));
    layout.setCenter(cardContainer);

    Button prevButton = new Button("◀");
    StyleUtils.styleNormalButton(prevButton);
    prevButton.setOnAction(e -> {
      currentCardIndex = (currentCardIndex - 1 + chanceCardsList.size()) % chanceCardsList.size();
      cardContainer.getChildren().clear();
      cardContainer.getChildren().add(createChanceCardNode(chanceCardsList.get(currentCardIndex)));
    });

    Button nextButton = new Button("▶");
    StyleUtils.styleNormalButton(nextButton);
    nextButton.setOnAction(e -> {
      currentCardIndex = (currentCardIndex + 1) % chanceCardsList.size();
      cardContainer.getChildren().clear();
      cardContainer.getChildren().add(createChanceCardNode(chanceCardsList.get(currentCardIndex)));
    });

    Button closeButton = new Button("Close");
    StyleUtils.styleNormalButton(closeButton);
    closeButton.setOnAction(e -> cardViewerStage.close());

    BorderPane buttonLayout = new BorderPane();
    buttonLayout.setLeft(prevButton);
    buttonLayout.setRight(nextButton);
    buttonLayout.setCenter(closeButton);
    BorderPane.setMargin(closeButton, new Insets(0, 20, 0, 20));

    layout.setBottom(buttonLayout);
    BorderPane.setMargin(buttonLayout, new Insets(20, 0, 0, 0));

    Scene cardScene = new Scene(layout, 800, 600);

    cardViewerStage.setScene(cardScene);
    cardViewerStage.show();
  }


  /**
   * Creates a visual representation of a property card.
   *
   * @param property the property to visualize
   * @return the JavaFX Node representing the property card
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
   * Creates a visual representation of a chance card.
   *
   * @param chanceCard the chance card to visualize
   * @return the JavaFX Node representing the chance card
   */
  private Node createChanceCardNode(Cards.ChanceCard chanceCard) {
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

    Label actionLabel = new Label(chanceCard.getTitle());
    actionLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
    actionLabel.setWrapText(true);
    actionLabel.setAlignment(Pos.CENTER);

    Label descriptionLabel = new Label(chanceCard.getDescription());
    descriptionLabel.setStyle("-fx-font-size: 16px;");
    descriptionLabel.setWrapText(true);
    descriptionLabel.setAlignment(Pos.CENTER);

    content.getChildren().addAll(titleLabel, actionLabel, descriptionLabel);
    cardPane.getChildren().add(content);

    return cardPane;
  }

  private void rollDiceAndMove(GridPane gridPane, Pane dicePane) {
    int diceValue = rollAndDisplayDice(dicePane);
    diceResultLabel.setText("Rolled: " + diceValue);

    Player currentPlayer = players.getFirst();

    movePlayer(gridPane, currentPlayer, diceValue);

    advanceToNextPlayer();
  }

  private int rollAndDisplayDice(Pane dicePane) {
    int diceValue = dice.rollSum();
    int diceValue1 = dice.getDie(0);

    diceResultLabel.setText("Rolled: " + diceValue);

    ImageView dice1Iv = new ImageView(new Image(dice.dicePath(diceValue1)));
    dice1Iv.setX(80);
    dice1Iv.setY(0);
    dice1Iv.setFitHeight(75);
    dice1Iv.setFitWidth(75);

    dicePane.getChildren().clear();
    dicePane.getChildren().addAll(dice1Iv);

    return diceValue;
  }

  private List<Property> createProperties() {
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
   * Calculates the position on the board based on the row and column.
   *
   * @param i Row index
   * @param j Column index
   * @return Position on the board
   * @AI_Based Logic and math calculation is based on AI logic.
   */
  private int calculatePosition(int i, int j) {
    // 7x7 board
    if (i == 6) {
      // positions 0-6
      return 6 - j; // Reversed to put START at bottom right
    } else if (j == 0) {
      // positions 7-12)
      return 7 + (5 - i);
    } else if (i == 0) {
      // positions 13-18
      if (j == 6) {
        return 18; // "Go to Jail"
      } else {
        return 13 + (j - 1);
      }
    } else if (j == 6) {
      // positions 19-23
      return 19 + (i - 1);
    } else {
      // Center of the board
      return -1;
    }
  }

  /**
   * Draws the monopoly board.
   *
   * @param gridPane the gridpane.
   */
  public void drawBoard(GridPane gridPane) {
    gridPane.getChildren().clear();

    List<Property> properties = createProperties();

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        StackPane tile = new StackPane();

        // Outer tiles larger than inner tiles
        if (i == 0 || i == 6 || j == 0 || j == 6) {
          tile.setPrefSize(120, 120);
        } else {
          tile.setPrefSize(80, 80);
        }

        // Only add tiles around the border to create the board path
        if (i == 0 || i == 6 || j == 0 || j == 6) {
          int position = calculatePosition(i, j);
          if (position >= 0 && position < properties.size()) {
            Property property = properties.get(position);
            tile = createPropertyTile(property);
          } else {
            tile.setStyle(
                "-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; "
                    + "-fx-border-width: 2px;");
          }
        } else {
          tile.setStyle("-fx-background-color: #c6efd1; -fx-border-color: transparent; "
              + "-fx-border-width: 2px;");
        }
        gridPane.add(tile, j, i);
      }
    }
    this.cards = new Cards(properties);

    this.properties = createProperties();

    if (!players.isEmpty()) {
      Player player = players.getFirst();
      player.setPosition(0);  // Start position
      placePlayerPieceOnBoard(gridPane, player);
    }

    this.cards = new Cards(properties);
  }

  private StackPane createPropertyTile(Property property) {
    StackPane tile = new StackPane();
    tile.setPrefSize(120, 120);
    tile.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 2px;");

    VBox content = new VBox(5);
    content.setAlignment(Pos.TOP_CENTER);

    Rectangle colorRect = new Rectangle(120, 30);
    colorRect.setFill(javafx.scene.paint.Color.web(property.getColor()));

    Label nameLabel = new Label(property.getName());
    nameLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
    nameLabel.setWrapText(true);
    nameLabel.setMaxWidth(115);
    nameLabel.setAlignment(Pos.CENTER);

    Label priceLabel = new Label("kr " + property.getPrice());
    priceLabel.setStyle("-fx-font-size: 14px;");

    content.getChildren().addAll(colorRect, nameLabel, priceLabel);
    tile.getChildren().add(content);

    return tile;
  }


  /**
   * Moves the player piece directly to the specified position.
   *
   * @param gridPane  the grid containing the board
   * @param player    the player to move
   * @param diceValue the dice roll value
   */
  private void movePlayer(GridPane gridPane, Player player, int diceValue) {
    int fromPosition = player.getPosition();
    int toPosition = (fromPosition + diceValue) % 24;

    player.setPosition(toPosition);

    placePlayerPieceOnBoard(gridPane, player);

    handlePropertyLanding(toPosition);
  }

  /**
   * Places the player piece on the board at their current position.
   *
   * @param gridPane the grid containing the board
   * @param player   the player to place
   * @AI_Based Logic and math calculation is based on AI logic.
   */
  public void placePlayerPieceOnBoard(GridPane gridPane, Player player) {
    int position = player.getPosition();

    int row;
    int col;

    if (position <= 6) {
      // Bottom row (positions 0-6)
      row = 6;
      col = 6 - position;
    } else if (position <= 12) {
      // Left column (positions 7-12)
      row = 6 - (position - 6);
      col = 0;
    } else if (position <= 18) {
      // Top row (positions 13-18)
      row = 0;
      col = position - 12;
    } else {
      // Right column (positions 19-23)
      row = position - 18;
      col = 6;
    }
    addPlayerPieceToGrid(gridPane, player, col, row);
  }

  /**
   * Adds the player piece to the specified grid position.
   */
  private void addPlayerPieceToGrid(GridPane gridPane, Player player, int col, int row) {
    if (player.getPlayerPiece() == null) {
      Image image = new Image("pictures/pieces/piece" + player.getIconIndex() + ".png");
      player.setIcon(image);
    }

    StackPane pieceContainer = new StackPane();
    pieceContainer.setId("playerPiece");
    pieceContainer.getChildren().add(player.getPlayerPiece());

    // Find the cell at the specified position
    for (Node node : gridPane.getChildren()) {
      if (node instanceof StackPane
          && GridPane.getRowIndex(node) == row
          && GridPane.getColumnIndex(node) == col) {

        ((StackPane) node).getChildren().add(player.getPlayerPiece());
        return;
      }
    }
  }

  /**
   * Handles effects of landing on a property.
   */
  private void handlePropertyLanding(int position) {
    Property property = null;
    for (Property p : properties) {
      if (p.getPosition() == position) {
        property = p;
        break;
      }
    }

    if (property == null) {
      return;
    }
    Player currentPlayer = players.getFirst();

    if (position == 0) {
      diceResultLabel.setText("Landed on START");
      currentPlayer.addMoney(200);
    } else if (position == 6) {
      diceResultLabel.setText("Landed on JAIL");
    } else if (position == 9 || position == 20) {
      diceResultLabel.setText("Landed on CHANCE");
      showCardViewer();
    } else if (position == 12) {
      diceResultLabel.setText("Landed on FREE PARKING");
    } else if (position == 18) {
      diceResultLabel.setText("GO TO JAIL!");
      sendPlayerToJail(currentPlayer);
    } else if (property.getPrice() > 0) {
      if (!property.isPurchased()) {
        offerPropertyPurchase(property, currentPlayer);
      } else if (property.getOwner() != currentPlayer) {
        payRent(property, currentPlayer);
      } else {
        diceResultLabel.setText("You already own " + property.getName());
      }
    }

    updatePlayerInfoDisplay(currentPlayer);
  }

  /**
   * Sends the player to jail.
   *
   * @param player the player to send to jail
   */
  private void sendPlayerToJail(Player player) {
    GridPane gridPane = new GridPane();

    player.setPosition(6);
    placePlayerPieceOnBoard(gridPane, player);

    // Show jail notification
    Stage jailStage = new Stage();
    jailStage.initOwner(primaryStage);
    jailStage.setTitle("Sent to Jail!");
    jailStage.initModality(Modality.APPLICATION_MODAL);

    VBox content = new VBox(15);
    content.setPadding(new Insets(20));
    content.setAlignment(Pos.CENTER);

    Label jailLabel = new Label(player.getName() + " has been sent to Jail!");
    jailLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

    Button okButton = new Button("OK");
    StyleUtils.styleNormalButton(okButton);
    okButton.setOnAction(e -> jailStage.close());

    content.getChildren().addAll(jailLabel, okButton);

    Scene scene = new Scene(content, 300, 150);
    jailStage.setScene(scene);
    jailStage.show();

    // Update UI
    diceResultLabel.setText(player.getName() + " was sent to Jail!");
  }

  private void offerPropertyPurchase(Property property, Player currentPlayer) {
    Stage purchaseStage = new Stage();
    purchaseStage.initOwner(primaryStage);
    purchaseStage.setTitle("Buy Property");
    purchaseStage.initModality(Modality.APPLICATION_MODAL);

    VBox content = new VBox(15);
    content.setPadding(new Insets(20));
    content.setAlignment(Pos.CENTER);

    Label infoLabel = new Label("Do you want to buy " + property.getName()
        + " for kr " + property.getPrice() + "?");
    infoLabel.setStyle("-fx-font-size: 16px");

    Rectangle colorSample = new Rectangle(50, 20);
    colorSample.setFill(javafx.scene.paint.Color.web(property.getColor()));

    Label moneyLabel = new Label("Your money: kr " + currentPlayer.getMoney());

    HBox buttons = new HBox(20);
    buttons.setAlignment(Pos.CENTER);

    Button buyButton = new Button("Buy");
    StyleUtils.styleNormalButton(buyButton);

    Button declineButton = new Button("Decline");
    StyleUtils.styleNormalButton(declineButton);

    buyButton.setDisable(currentPlayer.getMoney() < property.getPrice());

    buyButton.setOnAction(e -> {
      if (currentPlayer.subtractMoney(property.getPrice())) {
        property.setOwner(currentPlayer);
        diceResultLabel.setText("You bought " + property.getName());
        updatePropertyTileAppearance(property);
        updatePlayerInfoDisplay(currentPlayer);
        purchaseStage.close();
      }
    });

    declineButton.setOnAction(e -> {
      diceResultLabel.setText("You declined to buy " + property.getName());
      purchaseStage.close();
    });

    buttons.getChildren().addAll(buyButton, declineButton);
    content.getChildren().addAll(infoLabel, colorSample, moneyLabel, buttons);

    Scene scene = new Scene(content, 400, 250);
    purchaseStage.setScene(scene);
    purchaseStage.show();
  }

  private void payRent(Property property, Player currentPlayer) {
    int rentAmount = property.getRent();
    boolean canPay = currentPlayer.subtractMoney(rentAmount);

    if (canPay) {
      property.getOwner().addMoney(rentAmount);
      diceResultLabel.setText("Paid kr " + rentAmount + " rent to "
          + property.getOwner().getName());
    } else {
      diceResultLabel.setText("You can't afford the rent! Game Over!");
      // Handle bankruptcy
    }

    updatePlayerInfoDisplay(currentPlayer);
  }

  private void updatePropertyTileAppearance(Property property) {
    GridPane gridPane = new GridPane();

    for (Node node : gridPane.getChildren()) {
      int row = GridPane.getRowIndex(node);
      int col = GridPane.getColumnIndex(node);
      int pos = calculatePositionFromGrid(row, col);

      if (pos == property.getPosition()) {
        StackPane tile = (StackPane) node;
        // Add a small indicator of ownership
        Circle ownerIndicator = new Circle(5);
        ownerIndicator.setFill(Color.web(property.getColor()));
        ownerIndicator.setStroke(Color.BLACK);
        ownerIndicator.setTranslateX(45);
        ownerIndicator.setTranslateY(45);

        tile.getChildren().add(ownerIndicator);
        break;
      }
    }
  }

  private void updatePlayerInfoDisplay(Player player) {
    playerInfoLabel.setText(player.getName() + " - kr " + player.getMoney());
  }

  private int calculatePositionFromGrid(int row, int col) {
    // Inverse of calculatePosition
    if (row == 6) {
      return 6 - col;
    } else if (col == 0) {
      return 7 + (5 - row);
    } else if (row == 0) {
      return col == 6 ? 18 : 13 + (col - 1);
    } else if (col == 6) {
      return 19 + (row - 1);
    }
    return -1;
  }

  private void advanceToNextPlayer() {
    // Rotate the player list
    Player currentPlayer = players.removeFirst();
    players.add(currentPlayer);

    // Update current player display
    currentPlayerLabel.setText("Current Player: " + players.getFirst().getName());
    updatePlayerInfoDisplay(players.getFirst());
  }
}