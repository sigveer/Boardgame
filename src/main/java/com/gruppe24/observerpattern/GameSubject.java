package com.gruppe24.observerpattern;

import java.util.ArrayList;
import java.util.List;

public class GameSubject implements GameObservable {

  private final List<GameObserver> observers;


  public GameSubject() {
    observers = new ArrayList<>();
  }


  @Override
  public void registerListener(GameObserver observer) {
    if (observer == null) {
      throw new IllegalArgumentException("Observer missing");
    }
    if (!observers.contains(observer)) {
      observers.add(observer);
    }
  }


  @Override
  public void removeListener(GameObserver observer) {
    observers.remove(observer);
  }


  @Override
  public void notifyListener(EventType event, Object... args) {
    for (GameObserver observer : observers) {
      observer.update(event, args);
    }
  }

}