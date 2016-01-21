package threadDemo;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MultiThreadApplet extends Applet implements ActionListener {
	MainThread t1, t2, t3;
	TextArea ta1, ta2, ta3;

	public void init() {
		resize(500, 600);

		Button run1 = new Button("Run Thread One");
		add(run1);
		run1.addActionListener(this);
		ta1 = new TextArea(10, 50);
		add(ta1);

		Button run2 = new Button("Run Thread Two");
		add(run2);
		run2.addActionListener(this);
		ta2 = new TextArea(10, 50);
		add(ta2);

		Button run3 = new Button("Run Thread Three");
		add(run3);
		run3.addActionListener(this);
		ta3 = new TextArea(10, 50);
		add(ta3);
		
		t1 = new MainThread("Thread1");
		t2 = new MainThread("Thread2");
		t3 = new MainThread("Thread3");
	}

	public void actionPerformed(ActionEvent e) {
		DateFormat df = new SimpleDateFormat("MM/dd/yy h:mm:ss a");

		if (e.getActionCommand() == "Run Thread One") {
			try {
				t1.start();
				//When started, each thread should print out its name and the time it started. 
				ta1.append("\nThread 1 started at " + df.format(new Date()));
				System.out.println("\nThread 1 started at "
						+ df.format(new Date()));
				
				//repeat 4 times
				for (int i = 0; i < 4; i++) {
					ta1.append("\n" + "Cycle " + (i + 1));
					long start = (System.currentTimeMillis());
					//It should then go to sleep for a specified amount of time.
					int timeSlept = (int)(Math.random() * 1000);
					t1.sleep(timeSlept);
					//When it wakes, it should print out its name, how long it was put to sleep
					ta1.append("\nThread 1 was put to sleep for "
							+ timeSlept
							+ " milliseconds");
					//how long it actually has been asleep. 
					ta1.append("\nThread 1 slept for "
							+ (System.currentTimeMillis() - start)
							+ " milliseconds (" + df.format(new Date()) + ")");
					//It should then be put to sleep again.
					t1.sleep((int) (Math.random() * 1000));
				}
			} catch (IllegalThreadStateException ex) {
				System.out.println("Thread has already started.");
			} catch (InterruptedException ex) {
				System.out.println("Thread was interrupted.");
			}
		}

		if (e.getActionCommand() == "Run Thread Two") {
			try {
				t2.start();
				//When started, each thread should print out its name and the time it started. 
				ta2.append("\nThread 2 started at " + df.format(new Date()));
				System.out.println("\nThread 2 started at "
						+ df.format(new Date()));
				
				//repeat 4 times
				for (int i = 0; i < 4; i++) {
					ta2.append("\n" + "Cycle " + (i + 1));
					long start = (System.currentTimeMillis());
					//It should then go to sleep for a specified amount of time.
					int timeSlept = (int)(Math.random() * 1000);
					t2.sleep(timeSlept);
					//When it wakes, it should print out its name, how long it was put to sleep
					ta2.append("\nThread 2 was put to sleep for "
							+ timeSlept
							+ " milliseconds");
					//how long it actually has been asleep. 
					ta2.append("\nThread 2 slept for "
							+ (System.currentTimeMillis() - start)
							+ " milliseconds (" + df.format(new Date()) + ")");
					//It should then be put to sleep again.
					t2.sleep((int) (Math.random() * 1000));
				}
			} catch (IllegalThreadStateException ex) {
				System.out.println("Thread has already started.");
			} catch (InterruptedException ex) {
				System.out.println("Thread was interrupted.");
			}
		}

		if (e.getActionCommand() == "Run Thread Three") {
			try {
				t3.start();
				//When started, each thread should print out its name and the time it started. 
				ta3.append("\nThread 3 started at " + df.format(new Date()));
				System.out.println("\nThread 3 started at "
						+ df.format(new Date()));
				
				//repeat 4 times
				for (int i = 0; i < 4; i++) {
					ta3.append("\n" + "Cycle " + (i + 1));
					long start = (System.currentTimeMillis());
					//It should then go to sleep for a specified amount of time.
					int timeSlept = (int)(Math.random() * 1000);
					t3.sleep(timeSlept);
					//When it wakes, it should print out its name, how long it was put to sleep
					ta3.append("\nThread 3 was put to sleep for "
							+ timeSlept
							+ " milliseconds");
					//how long it actually has been asleep. 
					ta3.append("\nThread 3 slept for "
							+ (System.currentTimeMillis() - start)
							+ " milliseconds (" + df.format(new Date()) + ")");
					//It should then be put to sleep again.
					t3.sleep((int) (Math.random() * 1000));
				}
			} catch (IllegalThreadStateException ex) {
				System.out.println("Thread has already started.");
			} catch (InterruptedException ex) {
				System.out.println("Thread was interrupted.");
			}
		}

	}
}
