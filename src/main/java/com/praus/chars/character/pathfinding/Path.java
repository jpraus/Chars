package com.praus.chars.character.pathfinding;

import com.praus.chars.map.MoveOrder;
import com.praus.chars.character.Placeable;
import com.praus.chars.map.Location;
import org.slf4j.LoggerFactory;

/**
 * Path finder wrapper providing exact move orders to take when need to reach target.
 * Provides caching and auto-recalculation of path if target is moving.
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Path {
    
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Path.class);
	
	private final Placeable current;
	private final Placeable target;
	
	private Location lastTargetLocation = null;
    
    private Waypoints path = null;

	/**
	 * Creates pathfinding object to calculate path from source to target.
	 * Can be used to tract moving targets
	 * 
	 * @param current source object
	 * @param target target object
	 */
	public Path(Placeable current, Placeable target) {
		this.current = current;
		this.target = target;
	}
	
    /**
     * Get next move order that needs to be take from current position to reach target.
     * Recompute path if target location has changed.
     * 
     * @return next move order to take to reach target
     * @throws UnreachableException when target is not reachable
     */
	public MoveOrder nextMove() throws UnreachableException {
        try {
            recomputeIfNeeded();            
            return nextMoveInternal();
        } 
        catch (PathNotFollowedException ex) {
            computePath();
            try {
                return nextMoveInternal();
            }
            catch (PathNotFollowedException e) { // this should never happend
                logger.error("Path has been reported as not followed but recomputation doesnt help, something else is moving with source");
                throw new UnreachableException(); // internal server error but better to stop reaching this that to fail
            }
        }
	}
	
	/**
	 * Reset currently calculated path and recalculate new one in next round if needed
	 */
	public void reset() {
		this.path = null;
	}
    
    private MoveOrder nextMoveInternal() throws UnreachableException, PathNotFollowedException {
        if (path == null) {
            throw new UnreachableException();
        }
        MoveOrder order = path.next(current.getLocation());        
        logger.debug("Next move {}", order);
        return order;        
    }
    
    private void recomputeIfNeeded() throws UnreachableException {
        if (path == null) {
            computePath();
        }
        else if (current.getFloor() != target.getFloor()) {
            computePath(); // floors has changed?
		}
        else if (lastTargetLocation == null || !lastTargetLocation.equals(target.getLocation())) {
            computePath(); // last position unknown or changed            
		}
        this.lastTargetLocation = new Location(target.getLocation());
    }
    
    private void computePath() throws UnreachableException {
        logger.debug("Path is calculating ...");
        AStarAlgorithm algorithm = new AStarAlgorithm(current, target);
        path = algorithm.compute();
    }
}
