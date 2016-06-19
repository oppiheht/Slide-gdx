package com.blue.gdx.slide.screen.game;

import com.badlogic.gdx.Game;

public class TimedGameScreen extends SlideGameScreen {

   private static final float TIMED_MODE_DURATION = 120L;

   private float timer = TIMED_MODE_DURATION;
   
   public TimedGameScreen(Game game) {
      super(game);
   }
   
   
   @Override
   protected void renderUpdate(float delta) {
      decrementTimer(delta);
   }
   
   private void decrementTimer(float delta) {
      timer -= delta;
      if (timer < 0) {
         state = STATE.GAME_OVER;
      }
   }
   
   
   @Override
   protected void drawStatusText() {
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      batch.begin();
      
      drawScore();
      drawTimer();
      
      batch.end();
   }
   
   private void drawScore() {
      font.draw(batch,
            "Score: "+score+"\nMoves: "+moves+" of "+map.getSolutionLength(), 
            75, 
            WORLD_HEIGHT - 200);
      
   }
   
   private void drawTimer() {
      font.draw(batch, "Time Left:"+(int)timer, 75, WORLD_HEIGHT - 175);
   }
   
   @Override
   protected void drawGameOver() {
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      batch.begin();
      
      font.draw(
            batch, 
            "Game Over! You completed "+score+" levels", 
            100, 
            WORLD_HEIGHT / 2);
      
      batch.end();
   }
}
