package salesGUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SalesUserInterface extends JFrame {
	SalesApp app;
	JMenuBar mb;
	JMenu m, m1;
	JMenuItem q, r, s, t, k;
	InputPanel inputPanel;
	JLabel peopleLabel;
	JTextField peopleField;
	JButton jbNumPeople, done;
	int numPeople;
	OutputPanel results, minmax;
	boolean processed = false;

	public SalesUserInterface(SalesApp myApp) {
		app = myApp;
		app.setMyUserInterface(this);
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600, 600));
		mb = new JMenuBar();
		setJMenuBar(mb);
		m = new JMenu("File");
		m1 = new JMenu("Options");
		mb.add(m);
		mb.add(m1);
		m.add(q = new JMenuItem("Exit"));
		q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		m1.add(t = new JMenuItem("Results"));
		t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (processed) {
					remove(minmax);
				}
				results = new OutputPanel(app);
				add("South", results);
				processed = true;
				results.writeOutput();
			}
		});
		
		m1.add(k = new JMenuItem("MinMax"));
		k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (processed) {
					remove(results);
				}
				minmax = new OutputPanel(app);
				add("South", minmax);
				processed = true;
				minmax.writeMinMaxOutput();
			}
		});
		
		InitPanel specifyNumber = new InitPanel();
		add("North", specifyNumber);
		pack();
		setVisible(true);
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
				remove(inputPanel);
				app = new SalesApp();
			}
			try {
				numPeople = Integer.parseInt(peopleField.getText());
				inputPanel = new InputPanel(app, numPeople, 2);
				add("Center", inputPanel);
				SalesUserInterface.this.validate();
			}
			catch (NumberFormatException e) {
				String messageLine1 = "Invalid entry for number of Sales People.\n ";
				String messageLine2 = "Input must be a whole number.\n ";		
				JOptionPane.showMessageDialog(inputPanel, messageLine1+messageLine2,"Input Error", JOptionPane.ERROR_MESSAGE);
				
				numPeople = 0;
				peopleField.setText(Integer.toString(numPeople));
			}
		}
	}
}
