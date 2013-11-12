package com.praus.chars.map;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 * @param <T>
 */
public class ArrayGrid<T extends Object> {
    
	private final int columns;
    private final int rows;    
    
    private final Object[][] tiles; // columns first, rows second

    public ArrayGrid(int columns, int rows) {
        this.columns = columns;
		this.rows = rows;        
        this.tiles = new Object[columns][rows];
    }
    
    public int getHeight() {
        return rows;
    }

    public int getWidth() {
        return columns;
    }

    protected boolean set(int column, int row, T object) {
        if (row < 0 || row > rows - 1 || column < 0 || column > columns - 1) {
            return false; // out of bounds
        }
        tiles[column][row] = object;
		return true;
    }
    
    protected T get(int column, int row) {
        if (row < 0 || row > rows - 1 || column < 0 || column > columns - 1) {
            return null; // out of bounds
        }
        return (T) tiles[column][row];
    }
	
	protected void remove(int column, int row) {
		if (row < 0 || row > rows - 1 || column < 0 || column > columns - 1) {
            return; // out of bounds
        }
		tiles[column][row] = null;
	}
    
    protected void clear() {
        for (int column = 0; column < columns; column ++) {
            for (int row = 0; row < rows; row ++) {
                tiles[column][row] = null;
            }
        }
    }
}
