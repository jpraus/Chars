/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character;

import com.praus.chars.ListenerList;
import com.praus.chars.map.MoveOrder;
import com.praus.chars.map.Floor;
import com.praus.chars.map.Location;

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
    private final Location location;    

    public AbstractCharacter(String name, long hitPoints, long mana, Floor floor, Location location) {
        this.name = name;
        this.hitPoints = new Stat(hitPoints, hitPoints);
        this.mana = new Stat(mana, mana);

        changeFloor(floor);
        this.location = new Location(location);
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
    public Location getLocation() {
        return location;
    }

    @Override
    public Stat getHitPoints() {
        return hitPoints;
    }

    @Override
    public Stat getMana() {
        return mana;
    }
    
    public boolean tryMove(MoveOrder order) {
        Location future = getLocation().futureLocation(order);
        if (getFloor().isBlocked(future)) {
            return false;
        }
        this.getLocation().changeTo(future);     
        
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
