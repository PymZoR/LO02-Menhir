package gui;


import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * GUI-based game
 */
public class GUIGame {
    /**
     * Start the game; gui based
     */
    public GUIGame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException |
                 ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainWindow();
        });
    }
}
