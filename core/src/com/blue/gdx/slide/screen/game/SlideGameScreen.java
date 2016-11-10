package com.blue.gdx.slide.screen.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.blue.gdx.slide.GameWorld;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.actor.Background;
import com.blue.gdx.slide.actor.Goal;
import com.blue.gdx.slide.actor.Player;
import com.blue.gdx.slide.actor.Rock;
import com.blue.gdx.slide.actor.StatusText;
import com.blue.gdx.slide.input.InputHandler;
import com.blue.gdx.slide.input.KeyboardInputHandler;
import com.blue.gdx.slide.input.TouchInputHandler;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.screen.GameOverScreen;
import com.blue.gdx.slide.util.ButtonFactory;
import com.blue.gdx.slide.util.SlideAssetManager;

import java.util.ArrayList;
import java.util.List;

public abstract class SlideGameScreen extends ScreenAdapter {
   
   protected SlideGame game;
   
   public static final int LEVEL_SIZE = 12;
   public static final int WORLD_WIDTH_CELLS = LEVEL_SIZE + 1;
   
   protected float SIDE_PADDING_CELLS = 0.5F;
   protected float BOTTOM_PADDING_CELLS = 3;
   
   protected static final int STATUS_FONT_X = 18;

   public int gridCellSizePixels = 0;

   private Stage stage;

   protected Player player;
   protected Goal goal;
   protected List<Rock> rocks;
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
      gridCellSizePixels = Gdx.graphics.getWidth() / WORLD_WIDTH_CELLS;
      stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
      Gdx.input.setInputProcessor(stage);

      statusText = new StatusText(game.getAssetManager());
      statusText.setPosition(STATUS_FONT_X, Gdx.graphics.getHeight() * 0.7f);

      inputHandlers = new ArrayList<>();
      inputHandlers.add(new TouchInputHandler());
      inputHandlers.add(new KeyboardInputHandler());

      background = new Background(game.getAssetManager());
      player = new Player(game.getAssetManager(), gridCellSizePixels);
      goal = new Goal(game.getAssetManager(), gridCellSizePixels);

      stage.addActor(background);

      gameArea = new Group();
      gameArea.addActor(statusText);

      int rockPoolSize = LEVEL_SIZE * LEVEL_SIZE / 2;
      rocks = new ArrayList<>(rockPoolSize);
      for (int i = 0; i < rockPoolSize; i++) {
         Rock rock = new Rock(game.getAssetManager(), gridCellSizePixels);
         rocks.add(rock);
         gameArea.addActor(rock);
      }

      gameArea.addActor(player);
      gameArea.addActor(goal);
      gameArea.setPosition(gridCellSizePixels * SIDE_PADDING_CELLS, gridCellSizePixels * BOTTOM_PADDING_CELLS);
      stage.addActor(gameArea);
      ImageButton backButton = createBackButton();
      stage.addActor(backButton);

      world = new GameWorld(LEVEL_SIZE, rocks, player, goal);
   }

   private ImageButton createBackButton() {
      ImageButton backButton = ButtonFactory.createBackButton(game.getAssetManager(), new ActorGestureListener() {
         @Override
         public void tap(InputEvent event, float x, float y, int count, int button) {
            super.tap(event, x, y, count, button);
            gameOver();
         }
      });
      return backButton;
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

   protected String formatTime(float seconds) {
      int minutes = (int)seconds / 60;
      int remSeconds = (int)seconds % 60;
      String zeroPadding = remSeconds < 10 ? "0" : "";
      return minutes + ":" +zeroPadding + remSeconds;
   }
   
   protected void checkLevelCompleted() {
      if (world.isSolved()) {
         onLevelCompleted();
         world.createNewLevel(LEVEL_SIZE);
         lastMoveDirection = null;
      }
   }

   @Override
   public void dispose() {
      stage.dispose();
   }
}
