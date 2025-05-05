package com.gruppe24.boardgames.monopolylite.view;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.boardgames.commonclasses.CommonDice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.boardgames.monopolylite.model.Property;
import com.gruppe24.utils.StyleUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

  private final Stage primaryStage;
  private final List<Player> players;
  private Label currentPlayerLabel;
  private Label diceResultLabel;
  private final int currentPlayerIndex = 0;
  private final CommonDice dice = new CommonDice(1);
  private Button diceRollButton;
  private Cards cards;
  private int currentCardIndex = 0;

  private List<Property> properties;
  private Button checkPropertyButton;
  private Map<Integer, Property> propertyMap = new HashMap<>();

  public View(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.players = new ArrayList<>();

    // Testplayer
    Player tempPlayer = new Player("Player 1", 0);
    players.add(tempPlayer);
  }

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

    currentPlayerLabel = new Label("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
    currentPlayerLabel.setWrapText(true);
    currentPlayerLabel.setMaxWidth(230);

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    // Dice display area
    Pane dicePane = new Pane();
    dicePane.setPrefHeight(70);

    // Buttons
    Button diceRoll = new Button("Roll Dice");
    diceRollButton = diceRoll;
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage, dicePane));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new DashboardGui().start(primaryStage));
    StyleUtils.styleNormalButton(backToMenu);

    checkPropertyButton = new Button("Check Property");
    StyleUtils.styleNormalButton(checkPropertyButton);
    checkPropertyButton.setOnAction(event -> {
    });

    //midlertidig løsning
    Button viewCardsButton = new Button("View Cards");
    StyleUtils.styleNormalButton(viewCardsButton);
    viewCardsButton.setOnAction(event -> showCardViewer());

    controlPanel.getChildren().addAll(
        backToMenu,
        currentPlayerLabel,
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

  //midlertidig
  private void showCardViewer() {
    Stage cardViewerStage = new Stage();
    cardViewerStage.initOwner(primaryStage);
    cardViewerStage.setTitle("Card Viewer");

    cardViewerStage.initModality(Modality.APPLICATION_MODAL); // Block input to other windows
    cardViewerStage.setAlwaysOnTop(true); // Keep the card viewer on top
    cardViewerStage.setResizable(false); // Prevent resizing

    BorderPane layout = new BorderPane();
    layout.setStyle("-fx-background-color: #efefef; -fx-padding: 20;");

    List<Node> allCards = cards.getAllCards();
    if (allCards.isEmpty()) {
      return;  // No cards to display
    }

    StackPane cardContainer = new StackPane();
    cardContainer.getChildren().add(allCards.get(currentCardIndex));
    layout.setCenter(cardContainer);

    Button prevButton = new Button("◀");
    StyleUtils.styleNormalButton(prevButton);
    prevButton.setOnAction(e -> {
      currentCardIndex = (currentCardIndex - 1 + allCards.size()) % allCards.size();
      cardContainer.getChildren().clear();
      cardContainer.getChildren().add(allCards.get(currentCardIndex));
    });

    Button nextButton = new Button("▶");
    StyleUtils.styleNormalButton(nextButton);
    nextButton.setOnAction(e -> {
      currentCardIndex = (currentCardIndex + 1) % allCards.size();
      cardContainer.getChildren().clear();
      cardContainer.getChildren().add(allCards.get(currentCardIndex));
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

  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage, Pane dicePane) {
    int diceValue = rollAndDisplayDice(dicePane);

    diceResultLabel.setText("Rolled: " + diceValue);
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

  public void drawBoard(GridPane gridPane) {
    gridPane.getChildren().clear();

    List<Property> properties = createProperties();

    // 7x7 grid
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
                "-fx-background-color: #f0f0f0; -fx-border-color: #cccccc; -fx-border-width: 2px;");
          }
        } else {
          tile.setStyle(
              "-fx-background-color: #c6efd1; -fx-border-color: transparent; -fx-border-width: 2px;");
        }
        gridPane.add(tile, j, i);
      }
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
}