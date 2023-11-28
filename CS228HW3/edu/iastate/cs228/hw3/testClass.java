package edu.iastate.cs228.hw3;



import java.util.Iterator;
import java.util.ListIterator;

import edu.iastate.cs228.hw3.StoutList;

public class testClass {

	
	public static void main(String[] args) {
		
		
		
		
		
		StoutList<String> n = new StoutList<>(); 
		n.add("A");  n.add("B");  n.add("1");	 n.add("2"); 
		n.add("C");	 n.add("D");  n.add("E");	 n.add("F");
	
		n.remove(2); 
		n.remove(2); 
		n.remove(5);
		ListIterator<String> iter = n.listIterator();
		
		n.add("V");
		System.out.println(n.toStringInternal(iter));
		n.add("W");
		System.out.println(n.toStringInternal(iter));
		n.add(2,"X");
		System.out.println(n.toStringInternal(iter));
		n.add(2,"Y");
		System.out.println(n.toStringInternal(iter));
		n.add(2,"Z");
		System.out.println(n.toStringInternal(iter));
		
		n.remove(9);
		System.out.println(n.toStringInternal(iter));
		n.remove(3);
		System.out.println(n.toStringInternal(iter));
		n.remove(3);
		System.out.println(n.toStringInternal(iter));
		n.remove(5);
		System.out.println(n.toStringInternal(iter));
		n.remove(3);
		System.out.println(n.toStringInternal(iter));
		
		/**n.add("V");
		assertEquals("[(A, B, -, -), (C, D, E, V)]",n.toStringInternal());
		n.add("W");
		assertEquals("[(A, B, -, -), (C, D, E, V), (W, -, -, -)]",n.toStringInternal());
		n.add(2,"X");
		assertEquals("[(A, B, X, -), (C, D, E, V), (W, -, -, -)]",n.toStringInternal());
		n.add(2,"Y");
		assertEquals("[(A, B, Y, X), (C, D, E, V), (W, -, -, -)]",n.toStringInternal());
		n.add(2,"Z");
		assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V), (W, -, -, -)]",n.toStringInternal());
	//	System.out.println(n.toStringInternal());


		//Examples removing elements from a list

		//Removes W
		n.remove(9);
	//	System.out.println(n.toStringInternal());
		assertEquals("[(A, B, Z, -), (Y, X, -, -), (C, D, E, V)]",n.toStringInternal());

		//Removes Y
		n.remove(3);
	//	System.out.println(n.toStringInternal());
		assertEquals("[(A, B, Z, -), (X, C, -, -), (D, E, V, -)]",n.toStringInternal());

		//Removes X (mini merge)
		n.remove(3);
	//	System.out.println(n.toStringInternal());
		assertEquals("[(A, B, Z, -), (C, D, -, -), (E, V, -, -)]",n.toStringInternal());
	//	System.out.println(n.toStringInternal());

		//Removes E (No merge with predecessor Node)
		n.remove(5);
	//	System.out.println(n.toStringInternal());
		assertEquals("[(A, B, Z, -), (C, D, -, -), (V, -, -, -)]",n.toStringInternal());
	//	System.out.println(n.toStringInternal());

		//Removes C (Full merge with successor Node)
		n.remove(3);
	//	System.out.println(n.toStringInternal());
		assertEquals("[(A, B, Z, -), (D, V, -, -)]",n.toStringInternal());
	//	System.out.println(n.toStringInternal()); **/
		
		/**iter.next();
		iter.set("Q");
		System.out.println(n.toStringInternal(iter));
		iter.set("N");
		System.out.println(n.toStringInternal(iter));
		iter.next();
		iter.next();
		iter.next();
		iter.previous();
		iter.set("P");
		System.out.println(n.toStringInternal(iter));
		iter.set("L");
		System.out.println(n.toStringInternal(iter));**/
		/**ListIterator<String> iter = n.listIterator(n.size());
		System.out.println(n.toStringInternal(iter));
		n.add("V");
		System.out.println(n.toStringInternal(iter));
		n.add("W");
		System.out.println(n.toStringInternal(iter));
		n.add(2,"X");
		System.out.println(n.toStringInternal(iter));
		n.add(2, "Y");
		System.out.println(n.toStringInternal(iter));
		n.add(2,"Z");
		System.out.println(n.toStringInternal(iter));
		n.remove("W");
		System.out.println(n.toStringInternal(iter));
		n.remove("Y");
		System.out.println(n.toStringInternal(iter));
		n.remove("X");
		System.out.println(n.toStringInternal(iter));
		n.remove("E");
		System.out.println(n.toStringInternal(iter));
		n.remove("C");
		System.out.println(n.toStringInternal(iter));**/
		
	}
	
}
