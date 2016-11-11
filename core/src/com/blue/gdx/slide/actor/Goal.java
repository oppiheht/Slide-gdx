package com.blue.gdx.slide.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.blue.gdx.slide.util.SlideAssetManager;

public class Goal extends SlideActor {

   private boolean firstSetPositionCall = true;

   public Goal(AssetManager assetManager, int size) {
      super(assetManager.get(SlideAssetManager.GOAL, Texture.class), size);
   }

   @Override
   public void setPosition(float x, float y) {
      if (firstSetPositionCall) {
         firstSetPositionCall = false;
         addAction(fadeIn(x, y));
      }
      else {
         addAction(Actions.sequence(flyAway(), fadeIn(x, y)));
      }
      worldX = (int) x;
      worldY = (int) y;
   }

   private Action fadeIn(float x, float y) {
      return Actions.sequence(Actions.alpha(0), Actions.moveTo(x * size, y * size), Actions.fadeIn(2));
   }

   public Action flyAway() {
      return Actions.sequence(Actions.moveTo(getX(), Gdx.graphics.getHeight() + 100, 2, Interpolation.exp5In));
   }

}
