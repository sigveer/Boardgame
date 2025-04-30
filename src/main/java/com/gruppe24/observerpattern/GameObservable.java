package com.gruppe24.observerpattern;

public interface GameObservable {

  void registerObserver(GameObserver observer);

  void removeObserver(GameObserver observer);

  void notifyObservers(GameEventType event, Object... args);
}
