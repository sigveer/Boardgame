package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Controller.GameController;
import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderDownTile;
import com.gruppe24.Utils.StyleUtils;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClassicLadderGame extends Application {

  private final Board board;
  private final GameController gameController;
  private List<Player> players;
  private static final int tileSize = 75;
  private int currentPlayerIndex = 0;
  private Label diceResultLabel;
  private Label currentPlayerLabel;
  private Label snakeOrLadderCheck;
  private final Dice dice = new Dice(1);

  public ClassicLadderGame(List<Player> players) {
    this.gameController = new GameController();
    this.board = new Board();
    this.players = players;
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Laddergame Classic");
    primaryStage.setX(250);
    primaryStage.setY(100);

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 800);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(1);
    gridPane.setHgap(1);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    Label title = new Label("Board Games!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    drawBoard(gridPane); //drawing the board

    currentPlayerLabel = new Label("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    snakeOrLadderCheck = new Label("");
    snakeOrLadderCheck.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> {new LadderGameMenuGUI().start(primaryStage);});
    StyleUtils.styleNormalButton(backToMenu);

    VBox controlPanel = new VBox(10);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.getChildren().addAll(currentPlayerLabel, diceResultLabel, snakeOrLadderCheck, diceRoll, backToMenu);

    gridPane.add(controlPanel, 11, 0, 1, 5);

    initializePlayerPositions(gridPane);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void initializePlayerPositions(GridPane gridPane) {
    for (Player player : players) {
      if (player.getPosition() > 0) {
        updatePlayerPosition(gridPane, player);
      }
    }
  }

  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage) {
    Player currentPlayer = players.get(currentPlayerIndex);

    int diceValue = dice.rollSum();

    diceResultLabel.setText(currentPlayer.getName() + " rolled: " + diceValue);

    gameController.handlePlayerTurn(currentPlayer, diceValue);
    int position = currentPlayer.getPosition();

    if (gameController.checkAndHandleWin(position)) {
      new ClassicWinnerScreen(currentPlayer).start(primaryStage);
      return;
    }

    updatePlayerPosition(gridPane, currentPlayer);

    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();

    currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
  }

  private void updatePlayerPosition(GridPane gridPane, Player player) {
    int position = player.getPosition();

    if (position > 0) {
      int row = 9 - (position - 1) / 9;
      int col;

      if ((9 - row) % 2 == 0) {
        col = (position - 1) % 9;
      } else {
        col = 8 - (position - 1) % 9;
      }

      if(gameController.getCheckTileType() == 0){
        snakeOrLadderCheck.setText("");
      }
      else if(gameController.getCheckTileType() == 1){
        snakeOrLadderCheck.setText("Ladder!");
      }
      else if(gameController.getCheckTileType() == 2){
        snakeOrLadderCheck.setText("Snake...");
      }
      gridPane.getChildren().remove(player.getPlayerPiece());
      gridPane.add(player.getPlayerPiece(), col, row);
    }
  }

  public void drawBoard(GridPane gridPane) {
    for (int row = 9; row >= 0; row--) {
      for (int col = 0; col < 9; col++) {
        int tileNumber;
        if ((9 - row) % 2 == 0) {
          tileNumber = (9 - row) * 9 + col + 1;
        } else {
          tileNumber = (9 - row + 1) * 9 - col;
        }

        StackPane stackPane = new StackPane();
        Rectangle tile = new Rectangle(tileSize, tileSize);
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(1);

        if (board.getTile(tileNumber) instanceof LadderUpTile) {
          tile.setFill(Color.GREEN);
        } else if (board.getTile(tileNumber) instanceof LadderDownTile) {
          tile.setFill(Color.RED);
        } else {
          tile.setFill(Color.WHITE);
        }

        Label numberLabel = new Label(Integer.toString(tileNumber));
        numberLabel.setStyle("-fx-font-weight: bold;");
        numberLabel.setTranslateX(26);
        numberLabel.setTranslateY(30);

        stackPane.getChildren().addAll(tile, numberLabel);
        stackPane.setAlignment(Pos.CENTER);

        gridPane.add(stackPane, col, row);
      }
    }
  }
}