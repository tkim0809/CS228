package edu.iastate.cs228.hw2;

import java.io.File;
import java.util.Scanner;



/**
 * 
 * @author Taewan Kim
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	public Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;   
	private String outputFileName;
	
	private AbstractSorter aSorter = null; 
	private int x, y = 0;
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) {
			
			throw new IllegalArgumentException();
			
		}
		
		points = new Point[pts.length];
		
		for (int i = 0; i < pts.length; i++) {
			
			points[i] = pts[i];
			
		}
		
		sortingAlgorithm = algo;
		
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			
			outputFileName = "Insertion.txt";
			
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			
			outputFileName = "Merge.txt";
			
		} else if (sortingAlgorithm == Algorithm.QuickSort) {
			
			outputFileName = "Quick.txt";
			
		} else if (sortingAlgorithm == Algorithm.SelectionSort) {
			
			outputFileName = "Selection.txt";
			
		}
		
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		
		sortingAlgorithm = algo;
		
		// if statements to initialize outputFileName to use when method WriteMCPtoFile is called
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			
			outputFileName = "Insertion.txt";
			
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			
			outputFileName = "Merge.txt";
			
		} else if (sortingAlgorithm == Algorithm.QuickSort) {
			
			outputFileName = "Quick.txt";
			
		} else if (sortingAlgorithm == Algorithm.SelectionSort) {
			
			outputFileName = "Selection.txt";
			
		}
		
		
		File file = new File(inputFileName);
		int count = 0;
		Scanner scan = new Scanner(file);
		
		// counting how many numbers of integer is in the file
		while(scan.hasNextInt()) {
			
			scan.nextInt();
			count++;
			
		}
		
		
		// check if the number of integers in the file is even
		if (count % 2 != 0) {
			
			scan.close();
			throw new InputMismatchException();
			
		}
		
		
		// initializing another scanner of file because scan is already used to count the number of integers in the file
		Scanner scanPoints = new Scanner(file); 
		
		
		
		int length = count / 2; // count / 2 because if there are # of 2n of integers it makes n points
		points = new Point[length];
		
		for (int i = 0; i < length; i++) {
			
			points[i] = new Point(scanPoints.nextInt(), scanPoints.nextInt());
			
		}
		
		
		
		scanPoints.close();
		scan.close();
		
	
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		 
		AbstractSorter aSorter = null; 
		if (sortingAlgorithm == Algorithm.InsertionSort) {
			
		    aSorter = new InsertionSorter(points);
			
		} else if (sortingAlgorithm == Algorithm.MergeSort) {
			
			aSorter = new MergeSorter(points);
			
		} else if (sortingAlgorithm == Algorithm.QuickSort) {
			
			aSorter = new QuickSorter(points);
			
		} else if (sortingAlgorithm == Algorithm.SelectionSort) {
			
			aSorter = new SelectionSorter(points);
			
		}
		
		long start = 0; // start, end, result to get nanoseconds time
		long end = 0;
		long resultX, resultY = 0;
		
	
		aSorter.setComparator(0); // sort x-axis
		start = System.nanoTime(); // start and end initialized between sort() to count nanoseconds of how long it takes to sort pts array
		aSorter.sort();
		end = System.nanoTime();
		resultX = end - start; // calculate nanoseconds of how long it took to sort
		x = aSorter.points[points.length/2].getX(); // store x coordinate value
		
		aSorter.setComparator(1); // sort y-axis
		start = System.nanoTime();
		aSorter.sort();
		end = System.nanoTime();
		resultY = end-start;
		y = aSorter.points[points.length/2].getY(); // store y coor value
		
		medianCoordinatePoint = new Point(x,y); // store medianCoordinatePoint to x and y we stored above
		scanTime = resultX+resultY;
		
		
		
		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		
		return sortingAlgorithm.name() + "	" + points.length + "	" + scanTime; 
		
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		
		return "MCP: (" + x + ", " + y + ")";
		
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		
		File file = new File(outputFileName);
		PrintWriter print = new PrintWriter(file);
		print.write(this.toString());
		print.close();
		
	}	

	

		
}
