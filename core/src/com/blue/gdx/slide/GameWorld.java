package com.blue.gdx.slide;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.level.Level;
import com.blue.gdx.slide.level.Node;
import com.blue.gdx.slide.level.SolvableLevelFactory;
import com.blue.gdx.slide.level.Solver;
import com.blue.gdx.slide.sprite.Player;
import com.blue.gdx.slide.sprite.Rock;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
   private Level level;
   private List<Rectangle> rockRectangles = new ArrayList<Rectangle>();
   private Rectangle endRect;
   private Player player;
 
   private List<Rock> rockSprites;
   private Sprite goal;
   private int gridCellSizePixels;

   public GameWorld(int size, int gridCellSizePixels, List<Rock> rocks, Player player, Sprite goal) {
      this.rockSprites = rocks;
      this.player = player;
      this.goal = goal;
      this.gridCellSizePixels = gridCellSizePixels;
      createNewLevel(size);
   }

   public void createNewLevel(int size) {
      level = SolvableLevelFactory.newSolvableLevel(size, size);
      rockRectangles.clear();
      for (Node node : level.getWalls()) {
         rockRectangles.add(nodeToRectangle(node));
      }
      endRect = nodeToRectangle(level.getEndNode());
      
      player.setPosition(level.getStartNode().getX(), level.getStartNode().getY());
      goal.setPosition(level.getEndNode().getX() * gridCellSizePixels, level.getEndNode().getY() * gridCellSizePixels);
   }
   
   public boolean isSolved() {
      return endRect.x == player.getWorldX() && endRect.y == player.getWorldY();
   }
   
   public int getSolutionLength() {
      return level.getSolutionString().length();
   }
   
   public void movePlayer(Direction direction) {
      Node newPosition = Solver.slideDirection(direction, level, level.getNodeAt((int)player.getWorldX(), (int)player.getWorldY()));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }

   public void draw(SpriteBatch batch, float delta) {
      for (int i = 0; i < rockRectangles.size(); i++) {
         Rectangle r = rockRectangles.get(i);
         Rock rockSprite = rockSprites.get(i % rockSprites.size());
         rockSprite.setPosition(r.x * gridCellSizePixels, r.y * gridCellSizePixels);
         rockSprite.draw(batch);
      }
      goal.draw(batch);
      player.draw(batch, delta);
   }
   
   private Rectangle nodeToRectangle(Node node) {
      return new Rectangle(node.getX(), node.getY(), gridCellSizePixels, gridCellSizePixels);
   }
}
