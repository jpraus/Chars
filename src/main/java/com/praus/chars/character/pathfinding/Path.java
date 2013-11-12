package com.praus.chars.character.pathfinding;

import com.googlecode.lanterna.terminal.TerminalPosition;
import com.praus.chars.character.Placeable;

/**
 * Path A* algorithm implementation
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Path {
	
	private final Placeable start;
	private final Placeable target;
	
	private TerminalPosition lastTargetPosition = null;

	/**
	 * Creates pathfindinh object to calculate path from source to target.
	 * Can be used to tract moving targets
	 * 
	 * @param start source object
	 * @param target target object
	 */
	public Path(Placeable start, Placeable target) {
		this.start = start;
		this.target = target;
	}
	
	public TerminalPosition nextMove() {
		findPath();
		return null;
	}
	
	/**
	 * Calculate path between source to target
	 */
	private void findPath() {
		if (start.getFloor() != target.getFloor()) {
			return; // unable to reach objects between floors
		}
		if (lastTargetPosition != null && lastTargetPosition.getColumn() == target.getPosition().getColumn() && lastTargetPosition.getRow() == target.getPosition().getRow()) {
			return; // target didnt move a bit we can use previously calculated path
		}
		
		Pathfinding algorithm = new Pathfinding(start, target);
		algorithm.compute();
	}
}
