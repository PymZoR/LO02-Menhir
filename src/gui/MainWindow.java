package gui;


import core.Playable;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * Main window
 */
public class MainWindow extends JDialog {
    /**
     * Java UID
     */
    private static final long serialVersionUID = 3059170629543738819L;

    /**
     * Actual component
     */
    private Component component;

    /**
     * Game reference
     */
    private Playable game;

    /**
     * Stores message data for IA information If we use JOptionPane directly on the IA round, it'll fail the render. So
     * store the message here, and render on the next human round
     */
    private String iaMessage = "";

    /**
     * Create the dialog.
     */
    public MainWindow() {
        super(null, "Menhir", Dialog.ModalityType.MODELESS);

        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        this.switchPanel("InitPanel", 400, 160);

        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 27) {
                    RoundPanel gp = null;

                    switch (MainWindow.this.component.getClass().getName()) {
                        case "gui.RoundPanel":
                            gp = (RoundPanel) MainWindow.this.component;
                            break;
                        case "gui.GamePanel":
                            gp = (GamePanel) MainWindow.this.component;
                            break;
                    }

                    if (gp != null) {
                        gp.lockingCards   = false;
                        gp.choosingTarget = false;
                        gp.targetField    = null;

                        gp.revalidate();
                        gp.repaint();
                        for (Card card : gp.getCards()) {
                            card.clearRowFixed();
                        }

                        if ("gui.GamePanel".equals(gp.getClass().getName())) {
                            if (((GamePanel) gp).getAlliedCard() != null) {
                                ((GamePanel) gp).getAlliedCard().clearRowFixed();
                            }
                        }

                    }
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

        });
    }

    /**
     * Set the next IA message. If there is already a message, append it. If iaMessage is "empty", empty the ia message
     * queue
     *
     * @param iaMessage
     */
    public void addIAMessage(String iaMessage) {
        if ("empty".equals(iaMessage)) {
            this.iaMessage = "";
        } else if ("".equals(this.iaMessage)) {
            this.iaMessage += iaMessage;
        } else {
            this.iaMessage += "\n" + iaMessage;
        }
    }

    /**
     * Get the game reference
     *
     * @return The game
     */
    public Playable getGame() {
        return this.game;
    }

    /**
     * Get the IA Message
     *
     * @return The IA Message
     */
    public String getIAMessage() {
        return this.iaMessage;
    }

    /**
     * Set the game reference
     *
     * @param game The game
     */
    public void setGame(Playable game) {
        this.game = game;
    }

    /**
     * Load a JPanel on the window and change the viewport size
     *
     * @param panelClass The name of the class (prefixed with "gui.")
     * @param w          The new window width
     * @param h          The new window height
     */
    public final void switchPanel(String panelClass, int w, int h) {
        this.setSize(w, h);

        // Using reflect API to instantiate class by name
        Class<?> T                 = null;
        Constructor<?> constructor = null;
        Component c                = null;

        // Find class by name
        try {
            T = Class.forName("gui." + panelClass);
        } catch (ClassNotFoundException eClass) {
            eClass.printStackTrace();
        }

        // Get a constructor with params
        try {
            constructor = T.getConstructor(MainWindow.class);
        } catch (NoSuchMethodException | SecurityException eConstructor) {
            eConstructor.printStackTrace();
        }

        // Instantiate with the constructor
        try {
            c = (Component) constructor.newInstance(this);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException eInstance) {
            eInstance.printStackTrace();
        }

        JPanel jp = new JPanel();
        jp.add(c);

        this.setContentPane(jp);
        this.component = c;

        this.revalidate();
        this.repaint();

        this.setLocationRelativeTo(null);
    }

}
