package com.praus.chars.map;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal.Color;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Tiles extends Container<Tile> {  

    public Tiles(int rows, int columns) {
        super(rows, columns);

        // basic dungeon
        for (int column = 0; column < columns; column ++) {
            for (int row = 0; row < rows; row ++) {
                if (column == 0 || row == 0 || column == columns - 1 || row == rows - 1) {
                    set(column, row, Tile.CAVE_WALL);
                }
                else {
                    set(column, row, Tile.CAVE_FLOOR);
                }
            }
        }
    }
    
    public Tile getDefaultBackground() {
        return Tile.CAVE_WALL;
    }
    
    public TextGraphics repaint(int offsetColumn, int offsetRow, TextGraphics graphics) {
        // fill map with background
        Tile defaultTile = getDefaultBackground();
        graphics.setBackgroundColor(defaultTile.getBackground());
        graphics.setForegroundColor(defaultTile.getColor());
        graphics.fillArea(defaultTile.getCharacter());
        
        // TODO: better handle offsets
        for (int column = offsetColumn; column < getWidth(); column ++) {
            for (int row = offsetRow; row < getHeight(); row ++) {
                Tile tile = get(column, row);
                if (tile != null) {
                    graphics.setBackgroundColor(tile.getBackground());
                    graphics.setForegroundColor(tile.getColor());
                    graphics.drawString(column, row, tile.getCharacter() + "");
                }
            }
        }
        return graphics;
    }
    
    public Color getBackground(int column, int row) {
        Tile tile = get(column, row);
        if (tile == null) {
            return Color.DEFAULT;
        }
        return tile.getBackground();
    }
    
    public boolean isBlocked(int column, int row) {
        Tile tile = get(column, row);
        return tile == null || tile.getAttribute() == Tile.Attribute.WALL;
    }
}
