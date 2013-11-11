/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.monster;

import com.googlecode.lanterna.terminal.ACS;
import com.googlecode.lanterna.terminal.Terminal;
import com.praus.chars.Being;
import com.praus.chars.character.behavior.Behavior;
import com.praus.chars.character.AbstractNonPlayerCharacter;
import com.praus.chars.character.appearance.Appearance;
import com.praus.chars.character.appearance.SingleCharAppearance;
import com.praus.chars.map.Floor;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Ghoul extends AbstractNonPlayerCharacter {
    
    private final Behavior behavior = new GhoulBehavior();
    private final Appearance appearance = new GhoulAppearance();
    
    public Ghoul(Floor floor, int column, int row) {
        super("Ghoul", 200, 0, floor, column, row);
    }

    @Override
    public Appearance getAppearance() {
        return appearance;
    }

    @Override
    protected void behave() {
        tryMove((int)(Math.random() * 3) - 1, (int)(Math.random() * 3) - 1);
    }
    
    @Override
    protected int getMovement() {
        return 8;
    }
    
    private class GhoulBehavior implements Behavior {
        public void onTargeted(Being source) {
        }

        public void onHit(Being source) {
        }

        public void onSight(Being target) {
        }

        public void onSightLose(Being target) {
        }

        public void onMovementReady() {
        }

        public void onHandReady() {
        }

        public void onSpellReady() {
        }
    };
    
    private class GhoulAppearance extends SingleCharAppearance {
        public GhoulAppearance() {
            super(ACS.FACE_BLACK, Terminal.Color.MAGENTA, Terminal.Color.CYAN);
        }
    }
}
