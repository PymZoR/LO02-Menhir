package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JPanel;

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
	Card card1 = null;
	Card card2 = null;
	Card card3 = null;
	Card card4 = null;

	/**
	 * Player reference
	 */
	Player player;

	/**
	 * Parent window
	 */
	private MainWindow parentWindow;

	/**
	 * Create the panel
	 */
	public GamePanel(MainWindow parentWindow) {
		this.parentWindow = parentWindow;
		this.setLayout(null);
		this.setPreferredSize(this.parentWindow.getSize());

		this.player = this.parentWindow.game.getCurrentPlayer();

		this.card1  = new Card(this.player.getCards().get(0).getValueMatrix());
		this.card2  = new Card(this.player.getCards().get(1).getValueMatrix());
		this.card3  = new Card(this.player.getCards().get(2).getValueMatrix());
		this.card4  = new Card(this.player.getCards().get(3).getValueMatrix());

		this.addAbsolute(this.card1, 10, 10);
		this.addAbsolute(this.card2, 110, 10);
		this.addAbsolute(this.card3, 210, 10);
		this.addAbsolute(this.card4, 310, 10);
	}

	private void addAbsolute(Component c, int x, int y) {
		Insets i       = this.getInsets();
		Dimension size = c.getPreferredSize();

		this.add(c);
		// Correct window viewport
		y -= 5;
		c.setBounds(i.left + x, i.top + y, size.width, size.height);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);

		g2.setColor(Color.black);
		g2.drawLine(420, 5, 420, 100);

	    g2.drawString("Pas de cartes alliées en partie rapide", 430, 20);
	}
}
