package gui;

import java.awt.Component;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class MainWindow extends JDialog {
	/**
	 * Java UID
	 */
	private static final long serialVersionUID = 3059170629543738819L;

	/**
	 * Create the dialog.
	 */
	public MainWindow() {
		this.setTitle("Menhir");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		this.switchPanel("InitPanel", 400, 100);
	}

	/**
	 * Load a JPanel on the window and change the viewport size
	 * @param panelClass The name of the class (prefixed with "gui.")
	 * @param w The new window width
	 * @param h The new window height
	 */
	public void switchPanel(String panelClass, int w, int h) {
		this.setSize(w, h);

		// Using reflect API to instantiate class by name
		Class<?> T                 = null;
		Constructor<?> constructor = null;
		Component c                = null;

		// Find class by name
		try {
			T = Class.forName("gui." + panelClass);
		} catch (ClassNotFoundException eClass) {
			eClass.printStackTrace();
		}

		// Get a constructor with params
		try {
			constructor = T.getConstructor(MainWindow.class);
		} catch (NoSuchMethodException | SecurityException eConstructor) {
			eConstructor.printStackTrace();
		}

		// Instantiate with the constructor
		try {
			c = (Component) constructor.newInstance(this);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException eInstance) {
			eInstance.printStackTrace();
		}

		JPanel jp = new JPanel();
		jp.add(c);

		this.setContentPane(jp);
	}

}
