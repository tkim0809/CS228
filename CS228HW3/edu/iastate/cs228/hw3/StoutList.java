package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;



/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class StoutList<E extends Comparable<? super E>> extends AbstractSequentialList<E>
{
  /**
   * Default number of elements that may be stored in each node.
   */
  private static final int DEFAULT_NODESIZE = 4;
  //
  /**
   * Number of elements that can be stored in each node.
   */
  private final int nodeSize;
  
  /**
   * Dummy node for head.  It should be private but set to public here only  
   * for grading purpose.  In practice, you should always make the head of a 
   * linked list a private instance variable.  
   */
  public Node head;
  
  /**
   * Dummy node for tail.
   */
  private Node tail;
  
  /**
   * Number of elements in the list.
   */
  private int size;
  
  /**
   * Constructs an empty list with the default node size.
   */
  public StoutList()
  {
    this(DEFAULT_NODESIZE);
  }

  /**
   * Constructs an empty list with the given node size.
   * @param nodeSize number of elements that may be stored in each node, must be 
   *   an even number
   */
  public StoutList(int nodeSize)
  {
    if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();
    
    // dummy nodes
    head = new Node();
    tail = new Node();
    head.next = tail;
    tail.previous = head;
    this.nodeSize = nodeSize;
   
  }
  
  /**
   * Constructor for grading only.  Fully implemented. 
   * @param head
   * @param tail
   * @param nodeSize
   * @param size
   */
  public StoutList(Node head, Node tail, int nodeSize, int size)
  {
	  this.head = head; 
	  this.tail = tail; 
	  this.nodeSize = nodeSize; 
	  this.size = size; 
  }

  @Override
  public int size()
  {
    // TODO Auto-generated method stub
	  
    return size;
    
  }
  
  @Override
  public boolean add(E item)
  {
    // TODO Auto-generated method stub
	
	  if(item == null) {
			
			throw new NullPointerException();
			
		}
		
		// if the list is empty
		if(size == 0) {
			
			Node node = new Node();
			node.addItem(item);
			
			node.next = tail;
			node.previous = head;
			
			head.next = node;
			tail.previous = node;
			
			size++;
			return true;
			
			
		}  else if (tail.previous.count == nodeSize) { // if the node is full, create new node and store item in new node
			
			Node node = new Node();
			node.addItem(item);
			
			node.previous = tail.previous;
			node.next = tail;
			
			tail.previous.next = node;
			tail.previous = node;
			
			size++;
			return true;
			
		} else if (tail.previous.count < nodeSize) { // if the node is not full, add item to the node
			
			tail.previous.addItem(item);
			size++;
			return true;
			
		}
		
		return false;
    
  }
  

  @Override
  public void add(int pos, E item)
  {
    // TODO Auto-generated method stub
	 
	 if (pos < 0 || pos > size) {
		 
		 throw new IndexOutOfBoundsException();
		 
	 } 
	 
	 if (item == null) {
		 
		 throw new NullPointerException();
		 
	 }
	 
	 // if the list is empty
	 if (head.next == tail) {
		 
		 add(item);
		 
	 }
	 
	 NodeInfo nodeinfo = find(pos);
	 Node node = nodeinfo.node;
	 int offset = nodeinfo.offset;
	 
	 // if offset == 0 then,
	 // 1) if n has predecessor which has fewer than M elements (and is not the head), put x in n's predecessor
	 // 2) if n is the tail node and n's predessor has M elements, create a new node and put x at offset 0
	 
	 if (offset == 0) {
		 
		 if (node.previous != head && node.previous.count < nodeSize && node.count != 0) /** CASE1 **/ {
			 
			 node.previous.addItem(item);
			 size++;
			 return;
			 
		 } else if (node == tail) {
			 
			 add(item);
			 size++;
			 return;
			 
		 }
		 
	 }
	 
	 // if there is a space in node n, put x in node n at offset off, shifting elements as necessary
	 if (node.count < nodeSize) {
		 
		 node.addItem(offset, item);
		 
	 } else /**  Perform split operation, move the last M/2 elements of node n into a new successor node n  **/{
		 
		 Node newNode = new Node();
		 int count = 0;
		 int mHalf = nodeSize / 2; // M/2
		 
		 while(count < mHalf) {
			 
			 newNode.addItem(node.data[mHalf]);
			 node.removeItem(mHalf);
			 count++;
			 
		 }
		 
		 Node oldNode = node.next;
		 node.next = newNode;
		 newNode.previous = node;
		 newNode.next = oldNode;
		 oldNode.previous = newNode;
		 
		 // if off <= M/2, put X in node n at offset off
		 if (offset <= mHalf) {
			 
			 node.addItem(offset, item);
			 
		 }
		 
		 // if off >= M/2, put X in node n at offset (off - M/2)
		 if (offset > mHalf) {
			 
			 newNode.addItem((offset - mHalf), item);
			 
		 }
		 
	 }
	 
	 size++; // size 1 up because we added an element
	 
  }

  @Override
  public E remove(int pos)
  {
    // TODO Auto-generated method stub
	  
	 if (pos > size || pos < 0) {
		 
		 throw new IndexOutOfBoundsException();
		 
	 }
	 
	 
	 NodeInfo nodeinfo = find(pos);
	 int offset = nodeinfo.offset;
	 Node node = nodeinfo.node;
	 E eValue = node.data[offset];
	 
	 // if its located previously to tail and if it only has one element in it delete it
	 if (node.next == tail && node.count == 1) {
		 
		 Node currentPrev = node.previous; // previous node of current node
		 currentPrev.next = node.next;
		 node.next.previous = currentPrev; // tail previous set to previou of current node
		 node = null;
		 
	 } else if (node.next == tail || node.count > nodeSize / 2) /**   **/ {
		 
		 node.removeItem(offset);
		 
	 } else {
		 
		 node.removeItem(offset);
		 Node currentNext = node.next; // next node of current node
		 
		 if (currentNext.count > nodeSize / 2) {
			 
			 node.addItem(currentNext.data[0]);
			 currentNext.removeItem(0);
			 
		 } else if (currentNext.count <= nodeSize / 2) {
			 
			 for (int i = 0; i < currentNext.count; i++) {
				 
				 node.addItem(currentNext.data[i]);
				 
			 }
			 
			 node.next = currentNext.next;
			 currentNext.next.previous = node;
			 currentNext = null;
			 
		 }
		 
	 }
	  size--; // size 1 deducted because we removed an element
	  
    return eValue;

  }

  /**
   * Sort all elements in the stout list in the NON-DECREASING order. You may do the following. 
   * Traverse the list and copy its elements into an array, deleting every visited node along 
   * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting 
   * efficiency is not a concern for this project.)  Finally, copy all elements from the array 
   * back to the stout list, creating new nodes for storage. After sorting, all nodes but 
   * (possibly) the last one must be full of elements.  
   *  
   * Comparator<E> must have been implemented for calling insertionSort().    
   */
  public void sort()
  {
	  // TODO 
	  
	  E[] dataArray = (E[]) new Comparable[size];
	  
	  Node node = head.next;
	  int index = 0;
	  
	  while (node != tail) {
		  
		  for (int i = 0; i < node.count; i++) {
			  
			  dataArray[index] = node.data[i];
			  index++;
			  
		  }
		  
		  node = node.next;
		  
	  }
	  
	  head.next = tail;
	  tail.previous = head;
	  
	  insertionSort(dataArray, new insertionComparator());
	  size = 0;
	  for (int i = 0; i < dataArray.length; i++) {
		  
		  add(dataArray[i]);
		  
	  }
	  
  }
  
  /**
   * Sort all elements in the stout list in the NON-INCREASING order. Call the bubbleSort()
   * method.  After sorting, all but (possibly) the last nodes must be filled with elements.  
   *  
   * Comparable<? super E> must be implemented for calling bubbleSort(). 
   */
  public void sortReverse() 
  {
	  // TODO 
	  
	  E[] dataArray = (E[]) new Comparable[size];
	  
	  Node node = head.next;
	  int index = 0;
	 
	  while (node != tail) {
		  
		  for (int i = 0; i < node.count; i++) {
			  
			  dataArray[index] = node.data[i];
			  index++;
			  
		  }
		  
		  node = node.next;
		  
	  }
	  
	  head.next = tail;
	  tail.previous = head;
	  bubbleSort(dataArray);
	  size = 0;
	  
	  
	  for (int i = 0; i < dataArray.length; i++) {
		  
		  add(dataArray[i]);
		  
	  }
	  
	  
  }
  
  @Override
  public Iterator<E> iterator()
  {
    // TODO Auto-generated method stub
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    // TODO Auto-generated method stub
    return new StoutListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index)
  {
    // TODO Auto-generated method stub
    return new StoutListIterator(index);
  }
  
  /**
   * Returns a string representation of this list showing
   * the internal structure of the nodes.
   */
  public String toStringInternal()
  {
    return toStringInternal(null);
  }

  /**
   * Returns a string representation of this list showing the internal
   * structure of the nodes and the position of the iterator.
   *
   * @param iter
   *            an iterator for this list
   */
  public String toStringInternal(ListIterator<E> iter) 
  {
      int count = 0;
      int position = -1;
      if (iter != null) {
          position = iter.nextIndex();
      }

      StringBuilder sb = new StringBuilder();
      sb.append('[');
      Node current = head.next;
      while (current != tail) {
          sb.append('(');
          E data = current.data[0];
          if (data == null) {
              sb.append("-");
          } else {
              if (position == count) {
                  sb.append("| ");
                  position = -1;
              }
              sb.append(data.toString());
              ++count;
          }

          for (int i = 1; i < nodeSize; ++i) {
             sb.append(", ");
              data = current.data[i];
              if (data == null) {
                  sb.append("-");
              } else {
                  if (position == count) {
                      sb.append("| ");
                      position = -1;
                  }
                  sb.append(data.toString());
                  ++count;

                  // iterator at end
                  if (position == size && count == size) {
                      sb.append(" |");
                      position = -1;
                  }
             }
          }
          sb.append(')');
          current = current.next;
          if (current != tail)
              sb.append(", ");
      }
      sb.append("]");
      return sb.toString();
  }


  /**
   * Node type for this list.  Each node holds a maximum
   * of nodeSize elements in an array.  Empty slots
   * are null.
   */
  private class Node
  {
    /**
     * Array of actual data elements.
     */
    // Unchecked warning unavoidable.
    public E[] data = (E[]) new Comparable[nodeSize];
    
    /**
     * Link to next node.
     */
    public Node next;
    
    /**
     * Link to previous node;
     */
    public Node previous;
    
    /**
     * Index of the next available offset in this node, also 
     * equal to the number of elements in this node.
     */
    public int count;

    /**
     * Adds an item to this node at the first available offset.
     * Precondition: count < nodeSize
     * @param item element to be added
     */
    void addItem(E item)
    {
      if (count >= nodeSize)
      {
        return;
      }
      data[count++] = item;
      //useful for debugging
      //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
    }
  
    /**
     * Adds an item to this node at the indicated offset, shifting
     * elements to the right as necessary.
     * 
     * Precondition: count < nodeSize
     * @param offset array index at which to put the new element
     * @param item element to be added
     */
    void addItem(int offset, E item)
    {
      if (count >= nodeSize)
      {
    	  return;
      }
      for (int i = count - 1; i >= offset; --i)
      {
        data[i + 1] = data[i];
      }
      ++count;
      data[offset] = item;
      //useful for debugging 
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
    }

    /**
     * Deletes an element from this node at the indicated offset, 
     * shifting elements left as necessary.
     * Precondition: 0 <= offset < count
     * @param offset
     */
    void removeItem(int offset)
    {
      E item = data[offset];
      for (int i = offset + 1; i < nodeSize; ++i)
      {
        data[i - 1] = data[i];
      }
      data[count - 1] = null;
      --count;
    }
    
    
  }
  
  
  /**
   * private class NodeInfo to create find method
   * @author taewankim
   *
   */
  private class NodeInfo {
	  
	  public Node node;
	  public int offset;
	  
	  public NodeInfo(Node node, int offset) {
		  
		  this.node = node;
		  this.offset = offset;
		  
	  }
	  
  }
  
  /**
   * Finds information of its position and node of element
   * 
   * 
   * @param pos - position of its element
   * @return nodeInfo - where this given element is in which node and which index in the node
   */
  private NodeInfo find(int pos) {
	  
	  Node node = head.next;
	  int currentPos = 0;
	 
	  // while loop until we get to the given pos starting from the first node
	  while(node != tail) {
		  
		  if (currentPos + node.count <= pos) {
			  
			  currentPos += node.count;
			  node = node.next;
			  continue;
			  
		  }
		  
		  
		  NodeInfo nodeInfo = new NodeInfo(node, pos - currentPos);
		  return nodeInfo;
		  
	  }
	  
	 return null;
	  
  }
  
 
  private class StoutListIterator implements ListIterator<E>
  {
	// constants you possibly use ...   
	  
	// instance variables ... 
	  int startInd = 0, endInd = 0; // startInd = start index,  endInd = index of last action
	 public E[] dataArray;
	 int PREVorNEXT = -1; // will be assigned to = 0 if previous() is called, to = 1 if next() is called.
	/**
     * Default constructor 
     */
    public StoutListIterator()
    {
    	// TODO 
    	startInd = 0;
    	PREVorNEXT = -1;
    	copy();
    	
    }

    /**
     * Constructor finds node at a given position.
     * @param pos
     */
    public StoutListIterator(int pos)
    {
    	// TODO 
    	PREVorNEXT = -1;
    	startInd = pos;
    	copy();
    }
    
   
    
    /**
     * copies the data in array form to use it in iterator
     */
    public void copy() {
    	
    	dataArray = (E[]) new Comparable[size];
    	
    	int index = 0;
    	Node node = head.next;
    	
    	while(node != tail) {
    		
    		for (int i = 0; i < node.count; i++) {
    			
    			dataArray[index] = node.data[i];
    			index++;
    			
    		}
    		
    		node = node.next;
    		
    	}
    	
    }
    
    
    @Override
    public boolean hasNext()
   
    {
    	// TODO 
    	
    	if (startInd >= size) {
    		
    		return false;
    		
    	} else {
    		
    		return true;
    		
    	}

    }
    
  

    @Override
    public E next()
    {
    	// TODO 
    	
    
    	
    	if (hasNext() == false) {
    		
    		throw new NoSuchElementException();
    		
    	}
    	
    	PREVorNEXT = 1;
    	endInd = startInd;
    	
    	return dataArray[startInd++];
    	
    }

    @Override
    public void remove()
    {
    	// TODO 
    	
    	// if no previous() or next() method was called lastly
    	if (PREVorNEXT == -1) {
    		
    		throw new IllegalStateException();
    		
    	}
    	
    	if (PREVorNEXT == 0) /** if previous() was called lastly **/{
    		
    		StoutList.this.remove(startInd);
    		copy(); // call method because of changes
    		PREVorNEXT = -1; // set to default
    		
    		
    	} else if (PREVorNEXT == 1) /**  if next() was called lastly  **/{
    		
    		StoutList.this.remove(startInd-1);
    		copy();
    		startInd--;
    		PREVorNEXT = -1; // set to default
    		
    		if (startInd < 0) {
    			
    			startInd = 0;
    			
    		}
    		
    	}
    	
    	
    }

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		
		if (startInd <= 0) {
			
			return false;
			
		}
		
		return true;
	}

	@Override
	public E previous() {
		// TODO Auto-generated method stub
		
		
		
		if(hasPrevious() == false) {
			
			throw new NoSuchElementException();
			
		}
		
		PREVorNEXT = 0;
		startInd--;
		endInd = startInd;
		
		return dataArray[startInd];
		
	}

	@Override
	public int nextIndex() {
		// TODO Auto-generated method stub
		
		return startInd;
	
	}

	@Override
	public int previousIndex() {
		// TODO Auto-generated method stub
		return startInd - 1;
	}

	@Override
	public void set(E e) {
		// TODO Auto-generated method stub

		if (e == null) {
			
			throw new NullPointerException();
			
		}
		
		if (PREVorNEXT == -1) {
			
			throw new IllegalStateException();
			
		}
		
		if (PREVorNEXT == 0) {
			
			NodeInfo nodeinfo = find(startInd);
			nodeinfo.node.data[nodeinfo.offset] = e;
			dataArray[startInd] = e;
			
		} else if (PREVorNEXT == 1) {
			
			NodeInfo nodeinfo = find(startInd-1);
			nodeinfo.node.data[nodeinfo.offset] = e;
			dataArray[startInd - 1] = e;
			
		}
	
	}

	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
		if (e == null) {
			
			throw new NullPointerException();
			
		}
		
		
		StoutList.this.add(startInd, e);
		startInd++;
		copy();
		PREVorNEXT = -1;
		endInd = startInd;
		
	}
    
    // Other methods you may want to add or override that could possibly facilitate 
    // other operations, for instance, addition, access to the previous element, etc.
    // 
    // ...
    // 
  }
  

  /**
   * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order. 
   * @param arr   array storing elements from the list 
   * @param comp  comparator used in sorting 
   */
  private void insertionSort(E[] arr, Comparator<? super E> comp)
  {
	  // TODO
	  
	
	  
	  for (int i = 1; i < arr.length; i++) {
		  
		  int j = i - 1;
		  E key = arr[i];
		  
		  while(j >= 0 && comp.compare(arr[j], key) > 0) {
			  
			  arr[j+1] = arr[j];
			  j--;
			  
		  }
		  
		  arr[j+1] = key;
		  
	  }
	  
  }
  
 
  
  /**
   * comparator method created to use it in insertionsort
   * @author taewankim
   *
   * @param <E>
   */
  class insertionComparator<E extends Comparable<E>> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		// TODO Auto-generated method stub
		return o1.compareTo(o2);
	}
	  
	  
	  
  }
  
  /**
   * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a 
   * description of bubble sort please refer to Section 6.1 in the project description. 
   * You must use the compareTo() method from an implementation of the Comparable 
   * interface by the class E or ? super E. 
   * @param arr  array holding elements from the list
   */
  private void bubbleSort(E[] arr)
  {
	  // TODO
	  
	  for (int i = 0; i < arr.length-1; i++) {
		  
		  for (int j = 0; j < arr.length - i - 1; j++) {
			  
			  if (arr[j].compareTo(arr[j+1]) < 0) {
				  
				  E temp = arr[j];
				  arr[j] = arr[j+1];
				  arr[j+1] = temp;
				  
			  }
			  
		  }
		  
	  }
	  
  }
 

}