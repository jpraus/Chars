/*
 * Copyright (c) 2013 Jiri Praus
 */
package com.praus.chars;

import com.praus.chars.character.player.Player;
import com.praus.chars.clock.RoundClock;
import com.praus.chars.map.Floor;
import com.praus.chars.map.Location;

/**
 * Game container, singleton instance
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Globals {

    private static Player player;
    private static RoundClock clock;
    
    public static void start() {
        // clock
        clock = new RoundClock();
        clock.start();
        
        // player
        Floor floor = new Floor(21, 65);
        floor.populate();
        player = new Player(floor, new Location(2, 2));
    }
    
    public static Player player() {
        return player;
    }
    
    public static RoundClock clock() {
        return clock;
    }
    
    public static void stop() {
        clock.stop();
    }
    
    private Globals() {
        // not instantiable
    }
}
