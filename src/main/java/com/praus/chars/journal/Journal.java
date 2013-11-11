/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.journal;

import com.praus.chars.ListenerList;
import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Journal {    
    private static final Deque<JournalItem> items = new LinkedList<JournalItem>();
    private static final ListenerList<JournalListener> listeners = new ListenerList<JournalListener>();

    private Journal() {
        // not instantiable
    }
    
    public static ListenerList<JournalListener> listeners() {
        return listeners;
    }
    
    public static void addItem(String text) {
        JournalItem item = new TextJournalItem(text);
        items.addFirst(item);
        
        for (JournalListener listener : listeners) {
            listener.onItemAdded(item);
        }
    }    
}
