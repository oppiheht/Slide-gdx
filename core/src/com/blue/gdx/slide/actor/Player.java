package com.blue.gdx.slide.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.blue.gdx.slide.ui.SlideAssetManager;

import java.util.Random;

public class Player extends SlideActor {
   
   protected float rotationSpeed = 0f;
   Random rand = new Random();

   public Player(AssetManager assetManager, int size) {
      super(assetManager.get(SlideAssetManager.PLAYER, Texture.class), size);
      rotateRandomly();
   }

   @Override
   protected float getAnimationDuration() {
      return 0.01f;
   }

   public void rotateRandomly() {
      //between -10.0 and 10.0, with one decimal place
      rotationSpeed = (rand.nextInt(100) - 50) / 5f;
   }

   @Override
   public void setPosition(float x, float y) {
      float distance = (Math.abs(x - worldX) + Math.abs(y - worldY));
      addAction(Actions.moveTo(x * size, y * size, getAnimationDuration() * distance, Interpolation.pow2));
      worldX = (int) x;
      worldY = (int) y;
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      updateRotation(delta);
   }

   protected void updateRotation(float delta) {
      setRotation(getRotation() + (rotationSpeed * delta) % 360f);
   }

   public void setStartPosition(int x, int y) {
      addAction(Actions.moveTo(x * size, y * size, 1.0f, Interpolation.pow2));
      worldX = (int) x;
      worldY = (int) y;
   }
}
