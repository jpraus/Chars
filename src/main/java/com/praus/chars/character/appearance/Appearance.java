/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.appearance;

import com.googlecode.lanterna.gui.TextGraphics;
import com.praus.chars.gui.view.Pixel;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public interface Appearance {

    public Pixel getIcon();
    
    public void paint(TextGraphics graphics, int column, int row);
    
}
