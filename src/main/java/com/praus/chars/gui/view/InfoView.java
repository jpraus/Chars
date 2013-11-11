/*
 * Copyright (c) 2013 Ji?� Praus
 */

package com.praus.chars.gui.view;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.gui.component.AbstractComponent;
import com.googlecode.lanterna.terminal.ACS;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalPosition;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.praus.chars.Globals;
import com.praus.chars.character.Character;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class InfoView extends AbstractComponent {
    
    @Override
    protected TerminalSize calculatePreferredSize() {
        return new TerminalSize(15, 25);
    }

    @Override
    public void repaint(TextGraphics graphics) {
        repaintBackground(graphics);
        repaintCharacters(graphics.subAreaGraphics(
                new TerminalPosition(1, 3), new TerminalSize(graphics.getWidth() - 1, graphics.getHeight() - 11)));
    }
    
    private void repaintCharacters(TextGraphics graphics) {
        int item = 0;
        for (Character character : Globals.player().getFloor().getCharacters().getInLineOfSight(Globals.player())) {
            TextGraphics listItemGraphics = graphics.subAreaGraphics(new TerminalPosition(0, item * 2), new TerminalSize(graphics.getWidth(), 2));
            
            listItemGraphics.setForegroundColor(Terminal.Color.YELLOW);            
            listItemGraphics.setBackgroundColor(Terminal.Color.WHITE);
            listItemGraphics.drawString(0, 0, character.getName());
            
            character.getAppearance().getIcon().paint(listItemGraphics, 0, 1);
            
            listItemGraphics.setForegroundColor(Terminal.Color.YELLOW);
            listItemGraphics.setBackgroundColor(Terminal.Color.WHITE);
            String string = String.format("%d/%d", character.getHitPoints().getCurrent(), character.getHitPoints().getMaximum());            
            listItemGraphics.drawString(2, 1, string);
            
            item ++;
        }        
    }
    
    private void repaintBackground(TextGraphics graphics) {
        graphics.setBackgroundColor(Terminal.Color.WHITE);
        graphics.setForegroundColor(Terminal.Color.BLACK);
        graphics.fillArea(' ');
        graphics.fillRectangle(ACS.DOUBLE_LINE_VERTICAL, new TerminalPosition(0, 1), new TerminalSize(1, graphics.getHeight() - 2));

        // 1 row
        StringBuilder builder = new StringBuilder();
        builder.append(ACS.DOUBLE_LINE_T_DOWN);
        for (int i = 1; i < graphics.getWidth(); i++) {
            builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        }
        graphics.drawString(0, 0, builder.toString());

        // last row
        builder = new StringBuilder();
        builder.append(ACS.DOUBLE_LINE_T_UP);
        for (int i = 1; i < graphics.getWidth(); i++) {
            builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        }
        graphics.drawString(0, graphics.getHeight() - 1, builder.toString());
        
        // separators
        builder = new StringBuilder();
        builder.append(ACS.DOUBLE_LINE_T_RIGHT);
        for (int i = 1; i < graphics.getWidth(); i++) {
            builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        }
        graphics.drawString(0, graphics.getHeight() - 7, builder.toString());
        graphics.drawString(0, 2, builder.toString());
        
        // battle field
        graphics.setForegroundColor(Terminal.Color.BLUE);
        graphics.drawString(2, 1, "Battle Field");
        
        // menu
        graphics.setForegroundColor(Terminal.Color.BLACK);
        graphics.drawString(2, graphics.getHeight() - 6, "Character");
        graphics.drawString(2, graphics.getHeight() - 5, "Inventory");
        graphics.drawString(2, graphics.getHeight() - 4, "Quests");
        graphics.drawString(2, graphics.getHeight() - 3, "Rest");                
        graphics.drawString(2, graphics.getHeight() - 2, "Menu");        
        graphics.setForegroundColor(Terminal.Color.RED);
        graphics.drawString(2, graphics.getHeight() - 6, "C");
        graphics.drawString(2, graphics.getHeight() - 5, "I");
        graphics.drawString(2, graphics.getHeight() - 4, "Q");
        graphics.drawString(2, graphics.getHeight() - 3, "R");                
        graphics.drawString(2, graphics.getHeight() - 2, "M");
    }
}
