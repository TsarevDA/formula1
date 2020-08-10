package ru.tsar.formula1;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReportFormatter {
	public String format(List<Racer> racers) {
		if (racers == null) {
			throw new IllegalArgumentException("Agument can't be null");
		}

		final List<Racer> finalRacers = racers.stream().sorted((r1, r2) -> r1.getBestTime().compareTo(r2.getBestTime()))
				.collect(Collectors.toList());

		finalRacers.stream().forEach(racer -> racer.setPlace(finalRacers.indexOf(racer) + 1));

		Function<Racer, String> getNameFunction = Racer::getName;
		Function<Racer, String> getTeamFunction = Racer::getTeam;

		int maxNameLength = getMaxLength(racers, getNameFunction);
		int maxTeamLength = getMaxLength(racers, getTeamFunction);

		String racerFormat = "%d. %s%s | %s%s | %s";

		StringBuilder result = new StringBuilder();
		finalRacers.stream().forEach(racer -> {
			result.append(String.format(racerFormat, racer.getPlace(), racer.getName(),
					spaceGenerate(maxNameLength - racer.getName().length()), racer.getTeam(),
					spaceGenerate(maxTeamLength - racer.getTeam().length()),
					formatTime(racer.getBestTime()) + System.lineSeparator()));
			if (racer.getPlace() == 14) {
				result.append("------------------------------------------------------------------------"
						+ System.lineSeparator());
			}
		});

		return result.toString();
	}

	public static String formatTime(Duration time) {
		String result = "";
		result += time.toString().substring(time.toString().indexOf("T") + 1, time.toString().indexOf("M"));
		result += ":";
		result += time.toString().substring(time.toString().indexOf("M") + 1, time.toString().indexOf("S"));
		return result;
	}

	public static int getMaxLength(List<Racer> racers, Function<Racer, String> func) {
		return racers.stream().max(Comparator.comparingInt(a -> func.apply(a).length())).map(func).map(String::length)
				.orElse(0);
	}

	public static String spaceGenerate(int amount) {
		String result = "";
		for (int i = 0; i < amount; i++) {
			result += " ";
		}
		return result;
	}

}
