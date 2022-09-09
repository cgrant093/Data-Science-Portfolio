/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* Calendar class: Holds calendar objects						*/
/****************************************************************/

package project5;

public class Calendar 
{
	//calendars are made of file location, calendar title, and appointment list
	private String dataFileName;
	private String title;
	private MyLinkedList<Appointment> appointments;
	
	/****************************************************************/
	/* Method: Calendar constructor                        			*/
	/* Purpose: creates calendar object from 3 pieces of information*/
	/* Parameters:													*/
	/* 	String dataFileName:			original file name			*/
	/*  String calendar:				calendar title				*/
	/*  MyLinkedList appointments:		linked list of appointments */
	/*  											in calendar		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public Calendar(String dataFileName, String title, 
			MyLinkedList<Appointment> appointments)
	{
		if(dataFileName != null 
				&& title != null
				&& appointments != null)
		{
			this.dataFileName = dataFileName;
			this.title = title;
			this.appointments = appointments;
		}
		else
		{
			this.dataFileName = dataFileName;
			this.title = null;
		}
	}
	
	/****************************************************************/
	/* Method: toString                           					*/
	/* Purpose: overwrites toString for calendar object				*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String toString()
	{
		//creates string that will be added to and sent back
		String calendarString = this.title;
		//indexing variable: index
		for(int index=0; index< this.getAppointments().size(); index++)
		{
			calendarString = calendarString
					+ this.getAppointments().get(index).toString();
		}
		return calendarString + "\n\n";
	}
	
	/****************************************************************/
	/* Method: getTitle                           					*/
	/* Purpose: gets calendar title variable for other classes		*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String getTitle()
	{
		return this.title;
	}
	
	/****************************************************************/
	/* Method: getDataFileName	                          			*/
	/* Purpose: gets private file name variable for other classes	*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String getDataFileName()
	{
		return this.dataFileName;
	}
	
	/****************************************************************/
	/* Method: getAppointments		                          		*/
	/* Purpose: gets appointment linked list for other classes		*/
	/* Parameters:													*/
	/* Returns: MyLinkedList									    */
	/****************************************************************/
	public MyLinkedList<Appointment> getAppointments()
	{
		return this.appointments;
	}
}
