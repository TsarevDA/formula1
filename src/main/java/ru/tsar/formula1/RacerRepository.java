package ru.tsar.formula1;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RacerRepository {

	public List<Racer> getRacers() throws IOException {

		FileReader reader = new FileReader();

		Map<String, LocalDateTime> startTime = fileParsing(reader.read("start.log"));
		Map<String, LocalDateTime> endTime = fileParsing(reader.read("end.log"));

		Map<String, Duration> bestData = new HashMap<>();
		endTime.forEach((k, v) -> bestData.put(k, Duration.between(startTime.get(k), endTime.get(k))));

		List<String> abbreviations = reader.read("abbreviations.txt").collect(Collectors.toList());

		List<Racer> racers = new ArrayList<>();
		abbreviations.stream().forEach((a) -> {
			String[] strings = a.substring(4).split("_");
			Racer newRacer = new Racer(strings[0], strings[1], bestData.get(a.substring(0, 3)));
			racers.add(newRacer);
		});
		return racers;
	}

	public static LocalDateTime stringToTime(String string) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
		return LocalDateTime.parse(string, formatter);
	}

	public Map<String, LocalDateTime> fileParsing(Stream<String> stream) {
		return stream.collect(Collectors.toMap((s) -> s.substring(0, 3), (s) -> stringToTime(s.substring(3))));
	}
}