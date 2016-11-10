package com.blue.gdx.slide;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.blue.gdx.slide.screen.StartScreen;
import com.blue.gdx.slide.util.SlideAssetManager;
import com.blue.gdx.slide.util.HighScoreKeeper;

public class SlideGame extends Game {

   protected SlideAssetManager assetManager;
   protected HighScoreKeeper highScoreKeeper;
   
   @Override
   public void create() {
      assetManager = new SlideAssetManager();
      assetManager.loadAllAssetsBlocking();
      Gdx.input.setCatchBackKey(true);
      highScoreKeeper = new HighScoreKeeper();
      setScreen(new StartScreen(this));
   }
   
   public SlideAssetManager getAssetManager() {
      return assetManager;
   }
   public HighScoreKeeper getHighScoreKeeper() { return highScoreKeeper; }
   
   @Override
   public void dispose() {
      super.dispose();
      assetManager.dispose();
   }


}
