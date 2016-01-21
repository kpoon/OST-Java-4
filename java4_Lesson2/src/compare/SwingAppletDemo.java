package compare;

import javax.swing.*;
import java.awt.*;

public class SwingAppletDemo extends JApplet {
	public void init() {
		//Execute a job on the event-dispatching thread:
		//creating this applet's GUI
		try {
			javax.swing.SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					createGUI();
				}
			});
		} catch (Exception e) {
			System.err.println("createGUI didn't finish successfully");
		}
	}
	
	private void createGUI() {
		JLabel label = new JLabel("You are successfully running a Swing applet!");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
		getContentPane().add(label, BorderLayout.CENTER);
	}
}
