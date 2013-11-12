package com.praus.chars.character.pathfinding;

import com.googlecode.lanterna.terminal.Terminal;
import com.praus.chars.character.Placeable;
import com.praus.chars.map.Floor;
import com.praus.chars.map.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Path A* algorithm implementation
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
class AStarAlgorithm {
	
	private static final Logger logger = LoggerFactory.getLogger(AStarAlgorithm.class);
	
	private final Nodes nodes;
	
	private final Floor floor;
	
	private final Node startNode;
	private final Node targetNode;

	public AStarAlgorithm(Placeable start, Placeable target) throws UnreachableException {
		if (start.getFloor() != target.getFloor()) {
            throw new UnreachableException();
		}
		this.floor = start.getFloor(); // map to move on
		
		this.startNode = new Node(start.getLocation().getColumn(), start.getLocation().getRow());
		this.targetNode = new Node(target.getLocation().getColumn(), target.getLocation().getRow());
		
		this.nodes = new Nodes(floor.getColumns(), floor.getRows());
	}
	
	public Waypoints compute() throws UnreachableException {
        floor.getTiles().resetColors();
		if (startNode.getColumn() == targetNode.getColumn() && startNode.getRow() == targetNode.getRow()) {
			return new Waypoints(); // nothing to move to
		}
		
        // find path to target in revers order
		Node finish = findPath();
		if (finish != null) {
			Node step = finish;
            Waypoints path = new Waypoints();

			while (step != null) {
                path.prependWaypoint(step);								
				floor.getTiles().setColor(step.getColumn(), step.getRow(), Terminal.Color.BLUE);
                
                step = step.getParent();
			}

			logger.info("Path computed {}", path);
            return path;
		}
        throw new UnreachableException(); // path not found
	}
	
	private Node findPath() throws UnreachableException {
		// start
		nodes.add(startNode);
		Node currentNode, finishNode;

		// run iteration until finish is found or not reachable
		int i = 0;
		while ((currentNode = nodes.pollBestNodeAndClose()) != null) {	
			if (currentNode == null) {
                throw new UnreachableException();
			}
			i ++;

			finishNode = nextStep(currentNode);			
			if (finishNode != null) {
				logger.debug("Path from node {} to {} found in {} iterations", startNode, finishNode, i);
				return finishNode;
			}			
		}
        throw new UnreachableException();
	}
	
	private Node nextStep(Node currentNode) {
		floor.getTiles().setColor(currentNode.getColumn(), currentNode.getRow(), Terminal.Color.RED);
		
		// search nodes around current node
		int column, row;
		for (int columnIt = -1; columnIt <= 1; columnIt ++) {
			for (int rowIt = -1; rowIt <= 1; rowIt ++) {
				if (columnIt == 0 && rowIt == 0) {
					continue;  // do not serch no current position
				}
				column = currentNode.getColumn() + columnIt;
				row = currentNode.getRow()+ rowIt;
                
                // test if this node is final or not
                if (column == targetNode.getColumn() && row == targetNode.getRow()) {
                    Node node = new Node(column, row, targetNode);
                    node.setParent(currentNode);
                    return node;
                }
				
                Node node = nodes.find(column, row);
                if (node != null && node.isClosed()) {
                    continue;  // already an closed node, do not change it
                }
				
				// open reachable node and calculate parameters
                Location location = new Location(column, row);
				if (!floor.isBlocked(location)) {					
					int movementCost = movementCost(currentNode, location);
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
						node.setG(movementCost);
						nodes.add(node);
                        
                        floor.getTiles().setColor(node.getColumn(), node.getRow(), Terminal.Color.GREEN);
					}					
				}
			}
		}
		return null; // path not found in this iteration
	}
	
	private int movementCost(Node from, Location to) {
        int columnDiff = from.getColumn() - to.getColumn();
        int rowDiff = from.getColumn() - to.getColumn();
        
        if ((columnDiff == 1 || columnDiff == -1) && (rowDiff == 1 || rowDiff == -1)) {
            return 14 + from.getG(); // diagonal movement
        }
		return 10 + from.getG(); // straight movement
	}
}
