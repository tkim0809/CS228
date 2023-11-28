package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OUTAGETest {

	@Test
	void test() {
		
		// OUTAGE only have one rule and that is it becomes EMPTY
		Town town = new Town(2,2);
		town.grid[0][0] = new OUTAGE(town,0,0);
		town.grid[0][1] = new CASUAL(town,0,0);
		town.grid[1][0] = new STREAMER(town,0,0);
		town.grid[1][1] = new RESELLER(town,0,0);
		assertEquals(State.EMPTY, town.grid[0][0].next(town).who());
		
	}

}
