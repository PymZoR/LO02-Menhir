package gui;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JPanel;

/**
 * Multiple components will use addAbsolute
 */
abstract class AbsoluteJPanel extends JPanel {
    /**
     * Java UID
     */
    private static final long serialVersionUID = 2144318224862621946L;

    /**
     * Set no layout
     */
    public AbsoluteJPanel() {
        super();

        this.setLayout(null);
    }

    /**
     * Add an absolute component to the panel
     *
     * @param c The component
     * @param x X coordinate
     * @param y Y coordinate
     */
    protected void addAbsolute(Component c, int x, int y) {
        Insets i       = this.getInsets();
        Dimension size = c.getPreferredSize();

        this.add(c);
        // Correct window viewport
        y -= 5;
        c.setBounds(i.left + x, i.top + y, size.width, size.height);
    }
}
