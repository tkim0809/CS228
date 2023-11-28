package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RESELLERTest {

	@Test
	void test() {
		
		//a. If there are 3 or fewer casual users in the neighborhood, then Reseller finds
		//it unprofitable to maintain the business and leaves, making the cell Empty.
		
		// In this case, we only have 2 CASUAL which is less than 3, so expecting EMPTY cell
		Town town = new Town(2,2);
		town.grid[0][0] = new RESELLER(town,0,0);
		town.grid[0][1] = new CASUAL(town,0,1);
		town.grid[1][0] = new CASUAL(town,1,0);
		town.grid[1][1] = new EMPTY(town,1,1);
		assertEquals(State.EMPTY, town.grid[0][0].next(town).who());
		
	}
	
	@Test
	void testB() {
		
		//b. Also, if there are 3 or more empty cells in the neighborhood, 
		// then the Reseller leaves, making the cell Empty. Resellers do not like unpopulated regions.
		
		Town town = new Town(2,3);
		town.grid[0][0] = new EMPTY(town,0,0);
		town.grid[0][1] = new RESELLER(town,0,1);
		town.grid[0][2] = new EMPTY(town,0,2);
		town.grid[1][0] = new EMPTY(town,1,0);
		town.grid[1][1] = new RESELLER(town,1,1);
		town.grid[1][2] = new CASUAL(town,1,2);
		
		assertEquals(State.EMPTY, town.grid[0][1].next(town).who());
		
	}
	
	@Test 
	void testC() {
		
		// If none of the rules above apply, cell remains unchanged.
		
		Town town = new Town(3,3);
		town.grid[0][0] = new EMPTY(town,0,0);
		town.grid[0][1] = new EMPTY(town,0,1);
		town.grid[0][2] = new STREAMER(town,0,2);
		town.grid[1][0] = new STREAMER(town,1,0);
		town.grid[1][1] = new RESELLER(town,1,1);
		town.grid[1][2] = new CASUAL(town,1,2);
		town.grid[2][0] = new CASUAL(town,2,0);
		town.grid[2][1] = new CASUAL(town,2,1);
		town.grid[2][2] = new CASUAL(town,2,2);
		assertEquals(State.RESELLER, town.grid[1][1].next(town).who());
		
		
	}

}
