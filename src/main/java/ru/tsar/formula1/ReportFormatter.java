package ru.tsar.formula1;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class ReportFormatter {

	public String format(List<Racer> racers, int amountTopPlaces) {
		if (racers == null) {
			throw new IllegalArgumentException("Agument can't be null");
		}

		int maxNameLength = getMaxFieldLength(racers, Racer::getName);
		int maxTeamLength = getMaxFieldLength(racers, Racer::getTeam);

		String racerFormat = "%d." + "%-" + maxNameLength + "s" + " | " + "%-" + maxTeamLength + "s" + " | %s%n";
		StringBuilder result = new StringBuilder();
		AtomicInteger placeCounter = new AtomicInteger(1);
		racers.stream().sorted(Comparator.comparing(Racer::getBestTime)).forEach(racer -> {
			if (placeCounter.get() == amountTopPlaces) {
				String s = String.format(racerFormat, placeCounter.getAndIncrement(), racer.getName(), racer.getTeam(),
						formatTime(racer.getBestTime()));
				result.append(s);
				for (int i = 0; i < s.length() - 1; i++) {
					result.append("-");
				}
				result.append(System.lineSeparator());
			} else {
				result.append(String.format(racerFormat, placeCounter.getAndIncrement(), racer.getName(),
						racer.getTeam(), formatTime(racer.getBestTime())));
			}
		});
		return result.toString();
	}

	public static String formatTime(Duration time) {
		String result = "";
		result += time.toMinutes() + ":";
		if (time.getSeconds() > 59) {
			result += (time.getSeconds() - 60) + ".";
		} else {
			result += time.getSeconds() + ".";
		}
		if (time.getNano() / 1000000 > 99) {
			result += time.getNano() / 1000000;
		} else {
			result += time.getNano() / 100000;
		}
		return result;
	}

	public static int getMaxFieldLength(List<Racer> racers, Function<Racer, String> getter) {
		return racers.stream().map(getter).map(String::length).max(Integer::compare).orElse(0);
	}
}
