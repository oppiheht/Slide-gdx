package com.blue.gdx.slide.level;

import java.util.LinkedList;
import java.util.HashSet;

public class Solver {
   public static final int NORTH = 0;
   public static final int EAST = 1;
   public static final int SOUTH = 2;
   public static final int WEST = 3;

   /**
    * Attempts to solve the given game level. A level is solveable if some
    * combination of moves going North, East, South, or West move the character
    * in a sliding motion from the start to the end. Will return -1 if the Level
    * is unable to be solved.
    * 
    * The solver assumes that the border of the Level consists only of walls,
    * start, and end nodes.
    * 
    * @param level The game level to attempt to solve.
    * @return The number of moves to solve the given level, or -1 if the level
    *         is unable to be solved.
    */
   public static String solveLevel(Level level) {
      HashSet<Node> visitedNodes = new HashSet<Node>();
      NodePath currentNodePath;
      Node currentNode;

      LinkedList<NodePath> fringe = new LinkedList<NodePath>();
      fringe.add(new NodePath(level.getStartNode(), ""));

      while (!fringe.isEmpty()) {
         currentNodePath = fringe.pop();
         currentNode = currentNodePath.node;
         if (currentNode.getType() == Node.END) {
            return currentNodePath.path;
         }
         if (!visitedNodes.contains(currentNode)) {
            visitedNodes.add(currentNode);
            fringe.add(new NodePath(slideDirection(NORTH, level, currentNode), currentNodePath.path + "N"));
            fringe.add(new NodePath(slideDirection(EAST, level, currentNode), currentNodePath.path + "E"));
            fringe.add(new NodePath(slideDirection(SOUTH, level, currentNode), currentNodePath.path + "S"));
            fringe.add(new NodePath(slideDirection(WEST, level, currentNode), currentNodePath.path + "W"));
         }

      }

      return null;
   }

   /**
    * Returns the node that is in the given direction in a sliding motion from
    * the given start node on the given level.
    * 
    * @param direction The direction to slide.
    * @param level The level to move on.
    * @param start The node to start the move from.
    * 
    * @return The node the character will end at.
    */
   public static Node slideDirection(int direction, Level level, Node start) {
      Node currentNode = start;
      Node nextNodeInDirection = getNodeInDirection(direction, level, currentNode);

      if (nextNodeInDirection == currentNode) {
         return currentNode;
      }

      while (nextNodeInDirection != null && nextNodeInDirection.getType() != Node.WALL) {
         if (nextNodeInDirection.getType() == Node.END) {
            return nextNodeInDirection;
         }
         currentNode = nextNodeInDirection;
         nextNodeInDirection = getNodeInDirection(direction, level, currentNode);
      }

      return currentNode;
   }

   private static Node getNodeInDirection(int direction, Level level, Node start) {
      Node nextNode = null;
      if (direction == NORTH) {
         nextNode = level.getNodeAt(start.getX(), start.getY() + 1);
      } else if (direction == EAST) {
         nextNode = level.getNodeAt(start.getX() + 1, start.getY());
      } else if (direction == SOUTH) {
         nextNode = level.getNodeAt(start.getX(), start.getY() - 1);
      } else if (direction == WEST) {
         nextNode = level.getNodeAt(start.getX() - 1, start.getY());
      }

      return nextNode;
   }

   private static class NodePath {
      public Node node;
      public String path;

      public NodePath(Node node, String path) {
         this.node = node;
         this.path = path;
      }
   }
}
