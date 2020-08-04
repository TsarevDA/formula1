package ru.tsar.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataSorterTest {
	private DataSorter sorter;

	@BeforeEach
	void setUp() {
		sorter = new DataSorter();
	}

	@Test
	void givenNull_whenSorting_thenIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> sorter.sorting(null, null, null));
	}

	@Test
	void givenData_whenSorting_thenSortedRacers() {
		Racer racer1 = new Racer("Petya Petr", "Petya team", LocalTime.of(0, 1, 4, 500000000));
		Racer racer2 = new Racer("Vasia Vasilii", "Vasia team", LocalTime.of(0, 1, 5, 120000000));
		Racer racer3 = new Racer("Vitia Victor", "Vitia team", LocalTime.of(0, 1, 4, 440000000));
		List<Racer> expected = new ArrayList<>();
		expected.add(racer3);
		expected.add(racer1);
		expected.add(racer2);

		Map<String, LocalTime> startTime = new HashMap<>();
		Map<String, LocalTime> endTime = new HashMap<>();
		Map<String, String> abbreviations = new HashMap<>();

		startTime.put("racer1", LocalTime.of(12, 0, 0, 000000000));
		startTime.put("racer2", LocalTime.of(12, 0, 0, 000000000));
		startTime.put("racer3", LocalTime.of(12, 0, 0, 000000000));

		endTime.put("racer1", LocalTime.of(12, 1, 4, 500000000));
		endTime.put("racer2", LocalTime.of(12, 1, 5, 120000000));
		endTime.put("racer3", LocalTime.of(12, 1, 4, 440000000));

		abbreviations.put("racer1", "Petya Petr_Petya team");
		abbreviations.put("racer2", "Vasia Vasilii_Vasia team");
		abbreviations.put("racer3", "Vitia Victor_Vitia team");
		List<Racer> actual = new ArrayList<>();
		actual = sorter.sorting(startTime, endTime, abbreviations);
		assertEquals(expected, actual);
	}
}
