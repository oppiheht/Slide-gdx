package com.blue.gdx.slide;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class StartScreen extends ScreenAdapter {
   
   private final Game game;
   
   private static final int WORLD_WIDTH = 480;
   private static final int WORLD_HEIGHT = 640;
   
   private Stage stage;
   
   private Texture backgroundTexture;
   private Texture playTexture;
   private Texture playDownTexture;
   
   public StartScreen(Game game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      stage = new Stage(new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT));
      Gdx.input.setInputProcessor(stage);
      
      backgroundTexture = new Texture(Gdx.files.internal("bg.png"));
      Image background = new Image(backgroundTexture);
      playTexture = new Texture(Gdx.files.internal("play.png"));
      playDownTexture = new Texture(Gdx.files.internal("playPress.png"));
      
      ImageButton play = new ImageButton(
            new TextureRegionDrawable(new TextureRegion(playTexture)),
            new TextureRegionDrawable(new TextureRegion(playDownTexture))
            );
      play.setPosition(WORLD_WIDTH / 2, WORLD_HEIGHT / 3, Align.center);
      play.addListener(new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            game.setScreen(new GameScreen(game));
            dispose();
         }
      });
            
      stage.addActor(background);
      stage.addActor(play);
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
      playTexture.dispose();
      playDownTexture.dispose();
   }

}
