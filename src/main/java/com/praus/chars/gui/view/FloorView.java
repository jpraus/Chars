package com.praus.chars.gui.view;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.gui.component.AbstractInteractableComponent;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.ACS;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalPosition;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.praus.chars.Globals;
import com.praus.chars.map.MoveOrder;
import com.praus.chars.map.Floor;
import com.praus.chars.map.FloorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class FloorView extends AbstractInteractableComponent implements FloorListener {
    
    private static final Logger logger = LoggerFactory.getLogger(FloorView.class);

    public FloorView() {
        Globals.player().getFloor().listener().registerListener(this); // TODO: no a good place
    }
    
    public void playerMove(MoveOrder move) {
        logger.debug("Move order {}", move);
        Globals.player().tryMove(move);        
    }

    @Override
    public Result keyboardInteraction(Key key) {
        switch(key.getKind()) {
            case ArrowDown:
                playerMove(MoveOrder.DOWN);
                return Result.EVENT_HANDLED;
            case ArrowUp:
                playerMove(MoveOrder.UP);
                return Result.EVENT_HANDLED;
            case ArrowRight:
                playerMove(MoveOrder.RIGHT);
                return Result.EVENT_HANDLED;
            case ArrowLeft:
                playerMove(MoveOrder.LEFT);
                return Result.EVENT_HANDLED;
        }
        return Result.EVENT_NOT_HANDLED;
    }

    @Override
    protected TerminalSize calculatePreferredSize() {
        return new TerminalSize(65, 22);
    }

    @Override
    public void repaint(TextGraphics graphics) {
        repaintTitle(graphics.subAreaGraphics(
                new TerminalPosition(0, 0), new TerminalSize(graphics.getWidth(), 1)));
        repaintMap(graphics.subAreaGraphics(
                new TerminalPosition(0, 1), new TerminalSize(graphics.getWidth(), graphics.getHeight() - 1)));
    }
    
    private void repaintMap(TextGraphics graphics) {
        Floor floor = Globals.player().getFloor();
        floor.repaint(0, 0, graphics);
    }
    
    private void repaintTitle(TextGraphics graphics) {
        String title = Globals.player().getFloor().getTitle();
        int width = graphics.getWidth();
        int titleTextLength = Math.min(title.length(), width - 6); // 6 for border of title        
        
        long left = Math.round(Math.ceil((width - titleTextLength) / 2.0));
        long right = Math.round(Math.floor((width - titleTextLength) / 2.0));        
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < left - 1; i ++) {
            builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        }
        builder.append(ACS.DOUBLE_LINE_T_LEFT);
        builder.append(' ');
        
        builder.append(title.substring(0, titleTextLength));
        
        builder.append(' ');
        builder.append(ACS.DOUBLE_LINE_T_RIGHT);
        for (int i = 0; i < right - 1; i ++) {
            builder.append(ACS.DOUBLE_LINE_HORIZONTAL);
        }
        
        graphics.setBackgroundColor(Terminal.Color.YELLOW);
        graphics.setForegroundColor(Terminal.Color.BLACK);
        graphics.drawString(0, 0, builder.toString());
    }

    public void onChanged(Floor floor) {
        if (floor == Globals.player().getFloor()) {
            invalidate(); // invalidate current floor
        }        
    }
}
