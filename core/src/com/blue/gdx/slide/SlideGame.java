package com.blue.gdx.slide;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.blue.gdx.slide.screen.StartScreen;
import com.blue.gdx.slide.ui.SlideAssetManager;

public class SlideGame extends Game {

   protected SlideAssetManager assetManager;
   
   @Override
   public void create() {
      assetManager = new SlideAssetManager();
      assetManager.loadAllAssetsBlocking();
      Gdx.input.setCatchBackKey(true);
      setScreen(new StartScreen(this));
   }
   
   public SlideAssetManager getAssetManager() {
      return assetManager;
   }
   
   @Override
   public void dispose() {
      super.dispose();
      assetManager.dispose();
   }
}
