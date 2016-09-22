package com.blue.gdx.slide.screen.game;

import com.badlogic.gdx.Game;

public class PerfectionistGameScreen extends SlideGameScreen {

   private int moves = 0;
   private int score = 0;

   
   public PerfectionistGameScreen(Game game) {
      super(game);
   }

   @Override
   protected void renderUpdate(float delta) {
      //ACtually nothing to do for infinite mode
   }

   @Override
   protected void drawStatusText() {
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      batch.begin();
      
      drawScore();
      
      batch.end();
   }
   
   private void drawScore() {
      font.draw(batch,
            "Score: "+score+"\nMoves: "+moves,
            75, 
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
