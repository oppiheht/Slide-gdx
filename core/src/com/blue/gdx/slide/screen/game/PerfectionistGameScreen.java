package com.blue.gdx.slide.screen.game;

import com.blue.gdx.slide.SlideGame;

public class PerfectionistGameScreen extends SlideGameScreen {

   private int moves = 0;
   private int score = 0;

   
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
   protected String getGameOverText() {
      return "Game Over!\nYou completed " + score + " levels";
   }

   @Override
   protected void onPlayerMove() {
      moves++;
      if (moves > world.getSolutionLength()) {
         state = STATE.GAME_OVER;
      }
   }

   @Override
   protected void onLevelCompleted() {
      moves = 0;
      score++;
   }

}
