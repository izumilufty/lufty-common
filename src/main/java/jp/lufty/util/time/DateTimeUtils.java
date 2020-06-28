package jp.lufty.util.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	
	public static String nowUtcStr() {
		return format(nowUtc());
	}

	public static LocalDateTime parse(String str) {
		return LocalDateTime.parse(str, FORMATTER);
	}
	
	public static LocalDateTime nowUtc() {
		return LocalDateTime.now(ZoneId.of("UTC"));
	}

	public static String format(LocalDateTime datetime) {
		return datetime.format(FORMATTER);
	}

}
