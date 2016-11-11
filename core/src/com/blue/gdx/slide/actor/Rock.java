package com.blue.gdx.slide.actor;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.blue.gdx.slide.util.SlideAssetManager;

public class Rock extends SlideActor {

   private int worldCenterX = 0;
   private int worldCenterY = 0;

   public Rock(AssetManager assetManager, int size, int worldCenterX, int worldCenterY) {
      super(size);
      this.worldCenterX = worldCenterX;
      this.worldCenterY = worldCenterY;
      super.setPosition(worldCenterX, worldCenterY);
      setDrawable(assetManager.get(SlideAssetManager.ROCKS[MathUtils.random(0, SlideAssetManager.ROCKS.length-1)], Texture.class));
      setVisible(false);
   }

   @Override
   public void setPosition(float x, float y) {
      addAction(Actions.sequence(
            Actions.visible(true),
            Actions.moveTo(worldCenterX, worldCenterY, 0.5f, Interpolation.pow2),
            Actions.moveTo(x * size, y * size, 0.5f, Interpolation.pow2)
      ));
      this.worldX = (int) x;
      this.worldX = (int) y;
   }

}
