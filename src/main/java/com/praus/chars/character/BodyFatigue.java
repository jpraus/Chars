/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class BodyFatigue {
    
    private final FatigueCounter movement = new FatigueCounter();
    
    public void moved(int fatigue) {
        movement.fatigue(fatigue);
    }
    
    public boolean canMove() {
        return !movement.isTired();
    }
    
    public void dec() {
        movement.dec();
    }
    
    private class FatigueCounter {
        
        private final AtomicInteger counter = new AtomicInteger(0);
        
        public void fatigue(int fatigue) {
            counter.addAndGet(fatigue);
        }
        
        public void dec() {
            if (counter.get() > 0) {
                counter.decrementAndGet();
            }
        }
        
        public boolean isTired() {
            return counter.get() > 0;
        }
    }
}
