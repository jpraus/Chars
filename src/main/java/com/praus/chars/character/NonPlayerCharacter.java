/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import com.praus.chars.character.pathfinding.MoveOrder;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface NonPlayerCharacter extends Character {

    public BodyFatigue getFatigue();
    
    public boolean tryMove(MoveOrder order);
    
    public boolean isAwake();
    
    public void fallAsleep();
    
    public void wakeUp();
    
    public void killed(Placeable killer);
}
