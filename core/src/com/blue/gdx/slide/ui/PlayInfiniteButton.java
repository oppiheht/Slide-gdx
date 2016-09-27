package com.blue.gdx.slide.ui;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayInfiniteButton extends ImageButton {

   public PlayInfiniteButton() {
      super(new TextureRegionDrawable(AssetManager.getTextureRegion("playInfinite")),
            new TextureRegionDrawable(AssetManager.getTextureRegion("playInfinitePress")));
   }

}
