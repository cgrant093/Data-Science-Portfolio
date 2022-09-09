/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* Node class: Creates generic nodes for linked lists			*/
/****************************************************************/

package project5;

public class Node<T> 
{
	// each node has data and a reference to the next node and previous node
	private T datum;
	private Node next;
	private Node previous;
	
	/****************************************************************/
	/* Method: Node constructor                        				*/
	/* Purpose: creates a generic node object						*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	public Node()
	{
		datum = null;
		next = null;
		previous = null;
	}
	
	/****************************************************************/
	/* Method: getDatum		                           				*/
	/* Purpose: gets private datum for node for other classes		*/
	/* Parameters:													*/
	/* Returns: Object												*/
	/****************************************************************/
	public T getDatum()
	{
		return datum;
	}
	
	/****************************************************************/
	/* Method: getNext		                          				*/
	/* Purpose: gets private next node for other classes			*/
	/* Parameters:													*/
	/* Returns: Node											    */
	/****************************************************************/
	public Node getNext()
	{
		return next;
	}
	
	/****************************************************************/
	/* Method: getPrevious                          				*/
	/* Purpose: gets private previous node for other classes		*/
	/* Parameters:													*/
	/* Returns: Node												*/
	/****************************************************************/
	public Node getPrevious()
	{
		return previous;
	}
	
	/****************************************************************/
	/* Method: setDatum                           					*/
	/* Purpose: sets private datum for node for other classes		*/
	/* Parameters:													*/
	/* 	Object datum:		datum node will have					*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void setDatum(T datum)
	{
		this.datum = datum;
	}
	
	/****************************************************************/
	/* Method: setNext                           					*/
	/* Purpose: sets private next node for other classes			*/
	/* Parameters:													*/
	/* 	Node next:			next node in linked list				*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void setNext(Node next)
	{
		this.next = next;
	}
	
	/****************************************************************/
	/* Method: setPrevious                         					*/
	/* Purpose: sets private previous node for other classes		*/
	/* Parameters:													*/
	/* 	Node previous:		previous node in linked list			*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void setPrevious (Node previous)
	{
		this.previous = previous;
	}
}
