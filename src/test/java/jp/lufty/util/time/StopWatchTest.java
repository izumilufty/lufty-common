package jp.lufty.util.time;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StopWatchTest {

	@Test
	@DisplayName("止めてから")
	void test_stop() throws Exception {

		StopWatch sw = StopWatch.start();

		TimeUnit.NANOSECONDS.sleep(TimeUnit.SECONDS.toNanos(1));

		sw.stop();

		assertAll(//
				() -> assertThat(sw.elapsedMillis(), greaterThanOrEqualTo(1000l)),
				() -> assertThat(sw.elapsedMinutes(), greaterThanOrEqualTo(0l)),
				() -> assertThat(sw.elapsedNanos(), greaterThanOrEqualTo(1000000000l)),
				() -> assertThat(sw.elapsedSecs(), greaterThanOrEqualTo(1l))
		//
		);

	}

	@Test
	@DisplayName("止めずに")
	void test_nonstop() throws Exception {

		StopWatch sw = StopWatch.start();

		TimeUnit.NANOSECONDS.sleep(TimeUnit.SECONDS.toNanos(1));

		assertAll(//
				() -> assertThat(sw.elapsedMillis(), greaterThanOrEqualTo(1000l)),
				() -> assertThat(sw.elapsedMinutes(), greaterThanOrEqualTo(0l)),
				() -> assertThat(sw.elapsedNanos(), greaterThanOrEqualTo(1000000000l)),
				() -> assertThat(sw.elapsedSecs(), greaterThanOrEqualTo(1l))
		//
		);

	}
}
