package com.blue.gdx.slide.screen.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Align;
import com.blue.gdx.slide.GameWorld;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.input.InputHandler;
import com.blue.gdx.slide.input.KeyboardInputHandler;
import com.blue.gdx.slide.input.TouchInputHandler;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.screen.StartScreen;
import com.blue.gdx.slide.sprite.Background;
import com.blue.gdx.slide.sprite.Goal;
import com.blue.gdx.slide.sprite.Player;
import com.blue.gdx.slide.sprite.Rock;
import com.blue.gdx.slide.ui.SlideAssetManager;

public abstract class SlideGameScreen extends ScreenAdapter {
   
   protected SlideGame game;
   
   public enum STATE {
      GAME_ACTIVE, GAME_OVER
   }
   
   protected STATE state = STATE.GAME_ACTIVE;
   
   public static final int LEVEL_SIZE = 12;
   public static final int WORLD_WIDTH_CELLS = LEVEL_SIZE + 1;
   
   protected float SIDE_PADDING_CELLS = 0.5F;
   protected float BOTTOM_PADDING_CELLS = 3;
   
   protected static final int STATUS_FONT_X = 18;
   
   public int gridCellSizePixels = 0;
   
   protected Camera camera;

   protected SpriteBatch batch;
   protected BitmapFont font;

   protected Player player;
   protected Goal goal;
   protected Background background;
   
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
      batch = new SpriteBatch();
      font = game.getAssetManager().get(SlideAssetManager.FONTFILE, BitmapFont.class);
      font.getData().setScale(Gdx.graphics.getDensity());
      
      gridCellSizePixels = Gdx.graphics.getWidth() / WORLD_WIDTH_CELLS;
      
      camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 , 0);
      camera.update();
      
      inputHandlers = new ArrayList<>();
      inputHandlers.add(new TouchInputHandler());
      inputHandlers.add(new KeyboardInputHandler());
      
      background = new Background(game.getAssetManager().get(SlideAssetManager.BACKGROUND, Texture.class));
      player = new Player(game.getAssetManager().get(SlideAssetManager.PLAYER, Texture.class), gridCellSizePixels);
      goal = new Goal(game.getAssetManager().get(SlideAssetManager.GOAL, Texture.class), gridCellSizePixels);

      List<Rock> rocks = new ArrayList<>(3);
      rocks.add(new Rock(game.getAssetManager().get(SlideAssetManager.ROCK1, Texture.class), gridCellSizePixels));
      rocks.add(new Rock(game.getAssetManager().get(SlideAssetManager.ROCK2, Texture.class), gridCellSizePixels));
      rocks.add(new Rock(game.getAssetManager().get(SlideAssetManager.ROCK3, Texture.class), gridCellSizePixels));

      world = new GameWorld(LEVEL_SIZE, gridCellSizePixels, rocks, player, goal);
   }
   
   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      switch(state) {
      case GAME_ACTIVE: {
         queryInputHandlers(delta);
         queryBackButtonPressed();
         draw(delta);
         checkLevelCompleted();
         renderUpdate(delta);
         break;
      }
      case GAME_OVER:
         queryGameOverInput();
         drawGameOver();
         break;
      }

   }

   protected void queryBackButtonPressed() {
      if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
         state = STATE.GAME_OVER;
      }
   }

   protected abstract void renderUpdate(float delta);
   
   protected abstract void onPlayerMove();
   
   protected abstract void onLevelCompleted();
   
   protected abstract String getStatusText();
   
   protected String getGameOverText() {
      return "Game Over!\nYou completed " + score + " level" + (score == 1 ? "" : "s");
   }
   
   protected void drawStatusText() {
      font.draw(batch,
           getStatusText(),
           STATUS_FONT_X, 
           Gdx.graphics.getHeight() * 0.7f);
   }
   
   protected void drawGameOver() {
         batch.setProjectionMatrix(camera.projection);
         batch.setTransformMatrix(camera.view);
         batch.begin();
         
         font.draw(
               batch, 
               getGameOverText(), 
               Gdx.graphics.getWidth() * 0.05f,
               Gdx.graphics.getHeight() / 2,
               Gdx.graphics.getWidth() * 0.9f,
               Align.center,
               true);
         
         batch.end();
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
   
   protected void queryGameOverInput() {
      boolean resetPressed = Gdx.input.isKeyJustPressed(Input.Keys.R);
      if (resetPressed || Gdx.input.justTouched()) {
         game.setScreen(new StartScreen(game));
         dispose();
      }
   }
   
   protected void draw(float delta) {
      Matrix4 worldPadding = camera.view.cpy();
      worldPadding.translate(gridCellSizePixels * SIDE_PADDING_CELLS, gridCellSizePixels * BOTTOM_PADDING_CELLS, 0);
      
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      
      batch.begin();
      background.draw(batch);
      
      batch.setTransformMatrix(worldPadding);
      world.draw(batch, delta);
      drawStatusText();
      
      batch.end();
   }

}
