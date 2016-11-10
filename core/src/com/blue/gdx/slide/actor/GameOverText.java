package com.blue.gdx.slide.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.blue.gdx.slide.util.SlideAssetManager;


public class GameOverText extends Actor {

   private String text;
   private BitmapFont font;

   public GameOverText(AssetManager assetManager, String text) {
      this.text = text;
      font = assetManager.get(SlideAssetManager.FONTFILE, BitmapFont.class);
      font.getData().setScale(Gdx.graphics.getDensity());
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
               font.draw(
                batch,
                text,
                Gdx.graphics.getWidth() * 0.05f,
                Gdx.graphics.getHeight() / 2,
                Gdx.graphics.getWidth() * 0.9f,
                Align.center,
                true);
   }
}
