// Genevieve Leach
// CS 240 Section 1
// Programming Assignment 1
// 19 April 2016

/**
 * COPYRIGHT: smanna@cpp.edu
 * CS 240 Spring 2016
 * Programming Assignment 1
 *
 * STUDENTS SHOULD COMPLETE THIS CODE.
 * You will upload this code to Blackboard.
 *
 * Feel free to include your own utility private fields and methods.
 * But please make sure you do not change the signature
 * of the public methods provided. If you do so, your code
 * cannot be run automatically, and you will not be graded.
 *
 * If you do not write enough comments, at least two points
 * will be deducted from your assignment. Also make sure you
 * follow the coding conventions.
 *
 * Make sure you check all the boundary conditions while implementing
 * your code here.
 *
 **/
public class LinkedList {

  private Node head;
  private Node cursor;
  private int numNodes;
	
  // constructor
  public LinkedList() {
    head = null;
    numNodes=0;
  }

  //adds element to end of the list
  public void append(int elem) {
    if(head==null) {
    	head=new Node(elem, null);
    	numNodes++;
    } else {
    	cursor = head;
    	while(cursor.next!=null){
    		cursor = cursor.next;
    	}
    	cursor.next=new Node(elem, null);
    	numNodes++;
    }
  }

  //adds element to the beginning of the list
  public void prepend(int elem) {
	  if(head==null) {
	   	head=new Node(elem, null);
	   	numNodes++;
	  } else {
		Node secondNode = head;
		head = new Node(elem, secondNode);
		numNodes++;
	  }
  }

  // Post insert, element should be at a given index. Index is 0-
  // based. That means, insert(0, 5) is equivalent to prepend(5)
  public void insert(int index, int elem) {
    if(index == 0){
        prepend(elem);
    } else if (index==numNodes) {
    	append(elem);
    } else {
    	cursor = head;
    	for(int i = 0; i<index-1; i++){
    		cursor = cursor.next;
    	}
    	Node newNode = new Node(elem, cursor.next);
    	cursor.next = newNode;
    	numNodes++;
    }
  }

  //deletes element at index i(0-based)
  public void deleteElemAt(int i) {
    if (i>=numNodes){
    	// don't need to delete anything, the index is more than 
    	// the amount of nodes we have.
    } else if(i == 0){
    	head = head.next;
    	numNodes--;
    } else{
    	cursor = head;
    	for(int j = 0; j <i-1; j++){
    		cursor = cursor.next;
    	}
    	cursor.next = cursor.next.next;
    	numNodes--;
    }
  }

  //returns the index of the element found; return -1 if not found
  public int findElem(int elem) {
    int index = -1; // to hold the index the element is found at
    int counter = 0; // to count the index of each node
    if (head == null){
    	return -1;
    } else {
    	for (cursor = head; cursor != null; cursor = cursor.next){
    		if(cursor.getData() == elem){
    			index = counter;
    			return index;
    		} else {
    			counter++;
    		}
    	}
    // if this point of the loop is reached, element was not found and
    // an index of -1 is returned
    return index;
    }
  }

  //returns element at index i; return -1 if not found
  public int readElemAt(int i) {
    cursor = head;
    if(i == 0){
    	if(head == null){
        	return -1;
        } else {
    	return cursor.getData();
        }
    } else if (i>=numNodes) {
    	return -1; // higher index than however many nodes we have
    } else {
    	for(int j = 0; j<i; j++){
    		cursor = cursor.next;
    	}
    	return cursor.getData();
    }
  }

  //returns space separated list of elements such as "1 3 5 2".
  //For empty list it should return ""
  public String toString() {
    if(head==null){
      return ""; 
    } else {
      String list = "";
      cursor = head;
      while(cursor != null){
    	  list += (cursor.getData() + " ");
    	  cursor=cursor.next;
      }
      return list;
    }
  }


  // Defining node here
  // begin: node class
  //  WARNING: DO NOT MAKE ANY CHANGES TO THE NODE CLASS
  public class Node {
    private int data;
    private Node next;


    //for elements that are at the tail
    public Node(int data) {
      this.data=data;
      next=null;
    }

    //for everything else
    public Node(int data, Node next) {
      this.data=data;
      this.next=next;
    }

    public int getData() {
      return data;
    }
  } // end: node class
}
