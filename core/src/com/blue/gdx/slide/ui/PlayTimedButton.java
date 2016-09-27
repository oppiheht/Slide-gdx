package com.blue.gdx.slide.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayTimedButton extends ImageButton {
   
   public PlayTimedButton() {
      super(new TextureRegionDrawable(AssetManager.getTextureRegion("playTimed")),
            new TextureRegionDrawable(AssetManager.getTextureRegion("playTimedPress")));
      ;
   }
   
}
