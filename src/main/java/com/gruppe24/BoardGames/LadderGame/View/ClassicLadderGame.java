package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Controller.GameController;
import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClassicLadderGame extends Application {

  private final Board board;
  private final GameController gameController;
  private List<Player> players;
  private static final int tileSize = 75;

  public ClassicLadderGame(List<Player> players){
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
    Scene scene = new Scene(gridPane,1000,800);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(1);
    gridPane.setHgap(1);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    Label title = new Label("Board Games!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    drawBoard(gridPane);

    //Dice button
    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> drawPlayer(gridPane,players,primaryStage));
    gridPane.add(diceRoll,11,0);


    primaryStage.setScene(scene);
    primaryStage.show();
  }


  public void drawBoard(GridPane gridPane) {
    for (int row = 0; row < 10; row++) {
      for (int col = 0; col < 9; col++) {
        int tileNumber;
        if (row % 2 == 0) {
          tileNumber = 90 - (row * 9) - col;
        } else {
          tileNumber = 90 - (row * 9) - 8 + col;
        }

        javafx.scene.layout.StackPane tilePane = new javafx.scene.layout.StackPane();
        Rectangle tile = new Rectangle(tileSize, tileSize);
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(1);

        if (board.getTile(tileNumber) instanceof LadderTile) {
          tile.setFill(Color.GREEN);
        } else if (board.getTile(tileNumber) instanceof SnakeTile) {
          tile.setFill(Color.RED);
        } else {
          tile.setFill(Color.WHITE);
        }

        Label numberLabel = new Label(Integer.toString(tileNumber));
        numberLabel.setStyle("-fx-font-weight: bold;");
        numberLabel.setTranslateX(5);
        numberLabel.setTranslateY(5);

        tilePane.getChildren().addAll(tile, numberLabel);
        tilePane.setAlignment(Pos.CENTER);

        gridPane.add(tilePane, col, row);
      }
    }
  }

  public void drawPlayer(GridPane gridPane, List<Player> players, Stage primaryStage) {
    for (Player player : players) {
      gameController.handlePlayerTurn(player);
      int position = player.getPosition();

      if (gameController.checkAndHandleWin(position)) {
        new ClassicWinnerScreen(player).start(primaryStage);
        return;
      }

      int row = 9 - (position - 1) / 9;
      int col;
      if (row % 2 == 0) {
        col = (position - 1) % 9;
      } else {
        col = 8 - (position - 1) % 9;
      }

      gridPane.getChildren().remove(player.getPlayerPiece());
      gridPane.add(player.getPlayerPiece(), col, row);
    }
  }

}
