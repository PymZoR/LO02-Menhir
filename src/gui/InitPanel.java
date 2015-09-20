package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class InitPanel extends JPanel {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = 5634460867122728913L;

	JLabel numberOfPlayersLabel   = new JLabel("Nombre de joueurs :");
	JTextField numberOfPlayers    = new JTextField("0", 10);
	JToggleButton rapidGameButton = new JToggleButton("Partie rapide");

	MainWindow parentWindow;

	/**
	 * Create the panel.
	 */
	public InitPanel(MainWindow parent) {
		this.parentWindow = parent;

		GridLayout gl = new GridLayout(2, 2, 10, 10);
		this.setLayout(gl);

		this.add(this.numberOfPlayersLabel);
		this.add(this.numberOfPlayers);
		this.add(this.rapidGameButton);

		JButton validateButton = new JButton("Valider");
		validateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int players       = Integer.parseInt(numberOfPlayers.getText());
				boolean rapidGame = rapidGameButton.isSelected();
				System.out.print("Go ");
				System.out.print(players);
				System.out.print(" players and rapid: ");
				System.out.println(rapidGame);

			}
		});
		this.add(validateButton);
	}
}
