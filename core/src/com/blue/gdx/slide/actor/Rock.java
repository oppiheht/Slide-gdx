package com.blue.gdx.slide.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.blue.gdx.slide.util.SlideAssetManager;

public class Rock extends SlideActor {

   public Rock(AssetManager assetManager, int size) {
      super(size);
      setPosition(-size, -size);
      setDrawable(assetManager.get(SlideAssetManager.ROCKS[MathUtils.random(0, SlideAssetManager.ROCKS.length-1)], Texture.class));
   }

   @Override
   protected float getAnimationDuration() {
      return 1.0f;
   }

   @Override
   public void setPosition(float x, float y) {
      super.setPosition(x, y);
      setVisible(true);
   }

   public void hide() {
      setVisible(false);
   }

}
