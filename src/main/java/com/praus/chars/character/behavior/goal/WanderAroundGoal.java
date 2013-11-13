/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.behavior.goal;

import com.praus.chars.character.FatigueException;
import com.praus.chars.character.NonPlayerCharacter;
import com.praus.chars.character.pathfinding.PathBlockedException;
import com.praus.chars.map.MoveOrder;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class WanderAroundGoal implements Goal {

    private final NonPlayerCharacter npc;

    public WanderAroundGoal(NonPlayerCharacter npc) {
        this.npc = npc;
    }
    
    public boolean isPerformable() {
        return true; // TODO: if character is completely blocked it cannot perform wander around goal
    }

    public void perform() {
        if (npc.getFatigue().canMove()) {
			try {
				npc.tryMove(MoveOrder.random());
			} 
			catch (PathBlockedException ex) {
				// try next in round again
			} 
			catch (FatigueException ex) {
				// try next in round again
			}
        }
    }
    
}
