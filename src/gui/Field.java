package gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import core.Playable;
import core.Player;

public class Field extends AbsoluteJPanel implements MouseListener {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = 3180287191592167877L;

	/**
	 * URLs
	 */
	private static URL rockURL     = Field.class.getResource("/images/rock.png");
	private static URL menhirURL   = Field.class.getResource("/images/menhir.png");
	private static URL computerURL = Field.class.getResource("/images/computer.png");
	/**
	 * Big and small rocks amounts
	 */
	private int bigRockNumber   = 0;

	private int smallRockNumber = 0;
	private String playerName;

	/**
	 * Parent panel reference
	 */
	private RoundPanel parentPanel;
	private Playable game;

	/**
	 * Player
	 */
	private Player player;
	private boolean isSelfPlayer;

	/**
	 * Create a field
	 * @param parentPanel Parent reference
	 * @param playerName Player name
	 */
	public Field(final RoundPanel parentPanel, Player player, String playerName)  {
		super();

		this.game            = parentPanel.getGame();
		this.parentPanel     = parentPanel;
		this.playerName      = playerName;
		this.player			 = player;
		this.bigRockNumber   = player.getField().getBigRockNumber();
		this.smallRockNumber = player.getField().getSmallRockNumber();

		this.isSelfPlayer = (this.game.getCurrentPlayer() == player);

		this.setPreferredSize(new Dimension(100, 100));

		if ((Field.rockURL != null) && (Field.menhirURL != null) && (Field.computerURL != null)) {
			ImageIcon rockImage     = new ImageIcon(Field.rockURL);
			ImageIcon menhirImage   = new ImageIcon(Field.menhirURL);
			ImageIcon computerImage = new ImageIcon(Field.computerURL);

			JLabel rock     = new JLabel(String.valueOf(this.smallRockNumber), rockImage, JLabel.LEFT);
			JLabel menhir   = new JLabel(String.valueOf(this.bigRockNumber), menhirImage, JLabel.LEFT);
			JLabel computer = new JLabel(computerImage);

			this.addAbsolute(rock, 22, 30);
			this.addAbsolute(menhir, 22, 60);

			if (this.player.ia() != null) {
				this.addAbsolute(computer, 70, 10);
			}
		}

		if (!this.isSelfPlayer) {
			this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}

		this.addMouseListener(this);
	}

	/**
	 * Get the player that owns this field
	 * @return The player
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Mouse events
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.parentPanel.choosingTarget && !this.isSelfPlayer) {
			this.parentPanel.targetField = this;
			this.parentPanel.revalidate();
			this.parentPanel.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}

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

	    // Draw background first
	    g2.setColor(this.getBackground());
	    g2.fillRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);

	    if (this.parentPanel.targetField == this) {
	    	g2.setColor(Color.red);
	    	g2.drawRect(0, 0, 99, 99);
	    }

	    int opacity = 100;
	    if (this.parentPanel.choosingTarget) {
	    	if (!this.isSelfPlayer) {
	    		opacity = 255;
	    	}
	    } else {
	    	if (this.isSelfPlayer) {
	    		opacity = 255;
	    	}
	    }
		g2.setColor(new Color(255, 255, 255, opacity));
	    g2.fillRect(1, 1, 98, 98);

	    g2.setColor(new Color(0, 0, 0, opacity));
	    g2.drawString(this.playerName, 10, 20);
	}
}
