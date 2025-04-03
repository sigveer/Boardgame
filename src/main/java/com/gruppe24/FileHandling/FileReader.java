package com.gruppe24.FileHandling;

import java.io.IOException;
import java.lang.reflect.Array;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class FileReader {
  public static void main(String[] args) throws IOException, ParseException {


    //Reads one JSON-object
    Object board = new JSONParser().parse(new java.io.FileReader("src/main/resources/Object.json"));
    JSONObject j = (JSONObject) board;
    String name = (String) j.get("name");
    Long age = (Long) j.get("age");

    System.out.println("Name :" + name);
    System.out.println("Age: " +age + "\n");

    //Reads JSON-array and its elements
    Object names = new JSONParser().parse(new java.io.FileReader("src/main/resources/wordsArrayList.json"));
    JSONArray jsonArray = (JSONArray) names;

    for(int i = 0; i < jsonArray.size(); i++){
      System.out.println("Word "+(i+1)+": "+jsonArray.get(i));
    }
    System.out.println("\n");

    //Reads JSON-objects within JSON-array
    Object grandBoard = new JSONParser().parse(new java.io.FileReader("src/main/resources/objectsArrayList.json"));
    JSONArray a = (JSONArray) grandBoard;

    for(int i = 0; i < a.size(); i++){
      JSONObject obj = (JSONObject) a.get(i);
      System.out.println("Navn: "+obj.get("name") +" på "+ obj.get("age")+" år");
    }


  }
}
