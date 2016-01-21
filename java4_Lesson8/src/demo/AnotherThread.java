package demo;

public class AnotherThread {
	public static void main(String args[]) {
		T t = new T();
		t.start();
	}
}

class T extends Thread {
	public void run() {
		while(true) {
			System.out.println("b: ");
			try {
				sleep(300);
			}
			catch (InterruptedException e) {}
		}
	}
}
