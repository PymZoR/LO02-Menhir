package gui;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import core.Game;

public class InitPanel extends JPanel {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = 5634460867122728913L;

	/**
	 * Combo-box choices
	 */
	private String[] playerChoices = { "2 joueurs", "3 joueurs", "4 joueurs", "5 joueurs", "6 joueurs" };
	private String[] iaChoices = { "1 ordinateur" };

	/**
	 * Panel components
	 */
	private JLabel numberOfPlayersLabel       = new JLabel("Nombre de joueurs :");
	private JLabel numberOfIAsLabel           = new JLabel("Nombre d'ordinateurs :");
	private JComboBox<String> numberOfPlayers = new JComboBox<String>(playerChoices);
	private JComboBox<String> numberOfIAs     = new JComboBox<String>(iaChoices);
	private JToggleButton rapidGameButton     = new JToggleButton("Partie rapide");

	/**
	 * Parent window
	 */
	private MainWindow parentWindow;

	/**
	 * Create the panel
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
		numberOfPlayers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				numberOfIAs.removeAllItems();

				int players = numberOfPlayers.getSelectedIndex() + 2;
				for (int i = 1; i < players; i++) {
					if (i == 1) {
						numberOfIAs.addItem(Integer.toString(i) + " ordinateur");
					} else {
						numberOfIAs.addItem(Integer.toString(i) + " ordinateurs");
					}
				}
			}
		});

		// On game start
		JButton validateButton = new JButton("Valider");
		validateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int players       = (numberOfPlayers.getSelectedIndex() + 2);
				boolean rapidGame = rapidGameButton.isSelected();

				Game game = null;
				try {
					game = new Game(players);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				parentWindow.setGame(game);
				parentWindow.switchPanel("GamePanel", 800, 600);
			}
		});
		this.add(validateButton);
	}
}
