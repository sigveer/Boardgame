package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Controller.GameController;
import com.gruppe24.BoardGames.LadderGame.Models.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.LadderTile;
import com.gruppe24.BoardGames.LadderGame.Models.Tile.SnakeTile;
import java.util.ArrayList;
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
  private static final int tileSize = 50;

  public ClassicLadderGame(){
    this.gameController = new GameController();
    this.board = new Board();
    this.players = new ArrayList<>();
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
    drawPlayer(gridPane,players);

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
  //AI-støtte for metoden
  public void drawPlayer(GridPane gridPane, List<Player> players){
    for(Player player : players){
      gameController.handlePlayerTurn(player);
      gridPane.getChildren().remove(player.getPlayerPiece());
      gridPane.getChildren().add((player.getPlayerPiece()));
    }
  }

}
