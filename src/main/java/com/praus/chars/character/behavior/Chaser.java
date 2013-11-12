/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.behavior;

import com.praus.chars.Being;
import com.praus.chars.Globals;
import com.praus.chars.character.NonPlayerCharacter;
import com.praus.chars.character.behavior.goal.ChaseGoal;
import com.praus.chars.character.behavior.goal.Goal;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Chaser implements Behavior {

    private final NonPlayerCharacter npc;
    private Goal current = null;
    
    public Chaser(NonPlayerCharacter npc) {
        this.npc = npc;
        this.current = new ChaseGoal(npc, Globals.player());
    }

    @Override
    public void onTargeted(Being source) {
    }

    @Override
    public void onHit(Being source) {
    }

    @Override
    public void onSight(Being target) {
    }

    @Override
    public void onSightLose(Being target) {
    }

    @Override
    public void onMovementReady() {
    }

    @Override
    public void onHandReady() {
    }

    @Override
    public void onSpellReady() {
    }

    @Override
    public void behave() {
        if (current.isPerformable()) {
            current.perform();
        }
    }
}
