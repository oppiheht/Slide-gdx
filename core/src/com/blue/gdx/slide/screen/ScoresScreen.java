package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.actor.StatusText;
import com.blue.gdx.slide.util.ButtonFactory;
import com.blue.gdx.slide.util.HighScoreKeeper;

public class ScoresScreen extends ScreenAdapter {

   private final SlideGame game;
   private Stage stage;

   public ScoresScreen(SlideGame game) {
      this.game = game;
   }

   @Override
   public void show() {
      stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      Gdx.input.setInputProcessor(stage);

      StatusText titleText = createTitleText();
      StatusText scoreText = createScoreText();
      ImageButton backButton = createBackButton();

      stage.addActor(new Background(game.getAssetManager()));
      stage.addActor(titleText);
      stage.addActor(scoreText);
      stage.addActor(backButton);
   }

   private StatusText createTitleText() {
      StatusText text = new StatusText(game.getAssetManager());
      text.setText("High Scores!");
      text.setPosition(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.85f, Align.center);
      return text;
   }

   private StatusText createScoreText() {
      HighScoreKeeper.Scores scores = game.getHighScoreKeeper().getHighScores();
      StatusText text = new StatusText(game.getAssetManager());
      text.setText("Timed:                             "+ scores.timed +
            "\nInfinite:                              " + scores.infinite +
            "\nPerfect:                              " + scores.perfect +
            "\n\nAstronauts Saved:    " + scores.total
      );
      text.setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.7f, Align.center);
      text.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
      return text;
   }

   private ImageButton createBackButton() {
      ImageButton backButton = ButtonFactory.createBackButton(game.getAssetManager(), new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            backToHome();
         }
      });
      return backButton;
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      stage.act(delta);
      stage.draw();
      queryBackKeyPressed();
   }

   private void backToHome() {
      game.setScreen(new StartScreen(game));
      dispose();
   }

   protected void queryBackKeyPressed() {
      if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
         backToHome();
      }
   }
}
