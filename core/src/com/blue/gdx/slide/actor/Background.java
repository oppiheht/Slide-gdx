package com.blue.gdx.slide.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.blue.gdx.slide.util.SlideAssetManager;

public class Background extends Image {

   //On some devices, the left side of the screen has a pixel bleeding issue. Upping the image size fixes this
   private final int PIXEL_BLEED_PADDING = 1;

   public Background(AssetManager assetManager) {
      super(assetManager.get(SlideAssetManager.BACKGROUND, Texture.class));
      setSize(Gdx.graphics.getWidth() + PIXEL_BLEED_PADDING, Gdx.graphics.getHeight() + PIXEL_BLEED_PADDING);
   }

}
