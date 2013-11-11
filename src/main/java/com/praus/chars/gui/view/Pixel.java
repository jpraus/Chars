/*
 * Copyright (c) 2013 Jiří Praus
 */

package com.praus.chars.gui.view;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Pixel {

    private final Terminal.Color color;
    private final Terminal.Color background;
    private final char character;
    private final String string;

    public Pixel(Terminal.Color color, Terminal.Color background, char character) {
        this.color = color;
        this.background = background;
        this.character = character;
        this.string = character + "";
    }

    public Terminal.Color getColor() {
        return color;
    }

    public Terminal.Color getBackground() {
        return background;
    }

    public char getCharacter() {
        return character;
    }
    
    public void paint(TextGraphics graphics, int column, int row) {
        if (color != null) {
            graphics.setForegroundColor(color);
        }
        if (background != null) {
            graphics.setBackgroundColor(background);
        }
        graphics.drawString(column, row, string);
    }
}
