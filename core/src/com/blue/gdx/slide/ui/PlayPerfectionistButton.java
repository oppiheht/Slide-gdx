package com.blue.gdx.slide.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayPerfectionistButton extends ImageButton {
   
   public PlayPerfectionistButton() {
      super(new TextureRegionDrawable(AssetManager.getTextureRegion("playPerfectionist")),
            new TextureRegionDrawable(AssetManager.getTextureRegion("playPerfectionistPress")));
   }
}
