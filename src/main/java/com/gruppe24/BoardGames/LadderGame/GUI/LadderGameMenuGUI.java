package com.gruppe24.BoardGames.LadderGame.GUI;

import static com.gruppe24.Utils.JavaFX_GUI.styleButton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LadderGameMenuGUI extends Application {

  public void start(Stage primaryStage) {
    primaryStage.setTitle("Ladder game");
    primaryStage.setX(250);
    primaryStage.setY(100);

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 700);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #0a1b5e;");

    Label title = new Label("LadderGame");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    //Buttons
    Button ladderButton = new Button("Classic");
    ladderButton.setOnAction(event -> System.out.println("Classic"));
    styleButton(ladderButton);

    Button game2Button = new Button("Crazy Dice");
    game2Button.setOnAction(event -> System.out.println("Crazy Dice"));
    styleButton(game2Button);

    Button backToMenu = new Button("Back to menu");
    backToMenu.setOnAction(event -> new MenuGUI().start(primaryStage));
    styleButton(backToMenu);

    gridPane.add(title, 0, 0);
    gridPane.add(ladderButton, 0, 1);
    gridPane.add(game2Button, 0, 2);
    gridPane.add(backToMenu, 0, 3);

    primaryStage.setScene(scene);
    primaryStage.show();

  }

}