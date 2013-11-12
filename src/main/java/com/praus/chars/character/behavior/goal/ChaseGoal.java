/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.behavior.goal;

import com.praus.chars.character.NonPlayerCharacter;
import com.praus.chars.character.Placeable;
import com.praus.chars.map.MoveOrder;
import com.praus.chars.character.pathfinding.Path;
import com.praus.chars.character.pathfinding.UnreachableException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class ChaseGoal implements Goal {
    
    private final NonPlayerCharacter npc;
    private final Placeable target;
    
    private final Path path;

    public ChaseGoal(NonPlayerCharacter npc, Placeable target) {
        this.npc = npc;
        this.target = target; 
        this.path = new Path(npc, target);
    }
    
    public boolean isPerformable() {
        return true; // wander can always
    }

    public void perform() {
        if (npc.getLocation().isNextTo(target.getLocation())) {
            return; // nothing to do if we are next to target
        }
        if (npc.getFatigue().canMove()) {
            try {
                MoveOrder next = path.nextMove();
                if (next.equals(MoveOrder.NO)) {
                    // target reached, we can do something else instead
                }
                npc.tryMove(next);
            }
            catch (UnreachableException ex) {
                Logger.getLogger(ChaseGoal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}
