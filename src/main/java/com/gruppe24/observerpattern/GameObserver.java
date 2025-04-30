package com.gruppe24.observerpattern;

public interface GameObserver {

  void update(GameEventType event, Object... args);
}