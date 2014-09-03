package au.gov.da.tests.main.threads;

import java.util.Date;
import java.util.List;

import au.gov.da.tests.main.pojos.MyPojo;
import au.gov.da.tests.main.pojos.dummydata.DummyDataGenerator;
import au.gov.da.tests.main.threads.thread.MyThread;
import au.gov.da.tests.main.util.UtilTime;

/**
 * 
 * @author Villaca Klaus
 *
 *         The main purpose of this is show threads working in Java 1.7, with
 *         1.8 I'll post another project using the latest resources available.
 *
 *         As the JVM need some warming up to operate at optimal speed I ask to
 *         run one test and then the second, as running both at once will always
 *         privilege the second. However you can test it as you want.
 * 
 *
 */
public class ThreadsTest {

	private static Date startDate;
	private static Date endDate;

	private static final int MAX_RECORDS = 10;
	private static final int SLEEP_TIME_IN_SECONDS = 3000;

	public static void main(String[] args) {
		ThreadsTest tt = new ThreadsTest();

		/**
		 * To test it and see how powerful is working with threads Uncomment the
		 * first part of code and run it, take note of the time consumed then
		 * comment the first part, uncomment the second part and run take note
		 * of the time consumed and compare it against the first one.
		 * 
		 * I let the first part uncommented for start testing.
		 */

		// Test performance with threads - Start First part- Dont uncomment this
		// line
		startDate = null;
		endDate = null;
		System.out.println("\n===== TESTING WITH THREADS =====");
		tt.usingThreads(true);
		// First part finishes here - Dont uncomment this line

		// Test performance without threads - Start Second part- Dont uncomment
		// this line
		// startDate = null;
		// endDate = null;
		// System.out.println("\n===== TESTING NO THREADS =====");
		// tt.noThreads(true);
		// Second part finishes here- Dont uncomment this line
	}

	private void usingThreads(boolean showText) {
		// Prepare threads
		/*
		 * Try to use even numbers for MAX_RECORDS and NUMBER_OF_THREADS . If
		 * using odd numbers you need to adjust the division, to have always
		 * integer.
		 */
		final int NUMBER_OF_THREADS = 2;
		int divideByNumberOfThreads = MAX_RECORDS / NUMBER_OF_THREADS;

		MyThread mt1 = new MyThread("FirstHalf", divideByNumberOfThreads, 0);
		MyThread mt2 = new MyThread("SecondHalf", MAX_RECORDS,
				divideByNumberOfThreads);
		System.out.println("Threads Prepared");

		try {
			// Start threads
			// Time counter start
			startDate = new Date();
			mt1.start();
			mt2.start();
			System.out.println("Threads Started");

			// Some suspend and resume for test.
			System.out.println("Thread Suspended.");
			mt1.suspend();
			System.out.println("Thread going to sleep for "
					+ SLEEP_TIME_IN_SECONDS + "seconds.");
			mt1.sleep(SLEEP_TIME_IN_SECONDS);
			mt1.resume();
			System.out.println("Thread Resumed.");

			// Wait for thread to finish
			/*
			 * Before get to those lines bellow you may want to suspend and
			 * resume threads for test purposes e.g. mt1.
			 */
			mt1.stopMyThread();
			mt2.stopMyThread();
			System.out.println("Threads waiting for finish");

			// Merge both threads
			List<MyPojo> myPojoFirstHalf = mt1.getMyPojoList();
			myPojoFirstHalf.addAll(mt2.getMyPojoList());

			// Time counter stop
			endDate = new Date();

			if (showText) {
				System.out.println("Thread result received");
				System.out.println("Size of the final list is "
						+ myPojoFirstHalf.size());
				System.out.println("Time consumed for the task is "
						+ UtilTime.getDifferenceInMills(startDate, endDate)
						+ " milliseconds");
				System.out.println("Time consumed for the task is "
						+ UtilTime.getDifferenceInSeconds(startDate, endDate)
						+ " seconds");
			}
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
		}

	}

	private void noThreads(boolean showText) {
		DummyDataGenerator ddg = new DummyDataGenerator();
		// Time counter start
		startDate = new Date();
		List<MyPojo> myPojoFirstHalf = ddg.createListDummyData(MAX_RECORDS, 0);
		// Time counter stop
		endDate = new Date();

		if (showText) {
			System.out.println("Without thread result");
			System.out.println("Size of the final list is "
					+ myPojoFirstHalf.size());
			System.out.println("Time consumed for the task is "
					+ UtilTime.getDifferenceInMills(startDate, endDate)
					+ " milliseconds");
			System.out.println("Time consumed for the task is "
					+ UtilTime.getDifferenceInSeconds(startDate, endDate)
					+ " seconds");
		}
	}
}
