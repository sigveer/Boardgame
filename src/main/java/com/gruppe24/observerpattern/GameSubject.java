package com.gruppe24.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class GameSubject implements GameObservable {

  private final List<GameObserver> observers;


  public GameSubject() {
    observers = new ArrayList<>();
  }


  @Override
  public void registerObserver(GameObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException("Observer missing");
    }
    if (!observers.contains(observer)) {
      observers.add(observer);
    }
  }


  @Override
  public void removeObserver(GameObserver observer) {
    observers.remove(observer);
  }


  @Override
  public void notifyObservers(GameEventType event, Object... args) {
    for (GameObserver observer : observers) {
      observer.update(event);
    }
  }

}