package com.blue.gdx.slide;

import com.blue.gdx.slide.level.Direction;
import com.blue.gdx.slide.level.Level;
import com.blue.gdx.slide.level.Node;
import com.blue.gdx.slide.level.SolvableLevelFactory;
import com.blue.gdx.slide.level.Solver;
import com.blue.gdx.slide.actor.Goal;
import com.blue.gdx.slide.actor.Player;
import com.blue.gdx.slide.actor.Rock;

import java.util.List;

public class GameWorld {

   private Level level;
   private Player player;
 
   private List<Rock> rockActors;
   private Goal goal;

   public GameWorld(int size, List<Rock> rocks, Player player, Goal goal) {
      this.rockActors = rocks;
      this.player = player;
      this.goal = goal;
      createNewLevel(size);
   }

   public void createNewLevel(int size) {
      level = SolvableLevelFactory.newSolvableLevel(size, size);
      int i = 0;
      for (; i < level.getWalls().size(); i++) {
         Node node = level.getWalls().get(i);
         rockActors.get(i).setPosition(node.getX(), node.getY());
      }
      for (; i < rockActors.size(); i++) {
         rockActors.get(i).hide();
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
}
