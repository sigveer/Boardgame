package com.gruppe24.observerpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameSubject class implements the GameObservable interface and is responsible for managing
 * the list of observers and notifying them of events.
 */
public class GameSubject implements GameObservable {

  private final List<GameObserver> observers;
  private static final GameSubject instance = new GameSubject();

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

  /**
   * Constructor for the GameSubject class.
   * Initializes the list of observers.
   */
  private GameSubject() {
    observers = new ArrayList<>();
  }

  /**
   * Return singelton instance of GameSubject, to avoid having to make instance
   * of the observer.
   *
   * @return singelton instnace.
   */
  public static GameSubject gameSubjectInstance() {
    return instance;
  }
}