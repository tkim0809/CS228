package edu.iastate.cs228.hw1;

/**
 * 
 * @author Taewan Kim
 *
 */
public class EMPTY extends TownCell {

	
	
	public EMPTY(Town p, int r, int c) {
		
		super(p, r, c);
	
	}

	@Override
	public State who() {
	
		return State.EMPTY;
		
	}

	@Override
	public TownCell next(Town tNew) {
		
		
		census(nCensus);
		if (nCensus[EMPTY]+nCensus[OUTAGE] <= 1) {
			
			return new RESELLER(tNew,row,col);
			
		} else {
			
			return new CASUAL(tNew,row,col);
			
		} 
		
	}

}
