/*
 * Copyright (c) 2013 Jiří Praus
 */

package com.praus.chars.character.behavior;

import com.praus.chars.Being;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface Behavior {
    
    public void onTargeted(Being source);
    
    public void onHit(Being source);
    
    public void onSight(Being target);
    
    public void onSightLose(Being target);

    public void onMovementReady();
    
    public void onHandReady();
    
    public void onSpellReady();
}
