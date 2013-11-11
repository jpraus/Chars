/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface Character extends Placeable {
    
    public String getName();

    public Stat getHitPoints();
    
    public Stat getMana();
}
