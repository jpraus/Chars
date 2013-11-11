/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Stat {
    
    private long maximum;
    private long current;

    public Stat(long maximum, long current) {
        setMaximum(maximum);
        setCurrent(current);
    }
    
    public void changeMaximum(long change) {
        setMaximum(maximum + change);
    }

    public final void setMaximum(long maximum) {
        if (maximum < 0L) {
            maximum = 0L;
        }
        this.maximum = maximum;
    }
    
    public long getMaximum() {
        return maximum;
    }
    
    public void changeCurrent(long change) {
        setCurrent(current + change);
    }

    public final void setCurrent(long current) {
        if (current < 0L) {
            current = 0L;
        }
        this.current = current;
    }

    public long getCurrent() {
        return current;
    }
}
