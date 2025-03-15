package com.gruppe24.BoardGames.TicTacToe;

import com.gruppe24.BoardGames.MenuGUI;
import com.gruppe24.Utils.StyleUtils;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * Class to represent the Tic Tac Toe application
 *
 * @Author Sigveer
 * @Date: 15.03.2025
 * @Version: 1.0
 */
public class TicTacToeApp extends Application {
  private final Button[][] board = new Button[3][3];
  private boolean xTurn = true;
  private boolean gameOver = false;


  /**
   * Method to start the application
   *
   * @param primaryStage is the stage to be shown
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  @Override
  public void start(javafx.stage.Stage primaryStage) {
    GridPane background = new GridPane();
    background.setAlignment(Pos.CENTER);
    background.setVgap(25);
    background.setHgap(20);
    background.setStyle("-fx-background-color: #2e49ae;");

    GridPane boardgrid = new GridPane();
    boardgrid.setAlignment(Pos.CENTER);

    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col] = new Button();
        board[row][col].setPrefSize(200, 200);
        board[row][col].setStyle("-fx-font-size: 50px;");

        StyleUtils.styleTTTButton(board[row][col]);

        final int finalRow = row;
        final int finalCol = col;

        board[row][col].setOnAction(e -> handleClick(finalRow, finalCol));

        boardgrid.add(board[row][col], col, row);
      }
    }

    GridPane menuPanel = new GridPane();
    menuPanel.setAlignment(Pos.CENTER);
    menuPanel.setVgap(20);

    Button restartButton = new Button("Restart");
    StyleUtils.styleNormalButton(restartButton);
    restartButton.setOnAction(e -> restartGame());

    Button quitButton = new Button("Quit");
    StyleUtils.styleNormalButton(quitButton);
    quitButton.setOnAction(e -> new MenuGUI().start(primaryStage));

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
   * Method to handle the click event on the board
   *
   * @param row is the row of the button
   * @param col is the column of the button
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  public void handleClick(int row, int col) {
    if (gameOver || !board[row][col].getText().isEmpty()) {
      return;
    }

    board[row][col].setText(xTurn ? "X" : "O");

    if (checkWin(row, col)) {
      gameOver = true;
      showAlert(xTurn ? "X wins!" : "O wins!");
    } else if (checkDraw()) {
      gameOver = true;
      showAlert("It's a draw!");
    } else {
      xTurn = !xTurn;
    }
  }


  /**
   * Method to check if the game is won
   *
   * @param row is the row of the button
   * @param col is the column of the button
   * @return true if the game is won, false otherwise
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  private boolean checkWin(int row, int col) {
    String symbol = xTurn ? "X" : "O";

    //Check rows
    if(board[row][0].getText().equals(symbol) &&
        board[row][1].getText().equals(symbol) &&
        board[row][2].getText().equals(symbol)) {
      return true;
    }

    //Check columns
    if(board[0][col].getText().equals(symbol) &&
        board[1][col].getText().equals(symbol) &&
        board[2][col].getText().equals(symbol)) {
      return true;
    }

    //Check diagonals
    if(row == col &&
        board[0][0].getText().equals(symbol) &&
        board[1][1].getText().equals(symbol) &&
        board[2][2].getText().equals(symbol)) {
      return true;
    }

    return row + col == 2 &&
        board[0][2].getText().equals(symbol) &&
        board[1][1].getText().equals(symbol) &&
        board[2][0].getText().equals(symbol);
  }


  /**
   * Method to check if the game is a draw
   *
   * @return true if the game is a draw, false otherwise
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  private boolean checkDraw() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        if (board[row][col].getText().isEmpty()) {
          return false;
        }
      }
    }
    return true;
  }


  /**
   * Method to show an alert
   *
   * @param message is the message to be shown in the alert
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  private void showAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }


  /**
   * Method to restart the game
   *
   * @Author Sigveer
   * @Date: 15.03.2025
   * @Version: 1.0
   */
  private void restartGame() {
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col].setText("");
        StyleUtils.styleTTTButton(board[row][col]);
      }
    }
    xTurn = true;
    gameOver = false;
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
