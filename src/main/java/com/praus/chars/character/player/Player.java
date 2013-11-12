/*
 * Copyright (c) 2013 Ji?� Praus
 */

package com.praus.chars.character.player;

import com.googlecode.lanterna.terminal.ACS;
import com.googlecode.lanterna.terminal.Terminal;
import com.praus.chars.character.behavior.Behavior;
import com.praus.chars.character.AbstractCharacter;
import com.praus.chars.character.appearance.Appearance;
import com.praus.chars.character.appearance.SingleCharAppearance;
import com.praus.chars.map.Floor;
import com.praus.chars.map.Location;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Player extends AbstractCharacter {

    private final PlayerAppearance appearance;
    
    public Player(Floor floor, Location location) {
        super("Player", 1500, 1000, floor, location);

        this.appearance = new PlayerAppearance();
    }

    public Behavior getBehavior() {
        return null; // no behavior for player
    }
    
    public Appearance getAppearance() {
        return appearance;
    }
    
    private class PlayerAppearance extends SingleCharAppearance {
        public PlayerAppearance() {
            super(ACS.FACE_BLACK, Terminal.Color.YELLOW, null);
        }
    }
}
