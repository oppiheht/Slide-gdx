package com.blue.gdx.slide.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.blue.gdx.slide.ui.SlideAssetManager;

public class Background extends Image {

   public Background(AssetManager assetManager) {
      super(assetManager.get(SlideAssetManager.BACKGROUND, Texture.class));
      setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
   }

}
