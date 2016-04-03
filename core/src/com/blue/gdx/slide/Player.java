package com.blue.gdx.slide;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

@SuppressWarnings("serial")
public class Player extends Rectangle {

   public Player(Rectangle rect) {
      super(rect);
   }
   
   public void setPosition(int newX, int newY) {
      x = newX;
      y = newY;
   }

   public void drawDebug(ShapeRenderer renderer) {
      renderer.setColor(GameColors.PLAYER);
      renderer.rect(
            x * GameScreen.GRID_CELL,
            y * GameScreen.GRID_CELL,
            GameScreen.GRID_CELL,
            GameScreen.GRID_CELL);
   }
}
