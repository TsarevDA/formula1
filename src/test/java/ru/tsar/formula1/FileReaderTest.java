package ru.tsar.formula1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileReaderTest {

	private FileReader reader;

	@BeforeEach
	void setUp() {
		reader = new FileReader();
	}

	@Test
	void givenNotExistingFile_whenFormat_thenIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> reader.read("anyFile.txt"));
	}
}
