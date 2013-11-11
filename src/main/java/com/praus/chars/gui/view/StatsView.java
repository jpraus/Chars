/*
 * Copyright (c) 2013 Ji?� Praus
 */

package com.praus.chars.gui.view;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.gui.component.AbstractComponent;
import com.googlecode.lanterna.terminal.ACS;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.praus.chars.Globals;
import com.praus.chars.character.Stat;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class StatsView extends AbstractComponent {
    
    @Override
    protected TerminalSize calculatePreferredSize() {
        return new TerminalSize(65, 1);
    }

    @Override
    public void repaint(TextGraphics graphics) {
        repaintStats(graphics);
    }
    
    private void repaintStats(TextGraphics graphics) {
        StringBuilder builder = new StringBuilder();
        builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        builder.append(ACS.DOUBLE_LINE_T_LEFT);
        builder.append(" HP:");
        for (int i = 0; i < 15; i++) {
            builder.append(' '); // hp placeholder
        }
        builder.append(ACS.DOUBLE_LINE_T_RIGHT);
        builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        builder.append(ACS.DOUBLE_LINE_T_LEFT);
        builder.append(" Mana:");
        for (int i = 0; i < 15; i++) {
            builder.append(' '); // mana placeholder
        }
        builder.append(ACS.DOUBLE_LINE_T_RIGHT);
        builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        for (int i = 47; i < graphics.getWidth(); i++) {
            builder.append(ACS.DOUBLE_LINE_HORIZONTAL); // rest of stats
        }

        graphics.setBackgroundColor(Terminal.Color.YELLOW);
        graphics.setForegroundColor(Terminal.Color.BLACK);
        graphics.drawString(0, 0, builder.toString());
        
        repaintHitPoints(graphics);
        repaintMana(graphics);
    }
    
    void repaintHitPoints(TextGraphics graphics) {
        Stat hitPoints = Globals.player().getHitPoints();
        
        String hitPointsString = String.format("%1$7d/%2$-7d", hitPoints.getCurrent(), hitPoints.getMaximum());
        int green = (int) Math.round((15.0 / (double) hitPoints.getMaximum()) * (double) hitPoints.getCurrent());

        graphics.setBackgroundColor(Terminal.Color.GREEN);
        graphics.setForegroundColor(Terminal.Color.WHITE);
        graphics.drawString(6, 0, hitPointsString.substring(0, green));

        graphics.setBackgroundColor(Terminal.Color.RED);
        graphics.setForegroundColor(Terminal.Color.WHITE);
        graphics.drawString(6 + green, 0, hitPointsString.substring(green));
    }
    
    void repaintMana(TextGraphics graphics) {
        Stat mana = Globals.player().getMana();
        
        String manaString = String.format("%1$7d/%2$-7d", mana.getCurrent(), mana.getMaximum());
        int green = (int) Math.round((15.0 / (double) mana.getMaximum()) * (double) mana.getCurrent());

        graphics.setBackgroundColor(Terminal.Color.BLUE);
        graphics.setForegroundColor(Terminal.Color.WHITE);
        graphics.drawString(30, 0, manaString.substring(0, green));

        graphics.setBackgroundColor(Terminal.Color.RED);
        graphics.setForegroundColor(Terminal.Color.WHITE);
        graphics.drawString(30 + green, 0, manaString.substring(green));
    }
}
