import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import java.awt.Dimension;
import java.awt.GridLayout;

public class JtableDemo extends JPanel {

	public JtableDemo() {
		super(new GridLayout(1, 0));

		String[] columnNames = { "First Name", "Last Name", "Birthday",
				"Phone", "Address", "City", "Zip" };

		Object[][] data = {
				{ "Zoe", "Smith", "04/19/2000", "303-368-7896",
						"123 Sesame Street", "Elmo", "85963" },
				{ "Olivia", "Smith", "07/15/2001", "364-896-7852",
						"111 Crystal Palace", "Fluttershy", "78596" },
				{ "Nina", "Smith", "08/23/1985", "785-986-7421",
						"485 Strawberry Lane", "Shortcake", "78536" },
				{ "Vickie", "Smith", "12/02/1975", "365-968-7852",
						"785 McStuffin Street", "Doc", "63978" },
				{ "Steven", "Smith", "06/20/2003", "963-896-7852",
						"156 Piggie Circle", "Pink", "36785" } };

		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		TableColumn column = null;
		for (int i = 0; i < 7; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 4) {
				column.setPreferredWidth(100); // fifth column is bigger
			} else {
				column.setPreferredWidth(50);
			}
		}

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Add the scroll pane to this panel.
		add(scrollPane);
	}

	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("JTableDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JtableDemo newContentPane = new JtableDemo();
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
		frame.setSize(800, 300);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
