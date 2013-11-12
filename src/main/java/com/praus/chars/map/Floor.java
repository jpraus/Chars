/*
 * Copyright (c) 2013 Ji?� Praus
 */
package com.praus.chars.map;

import com.googlecode.lanterna.gui.TextGraphics;
import com.googlecode.lanterna.terminal.TerminalPosition;
import com.praus.chars.Globals;
import com.praus.chars.ListenerList;
import com.praus.chars.character.Character;
import com.praus.chars.character.Characters;
import com.praus.chars.character.Placeable;
import com.praus.chars.character.PlaceableListener;
import com.praus.chars.character.monster.Zombie;
import com.praus.chars.character.player.Player;

/**
 *
 * @author Jiří Praus <jpraus@kerio.com>
 */
public class Floor implements PlaceableListener {

    private final ListenerList<FloorListener> listeners = new ListenerList<FloorListener>();
    
    private final int columns;
	private final int rows;

    private final Tiles tiles;
    private final Characters characters;

    public Floor(int rows, int columns) {
		this.columns = columns;
        this.rows = rows;        

        // map
        this.tiles = new Tiles(columns, rows);

        // monsters
        this.characters = new Characters();
    }
    
    public final void populate() {
        characters.add(new Zombie(this, 10, 10));
        //characters.add(new Zombie(this, 40, 2));
        //characters.add(new Zombie(this, 60, 18));
        //characters.add(new Ghoul(this, 15, 8));
        //characters.add(new Ghoul(this, 60, 16));
    }

    public String getTitle() {
        return "Cave of Great Testing";
    }

    public Characters getCharacters() {
        return characters;
    }

    @Deprecated
    public Tiles getTiles() {
        return tiles;
    }

    public boolean isBlocked(int column, int row) {
        if (tiles.isBlocked(column, row)) {
            return true;
        }
        for (Character character : characters) {
            if (character.getPosition().getColumn() == column && character.getPosition().getRow() == row) {
                return true; // monster is on spot
            }
        }
        Player player = Globals.player();
        if (player.getPosition().getColumn() == column && player.getPosition().getRow() == row) {
            return true; // cannot move on player itself
        }
        return false;
    }

    public TextGraphics repaint(int offsetColumn, int offsetRow, TextGraphics graphics) {
        tiles.repaint(offsetColumn, offsetRow, graphics);
        
        // print all characters on floor
        for (Character character : characters) {
            TerminalPosition position = character.getPosition();
            character.getAppearance().paint(graphics, offsetColumn + position.getColumn(), offsetRow + position.getRow());
        }
        
        // print player
        Player player = Globals.player();
        if (this == player.getFloor()) {
            TerminalPosition position = player.getPosition();
            player.getAppearance().paint(graphics, offsetColumn + position.getColumn(), offsetRow + position.getRow());
        }

        return graphics;
    }

	public int getColumns() {
		return columns;
	}

	public int getRows() {
		return rows;
	}
    
    public ListenerList<FloorListener> listener() {
        return listeners;
    }

    @Override
    public void onAppearanceChanged(Placeable placeable) {
        notifyListenersChanged();
    }

    @Override
    public void onMoved(Placeable placeable) {
        notifyListenersChanged();
    }
    
    public void notifyListenersChanged() {
        for (FloorListener listener : listeners) {
            listener.onChanged(this);
        }
    }
}
