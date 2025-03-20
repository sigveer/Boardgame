package com.gruppe24.BoardGames.Connect4;

import com.gruppe24.BoardGames.MenuGUI;
import com.gruppe24.Utils.StyleUtils;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Draft extends Application {

  private static final int ROWS = 6;
  private static final int COLS = 7;
  private static final int TILE_SIZE = 120;
  private static final int CIRCLE_RADIUS = 45;
  private static final int WINNING_LENGTH = 4;

  private final Pane[][] board = new Pane[ROWS][COLS];
  private final int[][] gameState = new int[ROWS][COLS]; // 0=empty, 1=red, 2=green
  private boolean redTurn = true;

  @Override
  public void start(Stage primaryStage) {
    initializeView(primaryStage);
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
          showAlert((redTurn ? "Red" : "Green") + " wins!");
          disableBoard(); //remove this line
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
      if (count >= WINNING_LENGTH)
          return true;
      }

      // Check vertical
      count = 0;
      for (int r = 0; r < ROWS; r++) {
        count = (gameState[r][col] == player) ? count + 1 : 0;
        if (count >= WINNING_LENGTH)
          return true;
      }

      // Check diagonal (down-right)
      for (int r = 0; r <= ROWS - WINNING_LENGTH; r++) {
        for (int c = 0; c <= COLS - WINNING_LENGTH; c++) {
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
        for (int c = 0; c <= COLS - WINNING_LENGTH; c++) {
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

  private void disableBoard() {
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        board[row][col].setOnMouseClicked(null);
      }
    }
  }

  public void enableBoard() {
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        final int finalCol = col;
        board[row][col].setOnMouseClicked(event -> dropChip(finalCol));
      }
    }
  }


  //Lag funsjoner som erstatter kode her
  public void resetGame() {
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        gameState[row][col] = 0;
        Pane tile = board[row][col];
        tile.getChildren().clear();
        Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
        rect.setStyle("-fx-fill: #0000FF;");
        tile.getChildren().add(rect);
        Circle circle = new Circle(TILE_SIZE / 2, TILE_SIZE / 2, CIRCLE_RADIUS);
        circle.setStyle("-fx-fill: #FFFFFF; -fx-stroke: #000000;");
        tile.getChildren().add(circle);
        enableBoard();
      }
    }
    redTurn = true;
  }

  private void initializeView(Stage primaryStage) {
    GridPane background = new GridPane();
    background.setAlignment(Pos.CENTER);
    background.setVgap(25);
    background.setHgap(20);
    background.setStyle("-fx-background-color: #2e49ae;");

    GridPane boardgrid = new GridPane();
    boardgrid.setAlignment(Pos.CENTER);

    // Create the game board
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLS; col++) {
        Pane tile = createTile(row, col);
        board[row][col] = tile;
        boardgrid.add(tile, col, row);
      }
    }

    GridPane menuPanel = new GridPane();
    menuPanel.setAlignment(Pos.CENTER);
    menuPanel.setVgap(20);

    Button restartButton = new Button("Restart");
    StyleUtils.styleNormalButton(restartButton);
    restartButton.setOnAction(e -> {resetGame();});

    Button quitButton = new Button("Quit");
    StyleUtils.styleNormalButton(quitButton);
    quitButton.setOnAction(e -> new MenuGUI().start(primaryStage));

    menuPanel.add(restartButton, 0, 0);
    menuPanel.add(quitButton, 0, 1);

    background.add(boardgrid, 0, 0);
    background.add(menuPanel, 1, 0);

    Scene scene = new Scene(background);
    primaryStage.setTitle("Connect4");
    primaryStage.setScene(scene);
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public void showAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

