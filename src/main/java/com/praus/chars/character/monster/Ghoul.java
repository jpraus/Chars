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
import com.praus.chars.character.behavior.Wanderer;
import com.praus.chars.map.Floor;
import com.praus.chars.map.Location;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Ghoul extends AbstractNonPlayerCharacter {
        
    private final Appearance appearance = new GhoulAppearance();

    private Behavior behavior;
    
    public Ghoul(Floor floor, Location location) {
        super("Ghoul", 200, 0, floor, location);
    }

    @Override
    public Appearance getAppearance() {
        return appearance;
    }

    @Override
    protected void behave() {
        if (behavior == null) {
            behavior = new Wanderer(this);
        }
        behavior.behave();
    }
    
    @Override
    protected int getMovement() {
        return 8;
    }
    
    private class GhoulAppearance extends SingleCharAppearance {
        public GhoulAppearance() {
            super(ACS.FACE_BLACK, Terminal.Color.MAGENTA, Terminal.Color.CYAN);
        }
    }
}
