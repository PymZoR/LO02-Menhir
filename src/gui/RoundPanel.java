package gui;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import core.ActionType;
import core.Playable;
import core.Player;

/**
 * Round game panel
 */
public class RoundPanel extends AbsoluteJPanel implements ActionListener {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = -8860204251354754377L;

	/**
	 * Panel components
	 */
	protected Vector<Card> cards = new Vector<Card>();
	protected JButton nextRound = new JButton("Jouer !");

	/**
	 * Player field
	 */
	protected Field selfField = null;

	/**
	 * Game information
	 */
	protected JLabel actualSeason  = new JLabel("Saison actuelle : ");
	protected JLabel actualPlayer  = new JLabel();
	protected JLabel totalBigRocks = new JLabel("Score total : -");
	protected JLabel help1	       = new JLabel("Sélectionnez d'abord une carte et une action en cliquant sur la ligne voulue,");
	protected JLabel help2 		   = new JLabel("puis cliquez sur jouer. Appuyez sur Échap pour annuler. Si vous jouez Farfadet,");
	protected JLabel help3 		   = new JLabel("sélectionnez un champ cible avant de jouer.");

	/**
	 * Player reference
	 */
	protected Player player;

	/**
	 * Action management
	 */
	public boolean lockingCards   = false;
	public boolean choosingTarget = false;
	public Field   targetField    = null;

	/**
	 * Parent window
	 */
	protected MainWindow parentWindow;
	protected Playable game;

	/**
	 * Create the panel
	 */
	public RoundPanel(MainWindow parentWindow) {
		this.parentWindow = parentWindow;
		this.game         = parentWindow.getGame();

		this.actualSeason.setText(this.actualSeason.getText() + this.game.getActualSeason().toString());

		// Check for victory
		if (!this.game.isRunning()) {
			System.out.println("OK GAME STOPS");
			Vector<Player> scores = new Vector<Player>(this.game.getPlayers());
			Collections.sort(scores);
			Collections.reverse(scores);

			String message = "Rankings:" + System.lineSeparator();

			for (int i = 0; i < this.game.getPlayerNumber(); i++) {
				core.Field field = scores.get(i).getField();

				message += "    Joueur " + (i+1) + ". Champ: "+ field.getBigRockSum() +
						" menhirs; " + field.getSmallRockSum() + " graines;" + System.lineSeparator();
			}

			JOptionPane.showMessageDialog(this, message, "Partie terminée", JOptionPane.INFORMATION_MESSAGE);

			// Start again
			this.parentWindow.dispose();
			new MainWindow();

			return;
		}

		// Clone, else removing the actual player would change the vector
		@SuppressWarnings("unchecked")
		Vector<Player> players = (Vector<Player>) this.game.getPlayers().clone();
		players.remove(this.game.getCurrentPlayer());

		this.actualPlayer.setText("Joueur actuel : " + String.valueOf(this.game.getCurrentPlayer().getNumber()));

		this.setPreferredSize(this.parentWindow.getSize());

		this.player = this.game.getCurrentPlayer();

		for (int i = 0; i < this.player.getCards().size(); i++) {
			Card c = new Card(this, this.player.getCards().get(i).getType());
			this.cards.add(c);
		}

		this.selfField = new Field(this, this.player, "Votre terrain");

		int startX = 10;
		int stepX  = 100;
		for (int i = 0; i < players.size(); i++) {
			String playerN    = String.valueOf(players.get(i).getNumber() + 1);
			Field playerField = new Field(this, players.get(i), "Joueur " + playerN);
			this.addAbsolute(playerField, startX + (i * stepX), 260);
		}

		startX = 10;
		stepX  = 110;
		for (int i = 0; i < this.cards.size(); i++) {
			this.addAbsolute(this.cards.get(i), startX + (i * stepX), 30);
		}

		this.addAbsolute(this.actualSeason, 10, 10);
		this.addAbsolute(this.selfField, 10, 140);
		this.addAbsolute(this.help1, 120, 145);
		this.addAbsolute(this.help2, 120, 165);
		this.addAbsolute(this.help3, 120, 185);
		this.addAbsolute(this.actualPlayer, 420, 10);
		this.addAbsolute(this.totalBigRocks, 250, 10);
		this.addAbsolute(this.nextRound, 10, 370);

		this.nextRound.addActionListener(this);
	}

	/**
	 * Choose a card
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Give back focus to window (to re handle escapes)
		this.parentWindow.requestFocus();

		Card selectedCard          = null;

		core.Card selectedCoreCard = null;
		Player target              = null;
		ActionType action          = null;

		for (int i = 0; i < this.cards.size(); i++) {
			if (this.cards.get(i).isSelected()) {
				selectedCard = this.cards.get(i);
			}
		}

		if (selectedCard == null) {
			JOptionPane.showMessageDialog(this, "Veuillez choisir une carte", "Attention", JOptionPane.WARNING_MESSAGE);
			return;
		}

		selectedCoreCard = selectedCard.getCard();
		action           = selectedCard.getActionType();

		if ((action == ActionType.HOBGOBLIN) && (this.targetField == null)) {
			JOptionPane.showMessageDialog(this, "Veuillez choisir un adversaire", "Attention", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (action == ActionType.HOBGOBLIN) {
			target = this.targetField.getPlayer();
		}

		this.game.nextTurn(selectedCoreCard, action, target);

		this.goNextTurn();
	}

	/**
	 * Disable cards and self field
	 */
	public void chooseTarget() {
		this.choosingTarget = true;
		this.lockCards();
	}

	/**
	 * Get all cards
	 * @return All four or less cards
	 */
	public Card[] getCards() {
		Card[] arr = new Card[this.cards.size()];
		for (int i = 0; i < this.cards.size(); i++) {
			arr[i] = (this.cards.get(i));
		}
		return arr;
	}

	/**
	 * Get the game reference
	 * @return The game
	 */
	public Playable getGame() {
		return this.game;
	}

	/**
	 * Load the next turn panel
	 */
	protected void goNextTurn() {
		this.parentWindow.switchPanel("RoundPanel", 710, 450);
	}

	/**
	 * Disable cards and fields
	 */
	public void lockCards() {
		this.lockingCards = true;
		this.revalidate();
		this.repaint();
	}

	/**
	 * Draw the panel
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);

		g2.setColor(Color.black);
		g2.drawLine(445, 25, 445, 120);

	    g2.drawString("Pas de cartes alliées en partie rapide", 460, 70);
	}
}
