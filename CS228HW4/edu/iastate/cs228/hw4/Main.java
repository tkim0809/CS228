package edu.iastate.cs228.hw4;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author taewankim
 *
 */
public class Main {
	
	/**
	 * Main to run codes
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("File name: ");
		Scanner scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();
		scanner.close();
		
		File file = new File(fileName);
		Scanner fileScan = new Scanner(file);
		String encodedMsg = fileScan.nextLine();
		String message = fileScan.nextLine();
		
		if (fileScan.hasNext()) { // if there is more than one line.
			
			encodedMsg += "\n" + message;
			message = fileScan.nextLine();
			
		}
		
		fileScan.close();
		
		String code = "";
		MsgTree root = new MsgTree(encodedMsg);
		
		System.out.println("character code\n------------------------");
		MsgTree.printCodes(root, code);
		
		System.out.println("\nMessage: ");
		MsgTree.decode(root, message);
		
		
		// for extra credits.
		System.out.println("\n\n\n");
		System.out.println("STATISTICS: ");
		System.out.print("Avg bits/char: ");
		double bits = (double) message.length() / MsgTree.charTotal;
		System.out.printf("%.1f", bits);
		System.out.println("\nTotal Characters: " + MsgTree.charTotal);
		System.out.print("Space savings: ");
		double space = (1.0 - ((double)message.length() / MsgTree.charTotal)/16.0) * 100.0;
		System.out.printf("%.1f", space);
	}
	
	
	

}
