package com.blue.gdx.slide.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rock extends Sprite {

   public Rock(Texture texture, int size) {
      super(texture);
      setCenter(size/2.0f, size/2.0f);
      setScale(size / (float)texture.getWidth());
   }

}
