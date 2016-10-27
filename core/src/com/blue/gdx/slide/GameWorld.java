package com.blue.gdx.slide;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.level.Level;
import com.blue.gdx.slide.level.Node;
import com.blue.gdx.slide.level.SolvableLevelFactory;
import com.blue.gdx.slide.level.Solver;
import com.blue.gdx.slide.sprite.Player;

public class GameWorld {
   private Level level;
   private List<Rectangle> rocks = new ArrayList<Rectangle>();
   private Rectangle endRect;
   private Player player;
 
   private Sprite rock;
   private Sprite goal;
   private int gridCellSizePixels;

   public GameWorld(int size, int gridCellSizePixels, Sprite rock, Player player, Sprite goal) {
      this.rock = rock;
      this.player = player;
      this.goal = goal;
      this.gridCellSizePixels = gridCellSizePixels;
      createNewMap(size);
   }

   public void createNewMap(int size) {
      level = SolvableLevelFactory.newSolvableLevel(size, size);
      rocks.clear();
      for (Node node : level.getWalls()) {
         rocks.add(nodeToRectangle(node));
      }
      endRect = nodeToRectangle(level.getEndNode());
      
      player.setPosition(level.getStartNode().getX(), level.getStartNode().getY());
      goal.setPosition(level.getEndNode().getX() * gridCellSizePixels, level.getEndNode().getY() * gridCellSizePixels);
   }
   
   public boolean isSolved() {
      return endRect.x == player.getX() && endRect.y == player.getY();
   }
   
   public int getSolutionLength() {
      return level.getSolutionString().length();
   }
   
   public void movePlayer(Direction direction) {
      Node newPosition = Solver.slideDirection(direction, level, level.getNodeAt((int)player.getWorldX(), (int)player.getWorldY()));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }

   public void draw(SpriteBatch batch, float delta) {
      for (Rectangle r : rocks) {
         rock.setPosition(r.x * gridCellSizePixels, r.y * gridCellSizePixels);
         rock.draw(batch);
      }
      goal.draw(batch);
      player.draw(batch, delta);
   }
   
   private Rectangle nodeToRectangle(Node node) {
      return new Rectangle(node.getX(), node.getY(), gridCellSizePixels, gridCellSizePixels);
   }
}
