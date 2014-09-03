package au.gov.da.tests.main.threads.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 
 * @author Villaca Klaus
 *
 *         Class to throw the exception by yourself
 *
 */
public class MyTaskQueue {
	private static final int MAX_TASKS = 1000;
	private static final String QUEUE_FULL_MESSAGE = "Queue full, please remove some threads with removeTask()";

	private BlockingQueue<MyTaskQueue> queue = new LinkedBlockingDeque<MyTaskQueue>(
			MAX_TASKS);

	public void putTask(MyTaskQueue tq) throws InterruptedException {
		if (queue.size() == MAX_TASKS) {
			throw new InterruptedException(QUEUE_FULL_MESSAGE);
		} else {
			queue.put(tq);
		}
	}

	public MyTaskQueue getTask() throws InterruptedException {
		return queue.take();
	}

	public void removeTask() throws InterruptedException {
		queue.remove();
	}
}
