/*
 * Copyright (c) 2013 Jiří Praus
 */

package com.praus.chars.map;

import com.googlecode.lanterna.terminal.ACS;
import com.googlecode.lanterna.terminal.Terminal;
import com.praus.chars.gui.view.Pixel;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Tile extends Pixel {
    
    public static final Tile CAVE_WALL = new Tile(Terminal.Color.BLACK, Terminal.Color.WHITE, ACS.BLOCK_SOLID, Attribute.WALL);
    public static final Tile CAVE_FLOOR = new Tile(Terminal.Color.BLACK, Terminal.Color.WHITE, ACS.BLOCK_SPARSE, Attribute.FLOOR);
    
    public enum Attribute {
        WALL,
        FLOOR
    }

    private final Attribute attribute;

    public Tile(Terminal.Color color, Terminal.Color background, char tile, Attribute attribute) {
        super(color, background, tile);
        this.attribute = attribute;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
