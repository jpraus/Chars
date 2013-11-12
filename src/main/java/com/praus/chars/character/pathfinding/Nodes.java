package com.praus.chars.character.pathfinding;

import com.praus.chars.map.ArrayGrid;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
class Nodes extends ArrayGrid<Node> {

	public Nodes(int columns, int rows) {
		super(columns, rows);
	}
	
	public void add(Node node) {
		set(node.getColumn(), node.getRow(), node);		
	}
	
	public Node find(int column, int row) {
		return get(column, row);
	}
}
