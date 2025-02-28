package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.TileAction;
import com.gruppe24.BoardGames.MenuGUI;
import com.gruppe24.Utils.StyleUtils;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main GUI class for the Snakes and Ladders game
 *
 * @author Refactored by Claude
 * @date 28.02.2025
 * @version 2.0
 */
public class ClassicGUI extends Application {

  private final List<Player> players = new ArrayList<>();
  private final Board gameBoard = new Board();

  private GridPane boardGrid;
  private Button rollButton;

  private final Map<Player, Circle> playerTokens = new HashMap<>();

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Snakes and Ladders");

    BorderPane mainLayout = new BorderPane();
    mainLayout.setPadding(new Insets(10));
    mainLayout.setStyle("-fx-background-color: #2e49ae;");

    setupBoardGrid();
    mainLayout.setCenter(boardGrid);

    VBox controlsPane = createControlsPane();
    mainLayout.setRight(controlsPane);

    VBox setupPane = createSetupPane(primaryStage, mainLayout);

    Scene scene = new Scene(setupPane, 1000, 850);
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  private VBox createSetupPane(Stage primaryStage, BorderPane gameLayout) {
    VBox setupPane = new VBox(20);
    setupPane.setAlignment(Pos.CENTER);
    setupPane.setStyle("-fx-background-color: #2e49ae;");
    setupPane.setPadding(new Insets(20));

    Label titleLabel = new Label("LaddersGame");
    titleLabel.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    Label promptLabel = new Label("How many players? (1-4)");
    promptLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff;");

    HBox playerCountBox = new HBox(10);
    playerCountBox.setAlignment(Pos.CENTER);

    for (int i = 1; i <= 4; i++) {
      final int playerCount = i;
      Button countButton = new Button(String.valueOf(i));
      StyleUtils.styleButton(countButton);
      countButton.setOnAction(e -> showPlayerNameSetup(primaryStage, gameLayout, playerCount));
      playerCountBox.getChildren().add(countButton);
    }

    Button backButton = new Button("Back to Menu");
    StyleUtils.styleButton(backButton);
    backButton.setOnAction(e -> new MenuGUI().start(primaryStage));

    setupPane.getChildren().addAll(titleLabel, promptLabel, playerCountBox, backButton);
    return setupPane;
  }


  private void showPlayerNameSetup(Stage primaryStage, BorderPane gameLayout, int playerCount) {
    VBox nameSetupPane = new VBox(15);
    nameSetupPane.setAlignment(Pos.CENTER);
    nameSetupPane.setStyle("-fx-background-color: #2e49ae;");
    nameSetupPane.setPadding(new Insets(20));

    Label titleLabel = new Label("Enter Player Names");
    titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    List<TextField> nameFields = new ArrayList<>();

    for (int i = 1; i <= playerCount; i++) {
      HBox playerBox = new HBox(10);
      playerBox.setAlignment(Pos.CENTER);

      Label playerLabel = new Label("Player " + i + ":");
      playerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #ffffff;");

      TextField nameField = new TextField("Player " + i);
      nameField.setStyle("-fx-font-size: 18px;");
      nameFields.add(nameField);

      playerBox.getChildren().addAll(playerLabel, nameField);
      nameSetupPane.getChildren().add(playerBox);
    }

    Button startButton = new Button("Start Game");
    StyleUtils.styleButton(startButton);
    startButton.setOnAction(e -> {

      players.clear();
      for (TextField field : nameFields) {
        String name = field.getText().trim();
        if (name.isEmpty()) {
          name = "Player " + (players.size() + 1);
        }
        players.add(new Player(name));
      }

      Scene gameScene = new Scene(gameLayout, 1000, 850);
      primaryStage.setScene(gameScene);
    });

    nameSetupPane.getChildren().add(startButton);

    Scene nameScene = new Scene(nameSetupPane, 1000, 850);
    primaryStage.setScene(nameScene);
  }


  private VBox createControlsPane() {
    VBox controlsPane = new VBox(20);
    controlsPane.setPadding(new Insets(10));
    controlsPane.setAlignment(Pos.BOTTOM_CENTER);
    controlsPane.setPrefWidth(250);

    rollButton = new Button("Roll Dice");
    StyleUtils.styleButton(rollButton);

    Button quitButton = new Button("Quit");
    StyleUtils.styleButton(quitButton);
    quitButton.setOnAction(e -> {
      Stage stage = (Stage) quitButton.getScene().getWindow();
      new MenuGUI().start(stage);
    });

    controlsPane.getChildren().addAll(rollButton, quitButton);
    return controlsPane;
  }


  private void setupBoardGrid() {
    boardGrid = new GridPane();
    boardGrid.setAlignment(Pos.CENTER);
    boardGrid.setHgap(0);
    boardGrid.setVgap(0);

    int tileSize = 80;
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 9; col++) {

        int tileNumber;
        if (row % 2 == 0) {
          tileNumber = 90 - (row * 9) - col;
        } else {
          tileNumber = 90 - (row * 9) - 8 + col;
        }

        Rectangle tile = new Rectangle(tileSize, tileSize);

        TileAction tileAction = gameBoard.getTile(tileNumber);

        if (tileAction instanceof LadderTile) {
          tile.setFill(Color.GREEN);
        } else if (tileAction instanceof SnakeTile) {
          tile.setFill(Color.RED);
        } else {
          tile.setFill(Color.WHITE);
        }

        tile.setStroke(Color.BLACK);

        boardGrid.add(tile, col, row);

        Label numberLabel = new Label(String.valueOf(tileNumber));
        numberLabel.setStyle("-fx-font-weight: bold;");
        numberLabel.setTranslateX(5);
        numberLabel.setTranslateY(5);
        boardGrid.add(numberLabel, col, row);
      }
    }
  }



  public static void main(String[] args) {
    launch(args);
  }
}