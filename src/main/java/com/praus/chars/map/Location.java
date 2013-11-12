/*
 * Copyright (c) 2013 Jiri Praus
 */
package com.praus.chars.map;

import com.googlecode.lanterna.terminal.TerminalPosition;
import com.praus.chars.character.pathfinding.MoveOrder;

/**
 * 2D location system point
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Location {

    private int row;
    private int column;

    public Location(TerminalPosition position) {
        this(position.getColumn(), position.getRow());
    }

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
        if (!(obj instanceof MoveOrder)) {
            return false;
        }
        Location other = (Location) obj;
        return other.getColumn() == column && other.getRow() == row;
    }
}
