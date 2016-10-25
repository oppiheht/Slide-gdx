package com.blue.gdx.slide.screen.game;

import com.badlogic.gdx.Gdx;
import com.blue.gdx.slide.SlideGame;

public class InfiniteGameScreen extends SlideGameScreen {

   private static final float STARTING_TIME = 30L;
   private static final float LEVEL_COMPLETION_TIME_BONUS = 7L;

   private float timer = STARTING_TIME;
   private int score = 0;
   
   public InfiniteGameScreen(SlideGame game) {
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
      drawTimer();
   }
   
   private void drawTimer() {
      font.draw(batch, "Time Left:  " + formatTime(timer), STATUS_FONT_X, Gdx.graphics.getHeight() - 230);
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
            Gdx.graphics.getHeight() / 2);
      
      batch.end();
   }

   @Override
   protected void onPlayerMove() {
      
   }

   @Override
   protected void onLevelCompleted() {
      score++;
      timer += LEVEL_COMPLETION_TIME_BONUS;
   }

}
