package com.blue.gdx.slide;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.blue.gdx.slide.actor.Goal;
import com.blue.gdx.slide.actor.Player;
import com.blue.gdx.slide.actor.Rock;
import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.level.Level;
import com.blue.gdx.slide.level.Node;
import com.blue.gdx.slide.level.SolvableLevelFactory;
import com.blue.gdx.slide.level.Solver;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

   public static final int LEVEL_SIZE = 12;
   public static final int LEVEL_DIFFICULTY_MIN = 12;
   public static final int LEVEL_DIFFICULTY_MAX = 14;
   public static final int WORLD_WIDTH_CELLS = LEVEL_SIZE + 1;

   private Level level;
   private Player player;
   private List<Rock> rocks;
   private Goal goal;

   public GameWorld(Group actorGroup, AssetManager assetManager) {
      int gridCellSizePixels = Gdx.graphics.getWidth() / WORLD_WIDTH_CELLS;
      int worldCenterXY = gridCellSizePixels * LEVEL_SIZE / 2;

      int rockPoolSize = LEVEL_SIZE * LEVEL_SIZE / 2;
      rocks = new ArrayList<>(rockPoolSize);
      for (int i = 0; i < rockPoolSize; i++) {
         Rock rock = new Rock(assetManager, gridCellSizePixels, worldCenterXY, worldCenterXY);
         rocks.add(rock);
         actorGroup.addActor(rock);
      }

      player = new Player(assetManager, gridCellSizePixels);
      actorGroup.addActor(player);
      goal = new Goal(assetManager, gridCellSizePixels);
      actorGroup.addActor(goal);

      createNewLevel();
   }

   public void createNewLevel() {
      level = SolvableLevelFactory.newSolvableLevel(LEVEL_SIZE, LEVEL_DIFFICULTY_MIN, LEVEL_DIFFICULTY_MAX);
      int i = 0;
      for (; i < level.getWalls().size(); i++) {
         Node node = level.getWalls().get(i);
         rocks.get(i).setPosition(node.getX(), node.getY());
      }
      for (; i < rocks.size(); i++) {
         rocks.get(i).setVisible(false);
      }

      player.setStartPosition(level.getStartNode().getX(), level.getStartNode().getY());
      goal.setPosition(level.getEndNode().getX(), level.getEndNode().getY());
   }
   
   public boolean isSolved() {
      return goal.getWorldX() == player.getWorldX() && goal.getWorldY() == player.getWorldY();
   }
   
   public int getSolutionLength() {
      return level.getSolutionString().length();
   }
   
   public void movePlayer(Direction direction) {
      Node newPosition = Solver.slideDirection(direction, level, level.getNodeAt(player.getWorldX(), player.getWorldY()));
      player.setPosition(newPosition.getX(), newPosition.getY());
   }

   public void resetPlayer() {
      player.setPosition(level.getStartNode().getX(), level.getStartNode().getY());
   }
}
