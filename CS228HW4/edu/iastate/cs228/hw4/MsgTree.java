package edu.iastate.cs228.hw4;

import java.util.Stack;

/**
 * 
 * @author taewankim
 *
 */
public class MsgTree {

	public char payloadChar;
	public MsgTree left;
	public MsgTree right;
	public static int charTotal = 0; // to keep track of how many characters for extra credit statistics.
	/**
	 * MsgTree constructor with given string
	 * @param encodingString
	 */
	public MsgTree(String encodingString) {
		
		
		
		if (encodingString == null || encodingString.length() < 2) {
			
			return;
			
		}
		
		Stack<MsgTree> stack = new Stack(); // stacks to store characters
		int index = 0;
		this.payloadChar = encodingString.charAt(index);
		index++;
		stack.push(this);
		int inOrOut = 0; // 0 if into stack, 1 if out of stack
		MsgTree currentMsgTree = this;
		
		while (index < encodingString.length()) {
			
		
			MsgTree node = new MsgTree(encodingString.charAt(index));
			index++;
		
			// if last action was 0, into stack
			if (inOrOut == 0) {
				
				currentMsgTree.left = node;
				
				if (node.payloadChar == '^') {
					
					currentMsgTree = stack.push(node);
					inOrOut = 0;
					
				} else {
					
					if (stack.empty() == false) {
						
						currentMsgTree = stack.pop();
						
					}
					
					inOrOut = 1;
					
				}
				
			// if last action was 1, out of stack
			} else {
				
				currentMsgTree.right = node;
				if (node.payloadChar == '^') {
					
					currentMsgTree = stack.push(node);
					inOrOut = 0;
					
				} else {
					
					if (stack.empty() == false) {
						
						currentMsgTree = stack.pop();
						
					}
					
					inOrOut = 1;
					
				}
				
			}
			
		}
		
	}
		
	
	/**
	 * MsgTree constructor with single character
	 * @param payloadChar
	 */
	public MsgTree(char payloadChar) {
		
		this.left = null;
		this.right = null;
		this.payloadChar = payloadChar;
		
	}
	
	/**
	 * prints characters and codes
	 * 
	 * @param root
	 * @param code
	 */
	public static void printCodes(MsgTree root, String code) {
		
		if (root.payloadChar != '^' && root.payloadChar != '\n' && root.payloadChar != '\s') { 
			
			System.out.println("    " + root.payloadChar + "      " + code);
			
		} else if (root.payloadChar == '\n') { // if character is new line
			
			System.out.println("    \\n" + "     " + code);
			
		} else if (root.payloadChar == '\s') { // if character is space
			
			System.out.println("    \\s" + root.payloadChar + "    " + code);
			
		} 
		
		if (root.left != null) {
			
			printCodes(root.left, code + "0"); // recursive starting with left side of tree
			
		}
		
		if (root.right != null) {
			
			printCodes(root.right, code + "1");
			
		}
		
	}
	
	/**
	 * decodes message
	 * 
	 * @param root
	 * @param msg
	 */
	public static void decode(MsgTree root, String msg) {
		
		int index = 0;
		MsgTree curNode = root;
		String message = "";
		
		while(index < msg.length()) {
			
			if (curNode.payloadChar == '^') {
				
				if (msg.charAt(index) == '0') {
					
					curNode = curNode.left;
					
				}
				
				// other words, else if msg.charAt(index) == 1
				else {
					
					curNode = curNode.right;
					
				}
				
				if (curNode.payloadChar != '^') {
					
					message += curNode.payloadChar;
					curNode = root;
					charTotal++;
					
				}
				
				index++;
				
			}
			
			else {
				
				message += curNode.payloadChar;
				curNode = root;
				charTotal++;
				
			}
			
		}
		
		System.out.println(message);
		
	}
	
}
