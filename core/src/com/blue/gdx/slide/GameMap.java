package com.blue.gdx.slide;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.level.Level;
import com.blue.gdx.slide.level.Node;
import com.blue.gdx.slide.level.SolvableLevelFactory;
import com.blue.gdx.slide.level.Solver;

public class GameMap {
   private Level level;
   private List<Rectangle> rocks = new ArrayList<Rectangle>();
   private Rectangle endRect;
   private Player player;
 
   private Texture rockTexture;
   private Texture playerTexture;
   private Texture goalTexture;
   private int gridCellSizePixels;

   public GameMap(int size, int gridCellSizePixels, Texture rockTexture, Texture playerTexture, Texture goalTexture) {
      this.rockTexture = rockTexture;
      this.playerTexture = playerTexture;
      this.goalTexture = goalTexture;
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
      player = new Player(playerTexture);
      player.setWorldX(level.getStartNode().getX());
      player.setWorldY(level.getStartNode().getY());

      //System.out.println(level);
   }
   
   public boolean isSolved() {
      return endRect.x == player.getX() && endRect.y == player.getY();
   }
   
   public int getSolutionLength() {
      return level.getSolutionString().length();
   }
   
   public void movePlayer(Direction direction) {
      Node newPosition = Solver.slideDirection(direction, level, level.getNodeAt((int)player.getWorldX(), (int)player.getWorldY()));
      player.setWorldX(newPosition.getX());
      player.setWorldY(newPosition.getY());
      player.setPosition(newPosition.getX() * gridCellSizePixels, newPosition.getY() * gridCellSizePixels);
   }

   public void draw(SpriteBatch batch, float delta) {
      //rocks
      for (Rectangle r : rocks) {
         batch.draw(rockTexture, r.x * gridCellSizePixels, r.y * gridCellSizePixels);
      }
      
      //end
      batch.draw(goalTexture, endRect.x * gridCellSizePixels, endRect.y * gridCellSizePixels);
      
      //player
      player.draw(batch, delta);
   }
   
   private Rectangle nodeToRectangle(Node node) {
      return new Rectangle(node.getX(), node.getY(), gridCellSizePixels, gridCellSizePixels);
   }
}
