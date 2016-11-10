package com.blue.gdx.slide.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.blue.gdx.slide.util.SlideAssetManager;

public class Goal extends SlideActor {

   public Goal(AssetManager assetManager, int size) {
      super(assetManager.get(SlideAssetManager.GOAL, Texture.class), size);
   }

   @Override
   protected float getAnimationDuration() {
      return 1.0f;
   }


}
