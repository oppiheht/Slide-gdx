package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.screen.game.InfiniteGameScreen;
import com.blue.gdx.slide.screen.game.PerfectionistGameScreen;
import com.blue.gdx.slide.screen.game.TimedGameScreen;
import com.blue.gdx.slide.util.ButtonFactory;
import com.blue.gdx.slide.util.SlideAssetManager;

public class StartScreen extends ScreenAdapter {
   
   private final SlideGame game;
   private Stage stage;
   
   public StartScreen(SlideGame game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      Gdx.input.setInputProcessor(stage);
      
      Background background = new Background(game.getAssetManager());

      Texture gameLogoTexture = game.getAssetManager().get(SlideAssetManager.LOGO, Texture.class);
      Image logo = new Image(gameLogoTexture);
      logo.setSize(Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.2f);
      logo.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() * 0.77f, Align.center);

      ImageButton playInfiniteButton = createPlayInfiniteButton();
      ImageButton playTimedButton = createPlayTimedButton();
      ImageButton playPerfectionistButton = createPlayPerfectionistButton();
      ImageButton scoresButton = createScoresButton();
            
      stage.addActor(background);
      background.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(3)));
      stage.addActor(logo);
      logo.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
      stage.addActor(playTimedButton);
      stage.addActor(playInfiniteButton);
      playInfiniteButton.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
      playTimedButton.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
      stage.addActor(playPerfectionistButton);
      playPerfectionistButton.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
      stage.addActor(scoresButton);
      scoresButton.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
   }

   private ImageButton createPlayInfiniteButton() {
      ImageButton playInfiniteButton = ButtonFactory.createStartScreenButton(
            game.getAssetManager().get(SlideAssetManager.PLAYINFINITEBUTTON, Texture.class),
            Gdx.graphics.getHeight() * 0.5f,
            new ActorGestureListener() {
               @Override
               public void tap(InputEvent event, float x, float y, int count, int button) {
                  super.tap(event, x, y, count, button);
                  game.setScreen(new InfiniteGameScreen(game));
                  dispose();
               }
            }
      );
      return playInfiniteButton;
   }

   private ImageButton createPlayTimedButton() {
      ImageButton playTimedButton = ButtonFactory.createStartScreenButton(
            game.getAssetManager().get(SlideAssetManager.PLAYTIMEDBUTTON, Texture.class),
            Gdx.graphics.getHeight() * 0.38f,
            new ActorGestureListener() {
               @Override
               public void tap(InputEvent event, float x, float y, int count, int button) {
                  super.tap(event, x, y, count, button);
                  game.setScreen(new TimedGameScreen(game));
                  dispose();
               }
            }
      );
      return playTimedButton;
   }
   
   private ImageButton createPlayPerfectionistButton() {
      ImageButton playPerfectionistButton = ButtonFactory.createStartScreenButton(
            game.getAssetManager().get(SlideAssetManager.PLAYPERFECTIONISTBUTTON, Texture.class),
            Gdx.graphics.getHeight() * 0.26f,
            new ActorGestureListener() {
               @Override
               public void tap(InputEvent event, float x, float y, int count, int button) {
                  super.tap(event, x, y, count, button);
                  game.setScreen(new PerfectionistGameScreen(game));
                  dispose();
               }
            }
      );
      return playPerfectionistButton;
   }

   private ImageButton createScoresButton() {
      ImageButton scoresButton = ButtonFactory.createStartScreenButton(
            game.getAssetManager().get(SlideAssetManager.SCORESBUTTON, Texture.class),
            Gdx.graphics.getHeight() * 0.14f,
            new ActorGestureListener() {
               @Override
               public void tap(InputEvent event, float x, float y, int count, int button) {
                  super.tap(event, x, y, count, button);
                  game.setScreen(new ScoresScreen(game));
                  dispose();
               }
            }
      );
      return scoresButton;
   }

   @Override
   public void resize(int width, int height) {
      stage.getViewport().update(width, height, true);
   }
   
   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      stage.act(delta);
      stage.draw();
      queryBackKeyPressed();
   }

   protected void queryBackKeyPressed() {
      if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
         Gdx.app.exit();
      }
   }
   
   @Override
   public void dispose() {
      stage.dispose();
   }

}
