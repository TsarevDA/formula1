package ru.tsar.formula1;

import java.util.Comparator;
import java.util.List;

public class ReportFormatter {
	public String format(List<Racer> racers) {
		if (racers == null) {
			throw new IllegalArgumentException("Agument can't be null");
		}
		String result = "";
		int maxNameLength = 0;
		int maxTeamLength = 0;

		maxNameLength = racers.stream().max(Comparator.comparingInt(a -> a.getName().length())).map(Racer::getName)
				.map(String::length).orElse(0);

		maxTeamLength = racers.stream().max(Comparator.comparingInt(a -> a.getTeam().length())).map(Racer::getTeam)
				.map(String::length).orElse(0);

		for (Racer racer : racers) {
			result += Integer.toString(racers.indexOf(racer) + 1) + ".";
			result += racer.getName();
			for (int i = 0; i < maxNameLength - racer.getName().length(); i++) {
				result += " ";
			}
			result += "| ";
			result += racer.getTeam();
			for (int i = 0; i < maxTeamLength - racer.getTeam().length(); i++) {
				result += " ";
			}
			result += "| ";
			result += racer.getBestTime() + System.lineSeparator();

			if (racers.indexOf(racer) == 14) {
				result += "------------------------------------------------------------------------";
				result += System.lineSeparator();
			}
		}

		return result;
	}
}
