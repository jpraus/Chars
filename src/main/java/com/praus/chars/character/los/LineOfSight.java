/*
 * Copyright (c) 2013 Jiri Praus
 */
package com.praus.chars.character.los;

import com.googlecode.lanterna.terminal.Terminal;
import com.praus.chars.character.Placeable;
import com.praus.chars.map.Location;
import com.praus.chars.map.MoveOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class LineOfSight {

    private static final Logger logger = LoggerFactory.getLogger(LineOfSight.class);

    private final Placeable place;

    public LineOfSight(Placeable place) {
        this.place = place;
    }

    public void compute() {
        place.getFloor().getTiles().resetColors();

        int distance = 10;
        for (int row = -distance; row <= distance; row++) {            
            Location point = place.getLocation().futureLocation(row, distance);
            line(place.getLocation(), point);
            
            point = place.getLocation().futureLocation(row, -distance);
            line(place.getLocation(), point);
        }
        int distanceReduced = distance - 1; // corners are already covered
        for (int column = -distanceReduced; column <= distanceReduced; column++) {            
            Location point = place.getLocation().futureLocation(distance, column);
            line(place.getLocation(), point);
            
            point = place.getLocation().futureLocation(-distance, column);
            line(place.getLocation(), point);
        }
    }

    /**
     * Implementation of Bresenham's line algorithm
     * 
     * @param start start location
     * @param end end location
     */
    private void line(Location start, Location end) {
        int x0 = start.getColumn();
        int y0 = start.getRow();
        
        int dx = Math.abs(end.getColumn() - x0);
        int dy = Math.abs(end.getRow() - y0);
        int sx = (x0 < end.getColumn()) ? 1 : -1;
        int sy = (y0 < end.getRow()) ? 1 : -1;
        int err = dx - dy;

        while (true) {
            // plot(x0,y0)
            if (!sightPoint(x0, y0)) {
                break;
            }
            if (x0 == end.getColumn() && y0 == end.getRow()) {
                break;
            }
            int e2 = err * 2;
            if (e2 > -dy) {
                err = err - dy;
                x0 = x0 + sx;
            }
            if (x0 == end.getColumn() && y0 == end.getRow()) {
                sightPoint(x0, y0);
                break;
            }
            if (e2 < dx) {
                err = err + dx;
                y0 = y0 + sy;
            }
        }
    }
    
    private boolean sightPoint(int x, int y) {        
        if (!place.getFloor().getTiles().isInBounds(x, y)) {
            return false;
        }        
        if (!place.getFloor().getTiles().isBlocked(x, y)) {
            place.getFloor().getTiles().setColor(x, y, Terminal.Color.YELLOW);
            return true;
        }
        place.getFloor().getTiles().setColor(x, y, Terminal.Color.GREEN);
        return false;
    }
}
