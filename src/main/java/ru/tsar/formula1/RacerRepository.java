package ru.tsar.formula1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RacerRepository {


	public  List<Racer> getRacers() throws IOException {

		Path startPath = Paths.get(getClass().getClassLoader().getResource("start.log").getPath().substring(1));
		Path endPath = Paths.get(getClass().getClassLoader().getResource("end.log").getPath().substring(1));
		Path abbreviationsPath = Paths.get(getClass().getClassLoader().getResource("abbreviations.txt").getPath().substring(1));
		
		if (startPath == null || endPath == null || abbreviationsPath == null) {
			throw new IllegalArgumentException("One of log file does not exist");
		}
		
		Map<String, LocalTime> startTime = Files.lines(startPath).filter(Objects::nonNull)
		.collect(Collectors.toMap((s) -> s.substring(0, 3), (s) -> stringToTime(s.substring(14))));
		
		Map<String, LocalTime> endTime = Files.lines(endPath).filter(Objects::nonNull)
				.collect(Collectors.toMap((s) -> s.substring(0, 3), (s) -> stringToTime(s.substring(14))));
		
		Map<String, String> abbreviations = Files.lines(abbreviationsPath).filter(Objects::nonNull)
		.collect(Collectors.toMap((s) -> s.substring(0, 3), (s) -> s.substring(4)));
		 
		Map<String, Duration> bestData = new HashMap<>();
		endTime.forEach((k, v) -> bestData.put(k, Duration.between(startTime.get(k),endTime.get(k))));
			
		 List<Racer> racers = new ArrayList<>();
			abbreviations.entrySet().stream().forEach((v) -> {
				String[] strings = v.getValue().split("_");
				Racer newRacer = new Racer(strings[0], strings[1], bestData.get(v.getKey()));
				racers.add(newRacer);
			});
			return racers;
		
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
