package au.gov.da.tests.main.threads.thread;

import java.util.ArrayList;
import java.util.List;

import au.gov.da.tests.main.pojos.MyPojo;
import au.gov.da.tests.main.pojos.dummydata.DummyDataGenerator;

/**
 * 
 * @author Villaca Klaus
 *
 *         MyThread Class I couldn't find a good pattern so far to implement
 *         threads, To improve this class I could be passing generics. However
 *         the content from the run() method will have to be implemented in here
 *         if you want to have the suspend/resume control over it.
 * 
 */
public class MyThread implements Runnable {

	public Thread t;
	private String threadName;
	private boolean suspended = false;
	private int maxRecords;
	private int startingWith;

	private List<MyPojo> myPoloList;

	public MyThread(String name, int maxRecords, int startingWith) {
		threadName = name;
		this.maxRecords = maxRecords;
		this.startingWith = startingWith;
	}

	@Override
	public void run() {
		DummyDataGenerator ddg = new DummyDataGenerator();
		myPoloList = new ArrayList<MyPojo>();
		for (int i = startingWith; i < maxRecords; i++) {
			synchronized (this) {
				// If suspended then pause the loop
				while (suspended) {
					try {
						wait();
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
			myPoloList.add(ddg.getDummyPojoData(i));
		}
	}

	public void start() {
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public void suspend() {
		suspended = true;
	}

	public void sleep(long millisconds) throws InterruptedException {
		Thread.sleep(millisconds);
	}

	public synchronized void resume() {
		suspended = false;
		notify();
	}

	public void stopMyThread() throws InterruptedException {
		try {
			t.join();
		} catch (InterruptedException e) {
			/*
			 * Please comment one of those two lines bellow accordingly with
			 * your needs
			 */

			/*
			 * Restore the interrupted status
			 */
			Thread.currentThread().interrupt();

			/*
			 * re throw if it's needed
			 */
			throw e;
		}
	}

	public List<MyPojo> getMyPojoList() {
		return myPoloList;
	}
}
