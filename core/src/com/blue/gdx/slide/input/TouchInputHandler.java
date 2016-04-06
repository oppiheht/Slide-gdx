package com.blue.gdx.slide.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.blue.gdx.slide.level.Solver;

public class TouchInputHandler implements InputHandler {

   private Vector2 startTouch = new Vector2();
   private Vector2 currentTouch = new Vector2();
   private boolean screenIsTouched = false;
   
   @Override
   public int queryInputDirection() {
      int inputDirection = -1;
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

   private int handleScreenEndTouch() {
      System.out.println("Screen end touch at "+currentTouch);
      screenIsTouched = false;
      float xDelta = currentTouch.x - startTouch.x;
      float yDelta = currentTouch.y - startTouch.y;
      if (Math.abs(xDelta) > Math.abs(yDelta)) {
         if (xDelta > 0) {
            return Solver.EAST;
         }
         else {
            return Solver.WEST;
         }
      }
      else {
         if (yDelta > 0) {
            return Solver.SOUTH;
         }
         else {
            return Solver.NORTH;
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
