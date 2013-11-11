/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.brain;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class BrainsRunner {
    
    private static final ExecutorService executor = Executors.newCachedThreadPool(new ThreadFactoryBuilder().setNameFormat("Brains-%d").build());

    private BrainsRunner() {
        // not instanciable
    }
    
    public static void wakeUp(Brain brain) {
        executor.submit(brain);
    }
}
