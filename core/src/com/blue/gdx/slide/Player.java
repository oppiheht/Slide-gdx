package com.blue.gdx.slide;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.blue.gdx.slide.screen.game.TimedGameScreen;

@SuppressWarnings("serial")
public class Player extends Rectangle {
   
   private static final int CELL_SIZE = TimedGameScreen.GRID_CELL;
   
   protected TextureRegion playerTexture;
   protected float rotation = 360.0f;
   protected float rotationSpeed = 0f;
   Random rand = new Random();

   public Player(Rectangle rect, Texture playerTexture) {
      super(rect);
      this.playerTexture = new TextureRegion(playerTexture);
      rotateRandomly();
   }
   
   public void setPosition(int newX, int newY) {
      x = newX;
      y = newY;
      rotateRandomly();
   }
   
   public void rotateRandomly() {
      //between -10.0 and 10.0, with one decimal place
      rotationSpeed = (rand.nextInt(100) - 50) / 5f;
   }
   
   public void draw(SpriteBatch batch, float delta) {
      updateRotation(delta);
      batch.draw(playerTexture, x * CELL_SIZE, y * CELL_SIZE, 16, 16, 32, 32, 1, 1, rotation);
   }
   
   protected void updateRotation(float delta) {
      rotation += (rotationSpeed * delta) % 360f;
   }
   
}
