package com.blue.gdx.slide;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.blue.gdx.slide.level.Level;
import com.blue.gdx.slide.level.Node;
import com.blue.gdx.slide.level.SolvableLevelFactory;
import com.blue.gdx.slide.level.Solver;

public class GameMap implements PlayerController {
   public Level level;
   List<Rectangle> rocks = new ArrayList<Rectangle>();
   Rectangle startRect;
   Rectangle endRect;
   Player player;

   public GameMap(int size) {
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
      player = new Player(startRect);

      System.out.println(level);
   }
   
   public boolean solved() {
      return endRect.x == player.x && endRect.y == player.y;
   }
   
   @Override
   public void movePlayerNorth() {
      Node newPosition = Solver.slideDirection(Solver.NORTH, level, level.getNodeAt((int)player.x, (int)player.y));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }
   
   @Override
   public void movePlayerEast() {
      Node newPosition = Solver.slideDirection(Solver.EAST, level, level.getNodeAt((int)player.x, (int)player.y));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }
   
   @Override
   public void movePlayerSouth() {
      Node newPosition = Solver.slideDirection(Solver.SOUTH, level, level.getNodeAt((int)player.x, (int)player.y));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }
   
   @Override
   public void movePlayerWest() {
      Node newPosition = Solver.slideDirection(Solver.WEST, level, level.getNodeAt((int)player.x, (int)player.y));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }

   public void drawDebug(ShapeRenderer renderer) {
      //rocks
      renderer.setColor(GameColors.ROCK);
      for (Rectangle r : rocks) {
         renderer.rect(r.x * GameScreen.GRID_CELL, r.y * GameScreen.GRID_CELL, r.width, r.height);
      }
      //start
      renderer.setColor(GameColors.START);
      renderer.rect(startRect.x * GameScreen.GRID_CELL, startRect.y * GameScreen.GRID_CELL, startRect.width, startRect.height);
      //end
      renderer.setColor(GameColors.GOAL);
      renderer.rect(endRect.x * GameScreen.GRID_CELL, endRect.y * GameScreen.GRID_CELL, endRect.width, endRect.height);
      //player
      player.drawDebug(renderer);
   }

   private Rectangle nodeToRectangle(Node node) {
      return new Rectangle(node.getX(), 
            node.getY(),
            GameScreen.GRID_CELL, 
            GameScreen.GRID_CELL);
   }
}
