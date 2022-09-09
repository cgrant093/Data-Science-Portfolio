/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* MyLinkedList class: Creates generic linked list objects		*/
/* 						 & related methods						*/
/****************************************************************/

package project5;

public class MyLinkedList<T>
{
	//sets head of the linked list
	private Node<T> head;
	
	/****************************************************************/
	/* Method: generic MyLinkedList constructor       				*/
	/* Purpose: creates MyLinkedList object and sets head to null	*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	public MyLinkedList()
	{
		head = null;
	}
	
	/****************************************************************/
	/* Method: isEmpty                           					*/
	/* Purpose: finds out if linked list is empty					*/
	/* Parameters:													*/
	/* Returns: boolean:	is it empty?							*/
	/****************************************************************/
	public boolean isEmpty()
	{
		return (head==null);
	}
	
	/****************************************************************/
	/* Method: size			                           				*/
	/* Purpose: finds how many nodes are in the linked list			*/
	/* Parameters:													*/
	/* Returns: int:		number of elements					    */
	/****************************************************************/
	public int size()
	{
		//counts number of nodes
		int count = 0;
		//sets current to head
		Node<T> current = head;
		while(current != null)
		{
			count++;
			current = current.getNext();
		}
		return count;
	}
	
	/****************************************************************/
	/* Method: get			                          				*/
	/* Purpose: gets nodes in linked list at specific index			*/
	/* Parameters:													*/
	/* 	int index:			place to find node in list				*/
	/* Returns: Object:		data in node at index in linked list    */
	/****************************************************************/
	public T get(int index)
	{
		//sets current to head
		Node<T> current = head;
		
		while((current != null) && (index != 0))
		{
			index--;
			current = current.getNext();
		}
		
		if(current == null)
		{
			throw new IndexOutOfBoundsException();
		}
		
		return current.getDatum();
	}
	
	/****************************************************************/
	/* Method: add			                          				*/
	/* Purpose: adds a node in linked list							*/
	/* Parameters:													*/
	/* 	Object datum:		data for node that's being added		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void add(T datum)
	{
		//sets previous to null
		Node<T> previous = null;
		//sets current to head
		Node<T> current = head;
		
		//makes a new node: splice
		Node<T> splice = new Node<T>();
		splice.setDatum(datum);
		//makes index to count spot in list
		int index = 0;
		
		if(current != null)
		{
			index = compareAppointments(splice, current, index);
		
			while((current != null) && (index != 0))
			{
				index--;
				previous = current;
				current = current.getNext();
			}
			
			if(index != 0)
			{
				throw new IndexOutOfBoundsException();
			}
			
			splice.setNext(current);
			
			if(previous == null)
			{
				head = splice;
			}
			else
			{
				previous.setNext(splice);
				splice.setPrevious(previous);
				if(current != null)
				{
					current.setPrevious(splice);
				}
			}
		}
		else
		{
			head = splice;
		}
	}
	
	/****************************************************************/
	/* Method: compareAppointments                     				*/
	/* Purpose: figures out where to add an appointment in list		*/
	/* Parameters:													*/
	/* 	Node splice:		Node being added						*/
	/* 	Node current:		Current node method is looking at		*/
	/*  int index:			where in list splice should be added	*/
	/* Returns: int index										    */
	/****************************************************************/
	private int compareAppointments(Node splice, Node current, int index) 
	{
		while((current != null) &&
			(Integer.parseInt(((Appointment) splice.getDatum()).getDate()) 
			> Integer.parseInt(((Appointment) current.getDatum()).getDate())))
		{
			current = current.getNext();
			index++;
		}
		
		if((current != null) && (Integer.parseInt(
				((Appointment) splice.getDatum()).getDate()) 
				== Integer.parseInt(
						((Appointment) current.getDatum()).getDate())))
			while((current != null) &&
					(Integer.parseInt(
							((Appointment) splice.getDatum()).getStartTime()) 
					> Integer.parseInt(
							((Appointment) current.getDatum()).getStartTime())))
			{
				current = current.getNext();
				index++;
			}
		return index;
	}

	/****************************************************************/
	/* Method: remove	                           					*/
	/* Purpose: removes node from a linked list						*/
	/* Parameters:													*/
	/*	int index:			where the node needs to be removed from */
	/* Returns: Object:		data from node that was removed		    */
	/****************************************************************/
	public T remove(int index)
	{
		//sets current to head
		Node<T> current = head;
		
		while((current != null) && (index != 0))
		{
			index--;
			current = current.getNext();
		}
		
		if(current == null)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(current.getPrevious() == null)
		{
			head = current.getNext();
			head.setPrevious(null);
		}
		else
		{
			current.getPrevious().setNext(current.getNext());
			current.getNext().setPrevious(current.getPrevious());
		}
		return current.getDatum();
	}
	
	/****************************************************************/
	/* Method: removeAll                          					*/
	/* Purpose: removes all nodes from linked list					*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void removeAll()
	{
		//sets head to null to lose reference to the entire list
		head = null;
	}
}