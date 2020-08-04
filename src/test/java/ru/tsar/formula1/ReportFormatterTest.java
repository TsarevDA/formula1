package ru.tsar.formula1;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalTime;
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
		Racer racer1 = new Racer("Racer1","1 team",LocalTime.of(0, 1, 4, 500000000));
		Racer racer2 = new Racer("Racer2","2 team",LocalTime.of(0, 1, 5, 500000000));
		Racer racer3 = new Racer("Racer3","3 team",LocalTime.of(0, 1, 6, 500000000));
		Racer racer4 = new Racer("Racer4","4 team",LocalTime.of(0, 1, 7, 500000000));
		Racer racer5 = new Racer("Racer5","5 team",LocalTime.of(0, 1, 8, 500000000));
		Racer racer6 = new Racer("Racer6","6 team",LocalTime.of(0, 1, 9, 500000000));
		Racer racer7 = new Racer("Racer7","7 team",LocalTime.of(0, 1, 10, 500000000));
		Racer racer8 = new Racer("Racer8","8 team",LocalTime.of(0, 1, 11, 500000000));
		Racer racer9 = new Racer("Racer9","9 team",LocalTime.of(0, 1, 12, 500000000));
		Racer racer10 = new Racer("Racer10","10 team",LocalTime.of(0, 1, 13, 500000000));
		Racer racer11 = new Racer("Racer11","11 team",LocalTime.of(0, 1, 14, 500000000));
		Racer racer12 = new Racer("Racer12","12 team",LocalTime.of(0, 1, 15, 500000000));
		Racer racer13 = new Racer("Racer13","13 team",LocalTime.of(0, 1, 16, 500000000));
		Racer racer14 = new Racer("Racer14","14 team",LocalTime.of(0, 1, 17, 500000000));
		Racer racer15 = new Racer("Racer15","15 team",LocalTime.of(0, 1, 18, 500000000));
		Racer racer16 = new Racer("Racer16","16 team",LocalTime.of(0, 1, 19, 500000000));
		List<Racer> racers = new ArrayList<>();
		racers.add(racer1);
		racers.add(racer2);
		racers.add(racer3);
		racers.add(racer4);
		racers.add(racer5);
		racers.add(racer6);
		racers.add(racer7);
		racers.add(racer8);
		racers.add(racer9);
		racers.add(racer10);
		racers.add(racer11);
		racers.add(racer12);
		racers.add(racer13);
		racers.add(racer14);
		racers.add(racer15);
		racers.add(racer16);
		
		String actual = formatter.format(racers);
		String expected = 
				"1.Racer1 | 1 team | 00:01:04.500"+System.lineSeparator()+
				"2.Racer2 | 2 team | 00:01:05.500"+System.lineSeparator()+
				"3.Racer3 | 3 team | 00:01:06.500"+System.lineSeparator()+
				"4.Racer4 | 4 team | 00:01:07.500"+System.lineSeparator()+
				"5.Racer5 | 5 team | 00:01:08.500"+System.lineSeparator()+
				"6.Racer6 | 6 team | 00:01:09.500"+System.lineSeparator()+
				"7.Racer7 | 7 team | 00:01:10.500"+System.lineSeparator()+
				"8.Racer8 | 8 team | 00:01:11.500"+System.lineSeparator()+
				"9.Racer9 | 9 team | 00:01:12.500"+System.lineSeparator()+
				"10.Racer10| 10 team| 00:01:13.500"+System.lineSeparator()+
				"11.Racer11| 11 team| 00:01:14.500"+System.lineSeparator()+
				"12.Racer12| 12 team| 00:01:15.500"+System.lineSeparator()+
				"13.Racer13| 13 team| 00:01:16.500"+System.lineSeparator()+
				"14.Racer14| 14 team| 00:01:17.500"+System.lineSeparator()+
				"15.Racer15| 15 team| 00:01:18.500"+System.lineSeparator()+
				"------------------------------------------------------------------------"+System.lineSeparator()+
				"16.Racer16| 16 team| 00:01:19.500"+System.lineSeparator();
		
		assertEquals(expected,actual);
				
		}
		
}
