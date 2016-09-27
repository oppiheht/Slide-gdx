package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.screen.game.InfiniteGameScreen;
import com.blue.gdx.slide.screen.game.PerfectionistGameScreen;
import com.blue.gdx.slide.screen.game.TimedGameScreen;
import com.blue.gdx.slide.ui.AssetManager;
import com.blue.gdx.slide.ui.PlayInfiniteButton;
import com.blue.gdx.slide.ui.PlayPerfectionistButton;
import com.blue.gdx.slide.ui.PlayTimedButton;

public class StartScreen extends ScreenAdapter {
   
   private final Game game;
   
   private static final int WORLD_WIDTH = 480;
   private static final int WORLD_HEIGHT = 640;
   
   private Stage stage;
   
   private Texture backgroundTexture;
   private Texture gameLogoTexture;
   PlayTimedButton playTimedButton;
   PlayPerfectionistButton playPerfectionistButton;
   PlayInfiniteButton playInfiniteButton;
   protected BitmapFont font;
   
   public StartScreen(Game game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));
      Gdx.input.setInputProcessor(stage);
      
      font = new BitmapFont();

      backgroundTexture = new Texture(AssetManager.menu);
      Image background = new Image(backgroundTexture);
      
      gameLogoTexture = new Texture(AssetManager.logo);
      Image logo = new Image(gameLogoTexture);
      logo.setPosition(WORLD_WIDTH / 2, WORLD_HEIGHT - 160, Align.center);
      
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
      playTimedButton = new PlayTimedButton();
      playTimedButton.setPosition(WORLD_WIDTH / 4, WORLD_HEIGHT / 3, Align.center);
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
      playPerfectionistButton = new PlayPerfectionistButton();
      playPerfectionistButton.setPosition(WORLD_WIDTH / 4 * 3, WORLD_HEIGHT / 3, Align.center);
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
      playInfiniteButton = new PlayInfiniteButton();
      playInfiniteButton.setPosition(WORLD_WIDTH / 4, WORLD_HEIGHT / 8, Align.center);
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
      backgroundTexture.dispose();
   }

}
