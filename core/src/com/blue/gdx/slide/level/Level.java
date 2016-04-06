package com.blue.gdx.slide.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {

   public static final int WALLFACTOR = 10; // will generate 1/WALLFACTOR walls
   private Node grid[][];
   public int rows;
   public int cols;
   private String solution = "";

   public Level(int rows, int cols) {
      this.rows = rows;
      this.cols = cols;
      grid = new Node[rows][cols];
      replaceWithNewGameGrid();
   }

   public void replaceWithNewGameGrid() {
      Random rand = new Random();
      for (int i = 0; i < rows; i++) {
         for (int j = 0; j < cols; j++) {
            if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
               grid[i][j] = new Node(i, j, Node.WALL);
            } else {
               grid[i][j] = new Node(i, j, rand.nextInt(WALLFACTOR) == 0 ? Node.WALL : Node.EMPTY);
            }
         }
      }

      grid[rand.nextInt(rows - 2) + 1][0].setType(Node.START);
      grid[rand.nextInt(rows - 2) + 1][rows - 1].setType(Node.END); 
   }

   public Node getStartNode() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j].getType() == Node.START) {
               return grid[i][j];
            }
         }
      }
      throw new RuntimeException("Start Node Not Found\n" + this.toString());
   }

   public Node getEndNode() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j].getType() == Node.END) {
               return grid[i][j];
            }
         }
      }
      throw new RuntimeException("End Node Not Found\n" + this.toString());
   }

   /**
    * @override
    */
   public String toString() {
      String s = "";

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            switch (grid[j][cols-i-1].getType()) {
            case Node.EMPTY:
               s += " ";
               break;
            case Node.WALL:
               s += "X";
               break;
            case Node.START:
               s += "S";
               break;
            case Node.END:
               s += "E";
               break;
            }
         }
         s += "\n";
      }

      return s;
   }

   public int getWidth() {
      return grid.length;
   }

   public int getHeight() {
      return grid[1].length;
   }

   public Node getNodeAt(int x, int y) {
      Node node = null;
      if (x >= rows || y >= cols || x < 0 || y < 0) {
         return node;
      }
      return grid[x][y];
   }

   public List<Node> getWalls() {
      ArrayList<Node> nodes = new ArrayList<Node>();
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (getNodeAt(i, j).getType() == Node.WALL) {
               nodes.add(getNodeAt(i, j));
            }
         }
      }
      return nodes;
   }

   public void setSolutionString(String solution) {
      this.solution = solution;
   }
   
   public String getSolutionString() {
      return solution;
   }
}
