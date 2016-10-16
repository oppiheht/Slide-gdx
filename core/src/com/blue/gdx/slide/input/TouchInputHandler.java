package com.blue.gdx.slide.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.blue.gdx.slide.level.Direction;

public class TouchInputHandler implements InputHandler {

   private Vector2 startTouch = new Vector2();
   private Vector2 currentTouch = new Vector2();
   private boolean screenIsTouched = false;
   
   @Override
   public Direction queryInputDirection() {
      Direction inputDirection = null;
      if (Gdx.input.justTouched()) {
         handleNewScreenTouch();
      }
      else if (Gdx.input.isTouched()) {
         handleScreenHold();
      }
      else if (screenIsTouched) {
         inputDirection = handleScreenEndTouch();
      }
      return inputDirection;
   }

   private Direction handleScreenEndTouch() {
      //System.out.println("Screen end touch at "+currentTouch);
      screenIsTouched = false;
      float xDelta = currentTouch.x - startTouch.x;
      float yDelta = currentTouch.y - startTouch.y;
      if (Math.abs(xDelta) > Math.abs(yDelta)) {
         if (xDelta > 0) {
            return Direction.EAST;
         }
         else {
            return Direction.WEST;
         }
      }
      else {
         if (yDelta > 0) {
            return Direction.SOUTH;
         }
         else {
            return Direction.NORTH;
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
