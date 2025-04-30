package com.gruppe24.boardgames.tictactoe;

import com.gruppe24.utils.Validators;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class to represent the Tic Tac Toe application
 *
 * @Author Sigveer
 * @Date: 15.03.2025
 * @Version: 1.0
 */
public class TicTacToeApp extends Application {

  /**
   * Method to start the application.
   *
   * @param primaryStage is the stage to be shown
   */
  @Override
  public void start(Stage primaryStage) {
    if (primaryStage == null) {
      throw new IllegalArgumentException("primaryStage cannot be empty");
    }
    Validators.getLogger().log(Level.INFO, "TicTacToe started");

    Model model = new Model();
    Controller controller = new Controller(model);
    View view = new View(primaryStage, model, controller);

    controller.setView(view);
    view.initializeView();
  }

  /**
   * Main method to launch the application.
   *
   * @param args is the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}