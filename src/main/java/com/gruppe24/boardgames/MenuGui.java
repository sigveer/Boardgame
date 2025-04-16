package com.gruppe24.boardgames;

import static com.gruppe24.utils.StyleUtils.styleNormalButton;

import com.gruppe24.boardgames.laddergame.view.LadderGameMenuGui;
import com.gruppe24.boardgames.tictactoe.TicTacToeApp;
import com.gruppe24.utils.Validators;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * This class is the main menu for the game. It allows the user to select which game to play.
 */
public class MenuGui extends Application {

  /**
   * The main method is the entry point of the application.
   *
   * @param primaryStage the primary stage for this application.
   */
  @Override
  public void start(Stage primaryStage) {
    if (primaryStage == null) {
      throw new IllegalArgumentException("Parameter Stage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "Menu started");

    primaryStage.setTitle("GameMenu");
    primaryStage.setX(250);
    primaryStage.setY(100);

    GridPane gridPane = new GridPane();
    Scene scene = new Scene(gridPane, 1000, 850);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(25);
    gridPane.setHgap(20);
    gridPane.setStyle("-fx-background-color: #0a1b5e;");

    Label title = new Label("Board Games!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");


    //Buttons
    Button ladderButton = new Button("LadderGame");
    ladderButton.setOnAction(event -> new LadderGameMenuGui().start(primaryStage));
    styleNormalButton(ladderButton);

    Button game2Button = new Button("Different game");
    game2Button.setOnAction(event -> System.out.println("Game2!"));
    styleNormalButton(game2Button);

    Button ticTacToeApp = new Button("TicTacToe");
    ticTacToeApp.setOnAction(event -> new TicTacToeApp().start(primaryStage));
    styleNormalButton(ticTacToeApp);


    gridPane.add(title, 0, 0);
    gridPane.add(ladderButton, 0, 1);
    gridPane.add(game2Button, 0, 2);
    gridPane.add(ticTacToeApp, 0, 3);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

}
