package com.praus.chars.character.pathfinding;

import com.praus.chars.map.ArrayGrid;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
class Nodes extends ArrayGrid<Node> {
    
    private final Queue<Node> openedNodes; // best node is on the top

    public Nodes(int columns, int rows) {
		super(columns, rows);		
		openedNodes = new PriorityQueue<Node>();
	}
	
	public void add(Node node) {
		set(node.getColumn(), node.getRow(), node);
        if (!node.isClosed()) {
            openedNodes.add(node);
        }
	}
	
	public Node find(int column, int row) {
		return get(column, row);
	}

	public Node pollBestNodeAndClose() {
		Node node = openedNodes.poll();
		if (node != null) {
            node.closeNode();
		}
		return node;
	}
}
