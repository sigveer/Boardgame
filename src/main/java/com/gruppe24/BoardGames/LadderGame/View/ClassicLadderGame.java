package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Controller.GameController;
import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ClassicLadderGame extends Application {

  private final Board board;
  private final GameController gameController;
  private List<Player> players;
  private static final int tileSize = 50;

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
    Scene scene = new Scene(gridPane,1000,700);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    Label title = new Label("Board Games!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    drawBoard(gridPane);

    //Dice button
    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> drawPlayer(gridPane,players));
    gridPane.add(diceRoll,11,0);


    primaryStage.setScene(scene);
    primaryStage.show();
  }



  //AI-basert for metoden
  public void drawBoard(GridPane gridPane){
    for(int row = 0; row < 10; row++){
      for(int col = 0; col < 9; col++){
        int index = row * 9 + col; //AI-hjelp - klakulerer index effektivt
        Rectangle tile = new Rectangle(tileSize,tileSize);

        if(board.getTile(index) instanceof LadderTile){
          tile.setFill(Color.GREEN);
        } else if(board.getTile(index) instanceof SnakeTile){
          tile.setFill(Color.RED);
        } else{
          tile.setFill(Color.LIGHTGRAY);
        }

        gridPane.add(tile, col, 9 - row); //9 - row inverserer brettet

      }
    }
  }
  //AI-based method
  public void drawPlayer(GridPane gridPane, List<Player> players){
    for(Player player : players){
      //gridPane.getChildren().remove(player.getPlayerPiece());
      int oldRow = player.getPosition() / 9;
      int oldCol = player.getPosition() % 9;

      gameController.handlePlayerTurn(player);
      int newRow = player.getPosition() / 9;
      int newCol = player.getPosition() % 9;

      //converting grid coordinates to pixel values (for animation)
      double startX = oldCol * tileSize;
      double startY = (9 - oldRow) * tileSize;
      double endX = newCol * tileSize;
      double endY = (9 - newRow) * tileSize;

      //startposition for playerpiece in animation
      player.getPlayerPiece().setTranslateX(startX);
      player.getPlayerPiece().setTranslateY(startY);

      //the animation
      TranslateTransition transition = new TranslateTransition(Duration.millis(500),player.getPlayerPiece());
      transition.setToX(endX);
      transition.setToY(endY);
      transition.setCycleCount(1);
      transition.setOnFinished(event -> {
        player.getPlayerPiece().setTranslateX(0);
        player.getPlayerPiece().setTranslateY(0);
        gridPane.getChildren().remove(player.getPlayerPiece());
        //The exact end-coordinate:
        gridPane.add((player.getPlayerPiece()),newCol,9-newRow);
      });

      transition.play();
    }
  }

}
