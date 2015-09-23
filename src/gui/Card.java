package gui;


import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import core.ActionType;
import core.CardType;

/**
 * Card GUI representation
 */
public class Card extends JPanel implements MouseMotionListener {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = 6667949149794661529L;

	/**
	 * Parent panel reference
	 */
	private GamePanel parentPanel;

	/**
	 * Card value
	 */
	private int[][] valueMatrix;

	/**
	 * Selection
	 */
	private int rowSelected = -1;
	private int rowFixed    = -1;

	/**
	 * Create a card representation
	 * @param parentPanel The game panel reference
	 * @param valueMatrix The values of the card
	 */
	public Card(final GamePanel parentPanel, final CardType type)  {
		super();

		this.parentPanel = parentPanel;
		this.valueMatrix = core.Card.getCard(type).getValueMatrix();

		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.addMouseMotionListener(this);

		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (rowSelected > -1) {
					rowFixed = rowSelected;
					CardType cType     = type;
					ActionType cAction = ActionType.values()[rowSelected];
					if (cAction == ActionType.HOBGOBLIN) {
						parentPanel.chooseTarget();
						JOptionPane.showMessageDialog(parentPanel, "Veuillez choisir une cible");
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				rowSelected = -1;
				repaint();
				revalidate();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
		});

		int startX = 30;
		int stepX  = 15;
		int startY = 38;
		int stepY  = 20;
		for (int i = 0; i < this.valueMatrix.length; i++) {
			for (int j = 0; j < valueMatrix[i].length; j++) {
				JLabel l = new JLabel(String.valueOf(this.valueMatrix[i][j]));

				if (parentPanel.getGame().getActualSeason().ordinal() == j) {
					l.setForeground(Color.black);
				} else {
					l.setForeground(Color.gray);
				}

				this.addAbsolute(l, startX + j * stepX, startY + i * stepY);
			}
		}
	}

	/**
	 * Remove fixed row border (escape pressed)
	 */
	public void clearRowFixed() {
		this.rowFixed = -1;
	}

	/**
	 * Detect mouse position and update selection rect
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		if (this.parentPanel.choosingTarget) {
			return;
		}

		if (e.getY() >= 38 && e.getY() < 58) {
			this.rowSelected = 0;
		} else if (e.getY() >= 58 && e.getY() < 78) {
			this.rowSelected = 1;
		} else if (e.getY() >= 78 && e.getY() < 98) {
			this.rowSelected = 2;
		} else {
			this.rowSelected = -1;
		}

		repaint();
		revalidate();
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
	 * Draw the card
	 */
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
	    RenderingHints rh = new RenderingHints(
	             RenderingHints.KEY_TEXT_ANTIALIASING,
	             RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);

	    // Draw background first
	    g2.setColor(getBackground());
	    g2.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

		g2.setColor(Color.black);
		g2.drawRect(0, 0, 95, 95);

		int opacity = (this.parentPanel.choosingTarget) ? 100 : 255;
		g2.setColor(new Color(255, 255, 255, opacity));
		g2.fillRect(1, 1, 94, 94);

		g2.setColor(Color.black);
		g2.drawString("G", 10, 45);
		g2.drawString("F", 10, 65);
		g2.drawString("H", 10, 85);

		g2.drawString("s", 30, 30);
		g2.drawString("S", 45, 30);
		g2.drawString("F", 60, 30);
		g2.drawString("W", 75, 30);

		if (this.rowSelected >= 0) {
			g.setColor(Color.red);

			int startY = 33;
			g2.drawRect(5, startY + this.rowSelected * 19, 80, 15);
		}

		if (this.rowFixed >= 0) {
			g.setColor(Color.red);

			int startY = 33;
			g2.drawRect(5, startY + this.rowFixed * 19, 80, 15);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {}
}
