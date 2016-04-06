package com.blue.gdx.slide.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.blue.gdx.slide.level.Solver;

public class KeyboardInputHandler implements InputHandler {

   @Override
   public int queryInputDirection() {
      
      boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
      boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
      boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
      boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

      if (leftPressed) {
         return Solver.WEST;
      }
      if (rightPressed) {
         return Solver.EAST;
      }
      if (upPressed) {
         return Solver.NORTH;
      }
      if (downPressed) {
       return Solver.SOUTH;
      }
      
      return -1;
   }
}
