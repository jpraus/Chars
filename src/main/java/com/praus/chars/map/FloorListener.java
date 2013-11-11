/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.map;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface FloorListener {
    
    /**
     * Floor items, tiles or characters changed somehow
     * 
     * @param floor target floor
     */
    public void onChanged(Floor floor);
}
