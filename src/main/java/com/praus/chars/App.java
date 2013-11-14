package com.praus.chars;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
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
        //Terminal terminal = TerminalFacade.createTextTerminal();
        Terminal terminal = TerminalFacade.createSwingTerminal();
        Screen screen = TerminalFacade.createScreen(terminal);
        screen.startScreen();
        
        GUIScreen textGUI = TerminalFacade.createGUIScreen(screen);
        if(textGUI == null) {
            System.err.println("Couldn't allocate a terminal!");
            return;
        }        
        
        GameWindow window = new GameWindow();
        textGUI.showWindow(window, GUIScreen.Position.FULL_SCREEN);

        screen.stopScreen();
    }
}
