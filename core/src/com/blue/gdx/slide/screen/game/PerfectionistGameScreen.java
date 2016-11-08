package com.blue.gdx.slide.screen.game;

import com.blue.gdx.slide.SlideGame;

public class PerfectionistGameScreen extends SlideGameScreen {

   private int moves = 0;

   public PerfectionistGameScreen(SlideGame game) {
      super(game);
   }

   @Override
   protected void renderUpdate(float delta) {
      //Actually nothing to do for perfectionist mode
   }

   @Override
   protected String getStatusText() {
      return "Score: "+score+"\nMoves: "+moves;
   }

   @Override
   protected void onPlayerMove() {
      moves++;
      if (moves > world.getSolutionLength()) {
         gameOver();
      }
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

}
