package com.blue.gdx.slide.screen.game;

import com.blue.gdx.slide.SlideGame;

public class InfiniteGameScreen extends SlideGameScreen {

   public InfiniteGameScreen(SlideGame game) {
      super(game);
   }

   @Override
   protected void renderUpdate(float delta) {
      //intentionally empty
   }

   @Override
   protected String getStatusText() {
      return "\nLevels Completed:  " + score;
   }
   
   @Override
   protected void onPlayerMove() {
      
   }

   @Override
   protected void onLevelCompleted() {
      score++;
   }

}
