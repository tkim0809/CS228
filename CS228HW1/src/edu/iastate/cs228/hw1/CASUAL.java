package edu.iastate.cs228.hw1;

/**
 * 
 * @author Taewan Kim
 *
 */
public class CASUAL extends TownCell {

	public CASUAL(Town p, int r, int c) {
		
		super(p, r, c);
	
	}

	@Override
	public State who() {

		return State.CASUAL;
		
	}

	@Override
	public TownCell next(Town tNew) {
	
		
		census(nCensus);
		
		if (nCensus[EMPTY]+nCensus[OUTAGE] <= 1) {
			
			return new RESELLER(tNew,row,col);
			
		}
		
		if (nCensus[RESELLER] >= 1) {
			
			return new OUTAGE(tNew, row, col);
			
		} else if (nCensus[STREAMER] >= 1) {
			
			return new STREAMER(tNew, row, col);
			
		}
		
		
	
		if (nCensus[CASUAL] >= 5) {
			
			return new STREAMER(tNew,row,col);
			
		}
		
		return new CASUAL(tNew,row,col);
		
	}

}
