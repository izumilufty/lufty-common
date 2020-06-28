package jp.lufty.util.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadUtils {

	private static final Map<String, AtomicInteger> prefixMap = new HashMap<>();

	public synchronized static ThreadFactory newThreadFactory(String namePrefix) {

		AtomicInteger num = prefixMap.get(namePrefix);
		if (num == null) {
			num = new AtomicInteger(0);
			prefixMap.put(namePrefix, num);
		}
		String threadPrefix = namePrefix + "-" + num.incrementAndGet() + "-";
		return new CommonThreadFactory(threadPrefix);

	}

	static class CommonThreadFactory implements ThreadFactory {

		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String threadPrefix;

		CommonThreadFactory(String threadPrefix) {
			SecurityManager s = System.getSecurityManager();
			this.group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			this.threadPrefix = threadPrefix;
		}

		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, this.threadPrefix + threadNumber.getAndIncrement(), 0);
			if (t.isDaemon())
				t.setDaemon(false);
			if (t.getPriority() != Thread.NORM_PRIORITY)
				t.setPriority(Thread.NORM_PRIORITY);
			return t;
		}
	}

}
