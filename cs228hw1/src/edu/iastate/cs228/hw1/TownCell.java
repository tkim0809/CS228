package edu.iastate.cs228.hw1;

/**
 * 
 * @author Taewan Kim
 *	
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	
	// constants to be used as indices
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 
		
		
		int i, j, x, y;
		// i = row or length
		// j = column or width
		
		x = row + 1;
		y = col + 1;
		/* +1 to start counting census from +1 row and +1 col
		// For example, if we have (1,1) Towncell, then we want the for loop
		// to start with (2,2) Towncell and iterate left for 3 times 
		// Assuming S is in grid (1,1)
		// O E C  <<  this line counts thirdly starting with C(0,2) "" 
		// R S R  <<  this line counts secondly starting with R(1,2) again iterating same direction
		// C O C  <<  this line counts first starting with C(2,2) iterating left to O then C
	    //
		// We put ArrayIndexOutOfBoundsException to continue when there is no neighborhood
		/ Towncell*/ 

		
		
		for (i = x; (i >= x - 2); i--) {
		//	x - 2 to make this for loop to run 3 times, same as y-2 for j for loop
			
			for (j = y; j >= y - 2; j--) {
				
				
				
				try {
					
				if (i == row && j == col) {
					// Do nothing because we are not counting current State into nCensus[] 
					
				} else if(plain.grid[i][j].who() == State.RESELLER) {
					
					nCensus[RESELLER] += 1;
					
				} else if (plain.grid[i][j].who() == State.EMPTY) {
					
					nCensus[EMPTY] += 1;
					
				} else if (plain.grid[i][j].who() == State.CASUAL) {
					
					nCensus[CASUAL] += 1;
					
				} else if (plain.grid[i][j].who() == State.OUTAGE) {
					
					nCensus[OUTAGE] += 1;
					
				} else if (plain.grid[i][j].who() == State.STREAMER) {
					
					nCensus[STREAMER] += 1;
					
				}
				
				
				
				} catch (ArrayIndexOutOfBoundsException e){
					
					continue;
					
				}
				
			}
			
		}  
		

	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}
