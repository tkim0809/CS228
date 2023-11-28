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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed

	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		 
		super(pts);
		algorithm = "mergesort";
		
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		
		mergeSortRec(points);
		
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
	
	   if (pts.length <= 1) {
		   
		   return;
		   
	   }
			
	   int midPoint = pts.length / 2;
	   Point[] left = new Point[midPoint];
	   Point[] right;
	   
	   if(pts.length % 2 == 0) {
		   
		   right = new Point[midPoint];
		   
	   } else {
		   
		   right = new Point[midPoint + 1];
		   
	   }
	   
	   for (int i = 0; i < midPoint; i++) {
		   
		   left[i] = pts[i];
		   
	   }
	   
	   for (int j = 0; j < right.length; j++) {
		   
		   right[j] = pts[midPoint+j];
		   
	   }
	   
	 
	   mergeSortRec(left);
	   mergeSortRec(right);
	   merge(pts, left, right);
	  
		
	}
	
	/**
	 * merge array of points by given points array
	 * 
	 * 
	 * @param pts
	 * @param l
	 * @param r
	 */
	private void merge(Point[] pts, Point[] l, Point[] r) {
		
		
		
		int leftPoint, rightPoint, resultPoint;
		leftPoint = rightPoint = resultPoint = 0; // pointers of left, right array and final array
		
		while(leftPoint < l.length || rightPoint < r.length) {
			
			if(leftPoint < l.length && rightPoint < r.length) {
				
				if(pointComparator.compare(l[leftPoint], r[rightPoint]) < 0) {
					
					pts[resultPoint++] = l[leftPoint++];
					
				} else {
					
					pts[resultPoint++] = r[rightPoint++];
					
				} 
				
			} else if (leftPoint < l.length) {
				
				pts[resultPoint++] = l[leftPoint++];
				
			} else if (rightPoint < r.length) {
				
				pts[resultPoint++] = r[rightPoint++];
				
			} 
			
		}

	}
	
	// Other private methods if needed ...

}
