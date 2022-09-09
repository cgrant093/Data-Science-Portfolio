/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* Database class: Holds linked list of calendar objects		*/
/* 					& most search/print off methods				*/
/****************************************************************/

package project5;

import java.io.File;
import javax.swing.JOptionPane;

public class Database 
{
	//Database is made of a tree of calendars
	private Tree calendars;
	
	/****************************************************************/
	/* Method: Database Constructor		                            */
	/* Purpose: Makes database which holds linked list of calendars */
	/* Parameters:													*/
	/*  MyLinkedList calendars:	linked list of calendar objects     */
	/* Returns: n/a												    */
	/****************************************************************/
	public Database(Tree calendars)
	{
		this.calendars = calendars;
	}
	
	/****************************************************************/
	/* Method: getCalendars              	            			*/
	/* Purpose: gets private calendars linked list for other classes*/
	/* Parameters:													*/
	/* Returns: MyLinkedList									    */
	/****************************************************************/
	public Tree getCalendars()
	{
		return this.calendars;
	}
	
	/****************************************************************/
	/* Method: searchAPhrase                            			*/
	/* Purpose: see if appointment name matches the user's selected */
	/* 			word/phrase 										*/
	/* Parameters:													*/
	/* 	Scanner scanner:		input user's selected phrase		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void searchAPhrase(String text) 
	{
		//temporary linked list to hold appointments matching user input
		MyLinkedList<Appointment> tempApp = new MyLinkedList();
		//temporary linked list to hold calendars matching user inputs
		Tree tempCal = new Tree();
		
		//makes sure an appointment with matching user input exists
		boolean infoMatches = false;
		
		//indexing variable: index
		for(int index=0; index<this.getCalendars().size(); index++)
		{
			//indexing variable: jndex
			for(int jndex=0; jndex< this.getCalendars().get(index)
					.getAppointments().size(); jndex++)
			{
				//needs to be able to search for pieces of name too
				if(this.getCalendars().get(index).getAppointments()
						.get(jndex).getName().toLowerCase()
						.indexOf(text.toLowerCase()) >= 0)
				{
					
					//Places correct appointments into temporary linked lists
					tempCal.add(this.getCalendars().get(index));
					tempApp.add(this.getCalendars().get(index)
								.getAppointments().get(jndex));
					//Found at least one calendar -> set infoMatches to true
					infoMatches = true;
				}
			}	
		}
		if(infoMatches == true)
		{
			//creates string that will be shown in JOptionPane later
			String result = "";
			//indexing variable: kndex
			for(int kndex=0; kndex<tempApp.size(); kndex++)
			{
				//prints the tempCal & tempApp linked lists
				result = result + tempCal.get(kndex).getTitle() + 
						tempApp.get(kndex).toString() + "\n";
			}
			JOptionPane.showMessageDialog(null, result);
		}
		else
		{
			JOptionPane.showMessageDialog(null,
					"Appointment not found for given phrase.");
		}
	}
	
	/****************************************************************/
	/* Method: searchATime                           				*/
	/* Purpose: see if appointment name matches the user's selected */
	/* 			date and time 										*/
	/* Parameters:													*/
	/* 	Scanner scanner:		input user's selected date and time	*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void searchATime(String date, String time) 
	{	
		//temporary linked list to hold appointments matching user input
		MyLinkedList<Appointment> tempApp = new MyLinkedList();
		//temporary linked list to hold calendars matching user inputs
		Tree tempCal = new Tree();
		
		//makes sure an appointment with matching user input exists
		boolean infoMatches = false;
		
		//indexing variable: index
		for(int index=0; index<this.getCalendars().size(); index++)
		{
			//indexing variable: jndex
			for(int jndex=0; jndex<this.getCalendars().get(index)
					.getAppointments().size(); jndex++)
			{
				//makes sure date and time are possible
				if((this.getCalendars().get(index).getAppointments().get(jndex)
						.getDate().toLowerCase().indexOf(date.toLowerCase())>=0)
						&& (this.getCalendars().get(index).getAppointments()
								.get(jndex).compareTime(time) == true))
				{
					//Places correct appointments into temporary linked lists
					tempCal.add(this.getCalendars().get(index));
					tempApp.add(this.getCalendars().get(index)
								.getAppointments().get(jndex));
					//Found at least one calendar -> set infoMatches to true
					infoMatches = true;
				}
				
			}
		}
		if(infoMatches == true)
		{
			//creates string that will be shown in JOptionPane later
			String result = "";
			//indexing variable: kndex
			for(int kndex=0; kndex<tempApp.size(); kndex++)
			{
				result = result + tempCal.get(kndex).getTitle() + 
						tempApp.get(kndex).toString() + "\n";
			}
			JOptionPane.showMessageDialog(null, result);
		}
		else
		{
			JOptionPane.showMessageDialog(null,
					"Appointment not found for given date/time.");
		}
	}
	
	/****************************************************************/
	/* Method: addAnAppointment                           			*/
	/* Purpose: adds appointment to a calendar and the database		*/
	/* Parameters:													*/
	/* 	Scanner scanner:		to read the user's input			*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void addAnAppointment(Calendar calendar,
			String[] appointmentProps) 
	{		
		//making an appointment object with appointmentProperties
		Appointment appointment = new Appointment(appointmentProps);
		if(appointment.getName() != null)
	    {
	    	calendar.getAppointments().add(appointment);
	    	JOptionPane.showMessageDialog(null,
	    			"Appointment added to " + calendar.getTitle(),
	    			"Appointment Added",
	    			JOptionPane.OK_OPTION);
	    }
		else
	    {
	    	JOptionPane.showMessageDialog(null, 
	    			"Appointment wasn't added due to incorrect info.",
	    			"Appointment NOT Added",
	    			JOptionPane.OK_OPTION);
	    }
	}

	/****************************************************************/
	/* Method: addACalendar             	              			*/
	/* Purpose: adds calendar to the database						*/
	/* Parameters:													*/
	/* 	Scanner scanner:		to read the user's input			*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void addACalendar(String fileName) 
	{
		//creates file object
		File file = null;
		//creates calendar object
		Calendar calendar = null;
		
		try
		{
			file = new File(fileName + ".txt");
			calendar = Prog5.importSingleFile(file, calendar, fileName);
			
			if(calendar != null)
			{
				this.getCalendars().add(calendar);
				JOptionPane.showMessageDialog(null,
						calendar + " was added to database.",
						"Calendar Found",
						JOptionPane.OK_OPTION);
			}
			// if empty, doesn't add calendar to database
			if(calendar.getAppointments() == null)
			{
				JOptionPane.showConfirmDialog(null, 
						"No appointments in " + calendar.getDataFileName(),
						"Calendar NOT Added1", JOptionPane.OK_OPTION);
			}
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			JOptionPane.showMessageDialog(null, 
					"Unable to parse files from directory path.",
					"Calendar File NOT Found!",
					JOptionPane.OK_OPTION);
		}
	}

	/****************************************************************/
	/* Method: removeAnAppointment         	              			*/
	/* Purpose: possible removes appointment from the calendar		*/
	/* Parameters:													*/
	/* 	Scanner scanner:		to read the user's input			*/
	/* Returns: n/a												    */
	/****************************************************************/
	public Boolean removeAnAppointment(Calendar calendar, 
								String[] appointmentName) 
	{	
		//runs through all appointments in a callendar
		//indexing variable: jndex
		for(int jndex=0; jndex<calendar.getAppointments().size(); jndex++)
		{
			//if everything in the appointment is equal to requested appointment
			//will show to user and ask if s/he wants it removed
			if((calendar.getAppointments().get(jndex)
					.getName().toLowerCase().compareTo(
					appointmentName[3].toLowerCase()) == 0) && 
				(calendar.getAppointments().get(jndex)
					.getEndTime().toLowerCase().compareTo(
					appointmentName[2].toLowerCase()) == 0) &&
				(calendar.getAppointments().get(jndex)
					.getStartTime().toLowerCase().compareTo(
					appointmentName[1].toLowerCase()) == 0) &&
				(calendar.getAppointments().get(jndex)
					.getDate().toLowerCase().compareTo(
					appointmentName[0].toLowerCase()) == 0))
            {
				//the reply the user give whether s/he wants to remove
				//the appointment shown
				int reply = JOptionPane.showConfirmDialog(null, 
						"Is " + calendar.getTitle() + 
						calendar.getAppointments().get(jndex).toString()
						+ " the appointment you'd like to remove?",
						"Appointment Removal", 
						JOptionPane.YES_NO_OPTION);
				
				if(reply == JOptionPane.YES_OPTION)
				{
					calendar.getAppointments().remove(jndex);
					JOptionPane.showMessageDialog(null,
							"Appointment was removed from calendar.",
							"Removed Calendar", 
							JOptionPane.OK_OPTION);
				}
				else if(reply == JOptionPane.NO_OPTION)
				{
					JOptionPane.showMessageDialog(null,
							"Appointment was not removed from calendar.",
							"Calendar NOT Removed", 
							JOptionPane.OK_OPTION);
				}
				return true;
            }
		} 
		return false;
	}

	/****************************************************************/
	/* Method: removesCalendar             	              			*/
	/* Purpose: possible removes calendar from the database			*/
	/* Parameters:													*/
	/* 	Scanner scanner:		to read the user's input			*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void removeACalendar(Calendar calendarChoice) 
	{
		//Shows user appointment and confirms removal
		//user's reply whether s/he wants to remove the calendar
		int reply = JOptionPane.showConfirmDialog(null, "Is " + calendarChoice 
				+ " the calendar you'd like to remove?", 
				"Confirm Removal", JOptionPane.YES_NO_OPTION);
		
		if (reply == JOptionPane.YES_OPTION)
	    {
			this.getCalendars().remove(calendarChoice);;
			JOptionPane.showMessageDialog(null,
					"\nCalendar was removed from database.", "Removed Calendar",
					JOptionPane.OK_OPTION);
		}
		else if(reply == JOptionPane.NO_OPTION)
		{
			JOptionPane.showMessageDialog(null,
					"\nCalendar was not removed from database.", 
					"Calendar NOT Removed", JOptionPane.OK_OPTION);
		}		
	}

	/****************************************************************/
	/* Method: size		                             				*/
	/* Purpose: finds the size of the database (calendar tree)		*/
	/* Parameters:													*/
	/* Returns: int: number of treeNodes in tre					    */
	/****************************************************************/
	public int size() 
	{
		return this.getCalendars().size();
	}

	/****************************************************************/
	/* Method: restoreDatabase()                       				*/
	/* Purpose: rereads files, 										*/
	/* 			0 appointment calendars removed from database		*/
	/* Parameters:													*/
	/* Returns: 												    */
	/****************************************************************/
	public void restoreDatabase() 
	{
		//hold all files names of calendars
		String[] fileNames = new String[this.getCalendars().size()];
		//indexing variable: index
		for(int index=0; index<this.getCalendars().size(); index++)
		{
			//removes .txt from end of file name to reuse add calendar method
			String[] split = this.getCalendars().get(index).
					getDataFileName().split(".txt");
			
			fileNames[index] = split[0];
			
			if(this.getCalendars().get(index).getAppointments().size() > 0)
			{
				//removes current calendar and re-adds it w/ new appointments
				this.getCalendars().remove(this.getCalendars().get(index));
				this.addACalendar(fileNames[index]);
			}
			else
			{
				//only removes calendar if no appointments in it
				this.getCalendars().remove(this.getCalendars().get(index));
				JOptionPane.showMessageDialog(null, 
						"No appointments found. Calendar from "
						+ fileNames[index] + ".txt was not added to database.",
						"Calendar Not Re-added",
						JOptionPane.OK_OPTION);
			}
		}
	}
}

