package com.blue.gdx.slide;

import com.badlogic.gdx.Game;
import com.blue.gdx.slide.screen.StartScreen;

public class SlideGame extends Game {

   @Override
   public void create() {
      setScreen(new StartScreen(this));
   }
}
