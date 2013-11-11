package com.praus.chars;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.praus.chars.gui.GameWindow;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {  
        // initialization
        Globals.start();
        
        // main method
        run();        
        
        // closing
        Globals.stop();
    }
    
    public static void run() {
        GUIScreen textGUI = TerminalFacade.createGUIScreen();
        if(textGUI == null) {
            System.err.println("Couldn't allocate a terminal!");
            return;
        }
        textGUI.getScreen().startScreen();
        
        GameWindow window = new GameWindow();
        textGUI.showWindow(window, GUIScreen.Position.FULL_SCREEN);

        textGUI.getScreen().stopScreen();
    }
}
