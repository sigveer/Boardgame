package com.gruppe24.observerpattern;

public interface GameObservable {

  void registerListener(GameObserver observer);

  void removeListener(GameObserver observer);

  void notifyListener(EventType event, Object... args);
}
