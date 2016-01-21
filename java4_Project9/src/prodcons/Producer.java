package prodcons;

class Producer extends Thread {
	private Soup soup;
	private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private MyTableSetting bowlView;
	private Thread pThread = this;

	public Producer(MyTableSetting bowl, Soup s) {
		bowlView = bowl;
		soup = s;
	}

	public void run() {
		String c;
		while (pThread != null) {
			c = String.valueOf(alphabet.charAt((int) (Math.random() * 26)));
			soup.add(c);
			System.out.println("Added " + c + " to the soup.");
			bowlView.repaint();

			try {
				sleep((int) (Math.random() * 2000));
			} catch (InterruptedException e) {
			}
		}
	}

	public void stopThread() {
		pThread = null;
	}
}
