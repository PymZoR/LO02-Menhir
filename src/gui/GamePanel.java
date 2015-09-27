package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import core.Game;

/**
 * Full game GUI
 */
public class GamePanel extends RoundPanel {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = -8860204251354754377L;

	/**
	 * Panel components
	 */
	private JLabel roundLabel = new JLabel();

	/**
	 * Create the same window that RoundPanel
	 */
	public GamePanel(MainWindow parentWindow) {
		super(parentWindow);

		this.remove(this.totalBigRocks);
		this.remove(this.totalBigRocks);

		int totalScore = this.game.getCurrentPlayer().getField().getBigRockSum();
		this.totalBigRocks = new JLabel("Score total : " + String.valueOf(totalScore));

		String thisRound = String.valueOf((((Game)this.game).getCurrentRound()).getNumber() + 1);
		String maxRounds = String.valueOf(this.game.getPlayerNumber());
		this.roundLabel.setText("Manche actuel : " + thisRound + "/" + maxRounds);

		this.addAbsolute(this.totalBigRocks, 250, 10);
		this.addAbsolute(this.roundLabel, 550, 10);
	}

	/**
	 * Load the next turn panel
	 */
	@Override
	protected void goNextTurn() {
		this.parentWindow.switchPanel("GamePanel", 710, 450);
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
	}
}
