/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.journal;

import com.googlecode.lanterna.gui.TextGraphics;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface JournalItem {
    
    public void paint(TextGraphics graphics, boolean hightlight);
}
