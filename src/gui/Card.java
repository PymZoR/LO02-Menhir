package gui;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import core.ActionType;
import core.CardType;

/**
 * Card GUI representation
 */
public class Card extends AbsoluteJPanel implements MouseMotionListener {
    /**
     * Java UID
     */
    private static final long serialVersionUID = 6667949149794661529L;

    /**
     * Parent panel reference
     */
    private RoundPanel parentPanel;

    /**
     * Card value
     */
    private int[][]  valueMatrix;
    private boolean  isAllied;
    private CardType type;

    /**
     * Selection
     */
    private int rowSelected = -1;
    private int rowFixed    = -1;

    /**
     * Create a card representation
     *
     * @param parentPanel
     *            The game panel reference
     * @param valueMatrix
     *            The values of the card
     */
    public Card(final RoundPanel parentPanel, final CardType type) {
        super();

        this.parentPanel = parentPanel;
        this.type = type;
        this.valueMatrix = this.getCard().getValueMatrix();
        this.isAllied = (this.valueMatrix.length == 1);

        this.setPreferredSize(new Dimension(100, 100));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addMouseMotionListener(this);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (Card.this.rowSelected > -1) {
                    Card.this.rowFixed = Card.this.rowSelected;
                    ActionType cAction = ActionType.values()[Card.this.rowSelected];

                    if (cAction == ActionType.HOBGOBLIN) {
                        parentPanel.chooseTarget();
                        JOptionPane.showMessageDialog(parentPanel.parentWindow, "Veuillez choisir une cible");
                    } else {
                        if (!Card.this.isAllied) {
                            parentPanel.lockCards();
                        }
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                Card.this.rowSelected = -1;
                Card.this.repaint();
                Card.this.revalidate();
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });

        int startX = 30;
        int stepX = 15;
        int startY = 38;
        int stepY = 20;
        for (int i = 0; i < this.valueMatrix.length; i++) {
            for (int j = 0; j < this.valueMatrix[i].length; j++) {
                JLabel l = new JLabel(String.valueOf(this.valueMatrix[i][j]));

                if (parentPanel.getGame().getActualSeason().ordinal() == j) {
                    l.setForeground(Color.black);
                } else {
                    l.setForeground(Color.gray);
                }

                this.addAbsolute(l, startX + (j * stepX), startY + (i * stepY));
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
     * Get the selected action type
     *
     * @return The action type
     */
    public ActionType getActionType() {
        return ActionType.values()[this.rowFixed];
    }

    /**
     * Get the core card from the GUI representation
     *
     * @return The core card
     */
    public core.Card getCard() {
        return core.Card.getCard(this.type);
    }

    /**
     * Check if the card is selected
     *
     * @return True if the card is selected
     */
    public boolean isSelected() {
        return this.rowFixed != -1;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Detect mouse position and update selection rect
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.parentPanel.lockingCards && !this.isAllied) {
            return;
        }

        if ((e.getY() >= 38) && (e.getY() < 58)) {
            this.rowSelected = 0;
        } else if ((e.getY() >= 58) && (e.getY() < 78) && !this.isAllied) {
            this.rowSelected = 1;
        } else if ((e.getY() >= 78) && (e.getY() < 98) && !this.isAllied) {
            this.rowSelected = 2;
        } else {
            this.rowSelected = -1;
        }

        this.repaint();
        this.revalidate();
    }

    /**
     * Draw the card
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHints(rh);

        // Draw background first
        g2.setColor(this.getBackground());
        g2.fillRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);

        g2.setColor(Color.black);
        g2.drawRect(0, 0, 95, 95);

        int opacity = (this.parentPanel.lockingCards && !this.isAllied) ? 100 : 255;
        g2.setColor(new Color(255, 255, 255, opacity));
        g2.fillRect(1, 1, 94, 94);

        g2.setColor(Color.black);

        if (!this.isAllied) {
            g2.drawString("G", 10, 45);
            g2.drawString("F", 10, 65);
            g2.drawString("H", 10, 85);
        } else {
            switch (this.type) {
                case TAUPE1:
                case TAUPE2:
                case TAUPE3:
                    g2.drawString("T", 10, 45);
                    break;
                default:
                    g2.drawString("D", 10, 45);
                    break;
            }
        }

        g2.drawString("s", 30, 30);
        g2.drawString("S", 45, 30);
        g2.drawString("F", 60, 30);
        g2.drawString("W", 75, 30);

        if (this.rowSelected >= 0) {
            g.setColor(Color.red);

            int startY = 33;
            g2.drawRect(5, startY + (this.rowSelected * 19), 80, 15);
        }

        if (this.rowFixed >= 0) {
            g.setColor(Color.red);

            int startY = 33;
            g2.drawRect(5, startY + (this.rowFixed * 19), 80, 15);
        }
    }
}
