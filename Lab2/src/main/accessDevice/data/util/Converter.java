package main.accessDevice.data.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

public class Converter {

	public static LocalDateTime toLocalDateTime(Timestamp timestamp) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp.getTime()), TimeZone.getDefault().toZoneId());
	}

	public static Timestamp toTimestamp(LocalDateTime localDateTime) {
		return Timestamp.valueOf(localDateTime);
	}


}
