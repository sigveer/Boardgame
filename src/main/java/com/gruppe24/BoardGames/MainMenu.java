package com.gruppe24.BoardGames;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {

  public void start(Stage stage) {
    Button ladderGameButton = new Button("Ladder Game");
    Button exampleGameButton = new Button("Example Game");
    Button exitButton = new Button("Exit");

    ladderGameButton.setOnAction(e -> {
      System.out.println("Starting Ladder Game..."); // replace with method that starts Ladder Game
    });

    exampleGameButton.setOnAction(e -> {
      System.out.println("Starting Example Game..."); // replace with method that starts Example Game
    });

    exitButton.setOnAction(e -> {
      System.out.println("Exiting...");
      stage.close();
    });

    VBox vbox = new VBox(20, ladderGameButton, exampleGameButton, exitButton);

    Scene scene = new Scene(vbox, 1000, 600);
    stage.setTitle("Board Games Menu");
    stage.setScene(scene);
    stage.show();
  }
}
