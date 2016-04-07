package com.blue.gdx.slide.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.blue.gdx.slide.level.Direction;

public class KeyboardInputHandler implements InputHandler {

   @Override
   public Direction queryInputDirection() {
      
      boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
      boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
      boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
      boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);

      if (leftPressed) {
         return Direction.WEST;
      }
      if (rightPressed) {
         return Direction.EAST;
      }
      if (upPressed) {
         return Direction.NORTH;
      }
      if (downPressed) {
       return Direction.SOUTH;
      }
      
      return null;
   }
}
