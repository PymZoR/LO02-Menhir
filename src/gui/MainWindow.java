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
		this.setSize(400, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);

		this.switchPanel("InitPanel");
	}

	public void switchPanel(String panelClass) {
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

		// Instanciate with the constructor
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
