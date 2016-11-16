package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.actor.CenteredText;
import com.blue.gdx.slide.util.ButtonFactory;


public class GameOverScreen extends ScreenAdapter {

   private final SlideGame game;
   private Stage stage;
   private String text;


   public GameOverScreen(SlideGame game, String text) {
      this.game = game;
      this.text = text;
   }

   @Override
   public void show() {
      stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      Gdx.input.setInputProcessor(stage);

      CenteredText gameOverText = new CenteredText(game.getAssetManager(), text);
      gameOverText.setPosition(Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() / 2);

      ImageButton backButton = createBackButton();

      stage.addActor(new Background(game.getAssetManager()));
      stage.addActor(gameOverText);
      stage.addActor(backButton);
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      stage.act(delta);
      stage.draw();
      queryBackKeyPressed();
   }

   private ImageButton createBackButton() {
      return ButtonFactory.createBackButton(game.getAssetManager(), new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            backToHome();
         }
      });
   }

   protected void queryBackKeyPressed() {
      if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
         backToHome();
      }
   }

   private void backToHome() {
      game.setScreen(new StartScreen(game));
      dispose();
   }

   @Override
   public void dispose() {
      stage.dispose();
   }

}
