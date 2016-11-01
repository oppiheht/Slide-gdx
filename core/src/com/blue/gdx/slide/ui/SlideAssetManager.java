package com.blue.gdx.slide.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class SlideAssetManager extends AssetManager {

   private static String assetLocation = "nikky_space";
   
   public static String FONTFILE = "fonts/colo_pro_black.fnt";
   public static String FONTTEXTURE = "fonts/colo_pro_black.png";
   public static String BACKGROUND = assetLocation+"/menuBg.png";
   
   public static String LOGO = assetLocation+"/logo.png";
   public static String PLAYTIMEDBUTTON = assetLocation+"/playTimedButton.png";
   public static String PLAYINFINITEBUTTON = assetLocation+"/playInfiniteButton.png";
   public static String PLAYPERFECTIONISTBUTTON = assetLocation+"/playPerfectionistButton.png";
   
   public static String ROCK1 = assetLocation+"/asteroid1.png";
   public static String ROCK2 = assetLocation+"/asteroid2.png";
   public static String ROCK3 = assetLocation+"/asteroid3.png";
   public static String PLAYER = assetLocation+"/player.png";
   public static String GOAL = assetLocation+"/goal.png";
                 
   
   public void loadAllAssetsBlocking() {
      load(FONTFILE, BitmapFont.class);
      load(BACKGROUND, Texture.class);
      load(LOGO, Texture.class);
      load(PLAYTIMEDBUTTON, Texture.class);
      load(PLAYINFINITEBUTTON, Texture.class);
      load(PLAYPERFECTIONISTBUTTON, Texture.class);
      load(ROCK1, Texture.class);
      load(ROCK2, Texture.class);
      load(ROCK3, Texture.class);
      load(PLAYER, Texture.class);
      load(GOAL, Texture.class);
      
      finishLoading();
   }
}
