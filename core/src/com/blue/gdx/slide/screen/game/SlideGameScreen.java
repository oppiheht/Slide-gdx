package com.blue.gdx.slide.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.GameWorld;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.actor.StatusText;
import com.blue.gdx.slide.input.InputHandler;
import com.blue.gdx.slide.input.KeyboardInputHandler;
import com.blue.gdx.slide.input.TouchInputHandler;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.screen.GameOverScreen;
import com.blue.gdx.slide.util.ButtonFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class SlideGameScreen extends ScreenAdapter {
   
   protected SlideGame game;

   protected float SIDE_PADDING_CELLS = 0.5F;
   protected float BOTTOM_PADDING_CELLS = 3;
   
   protected static final int STATUS_FONT_X = 18;

   private Stage stage;

   protected Background background;
   protected StatusText statusText;
   protected Group gameArea;
   
   protected GameWorld world;

   protected Direction lastMoveDirection = null;
   
   protected float inputDelay = .05f;
   protected float lastInputTime = 0f;
   protected List<InputHandler> inputHandlers;

   protected int score = 0;
   
   public SlideGameScreen(SlideGame game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      Gdx.input.setInputProcessor(stage);

      statusText = new StatusText(game.getAssetManager());
      statusText.setPosition(STATUS_FONT_X, Gdx.graphics.getHeight() * 0.7f);

      inputHandlers = new ArrayList<>();
      inputHandlers.add(new TouchInputHandler());
      inputHandlers.add(new KeyboardInputHandler());

      background = new Background(game.getAssetManager());

      stage.addActor(background);

      int gridCellSizePixels = Gdx.graphics.getWidth() / GameWorld.WORLD_WIDTH_CELLS;
      gameArea = new Group();
      gameArea.setPosition(gridCellSizePixels * SIDE_PADDING_CELLS, gridCellSizePixels * BOTTOM_PADDING_CELLS);
      stage.addActor(gameArea);

      gameArea.addActor(statusText);
      ImageButton quitButton = createQuitButton();
      stage.addActor(quitButton);

      if (showResetButton()) {
         ImageButton resetButton = createResetButton();
         stage.addActor(resetButton);
      }

      world = new GameWorld(gameArea, game.getAssetManager());
   }

   private ImageButton createQuitButton() {
      return ButtonFactory.createQuitButton(game.getAssetManager(), new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            gameOver();
         }
      });
   }

   private ImageButton createResetButton() {
      return ButtonFactory.createResetButton(game.getAssetManager(), new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            world.resetPlayer();
            lastMoveDirection = null;
            onPlayerMove();
         }
      });
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      queryInputHandlers(delta);
      queryBackKeyPressed();
      statusText.setText(getStatusText());
      stage.act(delta);
      stage.draw();
      checkLevelCompleted();
      renderUpdate(delta);
   }

   protected void queryBackKeyPressed() {
      if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
         gameOver();
      }
   }

   protected abstract void renderUpdate(float delta);
   
   protected abstract void onPlayerMove();
   
   protected abstract void onLevelCompleted();
   
   protected abstract String getStatusText();

   protected abstract void updateScoreKeeper();

   protected abstract boolean showResetButton();

   public abstract String getTutorialText();

   protected void gameOver() {
      updateScoreKeeper();
      game.setScreen(new GameOverScreen(game, getGameOverText()));
      dispose();
   }
   
   protected String getGameOverText() {
      return "Game Over!\nYou completed " + score + " level" + (score == 1 ? "" : "s");
   }

   
   protected void queryInputHandlers(float delta) {
      lastInputTime -= delta;
      if (lastInputTime > 0) {
         return;
      }
      
      Direction inputDirection;
      for (InputHandler input : inputHandlers) {
         inputDirection = input.queryInputDirection();
         if (inputDirection != null) {
            if (inputDirection != lastMoveDirection) {
               movePlayerDirection(inputDirection);
            }
         }
      }
   }

   protected void movePlayerDirection(Direction inputDirection) {
      world.movePlayer(inputDirection);
      lastMoveDirection = inputDirection;
      lastInputTime = inputDelay;
      onPlayerMove();
   }

   protected void checkLevelCompleted() {
      if (world.isSolved()) {
         onLevelCompleted();
         world.createNewLevel();
         lastMoveDirection = null;
      }
   }

   @Override
   public void dispose() {
      stage.dispose();
   }
}
