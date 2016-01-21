package salesGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

public class InputPanel extends JPanel implements ActionListener {
	JPanel topPanel, middlePanel, bottomPanel, leftPanel, rightPanel;
	SalesApp app;
	JLabel prompt, doneLabel, jlSalesBar;
	JLabel[] jlSales;
	JButton done;
	JTextField[] jtfSales;
	JTextField jtfSalesBar;
	int numPeople;
	int[] sales;
	int goal;

	public InputPanel(SalesApp container, int numPeople, int gridX) {
		this.app = container;
		this.numPeople = numPeople;
		sales = new int[numPeople];
		this.setLayout(new BorderLayout());
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		middlePanel = new JPanel(new GridLayout(numPeople, gridX));
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		add("North", topPanel);
		add("Center", middlePanel);
		add("South", bottomPanel);
		add("East", rightPanel);
		add("West", leftPanel);
		jlSales = new JLabel[numPeople];
		jtfSales = new JTextField[numPeople];
		prompt = new JLabel("Give values for each salesperson:");
		topPanel.add(prompt);

		for (int x = 0; x < numPeople; x++) {
			jlSales[x] = new JLabel("Sales Person " + (x + 1));
			jtfSales[x] = new JTextField("0", 8);
			middlePanel.add(jlSales[x]);
			middlePanel.add(jtfSales[x]);
		}
		jlSalesBar = new JLabel("Enter a value for the sales goal");
		bottomPanel.add(jlSalesBar);
		jtfSalesBar = new JTextField("0", 8);
		bottomPanel.add(jtfSalesBar);
		doneLabel = new JLabel("Click when all are entered:");
		bottomPanel.add(doneLabel);
		done = new JButton("All Set");
		bottomPanel.add(done);
		done.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() instanceof JButton) {
			if ((JButton) event.getSource() == done) {
				for (int x = 0; x < numPeople; x++) {
					try {
						sales[x] = Integer.parseInt(jtfSales[x].getText()); // throws NumberFormatException 

						// if number is less than or equal to 0 ask user to
						// enter a valid number
						if (sales[x] <= 0) {
							String temp = JOptionPane
									.showInputDialog("Invalid entry for sales person "
											+ (x + 1)
											+ " \n Enter a whole number greater than zero.");
							sales[x] = Integer.parseInt(temp);
							jtfSales[x].setText(Integer.toString(sales[x]));
						}

					} catch (NumberFormatException e) {
						//if value contains letters then it's not a valid number
						if (Pattern.matches("[a-zA-Z]+", jtfSales[x].getText()) == true) {
							//set value to 0
							String line1 = "Input must be a valid whole number.\n ";
							String line2 = "Your value "
								+ jtfSales[x].getText() + " for Sales Person "
								+ (x + 1) + " will be zeroed.\n ";
							String line3 = "You may enter a different integer.";
							JOptionPane.showMessageDialog(this, line1 + line2
								+ line3, "Input Error - sales",
								JOptionPane.ERROR_MESSAGE);
							
							sales[x] = 0;
							jtfSales[x].setText(Integer.toString(sales[x]));
						} else {				
							String line1 = "Input must be whole numbers.\n ";
							String line2 = "Your decimal value "
								+ jtfSales[x].getText() + " for Sales Person "
								+ (x + 1) + " will be truncated.\n ";
							String line3 = "You may enter a different integer.";
							JOptionPane.showMessageDialog(this, line1 + line2
								+ line3, "Input Error - sales",
								JOptionPane.ERROR_MESSAGE);
						
							// if decimal is entered, truncate the number to Int
							sales[x] = (int) Double.parseDouble(jtfSales[x]
								.getText());
							jtfSales[x].setText(Integer.toString(sales[x]));
						}
						
						if (sales[x] <= 0) {
							String temp = JOptionPane
									.showInputDialog("Invalid entry for sales person "
											+ (x + 1)
											+ " \n Enter a whole number greater than zero.");
							sales[x] = Integer.parseInt(temp);
							jtfSales[x].setText(Integer.toString(sales[x]));
						}
					}
				}
				app.setSales(sales);
				goal = Integer.parseInt(jtfSalesBar.getText());
				app.setSalesBar(goal);
			}
		}
	}
}
