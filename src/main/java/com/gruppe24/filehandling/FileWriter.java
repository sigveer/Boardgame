package com.gruppe24.filehandling;

/**
 * Interface for writing objects to a file.
 */
public interface FileWriter<T> {

  /**
   * Writes the given object to a file at the specified file path.
   *
   * @param object the object to write to the file
   * @param filePath the path to the file where the object will be written
   * @return true if the write operation was successful, false otherwise
   */
  boolean writeToFile(T object, String filePath);
}