package com.gruppe24.BoardGames.TicTacToe;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class to represent the Tic Tac Toe application
 */
public class TicTacToeApp extends Application {

  /**
   * Method to start the application
   *
   * @param primaryStage is the stage to be shown
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  @Override
  public void start(Stage primaryStage) {
    Model model = new Model();
    Controller controller = new Controller(model);
    View view = new View(primaryStage, model, controller);

    controller.setView(view);
    view.initializeView();
  }

  /**
   * Main method to launch the application
   *
   * @param args is the command line arguments
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public static void main(String[] args) {
    launch(args);
  }
}