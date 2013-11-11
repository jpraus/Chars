/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface NonPlayerCharacter extends Character {

    public BodyFatigue getFatigue();
    
    public boolean tryMove(int columnChange, int rowChange);
    
    public boolean isAwake();
    
    public void fallAsleep();
    
    public void wakeUp();
    
    public void killed(Placeable killer);
}
