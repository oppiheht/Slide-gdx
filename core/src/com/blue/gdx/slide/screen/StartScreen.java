package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.screen.game.InfiniteGameScreen;
import com.blue.gdx.slide.screen.game.PerfectionistGameScreen;
import com.blue.gdx.slide.screen.game.TimedGameScreen;
import com.blue.gdx.slide.ui.SlideAssetManager;

public class StartScreen extends ScreenAdapter {
   
   private final SlideGame game;
   
   private Stage stage;
   
   private Texture gameLogoTexture;
   private ImageButton playTimedButton;
   private ImageButton playPerfectionistButton;
   private ImageButton playInfiniteButton;
   protected BitmapFont font;
   
   public StartScreen(SlideGame game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      Gdx.input.setInputProcessor(stage);
      
      font = new BitmapFont();

      Texture backgroundTexture = game.getAssetManager().get(SlideAssetManager.BACKGROUND, Texture.class);
      Image background = new Image(backgroundTexture);
      background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      
      gameLogoTexture = game.getAssetManager().get(SlideAssetManager.LOGO, Texture.class);
      Image logo = new Image(gameLogoTexture);
      logo.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.2f);
      logo.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.77f, Align.center);
      
      createPlayTimedButton();
      createPlayPerfectionistButton();
      createPlayInfiniteButton();
            
      stage.addActor(background);
      stage.addActor(logo);
      stage.addActor(playTimedButton);
      stage.addActor(playPerfectionistButton);
      stage.addActor(playInfiniteButton);
   }

   private void createPlayTimedButton() {
      playTimedButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(game.getAssetManager().get(SlideAssetManager.PLAYTIMEDBUTTON, Texture.class))),
            new TextureRegionDrawable(
                  new TextureRegion(game.getAssetManager().get(SlideAssetManager.PLAYTIMEDBUTTON, Texture.class)))
            );
      playTimedButton.setWidth(Gdx.graphics.getWidth() * 0.7f);
      playTimedButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.5f, Align.center);
      playTimedButton.addListener(new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            game.setScreen(new TimedGameScreen(game));
            dispose();
         }
      });
   }
   
   private void createPlayPerfectionistButton() {
      playPerfectionistButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(game.getAssetManager().get(SlideAssetManager.PLAYPERFECTIONISTBUTTON, Texture.class))),
            new TextureRegionDrawable(
                  new TextureRegion(game.getAssetManager().get(SlideAssetManager.PLAYPERFECTIONISTBUTTON, Texture.class)))
            );
      playPerfectionistButton.setWidth(Gdx.graphics.getWidth() * 0.7f);
      playPerfectionistButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.35f, Align.center);
      playPerfectionistButton.addListener(new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            game.setScreen(new PerfectionistGameScreen(game));
            dispose();
         }
      });
   }
   
   private void createPlayInfiniteButton() {
      playInfiniteButton = new ImageButton(
            new TextureRegionDrawable(
                  new TextureRegion(game.getAssetManager().get(SlideAssetManager.PLAYINFINITEBUTTON, Texture.class))),
            new TextureRegionDrawable(
                  new TextureRegion(game.getAssetManager().get(SlideAssetManager.PLAYINFINITEBUTTON, Texture.class)))
            );
      playInfiniteButton.setWidth(Gdx.graphics.getWidth() * 0.7f);
      playInfiniteButton.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.2f, Align.center);
      playInfiniteButton.addListener(new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            game.setScreen(new InfiniteGameScreen(game));
            dispose();
         }
      });
   }
   
   @Override
   public void resize(int width, int height) {
      stage.getViewport().update(width, height, true);
   }
   
   @Override
   public void render(float delta) {
      stage.act(delta);
      stage.draw();
   }
   
   @Override
   public void dispose() {
      stage.dispose();
   }

}
