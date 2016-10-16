package com.blue.gdx.slide.ui;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {

   private static String assetLocation = "space";
   
   private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
   
   public static FileHandle background = Gdx.files.internal(assetLocation+"/menuBg.png");
   public static FileHandle rock = Gdx.files.internal(assetLocation+"/asteroid1.png");
   public static FileHandle player = Gdx.files.internal(assetLocation+"/player.png");
   public static FileHandle goal = Gdx.files.internal(assetLocation+"/goal.png");
   public static FileHandle menu = Gdx.files.internal(assetLocation+"/menuBg.png");
   public static FileHandle logo = Gdx.files.internal(assetLocation+"/logo.png");
   
   public static FileHandle fontFile = Gdx.files.internal("fonts/ken_future.fnt");
   public static FileHandle fontTexture = Gdx.files.internal("fonts/ken_future.png");
   
   public static void loadAssets() {
      texturesMap.put("playTimed", new TextureRegion(new Texture(Gdx.files.internal(assetLocation+"/playTimedButton.png"))));
      texturesMap.put("playTimedPress", new TextureRegion(new Texture(Gdx.files.internal(assetLocation+"/playTimedButtonDown.png"))));
      texturesMap.put("playInfinite", new TextureRegion(new Texture(Gdx.files.internal(assetLocation+"/playInfiniteButton.png"))));
      texturesMap.put("playInfinitePress", new TextureRegion(new Texture(Gdx.files.internal(assetLocation+"/playInfiniteButtonDown.png"))));
      texturesMap.put("playPerfectionist", new TextureRegion(new Texture(Gdx.files.internal(assetLocation+"/playPerfectionistButton.png"))));
      texturesMap.put("playPerfectionistPress", new TextureRegion(new Texture(Gdx.files.internal(assetLocation+"/playPerfectionistButtonDown.png"))));
   }
   
   public static TextureRegion getTextureRegion(String key) {
      return texturesMap.get(key);
   }
   
   public static void dispose() {
      for (TextureRegion tr : texturesMap.values()) {
         tr.getTexture().dispose();
      }
      texturesMap.clear();
   }
}
