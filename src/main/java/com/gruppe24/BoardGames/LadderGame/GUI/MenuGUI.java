package com.gruppe24.BoardGames.LadderGame.GUI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MenuGUI extends Application {

  @Override
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Game Meny");
    primaryStage.setX(250);
    primaryStage.setY(100);

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane,1000,700);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #0a1b5e;");

    Label title = new Label("GameMenu");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");


    //Buttons
    Button ladderButton = new Button("LadderGame");
    ladderButton.setOnAction(event -> System.out.println("Melding"));
    styleButton(ladderButton);

    Button game2Button = new Button("Different game");
    game2Button.setOnAction(event -> System.out.println("Game2!"));
    styleButton(game2Button);

    Button game3Button = new Button("Another Game");
    game3Button.setOnAction(event -> System.out.println("Game3!"));
    styleButton(game3Button);


    gridPane.add(title, 0, 0);
    gridPane.add(ladderButton, 0, 1);
    gridPane.add(game2Button, 0, 2);
    gridPane.add(game3Button, 0, 3);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void styleButton(Button button) {
    String normalStyle =
        "-fx-background-color: linear-gradient(to bottom, #4a6cd4, #283a60); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 8, 0, 2, 2);";

    String hoverStyle =
        "-fx-background-color: linear-gradient(to bottom, #5c7ee8, #2e4370); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 10px 20px; " +
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.5), 10, 0, 3, 3);";

    String pressedStyle =
        "-fx-background-color: linear-gradient(to top, #283a60, #3a5590); " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 16px; " +
            "-fx-padding: 12px 20px 8px 20px; " + // Endrer padding for å simulere dybde
            "-fx-border-radius: 5px; " +
            "-fx-background-radius: 5px; " +
            "-fx-border-color: #1f2a44; " +
            "-fx-border-width: 2px; " +
            "-fx-effect: innershadow(gaussian, rgba(0,0,0,0.7), 5, 0, 0, 0);";

    button.setStyle(normalStyle);

    button.setOnMouseEntered(e -> button.setStyle(hoverStyle));
    button.setOnMouseExited(e -> button.setStyle(normalStyle));
    button.setOnMousePressed(e -> button.setStyle(pressedStyle));
    button.setOnMouseReleased(e -> button.setStyle(hoverStyle));
  }

}
