package edu.iastate.cs228.hw1;

/**
 * 
 * @author Taewan Kim
 *
 */
public class STREAMER extends TownCell {

	public STREAMER(Town p, int r, int c) {
		super(p, r, c);
	
	}

	@Override
	public State who() {
		
		return State.STREAMER;
		
	}

	@Override
	public TownCell next(Town tNew) {
		
		
		census(nCensus);
		
		if (nCensus[EMPTY]+nCensus[OUTAGE] <= 1) {
			
			return new RESELLER(tNew,row,col);
			
		}
		
		if (nCensus[RESELLER] >= 1) {
			
			return new OUTAGE(tNew, row, col);
			
		} else if(nCensus[OUTAGE] >= 1) {
			
			return new EMPTY(tNew, row, col);
			
		}
		
		
	
		if (nCensus[CASUAL] >= 5) {
			
			return new STREAMER(tNew,row,col);
			
		}
		
		return new STREAMER(tNew,row,col);
		
	}

}