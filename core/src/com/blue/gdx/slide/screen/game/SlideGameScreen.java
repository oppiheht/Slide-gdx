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
import com.blue.gdx.slide.GameMap;
import com.blue.gdx.slide.SlideGame;
import com.blue.gdx.slide.input.InputHandler;
import com.blue.gdx.slide.input.KeyboardInputHandler;
import com.blue.gdx.slide.input.TouchInputHandler;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.screen.StartScreen;
import com.blue.gdx.slide.ui.SlideAssetManager;

public abstract class SlideGameScreen extends ScreenAdapter {
   
   protected SlideGame game;
   
   public static final int GRID_CELL = 32;
   
   public enum STATE {
      GAME_ACTIVE, GAME_OVER
   }
   
   protected STATE state = STATE.GAME_ACTIVE;
   
   public static final int MAP_SIZE = 12;
   public static final float WORLD_WIDTH = 13 * GRID_CELL;
   public static final float WORLD_HEIGHT = 20 * GRID_CELL;
   
   protected static final int STATUS_FONT_X = 18;
   
   protected Camera camera;
   protected float BOTTOM_PADDING = GRID_CELL * 3;
   protected float SIDE_PADDING = (WORLD_WIDTH - (MAP_SIZE*GRID_CELL)) / 2F;

   protected SpriteBatch batch;
   protected BitmapFont font;

   protected Texture rockTexture;
   protected Texture playerTexture;
   protected Texture goalTexture;
   protected Texture background;
   
   protected GameMap map;

   protected Direction lastMoveDirection = null;
   
   protected float inputDelay = .05f;
   protected float lastInputTime = 0f;
   protected List<InputHandler> inputHandlers;
   
   public SlideGameScreen(SlideGame game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      batch = new SpriteBatch();
      font = game.getAssetManager().get(SlideAssetManager.FONTFILE, BitmapFont.class);
      
      camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
      camera.position.set(WORLD_WIDTH/2 - SIDE_PADDING, WORLD_HEIGHT/2 - BOTTOM_PADDING, 0);
      camera.update();
      
      inputHandlers = new ArrayList<InputHandler>();
      inputHandlers.add(new TouchInputHandler());
      inputHandlers.add(new KeyboardInputHandler());
      
      background = game.getAssetManager().get(SlideAssetManager.BACKGROUND, Texture.class);
      rockTexture = game.getAssetManager().get(SlideAssetManager.ROCK, Texture.class);
      playerTexture = game.getAssetManager().get(SlideAssetManager.PLAYER, Texture.class);
      goalTexture = game.getAssetManager().get(SlideAssetManager.GOAL, Texture.class);
      
      map = new GameMap(MAP_SIZE, rockTexture, playerTexture, goalTexture);
   }
   
   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0, 0, 0, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      switch(state) {
      case GAME_ACTIVE: {
         queryInputHandlers(delta);
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
   
   protected abstract void renderUpdate(float delta);
   
   protected abstract void onPlayerMove();
   
   protected abstract void onLevelCompleted();
   
   protected abstract void drawStatusText();
   
   protected abstract void drawGameOver();
   
   protected void queryInputHandlers(float delta) {
      lastInputTime -= delta;
      if (lastInputTime > 0) {
         return;
      }
      
      Direction inputDirection = null;
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
      map.movePlayer(inputDirection);
      lastMoveDirection = inputDirection;
      lastInputTime = inputDelay;
      onPlayerMove();
   }

   protected String formatTime(float seconds) {
      return (int)seconds / 60 + ":" + (int)seconds % 60;
   }
   
   protected void checkLevelCompleted() {
      if (map.isSolved()) {
         onLevelCompleted();
         map.createNewMap(MAP_SIZE);
         lastMoveDirection = null;
      }
   }
   
   protected void queryGameOverInput() {
      boolean resetPressed = Gdx.input.isKeyJustPressed(Input.Keys.R);
      if (resetPressed || Gdx.input.isTouched()) {
         game.setScreen(new StartScreen(game));
      }
   }
   
   protected void draw(float delta) {
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      batch.begin();
      batch.draw(background, -SIDE_PADDING, -BOTTOM_PADDING);
      map.draw(batch, delta);
      drawStatusText();
      batch.end();
   }

}
