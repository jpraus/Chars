/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.journal;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.terminal.Terminal;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class TextJournalItem implements JournalItem {

    private final String text;

    public TextJournalItem(String text) {
        this.text = text;
    }

    public void paint(TextGraphics graphics, boolean hightlight) {
        if (hightlight) {
            graphics.setForegroundColor(Terminal.Color.RED);
        }
        else {
            graphics.setForegroundColor(Terminal.Color.BLACK);
        }
        graphics.drawString(0, 0, text);
    }    
}
