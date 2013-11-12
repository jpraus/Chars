package com.praus.chars.character.pathfinding;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
class NearestNodes extends Nodes {
	
	private final Queue<Node> nearestNodes; // nearest node is on the top

	public NearestNodes(int columns, int rows) {
		super(columns, rows);		
		nearestNodes = new PriorityQueue<Node>();
	}

	@Override
	public void add(Node node) {		
		super.add(node);
		nearestNodes.add(node);
	}

	public Node poll() {
		Node node = nearestNodes.poll();
		if (node != null) {
			super.remove(node.getColumn(), node.getRow());
		}
		return node;
	}
}
