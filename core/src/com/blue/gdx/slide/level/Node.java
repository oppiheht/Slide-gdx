package com.blue.gdx.slide.level;

public class Node {
	public static final int EMPTY = 0;
	public static final int WALL = 1;
	public static final int START = 2;
	public static final int END = 3;
	
	private int type;
	private boolean visited;
	private int x;
	private int y;
	
	public Node(int x, int y, int type) {
		this.type = type;
		this.x = x;
		this.y = y;
		visited = false;
	}
	
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public void visit() {
		visited = true;
	}
	
	public boolean visited() {
		return visited;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
