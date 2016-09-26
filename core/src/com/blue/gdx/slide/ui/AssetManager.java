package com.blue.gdx.slide.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class AssetManager {

   private static String assetLocation = "temp_assets";
   
   public static FileHandle background = Gdx.files.internal(assetLocation+"/gameBg.png");
   public static FileHandle rock = Gdx.files.internal(assetLocation+"/wall.png");
   public static FileHandle player = Gdx.files.internal(assetLocation+"/player.png");
   public static FileHandle goal = Gdx.files.internal(assetLocation+"/goal.png");
   public static FileHandle menu = Gdx.files.internal(assetLocation+"/menuBg.png");
   public static FileHandle logo = Gdx.files.internal(assetLocation+"/logo.png");
   public static FileHandle play = Gdx.files.internal(assetLocation+"/playButton.png");
   public static FileHandle playPress = Gdx.files.internal(assetLocation+"/playButtonDown.png");
}
