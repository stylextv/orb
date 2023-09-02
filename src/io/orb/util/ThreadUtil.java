package io.orb.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtil {
	
	private static final ExecutorService threadPool = Executors.newCachedThreadPool();
	
	public static void run(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
}
