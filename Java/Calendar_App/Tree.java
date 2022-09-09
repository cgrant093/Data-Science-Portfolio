/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* Node class: Creates tree of calendar objects					*/
/****************************************************************/

package project5;

import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

public class Tree 
{
	//root of the whole tree
	TreeNode root;
	
	/****************************************************************/
	/* Method: Tree	constructor            	        				*/
	/* Purpose: creates Tree object									*/
	/* Parameters:													*/
	/* Returns: n/a													*/
	/****************************************************************/
	public Tree()
	{
		root = null;
	}
	
	/****************************************************************/
	/* Method: search	                 	        				*/
	/* Purpose: return boolean whether or not TreeNode is found		*/
	/* Parameters:													*/
	/* 		Calendar datum:		calendar object being searched for	*/
	/* Returns: boolean: is it found								*/
	/****************************************************************/
	public boolean search(Calendar datum)
	{
		return search(datum, root);
	}

	/****************************************************************/
	/* Method: search, helper method       	        				*/
	/* Purpose: return boolean whether or not TreeNode is found		*/
	/* Parameters:													*/
	/* 		Calendar datum:		calendar object being searched for	*/	
	/* 		TreeNode current: 	current tree root being looked at	*/
	/* Returns: boolean: is it found								*/
	/****************************************************************/
	private boolean search(Calendar datum, TreeNode current) 
	{
		if(current == null)
		{
			return false;
		}
		if(current.getDatum().equals(datum))
		{
			return true;
		}
		if(current.getDatum().getTitle().toLowerCase().compareTo(
				datum.getTitle().toLowerCase()) < 0)
		{
			return search(datum, current.getRight());
		}
		else
		{
			return search(datum, current.getLeft());
		}
	}
	
	/****************************************************************/
	/* Method: add      	        								*/
	/* Purpose: add calendar into Tree as a TreeNode				*/
	/* Parameters:													*/
	/* 		Calendar datum:		calendar object being added			*/	
	/* Returns: n/a													*/
	/****************************************************************/
	public void add(Calendar datum)
	{
		root = add(datum, root);
	}
	
	/****************************************************************/
	/* Method: add, helper method	   								*/
	/* Purpose: add calendar into Tree as a TreeNode				*/
	/* Parameters:													*/
	/* 		Calendar datum:		calendar object being added			*/	
	/* 		TreeNode current: 	current tree root being looked at	*/	
	/* Returns: n/a													*/
	/****************************************************************/
	private TreeNode add(Calendar datum, TreeNode current)
	{
		if(current == null)
		{
			//creates new leaf if it found where it's supposed to be added
			TreeNode leaf = new TreeNode();
			leaf.setDatum(datum);
			return leaf;
		}
		if(current.getDatum().getTitle().toLowerCase().compareTo(
				datum.getTitle().toLowerCase()) < 0)
		{
			current.setRight(add(datum, current.getRight()));
		}
		else
		{
			current.setLeft(add(datum, current.getLeft()));
		}
		return current;
	}
	
	/****************************************************************/
	/* Method: remove      	        								*/
	/* Purpose: remove calendar from Tree							*/
	/* Parameters:													*/
	/* 		Calendar datum:		calendar object being added			*/	
	/* Returns: TreeNode being removed								*/
	/****************************************************************/
	public void remove(Calendar datum)
	{
		root = remove(datum, root);
	}
	
	/****************************************************************/
	/* Method: remove, helper method   								*/
	/* Purpose: remove calendar from Tree							*/
	/* Parameters:													*/
	/* 		Calendar datum:		calendar object being added			*/
	/* 		TreeNode current: 	current tree root being looked at	*/
	/* Returns: TreeNode being removed								*/
	/****************************************************************/
	private TreeNode remove(Calendar datum, TreeNode current)
	{
		if(current == null)
		{
			throw new NoSuchElementException();
		}
		
		if(current.getDatum().getTitle().toLowerCase().compareTo(
				datum.getTitle().toLowerCase()) < 0)
		{
			current.setRight(remove(datum, current.getRight()));
			return current;
		}
		if(current.getDatum().getTitle().toLowerCase().compareTo(
				datum.getTitle().toLowerCase()) > 0)
		{
			current.setLeft(remove(datum, current.getLeft()));
			return current;
		}
		
		if(current.getLeft() == null)
		{
			return current.getRight();
		}
		if(current.getRight() == null)
		{
			return current.getLeft();
		}
		//new heir if TreeNode being removed has 2 children
		TreeNode heir = current.getLeft();
		while(heir.getRight() != null)
		{
			heir = heir.getRight();
		}
		current.setDatum(heir.getDatum());
		current.setLeft(remove(heir.getDatum(), current.getLeft()));
		return current;
	}
	
	/****************************************************************/
	/* Method: get      	        								*/
	/* Purpose: gets calendar at index (left to right)				*/
	/* Parameters:													*/
	/* 		int index:		index to get calendar at				*/	
	/* Returns: Calendar at index									*/
	/****************************************************************/
	public Calendar get(int index)
	{
		return get(index, root).getDatum();
	}
	
	/****************************************************************/
	/* Method: get, helper method      								*/
	/* Purpose: gets calendar at index (left to right)				*/
	/* Parameters:													*/
	/* 		int index:		index to get calendar at				*/
	/* 		TreeNode current: 	current tree root being looked at	*/	
	/* Returns: Calendar at index									*/
	/****************************************************************/
	private TreeNode get(int index, TreeNode current)
	{
		if(current == null)
		{
			throw new IndexOutOfBoundsException();
		}
		
		//size of left subtree
		int subsize = size(current.getLeft());
		//compares subsize to root 
		if(index < subsize)
		{
			current = get(index, current.getLeft());
		}
		if(index > subsize)
		{
			current = get(index-(subsize+1), current.getRight());
		}
		
		return current;
	}
	
	/****************************************************************/
	/* Method: size      	        								*/
	/* Purpose: finds how many treeNodes are in the Tree			*/
	/* Parameters:													*/	
	/* Returns: int: size of tree									*/
	/****************************************************************/
	public int size()
	{
		return size(root);
	}
	
	/****************************************************************/
	/* Method: size, helper method     								*/
	/* Purpose: finds how many treeNodes are in the Tree			*/
	/* Parameters:													*/
	/* 		TreeNode current: 	current tree root being looked at	*/	
	/* Returns: int: size of tree									*/
	/****************************************************************/
	public int size(TreeNode current)
	{
		if(current == null)
		{
			return 0;
		}
		else
		{
			return 1 + size(current.getLeft()) + size(current.getRight());
		}
	}
	
	/****************************************************************/
	/* Method: printInOrder	        								*/
	/* Purpose: prints TreeNodes InOrder							*/
	/* Parameters:													*/	
	/* Returns: n/a													*/
	/****************************************************************/
	public void printInOrder()
	{
		//String that will print off in JOptionPane
		String printTree = printInOrder(root);
		JOptionPane.showMessageDialog(null, printTree);
	}
	
	/****************************************************************/
	/* Method: printInOrder	        								*/
	/* Purpose: prints TreeNodes InOrder							*/
	/* Parameters:													*/
	/* 		TreeNode current: 	current tree root being looked at	*/
	/* Returns: n/a													*/
	/****************************************************************/
	private String printInOrder(TreeNode current)
	{
		//string that will be returned, is the whole tree
		String printTree = "";
		if(current == null)
		{
			return "";
		}
		printTree = printTree + printInOrder(current.getLeft());
		printTree = printTree + current.getDatum().toString();
		printTree = printTree + printInOrder(current.getRight());	
		
		return printTree;
	}
}
