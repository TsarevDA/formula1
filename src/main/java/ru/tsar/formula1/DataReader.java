package ru.tsar.formula1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class DataReader {

	public static Map<String, LocalTime> readTime(Path path) throws IOException {
		if (path == null) {
			throw new IllegalArgumentException("Path can't be null");
		}
		return Files.lines(path).filter(Objects::nonNull)
				.collect(Collectors.toMap((s) -> s.substring(0, 3), (s) -> stringToTime(s.substring(14))));
	}

	public static Map<String, String> readAbbreviations(Path path) throws IOException {
		if (path == null) {
			throw new IllegalArgumentException("Path can't be null");
		}
		return Files.lines(path).filter(Objects::nonNull)
				.collect(Collectors.toMap((s) -> s.substring(0, 3), (s) -> s.substring(4)));
	}

	public static LocalTime stringToTime(String string) {
		if (string == null) {
			throw new IllegalArgumentException("String can't be null");
		}
		String[] strings = string.split("[:.]");

		return LocalTime.of(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]),
				Integer.parseInt(strings[3]) * 1000000);
	}

}
