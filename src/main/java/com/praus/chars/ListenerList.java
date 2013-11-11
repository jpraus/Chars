/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 * @param <T>
 */
public class ListenerList<T> implements Iterable<T> {
    
    private final Collection<T> listeners = new LinkedList<T>();
    
    public void registerListener(T listener) {
        listeners.add(listener);
    }

    public void unregisterListener(T listener) {
        listeners.remove(listener);
    }

    public Iterator<T> iterator() {
        return listeners.iterator();
    }
}
