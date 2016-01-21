package prodcons;

class Consumer extends Thread {
	private Soup soup;
	private MyTableSetting bowlView;
	private Thread cThread;

	public Consumer(MyTableSetting bowl, Soup s) {
		bowlView = bowl;
		soup = s;
	}

	public void run() {
		String c;
		while (cThread != null) {
			c = soup.eat();
			System.out.println("Ate a letter: " + c);
			bowlView.repaint();

			try {
				sleep((int) (Math.random() * 3000));
			} catch (InterruptedException e) {
			}
		}
	}

	public void start() {
		if (cThread == null) {
			cThread = new Thread(this);
			cThread.start();
		}
	}

	public void stopThread() {
		cThread = null;
	}
}
