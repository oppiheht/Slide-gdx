package com.blue.gdx.slide.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.actor.CenteredText;
import com.blue.gdx.slide.screen.game.SlideGameScreen;
import com.blue.gdx.slide.util.ButtonFactory;

public class TutorialScreen extends ScreenAdapter {
   private final SlideGame game;
   private Stage stage;
   private SlideGameScreen screenToDisplay;

   public TutorialScreen(SlideGame game, SlideGameScreen screenToDisplay) {
      this.game = game;
      this.screenToDisplay = screenToDisplay;
   }

   @Override
   public void show() {
      stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      Gdx.input.setInputProcessor(stage);

      CenteredText tutorialText = createTutorialText();
      ImageButton confirmationButton = createConfirmationButton();

      stage.addActor(new Background(game.getAssetManager()));
      stage.addActor(tutorialText);
      stage.addActor(confirmationButton);
   }

   private CenteredText createTutorialText() {
      CenteredText text = new CenteredText(game.getAssetManager(), screenToDisplay.getTutorialText());
      text.setPosition(Gdx.graphics.getWidth() * 0.05f, Gdx.graphics.getHeight() * 0.65f, Align.center);
      return text;
   }

   private ImageButton createConfirmationButton() {
      ImageButton confirmationButton = ButtonFactory.createConfirmationButton(game.getAssetManager(), new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            game.setScreen(screenToDisplay);
         }
      });
      return confirmationButton;
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
         game.setScreen(new StartScreen(game));
      }
   }

   @Override
   public void dispose() {
      stage.dispose();
   }

}
