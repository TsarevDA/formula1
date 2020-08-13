package ru.tsar.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RacerRepositoryTest {
	private RacerRepository repository;

	@BeforeEach
	void setUp() {
		repository = new RacerRepository();
	}

	@Test
	void callRacerRepository_whenGetRacer_thenSomeRacers() throws IOException {
		assertNotNull(repository.getRacers());
	}

}
