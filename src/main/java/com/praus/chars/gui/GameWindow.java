/*
 * Copyright (c) 2013 Ji?� Praus
 */

package com.praus.chars.gui;

import com.praus.chars.gui.view.GamePanel;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.layout.LinearLayout;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.praus.chars.Globals;
import com.praus.chars.journal.Journal;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class GameWindow extends Window {
    
    public GameWindow() {
        super("Chars");
        
        setSoloWindow(true);
        setBorder(new Border.Invisible());
        
        GamePanel gamePanel = new GamePanel();
        gamePanel.init();
        
        Journal.addItem("Hello there");
        
        addComponent(gamePanel, LinearLayout.MAXIMIZES_VERTICALLY, LinearLayout.MAXIMIZES_HORIZONTALLY);
        //addComponent(new CheckBox("Ahoj sem muzes psat", false));
    }

    @Override
    public void onKeyPressed(Key key) {
        if (key.getKind() == Kind.Escape) {
            this.close();
            return;
        }        
        super.onKeyPressed(key);
    }
}
