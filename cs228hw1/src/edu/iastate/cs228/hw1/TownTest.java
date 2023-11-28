package edu.iastate.cs228.hw1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class TownTest {

	@Test
	void test() {
		
		// tests if constructors are built correctly
		Town town = new Town(4,4);
		assertEquals(4, town.getLength()); // check if row or length is correctly implemented
		assertEquals(4, town.getWidth()); // check if col or width is correctly implemented
		
		 
		
		// testing, randomInit() and toString() method
		town.randomInit(10);
		System.out.println("test Expected:\nO R O R\nE E C O\nE S O S\nE O R R");
		System.out.println("test OUTPUT:\n" + town.toString());

		
	}
	
	@Test
	void testB() throws FileNotFoundException {
		
		
		// tests if constructors are built correctly using file
		String fileName = "ISP4x4.txt";
		
		Town town = new Town(fileName);
		assertEquals(4, town.getLength()); // check if row or length is correctly implemented
		assertEquals(4, town.getWidth()); // check if col or width is correctly implemented
		System.out.println("testB Expected:\nO R O R\nE E C O\nE S O S\nE O R R");
		System.out.println("testB OUTPUT:\n" + town.toString());
		
		
	}
	
	

}
