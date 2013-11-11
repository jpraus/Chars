package com.praus.chars.gui;

import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.gui.component.AbstractContainer;
import com.googlecode.lanterna.gui.layout.BorderLayout;
import com.googlecode.lanterna.gui.layout.LayoutManager;
import com.googlecode.lanterna.gui.layout.LayoutParameter;
import com.googlecode.lanterna.terminal.TerminalSize;
import java.util.List;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class BorderLayoutPanel extends AbstractContainer {

    private final LayoutManager layoutManager;

    public BorderLayoutPanel() {
        layoutManager = new BorderLayout();
    }

    public LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public boolean maximisesVertically() {
        return layoutManager.maximisesVertically();
    }

    public boolean maximisesHorisontally() {
        return layoutManager.maximisesHorisontally();
    }

    @Override
    public void repaint(TextGraphics graphics) {
        List<? extends LayoutManager.LaidOutComponent> laidOutComponents = layoutManager.layout(graphics.getSize());
        for (LayoutManager.LaidOutComponent laidOutComponent : laidOutComponents) {
            TextGraphics subSubGraphics = graphics.subAreaGraphics(laidOutComponent.getTopLeftPosition(), laidOutComponent.getSize());
            if (laidOutComponent.getComponent().isVisible()) {
                laidOutComponent.getComponent().repaint(subSubGraphics);
            }
        }
    }

    @Override
    protected TerminalSize calculatePreferredSize() {
        return layoutManager.getPreferredSize();
    }

    @Override
    public void addComponent(Component component, LayoutParameter... layoutParameters) {
        super.addComponent(component);
        layoutManager.addComponent(component, layoutParameters);
    }

    @Override
    public boolean removeComponent(Component component) {
        if (super.removeComponent(component)) {
            layoutManager.removeComponent(component);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Border layout panel with " + getComponentCount() + " components";
    }
}
