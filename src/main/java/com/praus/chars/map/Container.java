package com.praus.chars.map;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 * @param <T>
 */
public class Container<T extends Object> {
    
    private final int rows;
    private final int columns;
    
    private final Object[][] tiles; // columns first, rows second

    public Container(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.tiles = new Object[columns][rows];
    }
    
    public int getHeight() {
        return rows;
    }

    public int getWidth() {
        return columns;
    }

    protected void set(int column, int row, T object) {
        if (row < 0 || row > rows - 1 || column < 0 || column > columns - 1) {
            return; // out of bounds
        }
        tiles[column][row] = object;
    }
    
    protected T get(int column, int row) {
        if (row < 0 || row > rows - 1 || column < 0 || column > columns - 1) {
            return null; // out of bounds
        }
        return (T) tiles[column][row];
    }
}
