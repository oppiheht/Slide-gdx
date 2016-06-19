package com.blue.gdx.slide;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.blue.gdx.slide.screen.game.TimedGameScreen;

@SuppressWarnings("serial")
public class Player extends Rectangle {
   
   Texture playerTexture;

   public Player(Rectangle rect, Texture playerTexture) {
      super(rect);
      this.playerTexture = playerTexture;
   }
   
   public void setPosition(int newX, int newY) {
      x = newX;
      y = newY;
   }

   public void draw(SpriteBatch batch) {
      batch.draw(playerTexture, x * TimedGameScreen.GRID_CELL, y * TimedGameScreen.GRID_CELL);
   }
   
   public void drawDebug(ShapeRenderer renderer) {
      renderer.setColor(GameColors.PLAYER);
      renderer.rect(
            x * TimedGameScreen.GRID_CELL,
            y * TimedGameScreen.GRID_CELL,
            TimedGameScreen.GRID_CELL,
            TimedGameScreen.GRID_CELL);
   }
}
