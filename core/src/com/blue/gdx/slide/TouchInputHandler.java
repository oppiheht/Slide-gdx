package com.blue.gdx.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class TouchInputHandler {

   private PlayerController playerController;
   
   private Vector2 startTouch = new Vector2();
   private Vector2 currentTouch = new Vector2();
   private boolean screenIsTouched = false;
   
   public TouchInputHandler(PlayerController playerController) {
      this.playerController = playerController;
   }
   
   public void query() {
      if (Gdx.input.justTouched()) {
         handleNewScreenTouch();
      }
      else if (Gdx.input.isTouched()) {
         handleScreenHold();
      }
      else if (screenIsTouched) {
         handleScreenEndTouch();
      }
   }

   private void handleScreenEndTouch() {
      System.out.println("Screen end touch at "+currentTouch);
      screenIsTouched = false;
      float xDelta = currentTouch.x - startTouch.x;
      float yDelta = currentTouch.y - startTouch.y;
      if (Math.abs(xDelta) > Math.abs(yDelta)) {
         if (xDelta > 0) {
            playerController.movePlayerEast();
         }
         else {
            playerController.movePlayerWest();
         }
      }
      else {
         if (yDelta > 0) {
            playerController.movePlayerSouth();
         }
         else {
            playerController.movePlayerNorth();
         }
      }
   }

   private void handleScreenHold() {
      currentTouch.x = Gdx.input.getX();
      currentTouch.y = Gdx.input.getY();
   }

   private void handleNewScreenTouch() {
      startTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
      screenIsTouched = true;
   }
   
}
