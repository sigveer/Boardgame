package com.gruppe24.BoardGames.LadderGame.View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClassicSetup extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Laddergame setup...");
    primaryStage.setX(250);
    primaryStage.setY(100);

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 700);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #2e49ae;");

    //text
    Text text = new Text("How many players are there?:");
    text.setStyle("-fx-font-size: 20px; -fx-fill: white; -fx-font-weight: bold;");
    gridPane.add(text,1,1,6,1);

    //buttons
    Button oneUser = new Button("1");
    Button twoUser = new Button("2");
    Button threeUser = new Button("3");
    Button fourUser = new Button("4");
    gridPane.add(oneUser,1,5);
    gridPane.add(twoUser,1,6);
    gridPane.add(threeUser,1,7);
    gridPane.add(fourUser,1,8);


    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
