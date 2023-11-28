package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Taewan Kim
 *  
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;  
	
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length length or row of this Town
	 * @param width width or col of this Town
	 */
	public Town(int length, int width) {
		//TODO: Write your code here.
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width]; 
	}
	
	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName given fileName of User
	 * @throws FileNotFoundException throwing FileNotFoundException to use file
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		
		
		
		File file = new File(inputFileName);
		Scanner scan = new Scanner(file);
		
		length = scan.nextInt();
		width = scan.nextInt();
	
		grid = new TownCell[length][width];
		
		
		for (int i = 0; i < length; i++) {
			
			
			for (int j = 0; j < width; j++) {
		
				String compare = scan.next();
				// used to compare either its R,C,E,S, or O.
				// Going to use charAt because compare.charAt(0) changes everytime
				// we declare String compare = scan.next(); in starting line of j for loop
				
				if (compare.charAt(0) == 'R') {
					
					grid[i][j] = new RESELLER(this, i, j);
					
				} else if (compare.charAt(0) == 'C') {
					
					grid[i][j] = new CASUAL(this, i, j);
					
				} else if (compare.charAt(0) == 'E') {
					
					grid[i][j] = new EMPTY(this, i, j);
					
				} else if (compare.charAt(0) == 'S') {
				
					grid[i][j] = new STREAMER(this, i, j);
					
					
				} else if (compare.charAt(0) == 'O') {
					
					grid[i][j] = new OUTAGE(this, i, j);
					
				}
				
				
				
			}
			
		}

		scan.close();
			
		
	}
	
	/**
	 * Returns width of the grid.
	 * @return width or col of this town
	 */
	public int getWidth() {
		
		
		return this.width;
		
	}
	
	/**
	 * Returns length of the grid.
	 * @return length or row of this town
	 */
	public int getLength() {
		
		return this.length;
		
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 * @param seed given by user
	 */
	public void randomInit(int seed) {
		
		Random rand = new Random(seed);
		
		int i,j,randNum;
		
		
		
		for (i = 0; i < this.getLength(); i++) {
			
			for (j = 0; j < this.getWidth(); j++) {
				
				randNum = rand.nextInt(5);
				
				if (randNum == TownCell.RESELLER) {
					
					grid[i][j] = new RESELLER(this, i, j);
					
				} else if (randNum == TownCell.EMPTY) {
					
					grid[i][j] = new EMPTY(this, i, j);
					
				} else if (randNum == TownCell.CASUAL) {
					
					grid[i][j] = new CASUAL(this, i, j);
					
				} else if (randNum == TownCell.OUTAGE) {
					
					grid[i][j] = new OUTAGE(this,i,j);
					
				} else if (randNum == TownCell.STREAMER) {
					
					grid[i][j] = new STREAMER(this,i,j);
					
				}
				
			}
			
		}
		
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 * @return string form of TownCell grid 2d array
	 */
	@Override
	public String toString() {
		
		String s = "";
		
		
		int i,j;
		
		for (i=0; i < this.getLength(); i++) {
			
			for (j=0; j < this.getWidth(); j++) {
				
				if (grid[i][j].who() == State.RESELLER) {
					
					s += "R ";
					
				} else if (grid[i][j].who() == State.EMPTY) {
					
					s += "E ";
					
				} else if (grid[i][j].who() == State.CASUAL) {
					
					s += "C ";
					
				} else if (grid[i][j].who() == State.OUTAGE) {
					
					s += "O ";
					
				} else if (grid[i][j].who() == State.STREAMER) {
					
					s += "S ";
					
				}
				
			}
			
			s += "\n";
			
		}
	
		
		return s;
	}
}
