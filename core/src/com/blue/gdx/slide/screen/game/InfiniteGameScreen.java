package com.blue.gdx.slide.screen.game;

import com.blue.gdx.slide.SlideGame;

public class InfiniteGameScreen extends SlideGameScreen {

   private static final float STARTING_TIME = 1L;
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
   protected String getStatusText() {
      return "\nTime Left:  " + formatTime(timer);
   }
   
   @Override
   protected String getGameOverText() {
      return "Game Over!\n You completed "+score+" levels";
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
