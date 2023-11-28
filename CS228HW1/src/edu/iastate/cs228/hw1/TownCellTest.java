package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class TownCellTest {

	@Test
	void test() throws FileNotFoundException {
		
		// checks if census method is correctly implemented
		String fileName = "ISP4x4.txt";
		
		/*
		protected static final int RESELLER = 0;
		protected static final int EMPTY = 1;
		protected static final int CASUAL = 2;
		protected static final int OUTAGE = 3;
		protected static final int STREAMER = 4;
		*/
		
		Town town = new Town(fileName); // Going to construct town using file for convenience.
		
		
		town.grid[1][1].census(TownCell.nCensus); // Going to check census of (1,1), which is 'E'
		assertEquals(1, TownCell.nCensus[0]); // Expected: 1 reseller
		assertEquals(2, TownCell.nCensus[1]); // Expected: 2 empty
		assertEquals(1, TownCell.nCensus[2]); // Expected: 1 casual
		assertEquals(3, TownCell.nCensus[3]); // Expected: 3 outage
		assertEquals(1, TownCell.nCensus[4]); // Expected: 1 streamer
		
		
		
		town.grid[0][0].census(TownCell.nCensus); // (0,0) 'O'
		assertEquals(1, TownCell.nCensus[0]); 
		assertEquals(2, TownCell.nCensus[1]); 
		
		
		
		town.grid[3][0].census(TownCell.nCensus); // (3,0) 'E'
		assertEquals(1, TownCell.nCensus[4]); 
		assertEquals(1, TownCell.nCensus[1]); 
		assertEquals(1, TownCell.nCensus[3]);
		
		
		town.grid[0][3].census(TownCell.nCensus); // (0,3) 'R'
		assertEquals(1, TownCell.nCensus[2]); 
		assertEquals(2, TownCell.nCensus[3]);
		
		
		town.grid[3][3].census(TownCell.nCensus); // (3,3) 'R'
		assertEquals(1, TownCell.nCensus[0]); 
		assertEquals(1, TownCell.nCensus[3]); 
		assertEquals(1, TownCell.nCensus[4]);
		
		
		town.grid[0][1].census(TownCell.nCensus); // (0,1) 'R'
		assertEquals(2, TownCell.nCensus[1]); 
		assertEquals(1, TownCell.nCensus[2]); 
		assertEquals(2, TownCell.nCensus[3]);
		
	}

}
