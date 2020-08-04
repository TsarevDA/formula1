package ru.tsar.formula1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DataReaderTest {

	@Test
	void givenNull_whenReadTime_thenIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> DataReader.readTime(null));
	}

	@Test
	void givenNull_whenReadAbbreviations_thenIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> DataReader.readAbbreviations(null));
	}
}
