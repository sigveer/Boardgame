package com.gruppe24.boardgames.monopolylite.view;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.boardgames.monopolylite.controller.MonopolyController;
import com.gruppe24.boardgames.monopolylite.model.Cards.ChanceCard;
import com.gruppe24.boardgames.monopolylite.model.Player;
import com.gruppe24.boardgames.monopolylite.model.Property;
import com.gruppe24.utils.StyleUtils;
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
  private final MonopolyController controller;
  private Label diceResultLabel;
  private Label playerInfoLabel;
  private Label currentPlayerLabel;
  private GridPane boardGridPane;

  /**
   * Constructor for View class.
   *
   * @param primaryStage the primary stage.
   */
  public View(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.controller = new MonopolyController();
  }

  /**
   * Initializes the view.
   */
  public void initializeView() {
    primaryStage.setTitle("Monopoly Lite");

    if (controller.getPlayers().isEmpty()) {
      controller.addPlayer(new Player("Player 1", 0));
      controller.addPlayer(new Player("Player 2", 1));
    }

    BorderPane mainLayout = new BorderPane();
    mainLayout.setStyle("-fx-background-color: #3a5ad7;");

    StackPane boardContainer = new StackPane();

    boardGridPane = new GridPane();
    boardGridPane.setAlignment(Pos.CENTER);

    boardContainer.getChildren().addAll(boardGridPane);
    VBox controlPanel = createControlPanel();

    mainLayout.setCenter(boardContainer);
    mainLayout.setRight(controlPanel);

    Scene scene = new Scene(mainLayout, 1200, 900);

    drawBoard();

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Creates the control panel.
   */
  private VBox createControlPanel() {
    VBox controlPanel = new VBox(15);
    controlPanel.setAlignment(Pos.TOP_CENTER);
    controlPanel.setPrefWidth(250);
    controlPanel.setMinWidth(200);
    StyleUtils.stylePanel(controlPanel);

    Player currentPlayer = controller.getCurrentPlayer();

    currentPlayerLabel = new Label("Current Player: "
        + (currentPlayer != null ? currentPlayer.getName() : ""));
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
    currentPlayerLabel.setWrapText(true);
    currentPlayerLabel.setMaxWidth(230);

    playerInfoLabel = new Label(currentPlayer != null
        ? currentPlayer.getName() + " - kr " + currentPlayer.getMoney() : "");
    playerInfoLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
    playerInfoLabel.setWrapText(true);
    playerInfoLabel.setMaxWidth(230);

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    Pane dicePane = new Pane();
    dicePane.setPrefHeight(70);

    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(dicePane));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new DashboardGui().start(primaryStage));
    StyleUtils.styleNormalButton(backToMenu);


    controlPanel.getChildren().addAll(
        backToMenu,
        currentPlayerLabel,
        playerInfoLabel,
        dicePane,
        diceResultLabel,
        diceRoll
    );
    return controlPanel;
  }

  /**
   * Rolls dice and moves player.
   */
  private void rollDiceAndMove(Pane dicePane) {
    int diceValue = controller.rollDiceAndMovePlayer();
    displayDiceRoll(diceValue, dicePane);

    Player currentPlayer = controller.getCurrentPlayer();
    placePlayerPieceOnBoard(currentPlayer);

    Property landedProperty = controller.getPropertyAtPosition(currentPlayer.getPosition());
    handlePropertyLanding(landedProperty);

    controller.advanceToNextPlayer();
    updatePlayerInfo();
  }

  /**
   * Handles property landing logic.
   */
  private void handlePropertyLanding(Property property) {
    if (property == null) {
      return;
    }

    Player currentPlayer = controller.getCurrentPlayer();
    int position = property.getPosition();

    if (position == 0) {
      diceResultLabel.setText("Landed on START");
    } else if (position == 6) {
      diceResultLabel.setText("Landed on JAIL");
    } else if (position == 9 || position == 20) {
      diceResultLabel.setText("Landed on CHANCE");
      showCardViewer();
    } else if (position == 12) {
      diceResultLabel.setText("Landed on FREE PARKING");
    } else if (position == 18) {
      diceResultLabel.setText("GO TO JAIL!");
      controller.sendPlayerToJail(currentPlayer);
      placePlayerPieceOnBoard(currentPlayer);
      showJailNotification(currentPlayer);
    } else if (property.getPrice() > 0) {
      if (!property.isPurchased()) {
        offerPropertyPurchase(property, currentPlayer);
      } else if (property.getOwner() != currentPlayer) {
        boolean rentPaid = controller.payRent(property, currentPlayer);
        diceResultLabel.setText(rentPaid
            ? "Paid kr " + property.getRent() + " rent to " + property.getOwner().getName()
            : "You can't afford the rent! Game Over!");
      } else {
        diceResultLabel.setText("You already own " + property.getName());
      }
    }

    updatePlayerInfo();
  }

  /**
   * Displays dice roll.
   */
  private void displayDiceRoll(int diceValue, Pane dicePane) {
    diceResultLabel.setText("Rolled: " + diceValue);

    int diceValue1 = controller.getDice().getDie(0);
    ImageView dice1Iv = new ImageView(new Image(controller.getDice().dicePath(diceValue1)));
    dice1Iv.setX(80);
    dice1Iv.setY(0);
    dice1Iv.setFitHeight(75);
    dice1Iv.setFitWidth(75);

    dicePane.getChildren().clear();
    dicePane.getChildren().addAll(dice1Iv);
  }

  /**
   * Updates player info in UI.
   */
  private void updatePlayerInfo() {
    Player current = controller.getCurrentPlayer();
    if (current != null) {
      currentPlayerLabel.setText("Current Player: " + current.getName());
      playerInfoLabel.setText(current.getName() + " - kr " + current.getMoney());
    }
  }

  /**
   * Displays the card viewer.
   */
  private void showCardViewer() {
    Stage cardViewerStage = new Stage();
    cardViewerStage.initOwner(primaryStage);
    cardViewerStage.setTitle("Card Viewer");
    cardViewerStage.initModality(Modality.APPLICATION_MODAL);
    cardViewerStage.setAlwaysOnTop(true);
    cardViewerStage.setResizable(false);

    BorderPane layout = new BorderPane();
    layout.setStyle("-fx-background-color: #efefef; -fx-padding: 20;");

    List<ChanceCard> chanceCardsList = controller.getCards().getChanceCards();
    if (chanceCardsList.isEmpty()) {
      return;
    }

    StackPane cardContainer = new StackPane();
    ChanceCard currentCard = controller.getCurrentChanceCard();
    cardContainer.getChildren().add(createChanceCardNode(currentCard));
    layout.setCenter(cardContainer);

    Button prevButton = new Button("◀");
    StyleUtils.styleNormalButton(prevButton);
    prevButton.setOnAction(e -> {
      ChanceCard prevCard = controller.getPreviousChanceCard();
      cardContainer.getChildren().clear();
      cardContainer.getChildren().add(createChanceCardNode(prevCard));
    });

    Button nextButton = new Button("▶");
    StyleUtils.styleNormalButton(nextButton);
    nextButton.setOnAction(e -> {
      ChanceCard nextCard = controller.getNextChanceCard();
      cardContainer.getChildren().clear();
      cardContainer.getChildren().add(createChanceCardNode(nextCard));
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
   * Creates a visual representation of a chance card.
   */
  private Node createChanceCardNode(ChanceCard chanceCard) {
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

  /**
   * Shows jail notification.
   */
  private void showJailNotification(Player player) {
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
  }

  /**
   * Offers property purchase UI.
   */
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
      if (controller.purchaseProperty(property)) {
        diceResultLabel.setText("You bought " + property.getName());
        drawBoard(); // Redraw the board to reflect ownership
        updatePlayerInfo();
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

  /**
   * Draws the monopoly board.
   */
  public void drawBoard() {
    boardGridPane.getChildren().clear();

    List<Property> properties = controller.getProperties();

    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        StackPane tile = new StackPane();

        if (i == 0 || i == 6 || j == 0 || j == 6) {
          tile.setPrefSize(120, 120);
        } else {
          tile.setPrefSize(80, 80);
        }

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
        boardGridPane.add(tile, j, i);
      }
    }

    // Place all players on the board
    for (Player player : controller.getPlayers()) {
      placePlayerPieceOnBoard(player);
    }
  }

  /**
   * Calculates position based on grid coordinates.
   */
  private int calculatePosition(int i, int j) {
    if (i == 6) {
      return 6 - j;
    } else if (j == 0) {
      return 7 + (5 - i);
    } else if (i == 0) {
      return j == 6 ? 18 : 13 + (j - 1);
    } else if (j == 6) {
      return 19 + (i - 1);
    } else {
      return -1; // Center of the board
    }
  }

  /**
   * Creates a property tile UI.
   */
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

    // If property is owned, add owner indicator
    if (property.isPurchased()) {
      updatePropertyTileOwnership(tile, property);
    }

    return tile;
  }

  /**
   * Adds ownership indicator to property tile.
   */
  private void updatePropertyTileOwnership(StackPane tile, Property property) {
    Circle ownerIndicator = new Circle(5);
    ownerIndicator.setFill(Color.RED); // Player indicator color
    ownerIndicator.setStroke(Color.BLACK);
    ownerIndicator.setTranslateX(45);
    ownerIndicator.setTranslateY(45);

    tile.getChildren().add(ownerIndicator);
  }

  /**
   * Places player piece on board with spesific algorytm for monopoly board.
   */
  public void placePlayerPieceOnBoard(Player player) {
    int position = player.getPosition();

    int row;
    int col;

    if (position <= 6) {
      row = 6;
      col = 6 - position;
    } else if (position <= 12) {
      row = 6 - (position - 6);
      col = 0;
    } else if (position <= 18) {
      row = 0;
      col = position - 12;
    } else {
      row = position - 18;
      col = 6;
    }
    addPlayerPieceToGrid(player, col, row);
  }

  /**
   * Adds player piece to grid.
   */
  private void addPlayerPieceToGrid(Player player, int col, int row) {
    if (player.getPlayerPiece() == null) {
      Image image = new Image("pictures/pieces/piece" + player.getIconIndex() + ".png");
      player.setIcon(image);
    }

    for (Node node : boardGridPane.getChildren()) {
      if (node instanceof StackPane
          && GridPane.getRowIndex(node) == row
          && GridPane.getColumnIndex(node) == col) {
        ((StackPane) node).getChildren().add(player.getPlayerPiece());
        return;
      }
    }
  }
}