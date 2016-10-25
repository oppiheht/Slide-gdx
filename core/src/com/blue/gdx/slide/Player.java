package com.blue.gdx.slide;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blue.gdx.slide.screen.game.SlideGameScreen;

public class Player extends Sprite {
   
   private static final int CELL_SIZE = SlideGameScreen.GRID_CELL;
   
   protected TextureRegion playerTexture;
   protected float rotationSpeed = 0f;
   Random rand = new Random();

   public Player(Texture playerTexture) {
      this.playerTexture = new TextureRegion(playerTexture);
      rotateRandomly();
   }
   
   @Override
   public void setPosition(float x, float y) {
      rotateRandomly();
      super.setPosition(x, y);
   }
   
   public void rotateRandomly() {
      //between -10.0 and 10.0, with one decimal place
      rotationSpeed = (rand.nextInt(100) - 50) / 5f;
   }
   
   public void draw(SpriteBatch batch, float delta) {
      updateRotation(delta);
      batch.draw(playerTexture, getX() * CELL_SIZE, getY() * CELL_SIZE, 16, 16, 32, 32, 1, 1, getRotation());
   }
   
   protected void updateRotation(float delta) {
      setRotation(getRotation() + (rotationSpeed * delta) % 360f);
   }
   
}
