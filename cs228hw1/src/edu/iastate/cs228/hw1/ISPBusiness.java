package edu.iastate.cs228.hw1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Taewan Kim
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	
	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	
	
	public static Town updatePlain(Town tOld) {
		
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		
		for(int i = 0; i < tOld.getLength(); i++) {
			
			for (int j = 0; j < tOld.getWidth(); j++) {
				
				tNew.grid[i][j] = tOld.grid[i][j].next(tNew);
				
			}
			
		}
		
		return tNew;
		
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {

		
		int profit = 0;
		int row = town.getLength();
		int col = town.getWidth();
		
		for (int i = 0; i < row; i++) {
			
			for (int j = 0; j < col; j++) {
				
				if (town.grid[i][j].who() == State.CASUAL) {
					
					profit++;
					
				}		
			}
			
		}
		
		
		return profit;
	}
	

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {

		
		int row = 0, col = 0,seed = 0;
		Town town = new Town(0,0); // initialize town and tNew here because we will be using these two
		Town tNew = new Town(0,0); // towns for two if statements down below.

		
		
		System.out.println("How to populate grid (type 1 or 2) : 1: from a file. 2: randomly with seed");
		Scanner scan = new Scanner(System.in);
		int type = scan.nextInt(); // type 1 or 2
		scan.nextLine();
		
		
		int profit = 0;
		double profitSum = 0;
		
		if (type == 1) {
			
			System.out.println("Please enter the path: ");
			String filename = scan.nextLine();
			
			try {
				
				town = new Town(filename);
				tNew = new Town(town.getLength(),town.getWidth()); 
				// declaring tNew with same row and col as town to use it for the profit calculation below.
				
				
			} catch (FileNotFoundException e) {
				e.printStackTrace(); // to print when there is no file found.
				scan.close();
				return;
				
			}
		
		} else if(type == 2) {
			
			System.out.println("Provide rows, cols and seed integer separated by space: ");
			row = scan.nextInt(); 
			col = scan.nextInt(); 
			seed = scan.nextInt();
			
			town = new Town(row, col);
			tNew = new Town(row, col);
			town.randomInit(seed);
		
			
		} 
		
		for (int i = 0; i < 12; i++) {
		
		profit = getProfit(town); // get profit by getProfit in this town
		profitSum += profit; // profitSum += profit to accumulate profits for 12 months cycle
		tNew = updatePlain(town); // update town to tNew town
		town = tNew; // declare town to tNew to use for profit = getProfit(town) and update town
		
		}
		
		
		profitSum = (profitSum * 100)/(16*12); // (16*12) because 16 is the maximum casual profit and 12 referring to 12 months cycle
		System.out.printf("%.2f",profitSum);
		
		
		
		scan.close();
		
		
	}
}
