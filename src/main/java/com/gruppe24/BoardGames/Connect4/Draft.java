package com.gruppe24.BoardGames.Connect4;

import static javafx.application.Application.launch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Draft extends Application {

  private static final int ROWS = 6;
  private static final int COLS = 7;
  private static final int TILE_SIZE = 80;
  private static final int CIRCLE_RADIUS = 30;
  private static final int WINNING_LENGTH = 4;

  private final Pane[][] board = new Pane[ROWS][COLS];
  private final int[][] gameState = new int[ROWS][COLS]; // 0=empty, 1=red, 2=green
  private boolean redTurn = true;

  @Override
  public void start(Stage stage) {
    GridPane grid = new GridPane();

    // Create the game board
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        Pane tile = createTile(row, col);
        board[row][col] = tile;
        grid.add(tile, col, row);
      }
    }

    Scene scene = new Scene(grid);
    stage.setTitle("Connect4");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();
  }

  private Pane createTile(int row, int col) {
    Pane tile = new Pane();
    tile.setPrefSize(TILE_SIZE, TILE_SIZE);

    //Blue Tile
    Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
    rect.setStyle("-fx-fill: #0000FF;");
    tile.getChildren().add(rect);

    //Circle
    Circle circle = new Circle(TILE_SIZE / 2, TILE_SIZE / 2, CIRCLE_RADIUS);
    circle.setStyle("-fx-fill: #FFFFFF; -fx-stroke: #000000;");
    tile.getChildren().add(circle);

    tile.setOnMouseClicked(event -> {dropChip(col);
    });

    return tile;
  }


  private void dropChip(int col) {
    for (int row = ROWS - 1; row >= 0; row--) {
      if (gameState[row][col] == 0) {

        // Place Chip
        gameState[row][col] = redTurn ? 1 : 2;
        showChip(row, col);

        // Check for win
        if (checkWin(row, col)) {

//          disableBoard();
        }

        // Switch turn
        redTurn = !redTurn;
        return;
      }
    }
  }

    private void showChip ( int row, int col){
      Circle chip = new Circle(TILE_SIZE / 2, TILE_SIZE / 2, CIRCLE_RADIUS);
      chip.setStyle("-fx-fill: " + (redTurn ? "#FF0000;" : "#00FF00;"));
      board[row][col].getChildren().add(chip);
    }

    private boolean checkWin ( int row, int col){
      int player = gameState[row][col];

      // Check horizontal
      int count = 0;
      for (int c = 0; c < COLS; c++) {
        count = (gameState[row][c] == player) ? count + 1 : 0;
        if (count >= 4)
          return true;
      }

      // Check vertical
      count = 0;
      for (int r = 0; r < ROWS; r++) {
        count = (gameState[r][col] == player) ? count + 1 : 0;
        if (count >= 4)
          return true;
      }

      // Check diagonal (down-right)
      for (int r = 0; r <= ROWS - 4; r++) {
        for (int c = 0; c <= COLS - 4; c++) {
          if (gameState[r][c] == player &&
              gameState[r + 1][c + 1] == player &&
              gameState[r + 2][c + 2] == player &&
              gameState[r + 3][c + 3] == player) {
            return true;
          }
        }
      }

      // Check diagonal (up-right)
      for (int r = 3; r < ROWS; r++) {
        for (int c = 0; c <= COLS - 4; c++) {
          if (gameState[r][c] == player &&
              gameState[r - 1][c + 1] == player &&
              gameState[r - 2][c + 2] == player &&
              gameState[r - 3][c + 3] == player) {
            return true;
          }
        }
      }

      return false;
    }

  public static void main(String[] args) {
    launch(args);
  }
}

