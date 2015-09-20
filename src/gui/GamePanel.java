package gui;


import javax.swing.JPanel;

/**
 * Game panel
 */
public class GamePanel extends JPanel {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = -8860204251354754377L;

	/**
	 * Parent window
	 */
	private MainWindow parentWindow;

	/**
	 * Create the panel
	 */
	public GamePanel(MainWindow parentWindow) {
		this.parentWindow = parentWindow;
	}

}
