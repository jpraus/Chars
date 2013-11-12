/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import com.praus.chars.character.appearance.Appearance;
import com.praus.chars.ListenerList;
import com.praus.chars.map.Floor;
import com.praus.chars.map.Location;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface Placeable {
    
    public Appearance getAppearance();
    
    public Floor getFloor();
    
    public Location getLocation();

    public ListenerList<PlaceableListener> listeners();    
}
