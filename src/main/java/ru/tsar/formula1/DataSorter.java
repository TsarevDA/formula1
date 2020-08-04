package ru.tsar.formula1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataSorter {

	public List<Racer> sorting(Map<String, LocalTime> startTime, Map<String, LocalTime> endTime,
			Map<String, String> abbreviations) {
		if (startTime == null || endTime == null || abbreviations == null) {
			throw new IllegalArgumentException("One of recieved argument is null");
		}
		Map<String, LocalTime> bestData = new HashMap<>(startTime);
		endTime.forEach((k, v) -> bestData.put(k, timeSubtraction(v, bestData.get(k))));

		List<Racer> racers = new ArrayList<>();
		abbreviations.entrySet().stream().forEach((v) -> {
			String[] strings = v.getValue().split("_");
			Racer newRacer = new Racer(strings[0], strings[1], bestData.get(v.getKey()));
			racers.add(newRacer);
		});
		return racers.stream().sorted((r1, r2) -> r1.getBestTime().compareTo(r2.getBestTime()))
				.collect(Collectors.toList());
	}

	public static LocalTime timeSubtraction(LocalTime minuendTime, LocalTime subtrahendTime) {
		return minuendTime.minusHours(subtrahendTime.getHour()).minusMinutes(subtrahendTime.getMinute())
				.minusSeconds(subtrahendTime.getSecond()).minusNanos(subtrahendTime.getNano());
	}
}
