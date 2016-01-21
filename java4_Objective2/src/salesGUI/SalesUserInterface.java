package salesGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SalesUserInterface extends JFrame {
	SalesApp app;
	JMenuBar mb;
	JMenu m, m1, d;
	JMenuItem q, r, s, t, a, b, c;
	static InputPanel inputPanel, previousInputPanel;
	JLabel peopleLabel;
	JTextField peopleField;
	JButton jbNumPeople, done;
	int numPeople;
	boolean processed = false;

	public SalesUserInterface(SalesApp myApp) {
		app = myApp;
		app.setMyUserInterface(this);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));
		mb = new JMenuBar();
		setJMenuBar(mb);
		m = new JMenu("File");
		// Create Data as a menu
		d = new JMenu("Data");
		mb.add(m);
		// Add Data to the menu bar
		mb.add(d);
		m.add(q = new JMenuItem("Exit"));
		// Add Reset to the Data menu
		d.add(a = new JMenuItem("Reset"));
		a.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reset();
			}
		});

		// Add Retrieve Previous to the Data menu
		d.add(b = new JMenuItem("Retrieve Previous"));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RetrievePrevious();
			}
		});

		// Add Return to the Data menu
		d.add(c = new JMenuItem("Return"));
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return();
			}
		});

		q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		InitPanel specifyNumber = new InitPanel();
		add("North", specifyNumber);
		pack();
		setVisible(true);
	}

	// Reset form elements
	public void Reset() {
		peopleField.setText(null);
		inputPanel.jtfSalesBar.setText("0");
		inputPanel.removeAll();
		previousInputPanel.removeAll();
		inputPanel.updateUI();
		inputPanel.repaint();
		app = new SalesApp();
	}

	// Retrieve previous form values
	public void RetrievePrevious() {
		if (inputPanel != null && previousInputPanel != null) {
			remove(inputPanel);
			add(previousInputPanel, BorderLayout.CENTER);
			validate();
			repaint();
		}
	}

	// Return to current form values
	public void Return() {
		remove(previousInputPanel);
		add(inputPanel, BorderLayout.CENTER);
		validate();
		repaint();
	}

	private class InitPanel extends JPanel {
		public InitPanel() {
			peopleLabel = new JLabel("Enter the number of sales people");
			add(peopleLabel);
			peopleField = new JTextField(5);
			add(peopleField);
			jbNumPeople = new JButton("Submit");
			add(jbNumPeople);
			jbNumPeople.addActionListener(new NumSalesPeopleListener());
		}
	}

	private class NumSalesPeopleListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (inputPanel != null) {
				previousInputPanel = inputPanel;
				remove(inputPanel);
				app = new SalesApp();
			}
			numPeople = Integer.parseInt(peopleField.getText());
			inputPanel = new InputPanel(app, numPeople, 2);
			add("Center", inputPanel);
			SalesUserInterface.this.validate();
		}
	}
}
