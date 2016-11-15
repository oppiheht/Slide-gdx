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
         gameOver();
      }
   }

   @Override
   protected void updateScoreKeeper() {
      game.getHighScoreKeeper().timedModeCompleted(score);
   }

   @Override
   protected boolean showResetButton() {
      return true;
   }

   @Override
   protected String getStatusText() {
      return "Time Left:  " + formatTime(timer) + 
            "\nScore: " + score;
   }


   protected String formatTime(float seconds) {
      int minutes = (int)seconds / 60;
      int remSeconds = (int)seconds % 60;
      if (seconds < 10) {
         return String.format("%1.2f", seconds);
      }
      String zeroPadding = remSeconds < 10 ? "0" : "";
      return minutes + ":" +zeroPadding + remSeconds;
   }
}
