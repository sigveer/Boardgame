package com.gruppe24.BoardGames.LadderGame.View;

import com.gruppe24.BoardGames.LadderGame.Controller.GameController;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Board;
import com.gruppe24.BoardGames.LadderGame.Models.Board.BoardType;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.FrozenTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.RandomTeleportTile;
import com.gruppe24.BoardGames.LadderGame.Models.Dice;
import com.gruppe24.BoardGames.LadderGame.Models.Player;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderUpTile;
import com.gruppe24.BoardGames.LadderGame.Models.Board.Tile.LadderDownTile;
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
  private Label isFrozenLabel;
  boolean unfreeze = false;
  private final Dice dice = new Dice(2);

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

    //Pane for laddersAndSnakes and dices
    Pane ladderSnakeP = new Pane();
    ladderSnakeP.setMouseTransparent(true);
    Pane diceP = new Pane();
    diceP.setMouseTransparent(true);

    //Scene
    Scene scene = new Scene(new StackPane(gridPane,ladderSnakeP, diceP), 1000, 850); //AI-suggestion

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

    isFrozenLabel = new Label("");
    isFrozenLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");

    //Buttons
    Button diceRoll = new Button("Roll Dice");
    diceRoll.setOnAction(event -> rollDiceAndMove(gridPane, primaryStage, diceP));
    StyleUtils.styleNormalButton(diceRoll);

    Button backToMenu = new Button("Back to Menu");
    backToMenu.setOnAction(event -> {new LadderGameMenuGUI().start(primaryStage);});
    StyleUtils.styleNormalButton(backToMenu);

    //Vertical Box for control panel
    VBox controlPanel = new VBox(10);
    controlPanel.setAlignment(Pos.CENTER);
    controlPanel.getChildren().addAll(currentPlayerLabel, diceResultLabel, snakeOrLadderCheck, isFrozenLabel, diceRoll, backToMenu);
    gridPane.add(controlPanel, 11, 0, 1, 5);


    primaryStage.setScene(scene);
    primaryStage.setFullScreen(true); // can not resize the window. A temporary fix?
    primaryStage.show();
  }

  /**
   * Draws the board, hardcoded with snakes and ladders
   * @param gridPane
   * @param ladderSnakePane
   */
  public void drawBoard(GridPane gridPane, Pane ladderSnakePane) {
    for (int row = 9; row >= 0; row--) {
      for (int col = 0; col < 9; col++) {
        int tileNumber = (9 - row) % 2 == 0 ? (9 - row) * 9 + col + 1 : (9 - row + 1) * 9 - col;

        StackPane stackPane = new StackPane();
        Rectangle tile = new Rectangle(tileSize, tileSize);
        tile.setStroke(Color.BLACK);
        tile.setStrokeWidth(1);

        if (tileNumber == 90) {
          tile.setFill(Color.YELLOW);
        } else if(board.getTile(tileNumber) instanceof RandomTeleportTile) {
          tile.setFill(Color.PURPLE);
        } else if(board.getTile(tileNumber) instanceof FrozenTile){
          tile.setFill(Color.LIGHTBLUE);
        } else if (board.getTile(tileNumber) instanceof LadderUpTile) {
          tile.setFill(Color.GREEN);
        } else if (board.getTile(tileNumber) instanceof LadderDownTile) {
          tile.setFill(Color.RED);
        } else {
          tile.setFill(Color.WHITE);
        }

        //Landing-tile upon special tiles
        for(Integer value : board.getLadderUp().values()){
          if(tileNumber == value){
            tile.setFill(Color.LIGHTGREEN);
          }
        }
        for(Integer value : board.getLadderDown().values()){
          if(tileNumber == value){
            tile.setFill(Color.LIGHTPINK);
          }
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
    Image ladderUpImage = new Image("Ladder.png");

    ImageView ladderUpView1 = new ImageView(ladderUpImage);
    ladderUpView1.setFitWidth(180);
    ladderUpView1.setFitHeight(480);
    ladderUpView1.setX(420);
    ladderUpView1.setY(420);
    ladderUpView1.setRotate(25);

    ImageView ladderUpView2 = new ImageView(ladderUpImage);
    ladderUpView2.setFitWidth(150);
    ladderUpView2.setFitHeight(150);
    ladderUpView2.setX(850);
    ladderUpView2.setY(700);
    ladderUpView2.setRotate(30);

    ImageView ladderUpView3 = new ImageView(ladderUpImage);
    ladderUpView3.setFitWidth(220);
    ladderUpView3.setFitHeight(270);
    ladderUpView3.setX(310);
    ladderUpView3.setY(365);
    ladderUpView3.setRotate(35);

    ImageView ladderUpView4 = new ImageView(ladderUpImage);
    ladderUpView4.setFitWidth(190);
    ladderUpView4.setFitHeight(240);
    ladderUpView4.setX(760);
    ladderUpView4.setY(310);
    ladderUpView4.setRotate(18);

    ImageView ladderUpView5 = new ImageView(ladderUpImage);
    ladderUpView5.setFitWidth(200);
    ladderUpView5.setFitHeight(300);
    ladderUpView5.setX(660);
    ladderUpView5.setY(170);
    ladderUpView5.setRotate(15);

    ImageView ladderUpView6 = new ImageView(ladderUpImage);
    ladderUpView6.setFitWidth(200);
    ladderUpView6.setFitHeight(230);
    ladderUpView6.setX(822);
    ladderUpView6.setY(90);
    ladderUpView6.setRotate(15);

    ImageView ladderUpView7 = new ImageView(ladderUpImage);
    ladderUpView7.setFitWidth(200);
    ladderUpView7.setFitHeight(230);
    ladderUpView7.setX(585);
    ladderUpView7.setY(80);
    ladderUpView7.setRotate(15);

    ladderSnakePane.getChildren().addAll(ladderUpView1,ladderUpView2,ladderUpView3,ladderUpView4,ladderUpView5,ladderUpView6,ladderUpView7);

    //Downward Ladder
    Image ladderDownImage = new Image("Ladder.png");

    ImageView ladderDownView1 = new ImageView(ladderDownImage);
    ladderDownView1.setFitWidth(200);
    ladderDownView1.setFitHeight(200);
    ladderDownView1.setX(370);
    ladderDownView1.setY(590);
    ladderDownView1.setRotate(-45);

    ImageView ladderDownView2 = new ImageView(ladderDownImage);
    ladderDownView2.setFitWidth(250);
    ladderDownView2.setFitHeight(150);
    ladderDownView2.setX(200);
    ladderDownView2.setY(560);
    ladderDownView2.setRotate(-58);

    ImageView ladderDownView3 = new ImageView(ladderDownImage);
    ladderDownView3.setFitWidth(100);
    ladderDownView3.setFitHeight(100);
    ladderDownView3.setX(500);
    ladderDownView3.setY(440);
    ladderDownView3.setRotate(45);

    ImageView ladderDownView4 = new ImageView(ladderDownImage);
    ladderDownView4.setFitWidth(175);
    ladderDownView4.setFitHeight(150);
    ladderDownView4.setX(75);
    ladderDownView4.setY(300);
    ladderDownView4.setRotate(-58);

    ImageView ladderDownView5 = new ImageView(ladderDownImage);
    ladderDownView5.setFitWidth(400);
    ladderDownView5.setFitHeight(150);
    ladderDownView5.setX(530);
    ladderDownView5.setY(320);
    ladderDownView5.setRotate(90);

    ImageView ladderDownView6 = new ImageView(ladderDownImage);
    ladderDownView6.setFitWidth(680);
    ladderDownView6.setFitHeight(200);
    ladderDownView6.setX(50);
    ladderDownView6.setY(300);
    ladderDownView6.setRotate(55);

    ImageView ladderDownView7 = new ImageView(ladderDownImage);
    ladderDownView7.setFitWidth(200);
    ladderDownView7.setFitHeight(150);
    ladderDownView7.setX(240);
    ladderDownView7.setY(70);
    ladderDownView7.setRotate(-60);

    ladderSnakePane.getChildren().addAll(ladderDownView1,ladderDownView2,ladderDownView3,ladderDownView4,ladderDownView5,ladderDownView6,ladderDownView7);


  }


  /**
   * These next two methods; {@link #rollDiceAndMove(GridPane, Stage, Pane)},
   * {@link #animateAndMove(GridPane, Player, int, int)}, are intervoven and works to a high degree
   * together. animateAndMove method is heavely assisted by AI - ChatGPT-free and Grok 3.
   * <p>1/2 method</p>
   * @param gridPane
   */
  private void rollDiceAndMove(GridPane gridPane, Stage primaryStage, Pane diceP) {
    Player currentPlayer = players.get(currentPlayerIndex);

    if (currentPlayer.isFrozen()) {
      currentPlayer.setFrozen(false);
      isFrozenLabel.setText(currentPlayer.getName() + " is frozen and skips this turn!");

      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
      currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
      return;
    }

    //Get the original position; for animation
    int previousPosition = currentPlayer.getPosition();

    //Update game-logic with dice-roll and display dices
    int diceValue = dice.rollSum();
    int diceValue1 = dice.getDie(0);
    int diceValue2 = dice.getDie(1);

    String dice1Path = dicePath(diceValue1);
    Image dice1 = new Image(dice1Path);
    ImageView dice1IV = new ImageView(dice1);
    dice1IV.setX(1030);
    dice1IV.setY(450);
    dice1IV.setFitHeight(75);
    dice1IV.setFitWidth(75);

    String dice2Path = dicePath(diceValue2);
    Image dice2 = new Image(dice2Path);
    ImageView dice2IV = new ImageView(dice2);
    dice2IV.setX(1030);
    dice2IV.setY(530);
    dice2IV.setFitHeight(75);
    dice2IV.setFitWidth(75);

    diceResultLabel.setText("Totalsum: "+diceValue);
    diceP.getChildren().clear(); //removes old dice
    diceP.getChildren().addAll(dice1IV,dice2IV);

    gameController.handlePlayerTurn(currentPlayer, diceValue);

    //Update animation with the new position
    int newPosition = currentPlayer.getPosition();
    animateAndMove(gridPane, currentPlayer, previousPosition, newPosition);

    //Check winner
    if (gameController.checkAndHandleWin(newPosition)) {
      new WinnerScreen(currentPlayer).start(primaryStage);
      return;
    }

    //Switch player
    currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    currentPlayerLabel.setText("Current Player: " + players.get(currentPlayerIndex).getName());
  }
  private void animateAndMove(GridPane gridPane, Player player, int fromPosition, int toPosition) {
    int tileType = gameController.getCheckTileType();
    int specialTilePosition = gameController.getSpecialTilePosition();

    // --------------- Frozen Tile ---------------------
    if(tileType == 4 && !unfreeze){
      //unfreeze = true;
      currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
      Timeline timeline = new Timeline();
      timeline.setCycleCount(1);

      int steps = toPosition - fromPosition;
      int currentPosition = fromPosition;
      isFrozenLabel.setText("Player Frozen!");

      for (int i = 0; i < steps; i++) {
        currentPosition++;
        // Calculate into coordinates
        int row = 9 - (currentPosition - 1) / 9;
        int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 : 8 - (currentPosition - 1) % 9;

        KeyFrame keyFrame = new KeyFrame(
            Duration.seconds((i + 1) * 0.3),
            event -> {
              gridPane.getChildren().remove(player.getPlayerPiece());
              gridPane.add(player.getPlayerPiece(), col, row);
            }
        );
        timeline.getKeyFrames().add(keyFrame);
      }
      timeline.play();
      return;
    } isFrozenLabel.setText("");

    // --------------- Teleport Tile ------------------
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

    // ---------------- Ladder or Snake -------------------
    else if (tileType == 1 || tileType == 2) {
      Timeline snakesLadderTL = new Timeline();
      snakesLadderTL.setCycleCount(1);

      int specialTileStartPosition = gameController.getSpecialTilePosition();
      int stepsToSpecial = specialTileStartPosition - fromPosition;
      boolean isForward = stepsToSpecial > 0; //check if ladder or snake


      // Feedback upon hitting ladders or snakes
      snakeOrLadderCheck.setText(tileType == 1 ? "Ladder!" : "Snake...");

      // Create a list of keyframes
      List<KeyFrame> keyFrames = new ArrayList<>();
      int currentPosition = fromPosition;

      int absSteps = Math.abs(stepsToSpecial);
      for (int i = 0; i <= absSteps + 1; i++) {
        currentPosition = fromPosition + (isForward ? i : -i); //So it does not matter if it goes backward or forwards
        // Calculate the current position coordinates
        int row = 9 - (currentPosition - 1) / 9;
        int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 :  8 - (currentPosition - 1) % 9;
        if(col == -1){
          col = 0;
        }

        // Add a keyframe for current step
        int finalCol = col;
        KeyFrame keyFrame = new KeyFrame(
            Duration.seconds((i + 1) * 0.3),
            event -> {
              gridPane.getChildren().remove(player.getPlayerPiece());
              gridPane.add(player.getPlayerPiece(), finalCol, row);
            }
        );
        keyFrames.add(keyFrame);
      }
      snakesLadderTL.getKeyFrames().addAll(keyFrames);

      // After animation, teleport to final destination
      snakesLadderTL.setOnFinished(event -> {
        int finalRow = 9 - (toPosition - 1) / 9;
        int finalCol = (9 - finalRow) % 2 == 0 ? (toPosition - 1) % 9 : 8 - (toPosition - 1) % 9;

        gridPane.getChildren().remove(player.getPlayerPiece());
        gridPane.add(player.getPlayerPiece(), finalCol, finalRow);
      });
      snakesLadderTL.play();
      return;
    }
    // --------------- Normal tiles -----------------
    Timeline timeline = new Timeline();
    timeline.setCycleCount(1);

    int steps = toPosition - fromPosition;
    int currentPosition = fromPosition;
    snakeOrLadderCheck.setText("");

    for (int i = 0; i < steps; i++) {
      currentPosition++;
      // Calculate into coordinates
      int row = 9 - (currentPosition - 1) / 9;
      int col = (9 - row) % 2 == 0 ? (currentPosition - 1) % 9 : 8 - (currentPosition - 1) % 9;

      KeyFrame keyFrame = new KeyFrame(
          Duration.seconds((i + 1) * 0.3),
          event -> {
            gridPane.getChildren().remove(player.getPlayerPiece());
            gridPane.add(player.getPlayerPiece(), col, row);
          }
      );
      timeline.getKeyFrames().add(keyFrame);
    }
    timeline.play();
  }

  public String dicePath(int dice){
    return "dice"+dice+".png";
  }

}