/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.pathfinding;

import com.praus.chars.map.MoveOrder;
import com.praus.chars.map.Location;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
class Waypoints {
    
    private int length = 0;    
    private Waypoint start = null;
    
    public void prependWaypoint(Node node) {        
        this.start = new Waypoint(node, this.start);
        this.length ++;
    }

    public int getLength() {
        return length;
    }
    
    /**
     * Find next waypoint from given current location, throws PathNotFollowedException
     * if location is not on any waypoint
     * TODO: could change also neighbours if current location not found on path.
     * 
     * @param location current location
     * @return next move order to take to reach next waypoint
     * @exception PathNotFollowedException if current location is not on any waypoint
     */
    public MoveOrder next(Location location) throws PathNotFollowedException {
        if (start == null) {
            return MoveOrder.NO; // end reached or no need to move at all
        }
        Waypoint current = start;
        int gone = 0;
        while (current != null) {
            if (current.location.equals(location)) {
                this.start = current; // path has been reached to here, we can remove old column
                this.length -= gone; // path has shortened from now

                if (current.next == null) {
                    return MoveOrder.NO; // no need to move at all
                }
                return location.moveTo(current.next.location);
            }
            gone ++;
            current = current.next;
        }
        throw new PathNotFollowedException();
    }
    
    private class Waypoint {

        private final Location location;
        private final Waypoint next; // path next waypoint to follow 
        
        private Waypoint(Node node, Waypoint next) {
            this.location = new Location(node.getColumn(), node.getRow());
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("{Waypoint %s}", location);
        }
    }

    @Override
    public String toString() {
        return String.format("{Waypoints len=%d, start=%s}", length, start);
    }
}
