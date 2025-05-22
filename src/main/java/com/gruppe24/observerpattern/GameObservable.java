package com.gruppe24.observerpattern;

/**
 * Interface for the observable in the observer pattern.
 */
public interface GameObservable {

  /**
   * Registers a listener to the observable.
   *
   * @param observer the observer to register
   */
  void registerListener(GameObserver observer);

  /**
   * Unregisters a listener from the observable.
   *
   * @param observer the observer to unregister
   */
  void removeListener(GameObserver observer);


  /**
   * Notifies all registered listeners of an event.
   *
   * @param event the event type
   * @param args  additional arguments for the event
   */
  void notifyListener(EventType event, Object... args);
}
