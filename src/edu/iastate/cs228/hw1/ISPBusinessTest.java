package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ISPBusinessTest {

	@Test
	void test() {
		
		
		// Test updatePlain method
		Town tNew = new Town(2,2); // initialize two different towns with same row and col
		Town town = new Town(2,2);
		town.grid[0][0] = new CASUAL(town, 0,0);
		town.grid[0][1] = new EMPTY(town, 0,1);
		town.grid[1][0] = new STREAMER(town, 1,0);
		town.grid[1][1] = new EMPTY(town, 1,1);
		tNew = ISPBusiness.updatePlain(town);
		System.out.println("Expected:\nS R\nS R");
		System.out.println("OUTPUT:\n" + tNew.toString());
		
	}
	
	@Test
	void testB() {
		
		// Tests getProfit method
		Town town = new Town(2,2);
		town.grid[0][0] = new CASUAL(town,0,0);
		town.grid[0][1] = new CASUAL(town,0,1);
		town.grid[1][0] = new CASUAL(town,1,0);
		town.grid[1][1] = new CASUAL(town,1,1);
		assertEquals(4, ISPBusiness.getProfit(town));
	
		
	}
	
	@Test 
	void testC() {
		
		// Going to run same 12 month cycles listed on homework pdf and going to check if I get same result
		
		Town town = new Town(4,4);
		Town tNew = new Town(4,4);
		town.randomInit(10);
		for (int i = 0; i < 11; i++) {
			
			tNew = ISPBusiness.updatePlain(town);
			town = tNew;
			System.out.println(tNew.toString());
			
		}
		
		
	}

}
