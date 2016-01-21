package bomb;

import java.applet.Applet;

public class Producer extends Thread {
	private World myWorld;
	private String bank = "qwertyuiopasdfghjklzxcvbnm";
	private Applet apl;
	private int bombRate = 2000;
	
	public Producer(World myWorld, Applet apl) {
		this.myWorld = myWorld;
		this.apl = apl;
	}
	
	public void toggleBombRate() {
		if (bombRate == 2000)
			bombRate = 4000;
		else
			bombRate = 2000;
	}
	
	public void run() {
		String str;
		while (true) {
			int length = (int)Math.ceil(Math.random() *6);
			char []str_arry = new char[length];
			for (int i = 0; i < length; i++) {
				str_arry[i] = bank.charAt((int)(Math.random() * bank.length()));
			}
			str = new String(str_arry);
			int x = ((int)(Math.random() * 500));
			int y = ((int)(Math.random() * 335));
			Bomb b = new Bomb(str, x, y, apl);
			while (myWorld.overlaps(b)) {
				b = new Bomb(str, b.getX()+10, y, apl);
			}
			myWorld.add(b);
			System.out.println("Added bomb " + str + " to the world at " + b.getX()+", " + b.getY());
			apl.repaint();
			try {
				sleep(bombRate);
			} catch (InterruptedException e) {}
		}
	}

}
