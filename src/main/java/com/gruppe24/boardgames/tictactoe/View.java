package com.gruppe24.boardgames.tictactoe;

import com.gruppe24.boardgames.DashboardGui;
import com.gruppe24.utils.StyleUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * {@code View} is the view class for the Tic Tac Toe game.
 *
 */
public class View {
  private final Stage primaryStage;
  private final Button[][] boardButtons = new Button[3][3];
  private final Model model;
  private final Controller controller;

  /**
   * Constructor for the view class.
   *
   * @param primaryStage is the stage to be shown
   * @param model is the model for the game
   * @param controller is the controller for the game
   */
  public View(Stage primaryStage, Model model, Controller controller) {
    this.primaryStage = primaryStage;
    this.model = model;
    this.controller = controller;
  }

  /**
   * Method to initialize the view.
   */
  public void initializeView() {
    GridPane background = new GridPane();
    background.setAlignment(Pos.CENTER);
    background.setVgap(25);
    background.setHgap(20);
    background.setStyle("-fx-background-color: #2e49ae;");

    GridPane boardgrid = new GridPane();
    boardgrid.setAlignment(Pos.CENTER);

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        boardButtons[row][col] = new Button();
        boardButtons[row][col].setPrefSize(200, 200);
        boardButtons[row][col].setStyle("-fx-font-size: 50px;");

        StyleUtils.styletttButton(boardButtons[row][col]);

        final int finalRow = row;
        final int finalCol = col;

        boardButtons[row][col].setOnAction(e -> controller.handleClick(finalRow, finalCol));

        boardgrid.add(boardButtons[row][col], col, row);
      }
    }

    GridPane menuPanel = new GridPane();
    menuPanel.setAlignment(Pos.CENTER);
    menuPanel.setVgap(20);

    Button restartButton = new Button("Restart");
    StyleUtils.styleNormalButton(restartButton);
    restartButton.setOnAction(e -> controller.restartGame());

    Button quitButton = new Button("Quit");
    StyleUtils.styleNormalButton(quitButton);
    quitButton.setOnAction(e -> new DashboardGui().start(primaryStage));

    menuPanel.add(restartButton, 0, 0);
    menuPanel.add(quitButton, 0, 1);

    background.add(boardgrid, 0, 0);
    background.add(menuPanel, 1, 0);

    Scene scene = new Scene(background, 1000, 700);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Tic Tac Toe");
    primaryStage.show();
  }

  /**
   * Method to update the board.
   */
  public void updateBoard() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        boardButtons[row][col].setText(model.getCell(row, col));
        StyleUtils.styletttButton(boardButtons[row][col]);
      }
    }
  }

  /**
   * Method to show an alert.
   *
   * @param message is the message to be shown
   */
  public void showAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}