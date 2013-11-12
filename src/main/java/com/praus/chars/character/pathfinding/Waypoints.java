/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.pathfinding;

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
     * Find next waypoint from given current position, throws PathNotFollowedException
     * if position is not on any waypoint
     * TODO: could change also neighbours if current position not found on path.
     * 
     * @param column current position column
     * @param row current position row
     * @return next move order to take to reach next waypoint
     * @exception PathNotFollowedException if current position is not on any waypoint
     */
    public MoveOrder next(int column, int row) throws PathNotFollowedException {
        if (start == null) {
            return MoveOrder.NO; // end reached or no need to move at all
        }
        Waypoint current = start;
        int gone = 0;
        while (current != null) {
            if (current.column == column && current.row == row) {
                this.start = current; // path has been reached to here, we can remove old column
                this.length -= gone; // path has shortened from now

                if (current.next == null) {
                    return MoveOrder.NO; // no need to move at all
                }
                return new MoveOrder(current.next.column - column, current.next.row - row);
            }
            gone ++;
            current = current.next;
        }
        throw new PathNotFollowedException();
    }
    
    private class Waypoint {

        private final int column;
        private final int row;

        private final Waypoint next; // path next waypoint to follow 
        
        private Waypoint(Node node, Waypoint next) {
            this.column = node.getColumn();
            this.row = node.getRow();
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("{Waypoint %d,%d}", column, row);
        }
    }

    @Override
    public String toString() {
        return String.format("{Waypoints len=%d, start=%s}", length, start);
    }
}
