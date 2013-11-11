/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import com.googlecode.lanterna.terminal.TerminalPosition;
import com.praus.chars.ListenerList;
import com.praus.chars.map.Floor;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public abstract class AbstractCharacter implements Character {

    private final ListenerList<PlaceableListener> listeners = new ListenerList<PlaceableListener>();
    
    private final String name;
    
    private final Stat hitPoints;
    private final Stat mana;

    private Floor floor;
    private final TerminalPosition position;    

    public AbstractCharacter(String name, long hitPoints, long mana, Floor floor, int column, int row) {
        this.name = name;
        this.hitPoints = new Stat(hitPoints, hitPoints);
        this.mana = new Stat(mana, mana);

        changeFloor(floor);
        this.position = new TerminalPosition(column, row);
    }

    @Override
    public String getName() {
        return name;
    }
    
    protected final void changeFloor(Floor floor) {
        if (this.floor != floor) {
            // floor of character change, move listeners also
            listeners().unregisterListener(this.floor);
            listeners().registerListener(floor);
        }
        this.floor = floor;
    }
    
    @Override
    public Floor getFloor() {
        return floor;
    }

    @Override
    public TerminalPosition getPosition() {
        return position;
    }

    @Override
    public Stat getHitPoints() {
        return hitPoints;
    }

    @Override
    public Stat getMana() {
        return mana;
    }
    
    public boolean tryMove(int columnChange, int rowChange) {
        int column = getPosition().getColumn() + columnChange;
        int row = getPosition().getRow()+ rowChange;
        
        if (getFloor().isBlocked(column, row)) {
            return false;
        }
        this.getPosition().setColumn(column);
        this.getPosition().setRow(row);        
        
        this.notifyListenersMoved();
        return true;
    }
    
    @Override
    public ListenerList<PlaceableListener> listeners() {
        return listeners;
    }
    
    public void notifyListenersAppearanceChanged() {
        for (PlaceableListener listener : listeners) {
            listener.onAppearanceChanged(this);
        }
    }
    
    public void notifyListenersMoved() {
        for (PlaceableListener listener : listeners) {
            listener.onMoved(this);
        }
    }
}
