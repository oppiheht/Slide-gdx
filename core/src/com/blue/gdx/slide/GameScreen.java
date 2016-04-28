package com.blue.gdx.slide;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.blue.gdx.slide.input.InputHandler;
import com.blue.gdx.slide.input.KeyboardInputHandler;
import com.blue.gdx.slide.input.TouchInputHandler;
import com.blue.gdx.slide.level.Direction;

public class GameScreen extends ScreenAdapter {

   private Game game;
   
   public static final int GRID_CELL = 32;
   private enum STATE {
      TIMED_MODE, GAME_OVER
   }
   private STATE state = STATE.TIMED_MODE;
   
   private static final int MAP_SIZE = 12;
   private static final float WORLD_WIDTH = 13 * GRID_CELL;
   private static final float WORLD_HEIGHT = 20 * GRID_CELL;
   private static final float TIMED_MODE_DURATION = 120L;
   
   private Camera camera;
   private float BOTTOM_PADDING = GRID_CELL * 3;
   private float SIDE_PADDING = (WORLD_WIDTH - (MAP_SIZE*GRID_CELL)) / 2F;

   private SpriteBatch batch;
   private BitmapFont font;
   private ShapeRenderer shapeRenderer;
   
   private GameMap map;
   private int score = 0;
   private int moves = 0;
   private Direction lastMoveDirection = null;
   private float timer = TIMED_MODE_DURATION;
   
   float inputDelay = .05f;
   float lastInputTime = 0f;
   private List<InputHandler> inputHandlers;
   
   public GameScreen(Game game) {
      this.game = game;
   }
   
   @Override
   public void show() {
      batch = new SpriteBatch();
      font = new BitmapFont();
      camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
      camera.position.set(WORLD_WIDTH/2 - SIDE_PADDING, WORLD_HEIGHT/2 - BOTTOM_PADDING, 0);
      camera.update();
      shapeRenderer = new ShapeRenderer();
      map = new GameMap(MAP_SIZE);
      inputHandlers = new ArrayList<>();
      inputHandlers.add(new TouchInputHandler());
      inputHandlers.add(new KeyboardInputHandler());
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(GameColors.BACKGROUND.r, GameColors.BACKGROUND.g, GameColors.BACKGROUND.b, GameColors.BACKGROUND.a);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      switch(state) {
      case TIMED_MODE: {
         queryInputHandlers(delta);
         drawDebug();
         drawStatusText();
         checkLevelCompleted();
         decrementTimer(delta);
         break;
      }
      case GAME_OVER:
         queryGameOverInput();
         drawGameOver();
         break;
      }

   }
   
   private void queryInputHandlers(float delta) {
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

   private void movePlayerDirection(Direction inputDirection) {
      map.movePlayer(inputDirection);
      lastMoveDirection = inputDirection;
      lastInputTime = inputDelay;      
      moves++;
   }

   private void checkLevelCompleted() {
      if (map.isSolved()) {
         map.createNewMap(MAP_SIZE);
         score++;
         lastMoveDirection = null;
         moves = 0;
      }
      
   }
   
   private void decrementTimer(float delta) {
      timer -= delta;
      if (timer < 0) {
         state = STATE.GAME_OVER;
      }
   }
   
   private void queryGameOverInput() {
      boolean resetPressed = Gdx.input.isKeyJustPressed(Input.Keys.R);
      if (resetPressed || Gdx.input.isTouched()) {
         game.setScreen(new StartScreen(game));
      }
   }
   
   private void drawDebug() {
      shapeRenderer.setProjectionMatrix(camera.projection);
      shapeRenderer.setTransformMatrix(camera.view);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
      map.drawDebug(shapeRenderer);
      shapeRenderer.end();
   }
   
   private void drawStatusText() {
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      batch.begin();
      
      drawScore();
      drawTimer();
      
      batch.end();
   }
   
   private void drawScore() {
      font.draw(batch,
            "Score: "+score+"\nMoves: "+moves+" of "+map.getSolutionLength(), 
            75, 
            WORLD_HEIGHT - 200);
      
   }
   
   private void drawTimer() {
      font.draw(batch, "Time Left:"+(int)timer, 75, WORLD_HEIGHT - 175);
   }
   
   private void drawGameOver() {
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      batch.begin();
      
      font.draw(
            batch, 
            "Game Over! You completed "+score+" levels", 
            100, 
            WORLD_HEIGHT / 2);
      
      batch.end();
   }
}
