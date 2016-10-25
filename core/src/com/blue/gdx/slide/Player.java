package com.blue.gdx.slide;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Sprite {
   
   protected float rotationSpeed = 0f;
   Random rand = new Random();

   public Player(Texture playerTexture) {
      super(playerTexture);
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
      draw(batch);
   }
   
   protected void updateRotation(float delta) {
      setRotation(getRotation() + (rotationSpeed * delta) % 360f);
   }
   
}
