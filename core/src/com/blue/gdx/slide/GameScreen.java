package com.blue.gdx.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {

   public static final int GRID_CELL = 32;
   private enum STATE {
      PLAYING, GAME_OVER
   }
   private STATE state = STATE.PLAYING;
   
   private static final int MAP_SIZE = 12;
   private static final float WORLD_WIDTH = 13 * GRID_CELL;
   private static final float WORLD_HEIGHT = 20 * GRID_CELL;
   
   private Viewport viewport;
   private Camera camera;
   
   private SpriteBatch batch;
   private BitmapFont font;
   private ShapeRenderer shapeRenderer;
   
   private GameMap map;
   private int score = 0;
   
   float inputDelay = .05f;
   float lastInputTime = 0f;
   
   @Override
   public void show() {
      batch = new SpriteBatch();
      font = new BitmapFont();
      camera = new OrthographicCamera(WORLD_WIDTH, WORLD_HEIGHT);
      float sidePadding = (WORLD_WIDTH - (MAP_SIZE*GRID_CELL)) / 2F;
      camera.position.set(WORLD_WIDTH/2 - sidePadding, WORLD_HEIGHT/2, 0);
      camera.update();
      viewport = new ScreenViewport(camera);
      shapeRenderer = new ShapeRenderer();
      map = new GameMap(MAP_SIZE);
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(Color.BLUE.r, Color.BLUE.g, Color.BLUE.b, Color.BLUE.a);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      switch(state) {
      case PLAYING: {
         queryInput(delta);
         drawDebug();
         drawScore();
         checkLevelCompleted();
      }
      break;
      case GAME_OVER:
         map.createNewMap(MAP_SIZE);
         score++;
         state = STATE.PLAYING;
         break;
      }

   }

   private void checkLevelCompleted() {
      if (map.solved()) {
         state = STATE.GAME_OVER;
      }
      
   }
   
   private void queryInput(float delta) {
      lastInputTime -= delta;
      if (lastInputTime > 0) {
         return;
      }
      lastInputTime = inputDelay;
      
      boolean leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
      boolean rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);
      boolean upPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
      boolean downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
      boolean resetPressed = Gdx.input.isKeyPressed(Input.Keys.R);
      boolean quitPressed = Gdx.input.isKeyPressed(Input.Keys.Q);

      if (leftPressed) {
         map.movePlayerWest();
      }
      if (rightPressed) {
         map.movePlayerEast();
      }
      if (upPressed) {
         map.movePlayerNorth();
      }
      if (downPressed) {
         map.movePlayerSouth();
      }
      if (resetPressed) {
         map.createNewMap(MAP_SIZE);
      }
      if (quitPressed) {
         state = STATE.GAME_OVER;
      }
   }
   
   private void drawDebug() {
      shapeRenderer.setProjectionMatrix(camera.projection);
      shapeRenderer.setTransformMatrix(camera.view);
      shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
      map.drawDebug(shapeRenderer);
      shapeRenderer.end();
   }
   
   private void drawScore() {
      batch.setProjectionMatrix(camera.projection);
      batch.setTransformMatrix(camera.view);
      batch.begin();
      font.draw(batch, "Score: "+score, 75, WORLD_HEIGHT - 200);
      batch.end();
   }
}
