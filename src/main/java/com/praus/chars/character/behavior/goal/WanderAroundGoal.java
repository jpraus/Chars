/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.behavior.goal;

import com.praus.chars.character.NonPlayerCharacter;
import com.praus.chars.character.pathfinding.MoveOrder;

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
        return true; // wander can always
    }

    public void perform() {
        if (npc.getFatigue().canMove()) {
            npc.tryMove(MoveOrder.random());
        }
    }
    
}
