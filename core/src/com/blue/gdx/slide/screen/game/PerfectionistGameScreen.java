package com.blue.gdx.slide.screen.game;

import com.blue.gdx.slide.SlideGame;

public class PerfectionistGameScreen extends SlideGameScreen {

   private int moves = 0;

   public PerfectionistGameScreen(SlideGame game) {
      super(game);
   }

   @Override
   protected void renderUpdate(float delta) {
      if (moves >= world.getSolutionLength()) {
         gameOver();
      }
   }

   @Override
   protected String getStatusText() {
      return "Score: "+score+"\nMoves Left: "+ (world.getSolutionLength() - moves);
   }

   @Override
   protected void onPlayerMove() {
      moves++;
   }

   @Override
   protected void updateScoreKeeper() {
      game.getHighScoreKeeper().perfectModeCompleted(score);
   }

   @Override
   protected void onLevelCompleted() {
      moves = 0;
      score++;
   }

   @Override
   protected boolean showResetButton() {
      return false;
   }

   @Override
   public String getTutorialText() {
      return "Swipe to move\n\n" +
            "No mistakes\n\n" +
            "Save them all";
   }

}
