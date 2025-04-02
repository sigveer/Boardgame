package com.gruppe24.BoardGames.LadderGame.View;

import static com.gruppe24.Utils.StyleUtils.styleNormalButton;

import com.gruppe24.BoardGames.LadderGame.Models.Board.BoardType;
import com.gruppe24.BoardGames.MenuGUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LadderGameMenuGUI extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Ladder Game");

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 850);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #2e49ae;");

    Label title = new Label("Snakes and Ladders");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    Button classicButton = new Button("Classic Mode");
    classicButton.setOnAction(event -> {
      Setup setup = new Setup();
      setup.setBoardType(BoardType.CLASSIC);
      setup.start(primaryStage);
    });
    styleNormalButton(classicButton);

    Button specialTileButton = new Button("Special Mode");
    specialTileButton.setOnAction(event -> {
      Setup setup = new Setup();
      setup.setBoardType(BoardType.SPECIAL);
      setup.start(primaryStage);
    });
    styleNormalButton(specialTileButton);

    Button textModeButton = new Button("Text Mode");
    textModeButton.setOnAction(event -> {
      new Thread(() -> new TextBasedLadderGame().setUpPlayers()).start(); //AI-based
      Platform.exit();
    });
    styleNormalButton(textModeButton);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new MenuGUI().start(primaryStage));
    styleNormalButton(backToMenu);

    gridPane.add(title, 0, 0);
    gridPane.add(classicButton, 0, 1);
    gridPane.add(specialTileButton, 0, 2);
    gridPane.add(textModeButton, 0, 3);
    gridPane.add(backToMenu, 0, 4);

    primaryStage.setScene(scene);
    primaryStage.show();
  }


}