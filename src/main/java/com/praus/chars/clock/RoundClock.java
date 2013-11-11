/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.clock;

import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class RoundClock {
    
    private static final Logger logger = LoggerFactory.getLogger(RoundClock.class);
    
    private final static long ROUND_MILLIS = 200;

    private final List<RoundClockListener> listeners = new LinkedList<RoundClockListener>();
    
    public Thread thread = null;
    public boolean running = false;
    
    public synchronized void start() {
        if (!running) {
            if (!listeners.isEmpty()) {
                startThread();
            }
            running = true;
        }
    }
    
    public synchronized void stop() {
        if (running) {
            running = false;
        }
    }
    
    public synchronized void register(RoundClockListener listener) {
        if (running) {
            if (!listeners.contains(listener)) {
                listeners.add(listener);
                startThread();
            }
        }
    }
    
    public synchronized void unregister(RoundClockListener listener) {
        listeners.remove(listener);
    }
    
    private synchronized void startThread() {
        if (running && thread == null) {
            thread = new Thread(new Runnable() {
                public void run() {
                    while (running) {
                        try {
                            Thread.sleep(ROUND_MILLIS);
                        }
                        catch (InterruptedException ex) {
                        }
                        if (running) {
                            for (RoundClockListener listener : listeners) {
                                listener.onRoundTick();
                            }
                            logger.debug("Round tick performed");
                        }
                    }
                }
            });
            thread.start();
        }
    }
}
