package com.gruppe24.observerpattern;

/**
 * Interface for the observable in the observer pattern.
 */
public interface GameObserver {

  /**
   * Updates the observer with the given event and arguments.
   *
   * @param event The event type that occurred.
   * @param args  The arguments associated with the event.
   */
  void update(EventType event, Object... args);
}