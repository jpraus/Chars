/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.monster;

import com.googlecode.lanterna.terminal.ACS;
import com.googlecode.lanterna.terminal.Terminal;
import com.praus.chars.character.behavior.Behavior;
import com.praus.chars.character.AbstractNonPlayerCharacter;
import com.praus.chars.character.appearance.Appearance;
import com.praus.chars.character.appearance.SingleCharAppearance;
import com.praus.chars.character.behavior.Chaser;
import com.praus.chars.map.Floor;
import com.praus.chars.map.Location;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Zombie extends AbstractNonPlayerCharacter {

    private final Appearance appearance = new ZombieAppearance();
    
    private Behavior behavior;
    
    public Zombie(Floor floor, Location location) {
        super("Zombie", 100, 0, floor, location);
    }

    @Override
    public Appearance getAppearance() {
        return appearance;
    }

    @Override
    protected void behave() {
        if (behavior == null) {
            behavior = new Chaser(this);
        }
        behavior.behave();
    }
    
    @Override
    protected int getMovement() {
        return 5;
    }
    
    private class ZombieAppearance extends SingleCharAppearance {
        public ZombieAppearance() {
            super(ACS.FACE_BLACK, Terminal.Color.CYAN, null);
        }
    }
}
