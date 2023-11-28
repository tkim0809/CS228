package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class STREAMERTest {

	@Test
	void test() {
		
		//Any cell that (1) is not a Reseller or Outage and (2) and has 
		// (Number of Empty + Number of Outage neighbors less than or equal to 1) converts to Reseller.
		
		Town town = new Town(2,2);
		town.grid[0][0] = new STREAMER(town,0,0);
		town.grid[0][1] = new EMPTY(town,0,1);
		town.grid[1][0] = new STREAMER(town,1,0);
		town.grid[1][1] = new CASUAL(town,1,1);
		assertEquals(State.RESELLER, town.grid[0][0].next(town).who());
		
	}

	@Test
	void testB() {
		
		// If there is any reseller in the neighborhood, the reseller causes outage for the
		// streamer as well.

		Town town = new Town(2,3);
		town.grid[0][0] = new STREAMER(town,0,0);
		town.grid[0][1] = new STREAMER(town,0,1);
		town.grid[0][2] = new RESELLER(town,0,2);
		town.grid[1][0] = new STREAMER(town,1,0);
		town.grid[1][1] = new EMPTY(town,1,1);
		town.grid[1][2] = new EMPTY(town,1,2);
		assertEquals(State.OUTAGE, town.grid[0][1].next(town).who());
		
		
	}
	
	@Test 
	void testC() {
		
		// if there is any Outage in the neighborhood, 
		// then the streamer leaves and the cell becomes Empty.
		
		Town town = new Town(2,3);
		town.grid[0][0] = new STREAMER(town,0,0);
		town.grid[0][1] = new STREAMER(town,0,1);
		town.grid[0][2] = new RESELLER(town,0,2);
		town.grid[1][0] = new OUTAGE(town,1,0);
		town.grid[1][1] = new EMPTY(town,1,1);
		town.grid[1][2] = new EMPTY(town,1,2);
		assertEquals(State.OUTAGE, town.grid[0][1].next(town).who());
		
		
	}
	
	@Test
	void testD() {
		
		// If none of the above rules apply, 
		// any cell with 5 or more casual neighbors becomes a Streamer.
		
		
		Town town = new Town(3,3);
		town.grid[0][0] = new CASUAL(town,0,0);
		town.grid[0][1] = new STREAMER(town,0,1);
		town.grid[0][2] = new CASUAL(town,0,2);
		town.grid[1][0] = new CASUAL(town,1,0);
		town.grid[1][1] = new CASUAL(town,1,1);
		town.grid[1][2] = new CASUAL(town,1,2);
		town.grid[2][0] = new EMPTY(town,2,0);
		town.grid[2][1] = new EMPTY(town,2,1);
		town.grid[2][2] = new CASUAL(town,2,2);
		assertEquals(State.STREAMER, town.grid[1][1].next(town).who());
		
	}
	
}
