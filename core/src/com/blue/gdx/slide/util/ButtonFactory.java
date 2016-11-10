package com.blue.gdx.slide.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class ButtonFactory {
   public static ImageButton createBackButton(AssetManager assetManager, ActorGestureListener listener) {
      ImageButton backButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.BACKBUTTON, Texture.class))),
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.BACKBUTTON, Texture.class)))
      );
      backButton.setWidth(Gdx.graphics.getWidth() * 0.3f);
      backButton.setPosition(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 0.05f, Align.center);
      backButton.addListener(listener);
      return backButton;
   }
}
