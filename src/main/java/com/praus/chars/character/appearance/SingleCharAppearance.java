/*
 * Copyright (c) 2013 Jiri Praus
 */

package com.praus.chars.character.appearance;

import com.googlecode.lanterna.terminal.Terminal.Color;
import com.praus.chars.gui.view.Pixel;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public abstract class SingleCharAppearance extends Pixel implements Appearance {

    public SingleCharAppearance(char character, Color color, Color backgroundColor) {
        super(color, backgroundColor, character);
    }

    @Override
    public Pixel getIcon() {
        return this;
    }
}
