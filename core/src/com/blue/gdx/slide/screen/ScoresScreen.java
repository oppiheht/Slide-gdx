package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.actor.StatusText;
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

      stage.addActor(new Background(game.getAssetManager()));
      stage.addActor(titleText);
      stage.addActor(scoreText);

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
      text.setText("Timed: "+ scores.timed +
            "\nInfinite: " + scores.infinite +
            "\nPerfect: " + scores.perfect +
            "\n\nGems Collected: " + scores.total
      );
      text.setPosition(Gdx.graphics.getWidth() * 0.2f, Gdx.graphics.getHeight() * 0.7f, Align.center);
      text.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(2)));
      return text;
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      stage.act(delta);
      stage.draw();
      queryBackButtonPressed();
   }

   protected void queryBackButtonPressed() {
      if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
         game.setScreen(new StartScreen(game));
         dispose();
      }
   }
}
