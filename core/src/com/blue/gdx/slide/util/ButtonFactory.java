package com.blue.gdx.slide.util;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
      positionInBottomLeft(backButton);
      backButton.addListener(listener);
      return backButton;
   }

   public static ImageButton createResetButton(AssetManager assetManager, ActorGestureListener listener) {
      ImageButton resetButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.RESETBUTTON, Texture.class))),
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.RESETBUTTON, Texture.class)))
      );
      positionInBottomRight(resetButton);
      resetButton.addListener(listener);
      return resetButton;
   }

   public static ImageButton createConfirmationButton(AssetManager assetManager, ActorGestureListener listener) {
      ImageButton confirmationButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.CONFIRMATIONBUTTON, Texture.class))),
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.CONFIRMATIONBUTTON, Texture.class)))
      );
      positionInBottomRight(confirmationButton);
      confirmationButton.addListener(listener);
      return confirmationButton;
   }

   public static ImageButton createQuitButton(AssetManager assetManager, ActorGestureListener listener) {
      ImageButton quitButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.QUITBUTTON, Texture.class))),
            new TextureRegionDrawable(
                  new TextureRegion(assetManager.get(SlideAssetManager.QUITBUTTON, Texture.class)))
      );
      positionInBottomLeft(quitButton);
      quitButton.addListener(listener);
      return quitButton;
   }

   public static ImageButton createStartScreenButton(Texture texture, float height, ActorGestureListener listener) {
      ImageButton imageButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(texture)),
            new TextureRegionDrawable(
                  new TextureRegion(texture))
      );
      imageButton.setWidth(Gdx.graphics.getWidth() * 0.7f);
      imageButton.setPosition(Gdx.graphics.getWidth() / 2, height, Align.center);
      imageButton.addListener(listener);
      return imageButton;
   }

   private static void positionInBottomLeft(ImageButton button) {
      button.setWidth(Gdx.graphics.getWidth() * 0.3f);
      button.setPosition(Gdx.graphics.getWidth() / 5, Gdx.graphics.getHeight() * 0.05f, Align.center);
   }

   private static void positionInBottomRight(ImageButton button) {
      button.setWidth(Gdx.graphics.getWidth() * 0.3f);
      button.setPosition(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.05f, Align.center);
   }
}
