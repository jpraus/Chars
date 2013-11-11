/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.brain;

import com.praus.chars.journal.Journal;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Brain implements Runnable {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Brain.class);
    
    private boolean isAwake = false;
    
    public final void run() {
        while (isAwake) {
            Journal.addItem("Brain is running");
            sleepSilently(1000);
        }
    }
    
    private void sleepSilently(long millis) {
        try {
            Thread.sleep(millis);
        } 
        catch (InterruptedException ex) {
            logger.debug("Brain failed to sleep", ex);
        }
    }
    
    public synchronized void wakeUp() {
        if (!isAwake) {
            BrainsRunner.wakeUp(this);
            isAwake = true;
        }
    }
    
    public void fallAsleep() {
        if (isAwake) {
            isAwake = false;
        }
    }    
}
