package com.praus.chars.character.pathfinding;

import com.googlecode.lanterna.terminal.Terminal;
import com.praus.chars.character.Placeable;
import com.praus.chars.map.Floor;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
class Pathfinding {
	
	private static final Logger logger = LoggerFactory.getLogger(Pathfinding.class);
	
	private final Nodes nodes;
	
	private final Floor floor;
	
	private final Node startNode;
	private final Node targetNode;

	public Pathfinding(Placeable start, Placeable target) {
		if (start.getFloor() != target.getFloor()) {
			// throw some error
		}
		this.floor = start.getFloor(); // map to move on
		
		this.startNode = new Node(start.getPosition().getColumn(), start.getPosition().getRow());
		this.targetNode = new Node(target.getPosition().getColumn(), target.getPosition().getRow());
		
		this.nodes = new Nodes(floor.getColumns(), floor.getRows());
	}
	
	public void compute() {
		if (startNode.getColumn() == targetNode.getColumn() && startNode.getRow() == targetNode.getRow()) {
			// TODO: you are standing on me
		}
		
		Node finish = findPath();
		if (finish != null) {
			List<Node> path = new ArrayList<Node>();
			Node step = finish;
			
			path.add(step);			
			
			while (step.getParent() != null) {
				step = step.getParent();
				path.add(step);
				
				floor.getTiles().setColor(step.getColumn(), step.getRow(), Terminal.Color.WHITE);
			}
			logger.warn("Path length is {}", path.size());
		}
	}
	
	private Node findPath() {
		// start
		nodes.add(startNode);
		Node currentNode, finishNode;

		// run iteration until finish is found or not reachable
		int i = 0;
		while ((currentNode = nodes.pollBestNodeAndClose()) != null) {	
			if (currentNode == null) {
				break; // not reachable, TODO exception
			}
			i ++;

			finishNode = nextStep(currentNode);			
			if (finishNode != null) {
				logger.debug("Path from node {} to {} found in {} iterations", startNode, finishNode, i);
				return finishNode;
			}			
		}
		return null; // no reachable
	}
	
	private Node nextStep(Node currentNode) {
		logger.debug("Pathfinding iteration");
		
		// search nodes around current node
		int column, row;
		for (int columnIt = -1; columnIt <= 1; columnIt ++) {
			for (int rowIt = -1; rowIt <= 1; rowIt ++) {
				if (columnIt == 0 && rowIt == 0) {
					continue;  // do not serch no current position
				}
				column = currentNode.getColumn() + columnIt;
				row = currentNode.getRow()+ rowIt;
				
                Node node = nodes.find(column, row);
                if (node != null && node.isClosed()) {
                    continue;  // already an closed node, do not change it
                }
				
				// open reachable node and calculate parameters
				if (!floor.isBlocked(column, row)) {					
					int movementCost = movementCost(currentNode, column, row);
					if (node != null) {
						// node on that position is already opened, do not open it again
						if (node.getG() > movementCost) {
							node.setParent(currentNode); // better path to opened node found, use it
							node.setG(movementCost);
						}
					}
					else {
						// open new node
						node = new Node(column, row, targetNode);
						node.setParent(currentNode);
						node.setG(movementCost(node));
						nodes.add(node);
					}
					if (node.getColumn() == targetNode.getColumn() && node.getRow() == targetNode.getRow()) {						
						return node; // path found, return finish node
					}
				}
			}
		}
		return null; // path not found in this iteration
	}
	
	private int movementCost(Node node) {
		Node from = node.getParent();
		return movementCost(from, node.getColumn(), node.getRow());
	}
	
	private int movementCost(Node from, int toColumn, int toRow) {
		return 10 + from.getG(); // TODO: dynamic movement cost according to movement type (diagonal/direct)
	}
}
