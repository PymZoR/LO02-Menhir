package gui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import core.Game;
import core.Playable;
import core.Round;

public class InitPanel extends JPanel {
    /**
     * Java UID
     */
    private static final long serialVersionUID = 5634460867122728913L;

    /**
     * Combo-box choices
     */
    private final String[] playerChoices = {"2 joueurs", "3 joueurs", "4 joueurs", "5 joueurs", "6 joueurs"};
    private final String[] iaChoices     = {"0 ordinateur", "1 ordinateur"};

    /**
     * Panel components
     */
    private final JLabel numberOfPlayersLabel = new JLabel("Nombre de joueurs :");
    private final JLabel numberOfIAsLabel     = new JLabel("Nombre d'ordinateurs :");
    private JComboBox<String> numberOfPlayers = new JComboBox<>(this.playerChoices);
    private JComboBox<String> numberOfIAs     = new JComboBox<>(this.iaChoices);
    private JToggleButton rapidGameButton     = new JToggleButton("Partie rapide");

    /**
     * Parent window
     */
    private MainWindow parentWindow;

    /**
     * Create the panel
     * @param parent Parent window reference
     */
    public InitPanel(MainWindow parent) {
        this.parentWindow = parent;

        GridLayout gl = new GridLayout(3, 3, 10, 10);
        this.setLayout(gl);

        this.add(this.numberOfPlayersLabel);
        this.add(this.numberOfPlayers);
        this.add(this.numberOfIAsLabel);
        this.add(this.numberOfIAs);
        this.add(this.rapidGameButton);

        // On number of players choice => update number of computer choices
        this.numberOfPlayers.addActionListener((ActionEvent e) -> {
            InitPanel.this.numberOfIAs.removeAllItems();
            
            int players = InitPanel.this.numberOfPlayers.getSelectedIndex() + 2;
            for (int i = 0; i < players; i++) {
                if (i <= 1) {
                    InitPanel.this.numberOfIAs.addItem(Integer.toString(i) + " ordinateur");
                } else {
                    InitPanel.this.numberOfIAs.addItem(Integer.toString(i) + " ordinateurs");
                }
            }
        });

        // On game start
        JButton validateButton = new JButton("Valider");
        validateButton.addActionListener((ActionEvent e) -> {
            int players       = InitPanel.this.numberOfPlayers.getSelectedIndex() + 2;
            int iaPlayers     = InitPanel.this.numberOfIAs.getSelectedIndex();
            boolean rapidGame = InitPanel.this.rapidGameButton.isSelected();
            
            try {
                Playable game;
                
                if (rapidGame) {
                    game = new Round(players, iaPlayers);
                    
                    game.start();
                    InitPanel.this.parentWindow.setGame(game);
                    
                    InitPanel.this.parentWindow.switchPanel("RoundPanel", 710, 450);
                } else {
                    game = new Game(players, iaPlayers);
                    
                    game.start();
                    InitPanel.this.parentWindow.setGame(game);
                    
                    InitPanel.this.parentWindow.switchPanel("GamePanel", 710, 450);
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        });
        this.add(validateButton);
    }
}
