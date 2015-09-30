package launcher;


import java.io.Console;

import console.ConsoleGame;
import gui.GUIGame;

/**
 * Application starter
 */
public class Main {
    /**
     * Start the application. Automatically detect whether it is console based
     * or GUI
     * 
     * @param args
     *            Application arguments
     */
    public static void main(String[] args) {
        Console console = System.console();
        if (console != null) {
            new ConsoleGame();
        } else {
            new GUIGame();
        }
    }
}
