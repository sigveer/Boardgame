package com.gruppe24.boardgames.monopolylite.view;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.boardgames.laddergame.models.Dice;
import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.utils.StyleUtils;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;

public class View {

  private Stage primaryStage;
  private List<Player> players;
  private Label currentPlayerLabel;
  private Label diceResultLabel;
  private int currentPlayerIndex = 0;
  private final Dice dice = new Dice(1);
  private Button diceRollButton;

  public View(Stage primaryStage) {
    this.primaryStage = primaryStage;
    this.players = new ArrayList<>();

    // Initialize with a temporary player for testing
    Player tempPlayer = new Player("Player 1", 0);
    players.add(tempPlayer);
  }

  public void initializeView() {
    primaryStage.setTitle("Monopoly Lite");

    // Create main layout container using BorderPane
    BorderPane mainLayout = new BorderPane();
    mainLayout.setStyle("-fx-background-color: #3a5ad7;");

    // Board container (left/center section)
    StackPane boardContainer = new StackPane();

    // GridPane for tiles
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);

    // Add components to board container
    boardContainer.getChildren().addAll(gridPane);

    // Control panel (right section)
    VBox controlPanel = new VBox(15);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.setPrefWidth(250);
    controlPanel.setMinWidth(200);
    StyleUtils.stylePanel(controlPanel);

    // Set up labels
    currentPlayerLabel = new Label("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
    currentPlayerLabel.setWrapText(true);
    currentPlayerLabel.setMaxWidth(230);

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    // Dice display area
    Pane dicePane = new Pane();
    dicePane.setPrefHeight(150);

    // Buttons
    Button diceRoll = new Button("Roll Dice");
    diceRollButton = diceRoll;
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage, dicePane));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new DashboardGui().start(primaryStage));
    StyleUtils.styleNormalButton(backToMenu);

    controlPanel.getChildren().addAll(
        currentPlayerLabel,
        diceResultLabel,
        dicePane,
        diceRoll,
        backToMenu
    );

    mainLayout.setCenter(boardContainer);
    mainLayout.setRight(controlPanel);

    Scene scene = new Scene(mainLayout, 1000, 850);

    drawBoard(gridPane);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void drawBoard(GridPane gridPane) {
    // Implement board drawing logic here
    // For now, let's add a simple placeholder
    Label placeholder = new Label("Monopoly Board Coming Soon");
    placeholder.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");
    gridPane.add(placeholder, 0, 0);
  }

  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage, Pane dicePane) {
    // Display dice roll
    int diceValue = rollAndDisplayDice(dicePane);

    // Simple implementation for now - just update label
    diceResultLabel.setText("Rolled: " + diceValue);
  }

  private int rollAndDisplayDice(Pane dicePane) {
    int diceValue = dice.rollSum();
    int diceValue1 = dice.getDie(0);

    ImageView dice1Iv = new ImageView(new Image(dice.dicePath(diceValue1)));
    dice1Iv.setX(80);
    dice1Iv.setY(0);
    dice1Iv.setFitHeight(75);
    dice1Iv.setFitWidth(75);

    diceResultLabel.setText("Rolled: " + diceValue);
    dicePane.getChildren().clear();
    dicePane.getChildren().addAll(dice1Iv);

    return diceValue;
  }
}