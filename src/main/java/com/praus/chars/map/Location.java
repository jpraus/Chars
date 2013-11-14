/*
 * Copyright (c) 2013 Jiri Praus
 */
package com.praus.chars.map;

import com.googlecode.lanterna.terminal.TerminalPosition;

/**
 * 2D location system point
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Location {

    private int row;
    private int column;

    public Location(Location position) {
        this(position.getColumn(), position.getRow());
    }

    public Location(int column, int row) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }
    
    public void changeTo(Location location) {
        this.column = location.getColumn();
        this.row = location.getRow();
    }
    
    public Location futureLocation(int columnChange, int rowChange) {
        return new Location(column + columnChange, row + rowChange);
    }
    
    public Location futureLocation(MoveOrder order) {
        return new Location(column + order.getColumn(), row + order.getRow());
    }
    
    public MoveOrder moveTo(Location target) {
        return new MoveOrder(target.getColumn() - column, target.getRow() - row);
    }
    
    /**
     * Returns true if given point is in distance 0 or 1 from this point, false if further.
     * This is optimized function.
     * 
     * @param target target location
     * @return true if in distance 0 or 1, false is further away
     */
    public boolean isNextTo(Location target) {
        int rowDiff = row - target.getRow();
        int columnDiff = column - target.getColumn();
        
        return (rowDiff == -1 || rowDiff == 0 || rowDiff == 1) 
                && (columnDiff == -1 || columnDiff == 0 || columnDiff == 1);
    }

    @Override
    public String toString() {
        return String.format("[%d,%d]", column, row);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Location)) {
            return false;
        }
        Location other = (Location) obj;
        return other.getColumn() == column && other.getRow() == row;
    }
}
