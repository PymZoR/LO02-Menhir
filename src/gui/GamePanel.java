package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Playable;
import core.Player;

/**
 * Game panel
 */
public class GamePanel extends JPanel {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = -8860204251354754377L;

	/**
	 * Panel components
	 */
	private Card card1 = null;
	private Card card2 = null;
	private Card card3 = null;
	private Card card4 = null;

	/**
	 * Player field
	 */
	private Field selfField = null;

	/**
	 * Game information
	 */
	private JLabel actualSeason  = new JLabel("Saison actuelle : Printemps");
	private JLabel totalBigRocks = new JLabel("Score total : 0");

	/**
	 * Player reference
	 */
	private Player player;

	/**
	 * Action management
	 */
	public boolean choosingTarget = false;

	/**
	 * Parent window
	 */
	private MainWindow parentWindow;
	private Playable game;

	/**
	 * Create the panel
	 */
	public GamePanel(MainWindow parentWindow) {
		this.parentWindow = parentWindow;
		this.game         = parentWindow.getGame();

		this.setLayout(null);
		this.setPreferredSize(this.parentWindow.getSize());

		this.player = this.game.getCurrentPlayer();

		this.card1 = new Card(this, this.player.getCards().get(0).getType());
		this.card2 = new Card(this, this.player.getCards().get(1).getType());
		this.card3 = new Card(this, this.player.getCards().get(2).getType());
		this.card4 = new Card(this, this.player.getCards().get(3).getType());

		this.selfField = new Field(this, "Votre terrain");

		this.addAbsolute(this.card1, 10, 30);
		this.addAbsolute(this.card2, 120, 30);
		this.addAbsolute(this.card3, 230, 30);
		this.addAbsolute(this.card4, 340, 30);
		this.addAbsolute(this.actualSeason, 10, 10);
		this.addAbsolute(this.selfField, 10, 140);
		this.addAbsolute(this.totalBigRocks, 350, 10);
	}

	/**
	 * Add an absolute component to the panel
	 * @param c The component
	 * @param x X coordinate
	 * @param y Y coordinate
	 */
	private void addAbsolute(Component c, int x, int y) {
		Insets i       = this.getInsets();
		Dimension size = c.getPreferredSize();

		this.add(c);
		// Correct window viewport
		y -= 5;
		c.setBounds(i.left + x, i.top + y, size.width, size.height);
	}

	/**
	 * Get the game reference
	 * @return The game
	 */
	public Playable getGame() {
		return this.game;
	}

	/**
	 * Disable cards and fields
	 */
	public void chooseTarget() {
		this.choosingTarget = true;
		revalidate();
		repaint();
	}

	/**
	 * Get all cards
	 * @return All four cards
	 */
	public Card[] getCards() {
		Card[] cards = { this.card1, this.card2, this.card3, this.card4 };
		return cards;
	}

	/**
	 * Choose a card
	 */
	public void chooseCard() {

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
