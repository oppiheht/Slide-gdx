package com.blue.gdx.slide.util;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class SlideAssetManager extends AssetManager {

   private static String assetLocation = "nikky_space/";
   
   public static String FONTFILE = "fonts/colo_pro_black.fnt";
   public static String FONTTEXTURE = "fonts/colo_pro_black.png";
   public static String BACKGROUND =  assetLocation+"menuBg.png";
   
   public static String LOGO =  assetLocation+"logo.png";
   public static String PLAYTIMEDBUTTON =  assetLocation+"playTimedButton.png";
   public static String PLAYINFINITEBUTTON =  assetLocation+"playInfiniteButton.png";
   public static String PLAYPERFECTIONISTBUTTON =  assetLocation+"playPerfectionistButton.png";
   public static String SCORESBUTTON =  assetLocation+"scoresButton.png";

   public static String BACKBUTTON =  assetLocation+"backButton.png";
   public static String RESETBUTTON =  assetLocation+"resetButton.png";
   public static String QUITBUTTON =  assetLocation+"quitButton.png";
   public static String CONFIRMATIONBUTTON = assetLocation+"confirmationButton.png";

   public static final String[] ROCKS = {
            assetLocation+"asteroid1.png",
            assetLocation+"asteroid2.png",
            assetLocation+"asteroid3.png"
   };
   public static String PLAYER =  assetLocation+"player.png";
   public static String GOAL =  assetLocation+"goal.png";

   
   public void loadAllAssetsBlocking() {
      load(FONTFILE, BitmapFont.class);
      load(BACKGROUND, Texture.class);
      load(LOGO, Texture.class);
      load(PLAYTIMEDBUTTON, Texture.class);
      load(PLAYINFINITEBUTTON, Texture.class);
      load(PLAYPERFECTIONISTBUTTON, Texture.class);
      load(SCORESBUTTON, Texture.class);
      load(BACKBUTTON, Texture.class);
      load(RESETBUTTON, Texture.class);
      load(QUITBUTTON, Texture.class);
      load(CONFIRMATIONBUTTON, Texture.class);
      for (String s : ROCKS) {
         load(s, Texture.class);
      }
      load(PLAYER, Texture.class);
      load(GOAL, Texture.class);
      
      finishLoading();
   }
}
