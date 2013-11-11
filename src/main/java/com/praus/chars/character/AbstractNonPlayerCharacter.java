/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import com.praus.chars.Globals;
import com.praus.chars.clock.RoundClockListener;
import com.praus.chars.map.Floor;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public abstract class AbstractNonPlayerCharacter extends AbstractCharacter implements NonPlayerCharacter, RoundClockListener {

    private final BodyFatigue fatigue = new BodyFatigue();

    private boolean isAwake = false;
    
    public AbstractNonPlayerCharacter(String name, long hitPoints, long mana, Floor floor, int column, int row) {
        super(name, hitPoints, mana, floor, column, row);
        wakeUp();
    }

    public BodyFatigue getFatigue() {
        return fatigue;
    }

    public final boolean isAwake() {
        return isAwake;
    }

    public final synchronized void fallAsleep() {
        if (isAwake) {
            Globals.clock().unregister(this);
            isAwake = false;
        }        
    }

    public final synchronized void wakeUp() {
        if (!isAwake) {
            Globals.clock().register(this);
            isAwake = true;
        }
    }
    
    public void killed(Placeable killer) {
        
    }

    public final void onRoundTick() {
        fatigue.dec();
        behave();
    }
    
    protected abstract void behave();
    
    protected abstract int getMovement();

    @Override
    public boolean tryMove(int columnChange, int rowChange) {
        if (!fatigue.canMove()) {
            return false; // TODO: or illegal call exception
        }
        boolean moved = super.tryMove(columnChange, rowChange);
        if (moved) {
            fatigue.moved(getMovement());
        }
        return moved;
    }
}