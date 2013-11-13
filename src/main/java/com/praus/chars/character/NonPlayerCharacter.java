/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import com.praus.chars.character.pathfinding.PathBlockedException;
import com.praus.chars.map.MoveOrder;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface NonPlayerCharacter extends Character {

    public BodyFatigue getFatigue();
    
	public void tryMove(MoveOrder order) throws PathBlockedException, FatigueException;
    
    public boolean isAwake();
    
    public void fallAsleep();
    
    public void wakeUp();
    
    public void killed(Placeable killer);
}
