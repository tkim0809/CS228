package edu.iastate.cs228.hw1;

/**
 * 
 * @author Taewan Kim
 *
 */
public class OUTAGE extends TownCell {

	public OUTAGE(Town p, int r, int c) {
		
		super(p, r, c);
		
	}

	@Override
	public State who() {
		
		return State.OUTAGE;
		
	}

	@Override
	public TownCell next(Town tNew) {
		
		
		return new EMPTY(tNew,row,col);
		
		
	}

}
