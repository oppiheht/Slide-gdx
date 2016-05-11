package com.blue.gdx.slide.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayButton extends ImageButton {

   private static Texture playTexture;
   private static Texture playDownTexture;
   
   public PlayButton() {
      //Need to initialize our textures to pass into super, but super has to be
      //the first line of the constructor. Gotta think of a cleaner way to do this
      //instead of cheating with one huge super call...
      super(new TextureRegionDrawable(new TextureRegion(playTexture = new Texture(Gdx.files.internal("play.png")))),
            new TextureRegionDrawable(new TextureRegion(playDownTexture = new Texture(Gdx.files.internal("playPress.png")))));
      ;
   }
   
   public void dispose() {
      playTexture.dispose();
      playDownTexture.dispose();
   }
   
   
}
