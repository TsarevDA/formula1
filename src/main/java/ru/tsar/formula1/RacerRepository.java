package ru.tsar.formula1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RacerRepository {

	public List<Racer> getRacers() throws IOException {

		Path startPath = Paths.get(getClass().getClassLoader().getResource("start.log").getPath().substring(1));
		Path endPath = Paths.get(getClass().getClassLoader().getResource("end.log").getPath().substring(1));
		Path abbreviationsPath = Paths
				.get(getClass().getClassLoader().getResource("abbreviations.txt").getPath().substring(1));

		if (startPath == null || endPath == null || abbreviationsPath == null) {
			throw new IllegalArgumentException("One of log file does not exist");
		}

		Map<String, LocalDateTime> startTime = fileParsing(startPath);
		Map<String, LocalDateTime> endTime = fileParsing(endPath);

		Map<String, Duration> bestData = new HashMap<>();
		endTime.forEach((k, v) -> bestData.put(k, Duration.between(startTime.get(k), endTime.get(k))));

		List<String> abbreviations = Files.lines(abbreviationsPath).filter(Objects::nonNull)
				.collect(Collectors.toList());

		List<Racer> racers = new ArrayList<>();
		abbreviations.stream().forEach((a) -> {
			String[] strings = a.substring(4).split("_");
			;
			Racer newRacer = new Racer(strings[0], strings[1], bestData.get(a.substring(0, 3)));
			racers.add(newRacer);

		});
		return racers;
	}

	public static LocalDateTime stringToTime(String string) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
		return LocalDateTime.parse(string, formatter);
	}

	public Map<String, LocalDateTime> fileParsing(Path path) throws IOException {
		return Files.lines(path).filter(Objects::nonNull)
				.collect(Collectors.toMap((s) -> s.substring(0, 3), (s) -> stringToTime(s.substring(3))));
	}

}
