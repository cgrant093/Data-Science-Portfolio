/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* Prog2 class: Reads/writes files, creates database, calls GUI */
/****************************************************************/

package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.FileWriter;

public class Prog5 
{
	/****************************************************************/
	/* Method: main  		                         			    */
	/* Purpose: calls top level methods (big picture items)			*/
	/* Parameters:													*/
	/* 	String[] args:	passes in arguments from the command line	*/
	/* Returns: n/a												    */
	/****************************************************************/
	public static void main(String[] args) 
	{
		// makes sure arguments have been called.
		if(args.length <= 0)
		{
			JOptionPane.showMessageDialog(null, 
					"You need to specify at least one file.",
					"Error",
					JOptionPane.OK_OPTION);
			return;
		}
		
		//makes database (linked list of calendar objects)
		Database database = readDirectory(args);
		
		//makes sure database is not null
		if (database == null)
		{
			JOptionPane.showMessageDialog(null,
					"Unable to populate database. Exiting.",
					"Error",
					JOptionPane.OK_OPTION);
			System.exit(0);
		}
		
		// calls method to run gui
		runGUI(database);
	}
	
	/****************************************************************/
	/* Method: runGUI	                            				*/
	/* Purpose: creates GUI and calls class to run it				*/
	/* Parameters:													*/
	/*  Database database:	access the tree of calendars			*/
	/* 	Database restore:	the unchanged, initial database 		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public static void runGUI(Database database) 
	{
		//Creates a gui for user to work with
		UserGUI gui = new UserGUI(database);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/****************************************************************/
	/* Method: writeToDisk                             				*/
	/* Purpose: rewrites database to datafiles						*/
	/* Parameters:													*/
	/*  Database datbase:	access the tree of calendars			*/
	/* Returns: n/a												    */
	/****************************************************************/
	public static void writeToDisk(Database database) 
	{
		//runs through each calendar
		//indexing variable: index
		for(int index=0; index<database.getCalendars().size(); index++)
		{
			try 
			{
				//creates new file for calendar at index
				File file = new File(database.getCalendars()
						.get(index).getDataFileName());
				//fileWriter to write information to file
				FileWriter fileWriter;
				//rewrites over the file
				fileWriter = new FileWriter(file, false);
				//writes title to file
				fileWriter.write(database.getCalendars().get(index).getTitle());
				//indexing variable: jndex
				for(int jndex=0; jndex<database.getCalendars().get(index)
						.getAppointments().size(); jndex++)
				{
					//writes appointment to file with toString2
					fileWriter.write("\r\n" + database.getCalendars().get(index)
							.getAppointments().get(jndex).toString2());
				}
				fileWriter.close();
			} 
			catch (IOException e) 
			{
				JOptionPane.showMessageDialog(null,
						"Failed to overwrite the calendar:\n"
						+ database.getCalendars().get(index),
						"Error",
						JOptionPane.OK_OPTION);
			}	
		}
		JOptionPane.showMessageDialog(null, "Database files created.");
	}

	/****************************************************************/
	/* Method: readDirectory 			                            */
	/* Purpose: will scan a specified directory for files and 		*/
	/* 			creates a File array to send off to next method		*/
	/* Parameters:													*/
	/* 	String[] fileNames:		array of original file names		*/
	/* Returns: database eventually								    */
	/****************************************************************/
	public static Database readDirectory(String[] fileNames)
	{
		//makes a file array that will hold all the files
		File[] files = new File[fileNames.length];
		
		try
		{
			//indexing variable: index
			for(int index=0; index<fileNames.length; index++)
			{
				files[index] = new File(fileNames[index]);
			}
			//makes database (linked list of calendar objects)
			Database database = readFiles(files, fileNames);
			return database;
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			
			JOptionPane.showMessageDialog(null,
					"Unable to parse files from directory path.",
					"Error",
					JOptionPane.OK_OPTION);
			return null;
		}
	}

	/****************************************************************/
	/* Method: readFiles	 			                            */
	/* Purpose: will scan a files and send them off to make 		*/
	/* 			appointment constructors and a database constructor */
	/* Parameters:													*/
	/* 	File[] files:		an array of files to read				*/
	/* Returns: database 										    */
	/****************************************************************/
	public static Database readFiles(File[] files, String[] fileNames) 
	{
		//new linked list of calendar objects
        Tree calendars = new Tree();
        
        //indexing variable: index
        for(int index = 0; index<files.length; index++)
        {
    		//makes calendar, which is set in importSingleFile
    		Calendar calendar = null;
    		calendar = importSingleFile(
    				files[index], calendar, fileNames[index]);
            //linkList Calendars together
            calendars.add(calendar);
        }
        //creates database once all calendars linked together
        Database database = new Database(calendars); 
        return database;
	}
	
	/****************************************************************/
	/* Method: importSingleFile 		                            */
	/* Purpose: make a calendar from a file							*/
	/* Parameters:													*/
	/* 	File files:			imported file to make a calendar from	*/
	/* 	Calendar calendar: 	calendar object file will be made into	*/
	/* 	String fileName:	original file name 						*/
	/* Returns: Calendar object 								    */
	/****************************************************************/
	public static Calendar importSingleFile(
			File file, Calendar calendar, String fileName) 
	{
		try 
        {
			//makes scanner to read the files
			Scanner scanner = new Scanner(file);
			//first line in each file is calendar title
	        String calendarTitle = scanner.nextLine();
	        //linked list of appointments for each calendar
	        MyLinkedList<Appointment> appointments = new MyLinkedList();
	        
	        //every line following will be made into an appointment
	        while(scanner.hasNextLine()) 
	        {
	        	//appointment object made from each line following the first
	            Appointment appointment = new Appointment(
	            		scanner.nextLine().split("/"));
	            if(appointment.getName() != null)
	            {
	            	appointments.add(appointment);
	            }
	            else
	            {
	            	JOptionPane.showMessageDialog(null,
	            			"Appointment in " + calendarTitle +
	            			" calendar doesn't have correct info.\n",
	            			"Error",
	            			JOptionPane.OK_OPTION);
	            }
	        } 
	        
        	//make calendar object
        	calendar = new Calendar(fileName, calendarTitle, appointments);
	        
	        scanner.close();
        }
        catch(FileNotFoundException exc) 
        {
        	//skips over a file if it isn't found and continues
            JOptionPane.showMessageDialog(null,
            		"Unable to open file " + file + ", skipping.",
            		"Error",
            		JOptionPane.OK_OPTION);
        }
        catch(@SuppressWarnings("hiding") IOException exc) 
        {
        	//skips over a file if it cannot read it and continues
            JOptionPane.showMessageDialog(null,
            		"Error reading file " + file + ", skipping.",
            		"Error",
            		JOptionPane.OK_OPTION);
        }
        return calendar;
	}
}

