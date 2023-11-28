package edu.iastate.cs228.hw2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

/**
* @author Oskar Niesen
* Here is a really simple random test for each of your sorting methods.
* This is more of a general test and is not meant to test any edge cases.
* The test cases have been improved to account that the abstract sorter
* constructor should not modify the array passed into it.
* Note: The random value is not limited from -50 to 50.
*/
public class RandomSorterTests {
Point[] points;

@Before
public void SetupPoints() {
Random rand = new Random();
points = new Point[rand.nextInt(10, 50)];
for (int i = 0; i < points.length; i++) {
points[i] = new Point(rand.nextInt(), rand.nextInt());
}
}

@Test
public void SelectionSortTestX() {
Point.xORy = true;
SelectionSorter selectionSorter = new SelectionSorter(points);
selectionSorter.setComparator(0);
selectionSorter.sort();
selectionSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

@Test
public void SelectionSortTestY() {
Point.xORy = false;
SelectionSorter selectionSorter = new SelectionSorter(points);
selectionSorter.setComparator(1);
selectionSorter.sort();
selectionSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

@Test
public void InsertionSortTestX() {
Point.xORy = true;
InsertionSorter insertionSorter = new InsertionSorter(points);
insertionSorter.setComparator(0);
insertionSorter.sort();
insertionSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

@Test
public void InsertionSortTestY() {
Point.xORy = false;
InsertionSorter insertionSorter = new InsertionSorter(points);
insertionSorter.setComparator(1);
insertionSorter.sort();
insertionSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

@Test
public void MergeSortTestX() {
Point.xORy = true;
MergeSorter mergeSorter = new MergeSorter(points);
mergeSorter.setComparator(0);
mergeSorter.sort();
mergeSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

@Test
public void MergeSortTestY() {
Point.xORy = false;
MergeSorter mergeSorter = new MergeSorter(points);
mergeSorter.setComparator(1);
mergeSorter.sort();
mergeSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

@Test
public void QuickSortTestX() {
Point.xORy = true;
QuickSorter quickSorter = new QuickSorter(points);
quickSorter.setComparator(0);
quickSorter.sort();
quickSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

@Test
public void QuickSortTestY() {
Point.xORy = false;
QuickSorter quickSorter = new QuickSorter(points);
quickSorter.setComparator(1);
quickSorter.sort();
quickSorter.getPoints(points);
Assert.assertTrue(checkPoints());
}

boolean checkPoints() {
for (int i = 1; i < points.length; i++) {
if (points[i - 1].compareTo(points[i]) > 0)
return false;
}
return true;
}
}