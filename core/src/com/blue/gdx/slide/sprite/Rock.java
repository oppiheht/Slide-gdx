package com.blue.gdx.slide.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Rock extends Sprite {

   public Rock(Texture texture, int size) {
      super(texture);
      setOrigin(0, 0);
      setSize(size, size);
   }

}
