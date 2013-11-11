/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.behavior.goal;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface Goal {
    
    public boolean isPerformable();
    
    public void perform();
    
}
