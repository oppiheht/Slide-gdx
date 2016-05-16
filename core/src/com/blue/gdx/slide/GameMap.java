package com.blue.gdx.slide;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.level.Level;
import com.blue.gdx.slide.level.Node;
import com.blue.gdx.slide.level.SolvableLevelFactory;
import com.blue.gdx.slide.level.Solver;

public class GameMap {
   public Level level;
   List<Rectangle> rocks = new ArrayList<Rectangle>();
   Rectangle startRect;
   Rectangle endRect;
   Player player;
   
   Texture rockTexture;
   Texture playerTexture;
   Texture goalTexture;

   public GameMap(int size, Texture rockTexture, Texture playerTexture, Texture goalTexture) {
      this.rockTexture = rockTexture;
      this.playerTexture = playerTexture;
      this.goalTexture = goalTexture;
      createNewMap(size);
   }

   public void createNewMap(int size) {
      level = SolvableLevelFactory.newSolvableLevel(size, size);
      rocks.clear();
      for (Node node : level.getWalls()) {
         rocks.add(nodeToRectangle(node));
      }
      startRect = nodeToRectangle(level.getStartNode());
      endRect = nodeToRectangle(level.getEndNode());
      player = new Player(startRect, playerTexture);

      System.out.println(level);
   }
   
   public boolean isSolved() {
      return endRect.x == player.x && endRect.y == player.y;
   }
   
   public int getSolutionLength() {
      return level.getSolutionString().length();
   }
   
   public void movePlayer(Direction direction) {
      Node newPosition = Solver.slideDirection(direction, level, level.getNodeAt((int)player.x, (int)player.y));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }

   public void draw(SpriteBatch batch) {
      //rocks
      for (Rectangle r : rocks) {
         batch.draw(rockTexture, r.x * TimedGameScreen.GRID_CELL, r.y * TimedGameScreen.GRID_CELL);
      }
      
      //start
      //no start icon yet
      
      //end
      batch.draw(goalTexture, endRect.x * TimedGameScreen.GRID_CELL, endRect.y * TimedGameScreen.GRID_CELL);
      
      //player
      player.draw(batch);
   }
   
   public void drawDebug(ShapeRenderer renderer) {
      //rocks
      renderer.setColor(GameColors.ROCK);
      for (Rectangle r : rocks) {
         renderer.rect(r.x * TimedGameScreen.GRID_CELL, r.y * TimedGameScreen.GRID_CELL, r.width, r.height);
      }
      //start
      renderer.setColor(GameColors.START);
      renderer.rect(startRect.x * TimedGameScreen.GRID_CELL, startRect.y * TimedGameScreen.GRID_CELL, startRect.width, startRect.height);
      //end
      renderer.setColor(GameColors.GOAL);
      renderer.rect(endRect.x * TimedGameScreen.GRID_CELL, endRect.y * TimedGameScreen.GRID_CELL, endRect.width, endRect.height);
      //player
      player.drawDebug(renderer);
   }

   private Rectangle nodeToRectangle(Node node) {
      return new Rectangle(node.getX(), 
            node.getY(),
            TimedGameScreen.GRID_CELL, 
            TimedGameScreen.GRID_CELL);
   }
}
