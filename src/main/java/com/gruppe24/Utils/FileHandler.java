//package com.gruppe24.FileHandling;
//
//import java.io.IOException;
//
///**
// * Interface for saving and loading data to and from a file.
// * @param <T> The type of data to be saved and loaded.
// */
//public interface FileHandler<T> {
//
//
//  /**
//   * Saves data to a file.
//   *
//   * @param data The data to be saved.
//   * @param filePath The path to the file where the data should be saved.
//   * @throws IOException If an I/O error occurs.
//   */
//  void saveToFile(T data, String filePath) throws IOException;
//
//
//  /**
//   * Loads data from a file.
//   *
//   * @param filePath The path to the file where the data should be loaded from.
//   * @return The data loaded from the file.
//   * @throws IOException If an I/O error occurs.
//   */
//  T loadFromFile(String filePath) throws IOException;
//}