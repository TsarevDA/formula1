package ru.tsar.formula1;

import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		RacerRepository repository = new RacerRepository();
		List<Racer> racers = repository.getRacers();

		ReportFormatter formatter = new ReportFormatter();
		System.out.println(formatter.format(racers, 15));

	}
}
