package edu.iastate.cs228.hw1;

/**
 * 
 * @author Taewan Kim
 *
 */
public class RESELLER extends TownCell {

	
	
	public RESELLER(Town p, int r, int c) {
		
		super(p, r, c);
		
	}

	@Override
	public State who() {
		
		return State.RESELLER;
		
	}

	@Override
	public TownCell next(Town tNew) {
		
		
		census(nCensus);
		
		if (nCensus[CASUAL] <= 3) {
			
			return new EMPTY(tNew, row, col);
			
		} else if (nCensus[EMPTY] >= 3) {
			
			return new EMPTY(tNew, row, col);
			
		}
		
		return new RESELLER(tNew,row,col);
	}
	
	

}
