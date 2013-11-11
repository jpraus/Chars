/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import com.praus.chars.character.appearance.Appearance;
import com.googlecode.lanterna.terminal.TerminalPosition;
import com.praus.chars.ListenerList;
import com.praus.chars.map.Floor;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface Placeable {
    
    public Appearance getAppearance();
    
    public Floor getFloor();
    
    public TerminalPosition getPosition();

    public ListenerList<PlaceableListener> listeners();    
}
