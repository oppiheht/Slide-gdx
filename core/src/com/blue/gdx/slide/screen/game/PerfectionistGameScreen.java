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
   protected void drawStatusText() {
      drawScore();
   }
   
   private void drawScore() {
      font.draw(batch,
            "Score: "+score+"\nMoves: "+moves,
            STATUS_FONT_X, 
            WORLD_HEIGHT - 200);
      
   }

   @Override
   protected void drawGameOver() {
      // TODO Auto-generated method stub
      
   }

   @Override
   protected void onPlayerMove() {
      moves++;
      if (moves > map.getSolutionLength()) {
         state = STATE.GAME_OVER;
      }
   }

   @Override
   protected void onLevelCompleted() {
      moves = 0;
      score++;
   }

}
