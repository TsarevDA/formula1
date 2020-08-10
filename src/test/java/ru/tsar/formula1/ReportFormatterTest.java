package ru.tsar.formula1;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReportFormatterTest {
	private ReportFormatter formatter;
	
	@BeforeEach
	void setUp() {
		formatter = new ReportFormatter();
	}

	@Test
	void givenNull_whenFormat_thenIllegalArgumentException(){
		assertThrows(IllegalArgumentException.class, () -> formatter.format(null));
	}
	
	@Test
	void givenListRassers_whenFormat_thenRightFormat() {
		
		Racer racer1 = new Racer("Racer1","1 team",Duration.ofSeconds(64,500000000));
		Racer racer2 = new Racer("Racer2","2 team",Duration.ofSeconds(64,950000000));
		Racer racer3 = new Racer("Racer3","3 team",Duration.ofSeconds(63,990000000));
		
		List<Racer> racers = new ArrayList<>();
		racers.add(racer1);
		racers.add(racer2);
		racers.add(racer3);

		
		String actual = formatter.format(racers);
		String expected = 
				"1. Racer3 | 3 team | 1:3.99"+System.lineSeparator()+
				"2. Racer1 | 1 team | 1:4.5"+System.lineSeparator()+
				"3. Racer2 | 2 team | 1:4.95"+System.lineSeparator();
		
		assertEquals(expected,actual);
				
		}
		
}
