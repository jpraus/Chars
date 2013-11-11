/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.journal;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface JournalListener {

    /**
     * New journal item added
     * 
     * @param item added item
     */
    public void onItemAdded(JournalItem item);
}
