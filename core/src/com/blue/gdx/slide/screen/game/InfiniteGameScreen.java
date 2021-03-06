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
      game.getHighScoreKeeper().infiniteLevelCompleted(score);
   }

   @Override
   protected void updateScoreKeeper() {
      //nothing, score keeper is updated on each level completed
   }

   @Override
   protected boolean showResetButton() {
      return true;
   }

   @Override
   public String getTutorialText() {
      return "Swipe to move\n\nSave them all";
   }

}
