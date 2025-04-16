package com.gruppe24.boardgames.laddergame.view;

import com.gruppe24.boardgames.laddergame.models.Player;
import com.gruppe24.utils.StyleUtils;
import com.gruppe24.utils.Validators;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class represents the winner screen of the game.
 * It displays the name of the winning player and provides an exit button to return to
 * the main menu.
 */
public class WinnerScreen extends Application {

  private final Player player;

  /**
   * Constructor for the WinnerScreen class.
   *
   * @param player The player who won the game.
   * @throws IllegalArgumentException if the player is null.
   */
  public WinnerScreen(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("Parameter player cannot be empty");
    }
    this.player = player;
  }

  @Override
  public void start(Stage primaryStage) {
    if (primaryStage == null) {
      throw new IllegalArgumentException("Parameter Stage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "Winner screen displayed");

    primaryStage.setTitle("Winner!");
    primaryStage.setX(250);
    primaryStage.setY(100);

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 700);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    Label winnerLabel = new Label("WINNER: " + player.getName());
    winnerLabel.setFont(new Font("Arial", 40));
    winnerLabel.setTextFill(Color.WHITE);
    gridPane.add(winnerLabel, 1, 1);

    Button exitButton = new Button("Exit");
    StyleUtils.styleNormalButton(exitButton);
    exitButton.setOnAction(event -> new LadderGameMenuGui().start(primaryStage));
    gridPane.add(exitButton, 1, 2);

    primaryStage.setScene(scene);
    primaryStage.show();

  }

}
