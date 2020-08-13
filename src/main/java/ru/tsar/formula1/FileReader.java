package ru.tsar.formula1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

public class FileReader {

	public Stream<String> read(String fileName) throws IOException {

		if (getClass().getClassLoader().getResource(fileName) == null ) {
			throw new IllegalArgumentException("File " + fileName + " does not exist.");
		}
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).getPath().substring(1));
		return Files.lines(path).filter(Objects::nonNull);
	}
}
