package com.praus.chars.character.pathfinding;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
class Node implements Comparable<Node> {
	
	private final int column;
	private final int row;	
	private final int h; // heuristic value

	private int g = 0; // movement cost
	
	private Node parent = null; // path parent
    private boolean closed = false; // indicate that this node has been closed

	/**
	 * Create new A* algorithm node with empty heuristic
	 * 
	 * @param column node column
	 * @param row  node row
	 */
	public Node(int column, int row) {
		this.column = column;
		this.row = row;
		
		this.h = 0;
	}
	
	/**
	 * Create new A* algorithm node and calculate heuristic to target node
	 * 
	 * @param column node column
	 * @param row node row
	 * @param target target node
	 */
	public Node(int column, int row, Node target) {
		this.column = column;
		this.row = row;
		
		this.h = Math.abs(target.getColumn() - column) + Math.abs(target.getRow() - row);
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getG() {
		return g;
	}
	
	public int getF() {
		return this.g + this.h;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

    public boolean isClosed() {
        return closed;
    }
    
    public void closeNode() {
        this.closed = true;
    }

	@Override
	public String toString() {
		return String.format("[column=%d, row=%d, h=%d, g=%d]", column, row, h, g);
	}

	@Override
	public int compareTo(Node t) {
		return getF() - t.getF(); // ascending by F value
	}
}
