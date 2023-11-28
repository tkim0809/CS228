package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EMPTYtest {

	@Test
	void test() {
		
		// a. Any cell that (1) is not a Reseller or Outage and (2) and has 
		// (Number of Empty + Number of Outage neighbors less than or equal to 1) converts to Reseller.
		
		Town town = new Town(2,2);
		town.grid[0][0] = new EMPTY(town,0,0);
		town.grid[0][1] = new CASUAL(town,0,1);
		town.grid[1][0] = new CASUAL(town,1,0);
		town.grid[1][1] = new CASUAL(town,1,1);
		assertEquals(State.RESELLER, town.grid[0][0].next(town).who());
		
	}
	
	@Test 
	void testB() {
		
		//If the cell was empty, then a Casual user takes it and it becomes a C.
		
		Town town = new Town(2,2);
		town.grid[0][0] = new EMPTY(town,0,0);
		town.grid[0][1] = new EMPTY(town,0,1);
		town.grid[1][0] = new EMPTY(town,1,0);
		town.grid[1][1] = new CASUAL(town,1,1);
		assertEquals(State.CASUAL, town.grid[0][0].next(town).who());
		
	}

}
