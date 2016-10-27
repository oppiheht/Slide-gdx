package com.blue.gdx.slide.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Goal extends Sprite {

   public Goal(Texture texture, int size) {
      super(texture);
      setCenter(size/2.0f, size/2.0f);
      setScale(size / (float)texture.getWidth());
   }

}
