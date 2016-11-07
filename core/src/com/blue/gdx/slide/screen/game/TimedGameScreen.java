package com.blue.gdx.slide.screen.game;

import com.blue.gdx.slide.SlideGame;

public class TimedGameScreen extends SlideGameScreen {

   private static final float TIMED_MODE_DURATION = 90L;
   private static final float LEVEL_COMPLETION_TIME_BONUS = 7L;

   private float timer = TIMED_MODE_DURATION;
   private int moves = 0;

   public TimedGameScreen(SlideGame game) {
      super(game);
   }
   
   
   @Override
   protected void renderUpdate(float delta) {
      decrementTimer(delta);
   }
   
   @Override
   protected void onPlayerMove() {
      moves++;
   }
   
   @Override
   protected void onLevelCompleted() {
      moves = 0;
      score++;
      timer += LEVEL_COMPLETION_TIME_BONUS;
   }
   
   private void decrementTimer(float delta) {
      timer -= delta;
      if (timer < 0) {
         state = STATE.GAME_OVER;
      }
   }
   

   @Override
   protected String getStatusText() {
      return "Time Left:  " + formatTime(timer) + 
            "\nScore: " + score;
   }
   
}
