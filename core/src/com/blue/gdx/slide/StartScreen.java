package com.blue.gdx.slide;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.ui.PlayButton;

public class StartScreen extends ScreenAdapter {
   
   private final Game game;
   
   private static final int WORLD_WIDTH = 480;
   private static final int WORLD_HEIGHT = 640;
   
   private Stage stage;
   
   private Texture backgroundTexture;
   PlayButton playButton;
   
   public StartScreen(Game game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));
      Gdx.input.setInputProcessor(stage);
      
      backgroundTexture = new Texture(Gdx.files.internal("bg.png"));
      Image background = new Image(backgroundTexture);
      
      playButton = new PlayButton();
      playButton.setPosition(WORLD_WIDTH / 2, WORLD_HEIGHT / 3, Align.center);
      playButton.addListener(new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            game.setScreen(new GameScreen(game));
            dispose();
         }
      });
            
      stage.addActor(background);
      stage.addActor(playButton);
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
      playButton.dispose();
   }

}
