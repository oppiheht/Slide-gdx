package com.blue.gdx.slide;

import com.badlogic.gdx.Game;
import com.blue.gdx.slide.screen.StartScreen;
import com.blue.gdx.slide.ui.AssetManager;

public class SlideGame extends Game {

   @Override
   public void create() {
      AssetManager.loadAssets();
      setScreen(new StartScreen(this));
   }
   
   @Override
   public void dispose() {
      super.dispose();
      AssetManager.dispose();
   }
}
