/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* Node class: Creates generic tree nodes for binary search tree*/
/****************************************************************/

package project5;

public class TreeNode 
{
	//Node is made of a calendar and a reference to left child and right child
	private Calendar datum;
	private TreeNode left;
	private TreeNode right;
	
	/****************************************************************/
	/* Method: TreeNode constructor                 				*/
	/* Purpose: creates a generic tree node object					*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	public TreeNode()
	{
		datum = null;
		left = null;
		right = null;
	}
	
	/****************************************************************/
	/* Method: getDatum		                           				*/
	/* Purpose: gets Calendar for tree node for other classes		*/
	/* Parameters:													*/
	/* Returns: T													*/
	/****************************************************************/
	public Calendar getDatum()
	{
		return datum;
	}
	
	/****************************************************************/
	/* Method: getRight                  	        				*/
	/* Purpose: gets right tree node for other classes				*/
	/* Parameters:													*/
	/* Returns: TreeNode											*/
	/****************************************************************/
	public TreeNode getRight()
	{
		return right;
	}
	
	/****************************************************************/
	/* Method: getLeft                  	        				*/
	/* Purpose: gets left tree node for other classes				*/
	/* Parameters:													*/
	/* Returns: TreeNode											*/
	/****************************************************************/
	public TreeNode getLeft()
	{
		return left;
	}
	
	/****************************************************************/
	/* Method: setDatum                           					*/
	/* Purpose: sets Calendar for tree node for other classes		*/
	/* Parameters:													*/
	/* 	Calendar datum:		Calendar datum tree node will have		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void setDatum(Calendar datum)
	{
		this.datum = datum;
	}
	
	/****************************************************************/
	/* Method: setRight                         					*/
	/* Purpose: sets right tree node for other classes				*/
	/* Parameters:													*/
	/* 	TreeNode right:		right node in binary search tree		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void setRight(TreeNode right)
	{
		this.right = right;
	}
	
	/****************************************************************/
	/* Method: setLeft                         						*/
	/* Purpose: sets left tree node for other classes				*/
	/* Parameters:													*/
	/* 	TreeNode left:		left node in binary search tree			*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void setLeft (TreeNode left)
	{
		this.left = left;
	}
}
