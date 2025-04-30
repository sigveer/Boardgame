package com.gruppe24.observerpattern;

public interface GameObserver {

  void update(EventType event, Object... args);
}