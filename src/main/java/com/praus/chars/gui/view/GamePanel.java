package com.praus.chars.gui.view;

import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.layout.BorderLayout;
import com.praus.chars.Globals;
import com.praus.chars.gui.BorderLayoutPanel;
import com.praus.chars.journal.Journal;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class GamePanel extends BorderLayoutPanel {

    private BorderLayoutPanel leftPanel;
    private BorderLayoutPanel leftTopPanel;
    
    public void init() {
        addMapComponent(new FloorView());
        addInfoComponent(new InfoView());
        addStatsComponent(new StatsView());
        
        // journal view
        JournalView journalView = new JournalView();
        Journal.listeners().registerListener(journalView);
        addJournalComponent(journalView);
    }    
    
    private void addInfoComponent(Component component) {
        addComponent(component, BorderLayout.RIGHT);
    }
    
    private void addMapComponent(Component component) {
        getLeftTopPanel().addComponent(component, BorderLayout.CENTER);
    }
    
    private void addJournalComponent(Component component) {
        getLeftTopPanel().addComponent(component, BorderLayout.BOTTOM);
    }
    
    private void addStatsComponent(Component component) {        
        getLeftPanel().addComponent(component, BorderLayout.BOTTOM);
    }
    
    private BorderLayoutPanel getLeftPanel() {
        if (leftPanel == null) {
            leftPanel = new BorderLayoutPanel();
            this.addComponent(leftPanel, BorderLayout.CENTER);
        }
        return leftPanel;
    }
    
    private BorderLayoutPanel getLeftTopPanel() {
        if (leftTopPanel == null) {
            leftTopPanel = new BorderLayoutPanel();
            getLeftPanel().addComponent(leftTopPanel, BorderLayout.CENTER);
        }
        return leftTopPanel;
    }

    @Override
    public String toString() {
        return "Game panel with " + getComponentCount() + " components";
    }
}
