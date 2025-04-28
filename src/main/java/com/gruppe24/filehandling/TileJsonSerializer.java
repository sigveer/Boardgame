package com.gruppe24.filehandling;

import com.google.gson.JsonObject;

public interface TileJsonSerializer {

  void addActionToJson(JsonObject tileJson, int tileId);
}
