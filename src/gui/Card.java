package gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * Card GUI representation
 */
public class Card extends JPanel {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = 6667949149794661529L;

	private int[][] valueMatrix;

	public Card(int[][] valueMatrix) {
		super();

		this.valueMatrix = valueMatrix;

		this.setPreferredSize(new Dimension(100, 100));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);

		g2.setColor(Color.black);
		g2.drawRect(0, 0, 95, 95);

		g2.setColor(Color.white);
		g2.fillRect(1, 1, 94, 94);

		g2.setColor(Color.black);
		g2.drawString("G", 10, 45);
		g2.drawString("F", 10, 65);
		g2.drawString("H", 10, 85);

		g2.drawString("s", 30, 30);
		g2.drawString("S", 45, 30);
		g2.drawString("F", 60, 30);
		g2.drawString("W", 75, 30);

		int startX = 30;
		int stepX  = 15;
		int startY = 45;
		int stepY  = 20;
		for (int i = 0; i < this.valueMatrix.length; i++) {
			for (int j = 0; j < valueMatrix[i].length; j++) {
				g2.drawString(String.valueOf(this.valueMatrix[i][j]), startX + j * stepX, startY + i * stepY);
			}
		}
	}
}
