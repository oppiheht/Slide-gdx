package com.blue.gdx.slide.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Background extends Sprite {

   public Background(Texture texture) {
      super(texture);
      setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
   }

}
