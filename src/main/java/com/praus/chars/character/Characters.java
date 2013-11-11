/*
 * Copyright (c) 2013 Jiri Praus
 */
package com.praus.chars.character;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.terminal.TerminalPosition;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Characters implements PlaceableListener, Iterable<Character> {

    private final List<PlaceableListener> listeners = new LinkedList<PlaceableListener>();
    private final List<Character> listImpl = new LinkedList<Character>();

    public boolean add(Character e) {
        return this.listImpl.add(e);
    }

    public Characters getInLineOfSight(Placeable placeable) {
        return this; // TODO: in line of sight
    }

    public void registerListener(PlaceableListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(PlaceableListener listener) {
        listeners.remove(listener);
    }

    public void onAppearanceChanged(Placeable placeable) {
        for (PlaceableListener listener : listeners) {
            listener.onAppearanceChanged(placeable);
        }
    }

    public void onMoved(Placeable placeable) {
        for (PlaceableListener listener : listeners) {
            listener.onMoved(placeable);
        }
    }

    public Iterator<Character> iterator() {
        return listImpl.iterator();
    }
}
