package ru.tsar.formula1;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {

		final String dir = System.getProperty("user.dir");

		Path startPath = Paths.get(dir + "\\" + "start.log");
		Path endPath = Paths.get(dir + "\\" + "end.log");
		Path abbreviationsPath = Paths.get(dir + "\\" + "abbreviations.txt");

		DataSorter sorter = new DataSorter();
		List<Racer> racers = new ArrayList<>();
		racers = sorter.sorting(DataReader.readTime(startPath), DataReader.readTime(endPath),
				DataReader.readAbbreviations(abbreviationsPath));

		ReportFormatter formatter = new ReportFormatter();
		System.out.println(formatter.format(racers));

	}
}
