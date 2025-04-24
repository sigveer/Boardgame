package com.gruppe24.boardgames.laddergame.view;

import static com.gruppe24.utils.StyleUtils.styleNormalButton;

import com.gruppe24.boardgames.MenuGui;
import com.gruppe24.boardgames.laddergame.models.board.BoardType;
import com.gruppe24.utils.Validators;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class represents the GUI for the Ladder Game menu.
 * It allows the user to choose between different game modes.
 */
public class LadderGameMenuGui extends Application {

  /**
   * The main method to launch the Ladder Game menu GUI.
   *
   * @param primaryStage the primary stage for this application.
   */
  @Override
  public void start(Stage primaryStage) {
    if (primaryStage == null) {
      throw new IllegalArgumentException("Parameter Stage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "Ladder Menu started");

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
      LadderGameSetup ladderGameSetup = new LadderGameSetup();
      ladderGameSetup.setBoardType(BoardType.CLASSIC);
      ladderGameSetup.start(primaryStage);
    });
    styleNormalButton(classicButton);

    Button specialTileButton = new Button("Special Mode");
    specialTileButton.setOnAction(event -> {
      LadderGameSetup ladderGameSetup = new LadderGameSetup();
      ladderGameSetup.setBoardType(BoardType.SPECIAL);
      ladderGameSetup.start(primaryStage);
    });
    styleNormalButton(specialTileButton);

    Button textModeButton = new Button("Text Mode");
    textModeButton.setOnAction(event -> {
      new Thread(() -> new TextBasedLadderGame().setUpPlayers()).start(); //AI-based
      Platform.exit();
    });
    styleNormalButton(textModeButton);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> new MenuGui().start(primaryStage));
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