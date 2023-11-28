package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CASUALtest {

	
	// Going to check if my rules are correctly prioritized by rules
	// Priority of rules are top to bottom.
	
	@Test
	void test() {
		
		// Any cell that (1) is not a Reseller or Outage and 
		// (2) and has (Number of Empty + Number of Outage neighbors 
		//less than or equal to 1) converts to Reseller.
		
		Town town = new Town(2,2);
		town.grid[0][0] = new CASUAL(town, 0,0);
		town.grid[0][1] = new RESELLER(town, 0,0);
		town.grid[1][0] = new EMPTY(town, 0, 0);
		town.grid[1][1] = new CASUAL(town, 0,0);
		assertEquals(State.RESELLER,town.grid[0][0].next(town).who());
		
		
	}
	@Test
	void testB() {
		
		// If there is any reseller in its neighborhood, then the 
		// reseller causes outage in the casual user cell. Thus,
		//the state of the cell changes from C (Casual) to O (Outage).
		
		Town town = new Town(2,2);
		town.grid[0][0] = new CASUAL(town, 0,0);
		town.grid[0][1] = new RESELLER(town, 0,0);
		town.grid[1][0] = new EMPTY(town,0,0);
		town.grid[1][1] = new OUTAGE(town,0,0);
		assertEquals(State.OUTAGE, town.grid[0][0].next(town).who());
		
	}
	@Test
	void testC() {
		
		// Otherwise, if there is any neighbor who is a streamer, then the
		// casual user also becomes a streamer, in the hopes of making it big
		// on the internet.
		
		Town town = new Town(2,2);
		town.grid[0][0] = new CASUAL(town, 0,0);
		town.grid[0][1] = new STREAMER(town,0,0);
		town.grid[1][0] = new EMPTY(town,0,0);
		town.grid[1][1] = new EMPTY(town,0,0);
		assertEquals(State.STREAMER, town.grid[0][0].next(town).who());
		
	}
	@Test
	void testD() {
	
	// for any cell with 5 or more casual neighbors
	// becomes a Streamer.
		// for this, im using 6 towncells because it has to have 5 or more casual neighbors
	
	
		Town town = new Town(3,3);
		town.grid[0][0] = new CASUAL(town, 0,0);
		town.grid[0][1] = new CASUAL(town,0,1);
		town.grid[0][2] = new CASUAL(town,0,2);
		town.grid[1][0] = new CASUAL(town,1,0);
		town.grid[1][1] = new CASUAL(town,1,1);
		town.grid[1][2] = new EMPTY(town,1,2);
		town.grid[2][0] = new EMPTY(town, 2,0);
		town.grid[2][1] = new CASUAL(town,2,1);
		town.grid[2][2] = new CASUAL(town,2,2);
		assertEquals(State.STREAMER, town.grid[1][1].next(town).who());
		
	}
	@Test
	void testE() {
		
		// If none of the rules apply, the cell remains unchanged 
		
		Town town = new Town(2,3);
		town.grid[0][0] = new CASUAL(town, 0,0);
		town.grid[0][1] = new EMPTY(town,0,1);
		town.grid[0][2] = new CASUAL(town,0,2);
		town.grid[1][0] = new EMPTY(town,1,0);
		town.grid[1][1] = new CASUAL(town,1,1);
		town.grid[1][2] = new CASUAL(town,1,2);
		assertEquals(State.CASUAL, town.grid[0][0].next(town).who());
		
	}

}
