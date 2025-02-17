package com.api.asisvirtual.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

	private static final String FORMAT_HH_MM_SS = "HH:mm:ss";
	private static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static void main(String[] args) {
		System.out.println(getHoraActual());
		System.out.println(getTimestampMillis());
		System.out.println(getFechaActual());
	}

	public static String getHoraActual() {
		LocalDateTime localDateTime = getLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_HH_MM_SS);
		return localDateTime.format(formatter);
	}

	public static String getFechaActual() {
		LocalDateTime localDateTime = getLocalDateTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_YYYY_MM_DD_HH_MM_SS);
		return localDateTime.format(formatter);
	}

	private static LocalDateTime getLocalDateTime() {
		Instant timestamp = Instant.now();
		return LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
	}

	public static long getTimestampMillis() {
		return Instant.now().toEpochMilli();
	}
}
