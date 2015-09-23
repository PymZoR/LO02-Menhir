package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.Playable;

public class Field extends JPanel {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = 3180287191592167877L;

	/**
	 * Big and small rocks amounts
	 */
	private int bigRockNumber   = 0;
	private int smallRockNumber = 0;
	private String playerName;

	/**
	 * Parent panel reference
	 */
	private GamePanel parentPanel;
	private Playable game;

	/**
	 * Create a field
	 * @param parentPanel Parent reference
	 * @param playerName Player name
	 */
	public Field(final GamePanel parentPanel, String playerName)  {
		super();

		this.game            = parentPanel.getGame();
		this.parentPanel     = parentPanel;
		this.playerName      = playerName;
		this.bigRockNumber   = this.game.getCurrentPlayer().getField().getBigRockNumber();
		this.smallRockNumber = this.game.getCurrentPlayer().getField().getSmallRockNumber();

		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));

		ImageIcon rockImage   = new ImageIcon(Field.class.getResource("/images/rock.png"));
		ImageIcon menhirImage = new ImageIcon(Field.class.getResource("/images/menhir.png"));

		JLabel rock   = new JLabel(String.valueOf(this.smallRockNumber), rockImage, JLabel.LEFT);
		JLabel menhir = new JLabel(String.valueOf(this.bigRockNumber), menhirImage, JLabel.LEFT);

		this.addAbsolute(rock, 22, 30);
		this.addAbsolute(menhir, 22, 60);
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
	 * Draw the field
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);

	    g2.setColor(Color.white);
	    g2.fillRect(0, 0, 100, 100);

	    g2.setColor(Color.black);
	    g2.drawString(this.playerName, 10, 20);
	}
}
