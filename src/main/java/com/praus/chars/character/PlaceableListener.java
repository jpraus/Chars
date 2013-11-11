/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface PlaceableListener {
    
    /**
     * Placeable has changed color or shape
     * 
     * @param placeable target placeable
     */
    public void onAppearanceChanged(Placeable placeable);
    
    /**
     * Placeable has moved
     * 
     * @param placeable target placeable
     */
    public void onMoved(Placeable placeable);
}
