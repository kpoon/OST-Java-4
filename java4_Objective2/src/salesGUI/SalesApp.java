package salesGUI;

public class SalesApp {
	// array to hold sales of current and previous
	private int[] currentSales, previousSales;
	// variable for sales goal (to be established by user)
	private int SalesBar;
	private int totalSales;
	// why not average = totalSales/sales.length; here?
	private double average;
	private int minIndex = 0;
	private int maxIndex = 0;
	SalesUserInterface myUserInterface;

	public void setMyUserInterface(SalesUserInterface myGUI) {
		myUserInterface = myGUI;
	}

	public void setCurrentSales(int[] sales) {
		this.previousSales = currentSales;
		this.currentSales = sales;

		System.out.println("Current Sales:");
		for (int i = 0; i < currentSales.length; i++)
			System.out.println(currentSales[i]);

		setTotalSales();
	}

	public void setPreviousSales(int[] sales) {
		this.previousSales = sales;
		System.out.println("Previous Sales:");
		for (int i = 0; i < sales.length; i++)
			// checking to see if it's working
			System.out.println(sales[i]);
		setTotalSales();
	}

	public void setTotalSales() {
		totalSales = 0;
		for (int x = 0; x < currentSales.length; x++)
			totalSales += currentSales[x];
		setAverage();
	}

	public void setAverage() {
		if (currentSales.length != 0)
			average = (double) (totalSales / currentSales.length);
		System.out.println("totalSales is " + totalSales
				+ " and sales.length is " + currentSales.length
				+ " making average "
				+ ((double) totalSales / currentSales.length));
	}

	public void setSalesBar(int goal) {
		SalesBar = goal;
	}

	public int[] getSales() {
		return currentSales;
	}

	public int[] getPreviousSales() {
		return previousSales;
	}

	public double getAverage() {
		if (currentSales.length != 0)
			// cast so does not truncate int division
			return ((double) totalSales / currentSales.length);
		else
			return average;
	}

	public int getBar() {
		return SalesBar;
	}

	public int getTotalSales() {
		return totalSales;
	}

	public int getMin() {
		return minIndex;
	}

	public int getMax() {
		return maxIndex;
	}

	public void calculateMinMax() {
		int minimum = currentSales[0];
		int maximum = currentSales[0];
		// loop through the sales array to see each sales amount
		for (int x = 0; x < currentSales.length; x++) {
			// check for max sale
			if (currentSales[x] > maximum) {
				maximum = currentSales[x];
				maxIndex = x;
			} else if (currentSales[x] < minimum) // check for min sale
			{
				minimum = currentSales[x];
				minIndex = x;
			}
		}
		System.out
				.println("Maximum value is at index " + maxIndex
						+ " (Salesperson " + (maxIndex + 1) + ") with value "
						+ maximum);
		System.out
				.println("Minimum value is at index " + minIndex
						+ " (Salesperson " + (minIndex + 1) + ") with value "
						+ minimum);
		setAverage();
	}

	// method returns performance array to indicate success at reaching goal
	public int[] determineTopSalesPeople() {
		// System.out prints to console to be sure we got here--debugging tool
		System.out.println("I'm here and salesBar is " + SalesBar);

		// an array with values of -1, 0, 1 to indicate success at reaching goal
		int[] performance = new int[currentSales.length];

		// Loop through the sales array and see who sold more than the sales bar
		for (int x = 0; x < currentSales.length; x++) {
			if (currentSales[x] > SalesBar) {
				performance[x] = 1;
			} else if (currentSales[x] == SalesBar) {
				performance[x] = 0;
			} else {
				performance[x] = -1;
			}
		}
		return performance;
	}
}
