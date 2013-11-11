/*
 * Copyright (c) 2013 Ji?� Praus
 */

package com.praus.chars.gui.view;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.gui.component.AbstractComponent;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalPosition;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.praus.chars.Globals;
import com.praus.chars.journal.JournalItem;
import com.praus.chars.journal.JournalListener;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class JournalView extends AbstractComponent implements JournalListener {

    private final static int MAX_ITEMS = 2; // max items to store that will be ever visible
    
    private final Deque<JournalItem> items = new LinkedList<JournalItem>();
    
    @Override
    protected TerminalSize calculatePreferredSize() {
        return new TerminalSize(65, 2);
    }

    @Override
    public void repaint(TextGraphics graphics) {
        repaintBackground(graphics);
    }
    
    private void repaintBackground(TextGraphics graphics) {
        graphics.setBackgroundColor(Terminal.Color.YELLOW);
        graphics.fillArea(' ');
        
        int line = 0;
        for (JournalItem item : items) {
            TextGraphics lineArea = graphics.subAreaGraphics(new TerminalPosition(0, line), new TerminalSize(graphics.getWidth(), 1));
            item.paint(lineArea, line == 0);
            
            line ++;
            if (line >= graphics.getHeight()) {
                break; // more lines cannot be drawn
            }
        }
    }

    public void onItemAdded(JournalItem item) {
        items.addFirst(item);
        while (items.size() > MAX_ITEMS) {
            items.removeLast();
        }
        invalidate();
    }
}
