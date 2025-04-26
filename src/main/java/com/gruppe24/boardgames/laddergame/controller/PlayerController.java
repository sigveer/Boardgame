package com.gruppe24.boardgames.laddergame.controller;

import com.gruppe24.boardgames.laddergame.models.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.scene.image.Image;

public class PlayerController {

  public PlayerController() {
    this.players = new ArrayList<>();
    addDefaultPlayer();
  }

  private List<Player> players;

  public void addPlayer() {
    if (players.size() >= 5)
      return;
    players.add(new Player("Player " + (players.size() + 1), getNextIcon()));
  }

  public void removePlayer() {
    if (players.size() > 1) {
      players.removeLast();
    }
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void cyclePlayerIcon(Player player, int index) {
    if (index >= 0 && index < players.size()) {
      players.get(index).cycleToNextIcon();
    }
  }

  private Image getNextIcon() {
    String[] iconPaths = Player.getIconPaths();
    String selectedPath = iconPaths[players.size() % iconPaths.length];
    return new Image(
        Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(selectedPath)));
  }

  private void addDefaultPlayer() {
    addPlayer();
  }
}