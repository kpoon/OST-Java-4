package bomb;

import java.awt.*;

public class World {
	private final int MAX_BOMBS = 5;
	private Bomb bombs[] = new Bomb[MAX_BOMBS];
	private int typeNext = -1;
	private int addNext = 0;
	private int num_bombs = 0;
	private boolean isFull = false;
	private boolean isEmpty = true;
	private boolean gameOver = false;
	
	public synchronized void type(char c) {
		while (isEmpty == true) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		int num_exploded = 0;
		for (int i = 0; i < MAX_BOMBS; i++) {
			if (bombs[i] != null && bombs[i].exploded)
				num_exploded++;
		}
		if (num_exploded >= 3) {
			gameOver = true;
			return;
		}
		
		if (typeNext < 0 || (bombs[typeNext].exploded)) {
			for (int i = 0; i < MAX_BOMBS; i++) {
				if (bombs[i] != null && !bombs[i].exploded && bombs[i].startsWith(c)) {
					typeNext = i;
					bombs[typeNext].setdisarming();
					break;
				}
			}
		}
		
		if (typeNext > -1 && !bombs[typeNext].exploded && bombs[typeNext].attemptDisarm(c)) {
			bombs[typeNext] = null;
			num_bombs--;
			typeNext = -1;
			if (num_bombs == 0) {
				isEmpty = true;
			}
			isFull = false;
			notifyAll();
		}
	}
	
	public synchronized boolean overlaps(Bomb b) {
		for (int i = 0; i < MAX_BOMBS; i++) {
			if (bombs[i] != null && (bombs[i].hasPoint(b.getX(), b.getY()) ||
					bombs[i].hasPoint(b.getX()+b.getWidth(), b.getY()) ||
					bombs[i].hasPoint(b.getX(), b.getY()+b.getHeight()) ||
					bombs[i].hasPoint(b.getX()+b.getWidth(), b.getY()+b.getHeight()))) {
				return true;
			}
		}
		return false;
	}
	
	public synchronized void clearBombs() {
		for (int i = 0; i < MAX_BOMBS; i++) {
			bombs[i] = null;
		}
		typeNext = -1;
		addNext = 0;
		num_bombs = 0;
		isFull = false;
		isEmpty = true;
		gameOver = false;
		notifyAll();
	}
	
	public synchronized void add(Bomb b) {
		while (isFull == true) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		int i;
		for (i = 0; i < MAX_BOMBS; i++) {
			if (bombs[i] == null) {
				bombs[i] = b;
				break;
			}
		}
		assert bombs[i] == b;
		num_bombs++;
		bombs[i].start();
		
		if (num_bombs == MAX_BOMBS) {
			isFull = true;
		}
		isEmpty = false;
		notifyAll();
	}
	
	public void draw(Graphics g) {
		for (int i = 0; i < MAX_BOMBS; i++) {
			if (bombs[i] != null)
				bombs[i].draw(g);
		}
		if (gameOver) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Monospaced", Font.PLAIN, 23));
			g.drawString("GAME OVER", 10, 390);
		}
	}
}
