package jp.lufty.util.time;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StopWatch {

	private final long startTimeNano;
	private final AtomicLong endTimeNano = new AtomicLong(-1);

	public static StopWatch start() {
		return new StopWatch(System.nanoTime());
	}

	public StopWatch stop() {
		this.endTimeNano.compareAndSet(-1, System.nanoTime());
		return this;
	}

	public long elapsedNanos() {
		return getCurrentTime() - this.startTimeNano;
	}

	public long elapsedMillis() {
		return TimeUnit.NANOSECONDS.toMillis(elapsedNanos());
	}

	public long elapsedSecs() {
		return TimeUnit.NANOSECONDS.toSeconds(elapsedNanos());
	}

	public long elapsedMinutes() {
		return TimeUnit.NANOSECONDS.toMinutes(elapsedNanos());
	}

	private long getCurrentTime() {
		long result = endTimeNano.get();
		return result == -1 ? System.nanoTime() : result;
	}
}
