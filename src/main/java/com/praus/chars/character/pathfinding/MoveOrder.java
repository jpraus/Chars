/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.pathfinding;

import java.util.Random;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class MoveOrder {
    
    private static final Random random = new Random();
    
    public static final MoveOrder NO = new MoveOrder(0, 0);
    
    public static final MoveOrder UP = new MoveOrder(0, -1);
    public static final MoveOrder DOWN = new MoveOrder(0, 1);
    public static final MoveOrder LEFT = new MoveOrder(-1, 0);
    public static final MoveOrder RIGHT = new MoveOrder(1, 0);
    
    public static final MoveOrder UP_LEFT = new MoveOrder(-1, -1);
    public static final MoveOrder UP_RIGHT = new MoveOrder(1, -1);
    public static final MoveOrder DOWN_LEFT = new MoveOrder(-1, 1);
    public static final MoveOrder DOWN_RIGHT = new MoveOrder(1, 1);
    
    private final int row;
    private final int column;

    MoveOrder(int column, int row) {
        this.column = column;
        this.row = row;        
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
    
    public static MoveOrder random() {
        return new MoveOrder(random.nextInt(3) - 1, random.nextInt(3) - 1);
    }

    @Override
    public String toString() {
        return String.format("{move %d,%d}", column, row);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MoveOrder)) {
            return false;
        }
        MoveOrder other = (MoveOrder) obj;
        return other.getColumn() == column && other.getRow() == row;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
        return hash;
    }
}
