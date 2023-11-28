package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Taewan Kim
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 

	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
			super(pts);
			algorithm = "selectionsort";
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		 
		int length = points.length;
		
		
		
		for (int i = 0; i < length - 1; i++) {
			
			int min = i;
			
			for (int j = i+1; j < length; j++) {
				
				if (pointComparator.compare(points[j], points[min]) < 0) {
					
					min = j;
					
				}
				
			}
			
			
			swap(i, min);
			
		}
	
		
	}	
	
}