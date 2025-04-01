package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Controller.GameController;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Board.BoardType;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.RandomTeleportTile;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.SnakeDownTile;
import com.gruppe24.Utils.StyleUtils;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LadderGame extends Application {

  private final Board board;
  private final GameController gameController;
  private List<Player> players;
  private static final int tileSize = 75;
  private int currentPlayerIndex = 0;
  private Label diceResultLabel;
  private Label currentPlayerLabel;
  private Label snakeOrLadderCheck;
  private final Dice dice = new Dice(1);

  public LadderGame(List<Player> players) {
    this(players, BoardType.CLASSIC);
  }

  public LadderGame(List<Player> players, BoardType boardType) {
    this.gameController = new GameController(boardType);
    this.board = gameController.getBoard();
    this.players = players;
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Laddergame Classic");

    //GridPane for tiles
    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(1);
    gridPane.setHgap(1);
    gridPane.setStyle("-fx-background-color: #607ee4;");

    //Pane for ladders
    Pane ladderSnakeP = new Pane();
    ladderSnakeP.setMouseTransparent(true);

    //Scene
    Scene scene = new Scene(new StackPane(gridPane,ladderSnakeP), 1000, 850); //AI-suggestion

    //Title Label
    Label title = new Label("Board Games!");
    title.setStyle("-fx-font-size: 40px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

    //Draw Board
    drawBoard(gridPane, ladderSnakeP); //drawing the board

    //Labels
    currentPlayerLabel = new Label("Current Player: " + players.get(currentPlayerIndex).getName());
    currentPlayerLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    diceResultLabel = new Label("Roll the dice!");
    diceResultLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    snakeOrLadderCheck = new Label("");
    snakeOrLadderCheck.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    //Buttons
    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> {new LadderGameMenuGUI().start(primaryStage);});
    StyleUtils.styleNormalButton(backToMenu);

    //Vertical Box for control panel
    VBox controlPanel = new VBox(10);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.getChildren().addAll(currentPlayerLabel, diceResultLabel, snakeOrLadderCheck, diceRoll, backToMenu);
    gridPane.add(controlPanel, 11, 0, 1, 5);


    primaryStage.setScene(scene);
    primaryStage.setFullScreen(false); // can not resize the window. A temporary fix?
    primaryStage.show();
  }

  /**
   * Draws the board, hardcoded with snakes and ladders
   */
  public void drawBoard(GridPane gridPane, Pane ladderSnakePane) {
    for (int row = 9; row >= 0; row--) {
      for (int col = 0; col < 9; col++) {
        int tileNumber;
        if ((9 - row) % 2 == 0) {
          tileNumber = (9 - row) * 9 + col + 1;
        } else {
          tileNumber = (9 - row + 1) * 9 - col;
        }

        StackPane stackPane = new StackPane();
        Rectangle tile = new Rectangle(tileSize, tileSize);
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(1);

        if (tileNumber == 90) {
          tile.setFill(Color.YELLOW);
        } else if (board.getTile(tileNumber) instanceof RandomTeleportTile) {
          tile.setFill(Color.PURPLE);
        } else if (board.getTile(tileNumber) instanceof LadderUpTile) {
          tile.setFill(Color.GREEN);
        } else if (board.getTile(tileNumber) instanceof SnakeDownTile) {
          tile.setFill(Color.RED);
        } else {
          tile.setFill(Color.WHITE);
        }

        Label numberLabel = new Label(Integer.toString(tileNumber));
        numberLabel.setStyle("-fx-font-weight: bold;");
        numberLabel.setTranslateX(26);
        numberLabel.setTranslateY(30);

        stackPane.getChildren().addAll(tile, numberLabel);
        stackPane.setAlignment(Pos.CENTER);

        gridPane.add(stackPane, col, row);
      }
    }

    //Ladders
    Image ladderImage = new Image("Ladder.png");

    ImageView ladderView1 = new ImageView(ladderImage);
    ladderView1.setFitWidth(200);
    ladderView1.setFitHeight(500);
    ladderView1.setX(125);
    ladderView1.setY(350);
    ladderView1.setRotate(30);

    ImageView ladderView2 = new ImageView(ladderImage);
    ladderView2.setFitWidth(150);
    ladderView2.setFitHeight(150);
    ladderView2.setX(630);
    ladderView2.setY(650);
    ladderView2.setRotate(30);

    ImageView ladderView3 = new ImageView(ladderImage);
    ladderView3.setFitWidth(200);
    ladderView3.setFitHeight(250);
    ladderView3.setX(90);
    ladderView3.setY(330);
    ladderView3.setRotate(30);

    ImageView ladderView4 = new ImageView(ladderImage);
    ladderView4.setFitWidth(200);
    ladderView4.setFitHeight(250);
    ladderView4.setX(500);
    ladderView4.setY(240);
    ladderView4.setRotate(18);

    ImageView ladderView5 = new ImageView(ladderImage);
    ladderView5.setFitWidth(200);
    ladderView5.setFitHeight(300);
    ladderView5.setX(450);
    ladderView5.setY(120);
    ladderView5.setRotate(15);

    ImageView ladderView6 = new ImageView(ladderImage);
    ladderView6.setFitWidth(200);
    ladderView6.setFitHeight(230);
    ladderView6.setX(590);
    ladderView6.setY(40);
    ladderView6.setRotate(15);

    ImageView ladderView7 = new ImageView(ladderImage);
    ladderView7.setFitWidth(200);
    ladderView7.setFitHeight(230);
    ladderView7.setX(350);
    ladderView7.setY(40);
    ladderView7.setRotate(15);

    ladderSnakePane.getChildren().addAll(ladderView1,ladderView2,ladderView3,ladderView4,ladderView5,ladderView6,ladderView7);

    //Downward Ladder
    Image snakeImage = new Image("Ladder.png");

    ImageView snakeView1 = new ImageView(snakeImage);
    snakeView1.setFitWidth(200);
    snakeView1.setFitHeight(200);
    snakeView1.setX(370);
    snakeView1.setY(590);
    snakeView1.setRotate(-45);

    ImageView snakeView2 = new ImageView(snakeImage);
    snakeView2.setFitWidth(250);
    snakeView2.setFitHeight(150);
    snakeView2.setX(200);
    snakeView2.setY(560);
    snakeView2.setRotate(-58);

    ImageView snakeView3 = new ImageView(snakeImage);
    snakeView3.setFitWidth(100);
    snakeView3.setFitHeight(100);
    snakeView3.setX(500);
    snakeView3.setY(440);
    snakeView3.setRotate(45);

    ImageView snakeView4 = new ImageView(snakeImage);
    snakeView4.setFitWidth(175);
    snakeView4.setFitHeight(150);
    snakeView4.setX(75);
    snakeView4.setY(300);
    snakeView4.setRotate(-58);

    ImageView snakeView5 = new ImageView(snakeImage);
    snakeView5.setFitWidth(400);
    snakeView5.setFitHeight(150);
    snakeView5.setX(530);
    snakeView5.setY(320);
    snakeView5.setRotate(90);

    ImageView snakeView6 = new ImageView(snakeImage);
    snakeView6.setFitWidth(680);
    snakeView6.setFitHeight(200);
    snakeView6.setX(50);
    snakeView6.setY(300);
    snakeView6.setRotate(55);

    ImageView snakeView7 = new ImageView(snakeImage);
    snakeView7.setFitWidth(200);
    snakeView7.setFitHeight(150);
    snakeView7.setX(240);
    snakeView7.setY(70);
    snakeView7.setRotate(-60);

    ladderSnakePane.getChildren().addAll(snakeView1,snakeView2,snakeView3,snakeView4,snakeView5,snakeView6,snakeView7);
  }


  /**
   * These next two methods; {@link #rollDiceAndMove(GridPane, Stage)},
   * {@link #animateAndMove(GridPane, Player, int, int)}, are intervoven and works to a high degree
   * together. animateAndMove method is heavely assisted by AI - ChatGPT-free and Grok 3.
   * <p>1/2 method</p>
   * @param gridPane
   */
  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage) {
    Player currentPlayer = players.get(currentPlayerIndex);
    //Get the original position; for animation
    int previousPosition = currentPlayer.getPosition();

    //Update the gamle-logic with dice
    int diceValue = dice.rollSum();
    diceResultLabel.setText(currentPlayer.getName() + " rolled: " + diceValue);
    gameController.handlePlayerTurn(currentPlayer, diceValue);

    //Update animation with the new position
    int newPosition = currentPlayer.getPosition();
    animateAndMove(gridPane, currentPlayer, previousPosition, newPosition);

    //Check winner
    if (gameController.checkAndHandleWin(newPosition)) {
      new WinnerScreen(currentPlayer).start(primaryStage);
      return;
    }

    //Display which player is to move
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
  }


  private void animateAndMove(GridPane gridPane, Player player, int fromPosition, int toPosition) {
    int tileType = gameController.getCheckTileType();
    int specialTilePosition = gameController.getSpecialTilePosition();

    // For teleport (type 3), do a direct movement to teleport tile then to destination
    if (tileType == 3) {
      // First, directly move to teleport tile position
      int teleportRow = 9 - (specialTilePosition - 1) / 9;
      int teleportCol = (9 - teleportRow) % 2 == 0 ? (specialTilePosition - 1) % 9 : 8 - (specialTilePosition - 1) % 9;

      Timeline moveToTeleportTL = new Timeline(new KeyFrame(
          Duration.seconds(0.3),
          e -> {
            gridPane.getChildren().remove(player.getPlayerPiece());
            gridPane.add(player.getPlayerPiece(), teleportCol, teleportRow);
            snakeOrLadderCheck.setText("Teleporting...");
          }
      ));

      // After reaching teleport, wait then jump to destination
      moveToTeleportTL.setOnFinished(e -> {
        // Wait briefly, then teleport to destination
        Timeline pauseTL = new Timeline(new KeyFrame(Duration.seconds(0.5)));
        pauseTL.setOnFinished(event -> {
          // Move directly to final destination
          int finalRow = 9 - (toPosition - 1) / 9;
          int finalCol = (9 - finalRow) % 2 == 0 ? (toPosition - 1) % 9 : 8 - (toPosition - 1) % 9;

          gridPane.getChildren().remove(player.getPlayerPiece());
          gridPane.add(player.getPlayerPiece(), finalCol, finalRow);
          snakeOrLadderCheck.setText("Teleported to " + toPosition + "!");
        });
        pauseTL.play();
      });

      moveToTeleportTL.play();
      return;
    }

    // For ladders and snakes (type 1 and 2)
    else if (tileType == 1 || tileType == 2) {
      // First move to ladder/snake position
      int specialRow = 9 - (specialTilePosition - 1) / 9;
      int specialCol = (9 - specialRow) % 2 == 0 ? (specialTilePosition - 1) % 9 : 8 - (specialTilePosition - 1) % 9;

      Timeline moveToSpecialTL = new Timeline(new KeyFrame(
          Duration.seconds(0.3),
          e -> {
            gridPane.getChildren().remove(player.getPlayerPiece());
            gridPane.add(player.getPlayerPiece(), specialCol, specialRow);
            snakeOrLadderCheck.setText(tileType == 1 ? "Ladder!" : "Snake...");
          }
      ));

      moveToSpecialTL.setOnFinished(e -> {
        // Move directly to final destination after a brief delay
        Timeline pauseTL = new Timeline(new KeyFrame(Duration.seconds(0.5)));
        pauseTL.setOnFinished(event -> {
          int finalRow = 9 - (toPosition - 1) / 9;
          int finalCol = (9 - finalRow) % 2 == 0 ? (toPosition - 1) % 9 : 8 - (toPosition - 1) % 9;

          gridPane.getChildren().remove(player.getPlayerPiece());
          gridPane.add(player.getPlayerPiece(), finalCol, finalRow);
        });
        pauseTL.play();
      });

      moveToSpecialTL.play();
      return;
    }

    // For normal movement (no special tiles)
    Timeline timeline = new Timeline();
    int steps = toPosition - fromPosition;

    for (int i = 1; i <= steps; i++) {
      final int step = i;
      int currentPosition = fromPosition + i;
      int row = 9 - (currentPosition - 1) / 9;
      int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 : 8 - (currentPosition - 1) % 9;

      KeyFrame keyFrame = new KeyFrame(
          Duration.seconds(step * 0.3),
          e -> {
            gridPane.getChildren().remove(player.getPlayerPiece());
            gridPane.add(player.getPlayerPiece(), col, row);
          }
      );
      timeline.getKeyFrames().add(keyFrame);
    }

    timeline.play();
  }

}