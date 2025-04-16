package com.gruppe24;

import com.gruppe24.boardgames.MenuGUI;
import com.gruppe24.utils.Validators;
import java.util.logging.Level;

public class Main {

  public static void main(String[] args) {
    MenuGUI.launch(MenuGUI.class,args);
    Validators.getLogger().log(Level.INFO,"Application closed");
  }
}




