/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.behavior.goal;

import com.praus.chars.character.NonPlayerCharacter;
import com.praus.chars.character.Placeable;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class ChaseGoal implements Goal {
    
    private final NonPlayerCharacter npc;
    private final Placeable target;

    public ChaseGoal(NonPlayerCharacter npc, Placeable target) {
        this.npc = npc;
        this.target = target;
    }
    
    public boolean isPerformable() {
        return true; // wander can always
    }
    
    private int signum(int number) {
        return number > 0 ? 1 : (number < 0 ? -1 : 0);
    }

    public void perform() {
        if (npc.getFatigue().canMove()) {
            int diffColumn = target.getPosition().getColumn() - npc.getPosition().getColumn();
            int diffRow = target.getPosition().getRow()- npc.getPosition().getRow();
            
            if ((diffColumn == -1 || diffColumn == 0 ||diffColumn == 1) && (diffRow == 1 || diffRow == 0 || diffRow == -1)) {
                return; // already next to target
            }
            
            if (Math.abs(diffRow) > Math.abs(diffColumn)) {
                npc.tryMove(0, Integer.signum(diffRow));
            }
            else if (Math.abs(diffRow) < Math.abs(diffColumn)) {
                npc.tryMove(Integer.signum(diffColumn), 0);
            }
            else {
                npc.tryMove(Integer.signum(diffColumn), Integer.signum(diffRow));
            }
        }
    }
    
}
