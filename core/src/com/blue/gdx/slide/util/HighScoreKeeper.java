package com.blue.gdx.slide.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HighScoreKeeper {

   private final String TIMED_HIGH_SCORE_KEY = "timed";
   private final String PERFECT_HIGH_SCORE_KEY = "perfect";
   private final String INFINITE_LEVELS_COMPLETED_KEY = "infinite";
   private final String ALL_MODES_LEVELS_COMPLETED_KEY = "total";

   private Preferences preferences;

   public HighScoreKeeper() {
      preferences = Gdx.app.getPreferences("scores");
      setDefaultValuesIfEmpty();
   }

   public void timedModeCompleted(int levelsCompleted) {
      updateHighScore(levelsCompleted, TIMED_HIGH_SCORE_KEY);
      addToAllModesLevelsCompleted(levelsCompleted);
      preferences.flush();
   }

   public void perfectModeCompleted(int levelsCompleted) {
      updateHighScore(levelsCompleted, PERFECT_HIGH_SCORE_KEY);
      addToAllModesLevelsCompleted(levelsCompleted);
      preferences.flush();
   }

   public void infiniteLevelCompleted() {
      addToAllModesLevelsCompleted(1);
      preferences.flush();
   }

   public Scores getHighScores() {
      return new Scores(preferences);
   }

   private void updateHighScore(int score, String key) {
      int currentHighScore = preferences.getInteger(key);
      if (score > currentHighScore) {
         preferences.putInteger(key, score);
      }
   }

   private void addToAllModesLevelsCompleted(int levelsCompleted) {
      int currentAllModesLevelsCompleted = preferences.getInteger(ALL_MODES_LEVELS_COMPLETED_KEY);
      preferences.putInteger(ALL_MODES_LEVELS_COMPLETED_KEY, levelsCompleted + currentAllModesLevelsCompleted);
   }

   private void setDefaultValuesIfEmpty() {
      boolean flushNeeded = false;
      if (!preferences.contains(TIMED_HIGH_SCORE_KEY)) {
         preferences.putInteger(TIMED_HIGH_SCORE_KEY, 0);
         flushNeeded = true;
      }
      if (!preferences.contains(PERFECT_HIGH_SCORE_KEY)) {
         preferences.putInteger(PERFECT_HIGH_SCORE_KEY, 0);
         flushNeeded = true;
      }

      if (!preferences.contains(INFINITE_LEVELS_COMPLETED_KEY)) {
         preferences.putInteger(INFINITE_LEVELS_COMPLETED_KEY, 0);
         flushNeeded = true;
      }

      if (!preferences.contains(ALL_MODES_LEVELS_COMPLETED_KEY)) {
         preferences.putInteger(ALL_MODES_LEVELS_COMPLETED_KEY, 0);
         flushNeeded = true;
      }

      if (flushNeeded) {
         preferences.flush();
      }
   }

   public class Scores {
      public final int timed;
      public final int perfect;
      public final int infinite;
      public final int total;

      private Scores(Preferences preferences) {
         this.timed = preferences.getInteger(TIMED_HIGH_SCORE_KEY);
         this.perfect = preferences.getInteger(PERFECT_HIGH_SCORE_KEY);
         this.infinite = preferences.getInteger(INFINITE_LEVELS_COMPLETED_KEY);
         this.total = preferences.getInteger(ALL_MODES_LEVELS_COMPLETED_KEY);
      }
   }
}
