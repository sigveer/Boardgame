package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.Utils.StyleUtils;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WinnerScreen extends Application {

  private Player player;

  public WinnerScreen(Player player){
    this.player = player;
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Winner!");
    primaryStage.setX(250);
    primaryStage.setY(100);

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane,1000,700);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    Label winnerLabel = new Label("WINNER: " + player.getName());
    winnerLabel.setFont(new Font("Arial",40));
    winnerLabel.setTextFill(Color.WHITE);
    gridPane.add(winnerLabel,1,1);

    Button exitButton = new Button("Exit");
    StyleUtils.styleNormalButton(exitButton);
    exitButton.setOnAction(event -> new LadderGameMenuGUI().start(primaryStage));
    gridPane.add(exitButton,1,2);

    primaryStage.setScene(scene);
    primaryStage.show();

  }

}
