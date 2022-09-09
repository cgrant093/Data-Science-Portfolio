/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* UserGUI class: Creates everything dealing w/ main GUI		*/
/****************************************************************/

package project5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserGUI extends JFrame
{
	//To interact with database
	private Database database;
	// Many objects in this GUI
	private Container contents;

	// column 1
	private JPanel optionPanel;
	private JButton [] optionButtons;
	private String [] optionNames = {
			 		"Print all records",
					"Print one calendar",
					"Search",
					"Add/Remove",
					"Restores Database",
					"Write to files"};

	// column 2
	// option search, add, remove, print calendar
	private JPanel radioPanel;
	private ButtonGroup choices;
	// if search is selected
	private JRadioButton phraseButton, dateTimeButton;
	// if add/remove is selected
	private JRadioButton calendarButton, appointmentButton;
	// if print calendars is selected
	private JRadioButton[] calendarTreeButtons;
	
	// option print all records
	private JPanel recordsPanel;
	 
	// column 3
	// option search, add remove, print calendar
	private JPanel searchPanel;
	private JButton searchButton, addButton, removeButton, printButton;
	private JLabel search, calendar, calendarTitle, time, 
					date, startTime, endTime, name;
	private JTextField searchText, calendarText, dateText, startTimeText;
	private JTextField endTimeText, nameText;
	
	/****************************************************************/
	/* Method: UserGUI Constructor		                            */
	/* Purpose: Makes GUI and all the listeners attached			*/
	/* Parameters:													*/
	/*  Database database:	access the tree of calendars			*/
	/* 	Database restore:	the unchanged, initial database 		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public UserGUI(Database database)
	{
		//title of GUI
		super("Welcome to the CS-102 Calendar Program");
		
		//2 Databases possibly used (database will be used more than restore)
		this.database = database;
		
		//creates a 1 by 3 grid layout, each will have their own thing
		//to deal with
		contents = getContentPane();
		contents.setLayout(new GridLayout(1,3));
	
		// rows 2-5, column 2: contains radio buttons for some options
		// creates new radioPanel
		radioPanel = new JPanel();
		//flow layout
		radioPanel.setLayout(new FlowLayout());
		// different set of radio buttons
		phraseButton = new JRadioButton("Phrase", true);
		dateTimeButton = new JRadioButton("Date & Time");
		calendarButton = new JRadioButton("Calendar");
		appointmentButton = new JRadioButton("Appointment");
		 
		// create button group
		choices = new ButtonGroup();
		// all added to group, but only some will be used
		choices.add(phraseButton);
		choices.add(dateTimeButton);
		choices.add(calendarButton);
		choices.add(appointmentButton);
		
		// and register it on the radio buttons
		RadioButtonHandler choicesHandler = new RadioButtonHandler();
		//add them to the listener
		phraseButton.addItemListener(choicesHandler);
		dateTimeButton.addItemListener(choicesHandler);
		calendarButton.addItemListener(choicesHandler);
		appointmentButton.addItemListener(choicesHandler);
		
		//creates all calendar buttons and assigns listener and adds to group
		redoCalendarButtons(choicesHandler);
	
		//add them to the 2nd panel
		radioPanel.add(phraseButton);
		radioPanel.add(dateTimeButton);
		radioPanel.add(calendarButton);
		radioPanel.add(appointmentButton);
		
		// col 1: options (Main buttons), 6 by 1 grid layout
		//creates new optionPanel
		optionPanel = new JPanel();
		optionPanel.setLayout(new GridLayout(6,1));
		OptionButtonHandler optionHandler = 
				 new OptionButtonHandler(choicesHandler);
		optionButtons = new JButton[optionNames.length];

		//assigns option button labels and listeners and the first panel
		//indexing variable: index
		for(int index=0; index<optionNames.length; index++)
		{
			optionButtons[index] = new JButton(optionNames[index]);
		    optionButtons[index].addActionListener(optionHandler);
		    optionPanel.add(optionButtons[index]);
		}
		 
		// rows 2-5, column 3: text search boxes
		// creates new SearchPanel
		searchPanel = new JPanel();
		// flow layout
		searchPanel.setLayout(new FlowLayout());
		
		// create another handler
		SearchButtonHandler searchHandler = 
				new SearchButtonHandler(choicesHandler);
		
		//many different options for this
		//added necessary buttons
		searchButton = new JButton("Search");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		printButton = new JButton("Print");
		
		//added all necessary text fields
		//if search a phrase is selected
		search = new JLabel("Phrase to search for (up to 30 characters):"); 
		searchText = new JTextField(30); 
		
		//if appointment is selected
		date = new JLabel("Enter date (in format YYYYMMDD):");
		dateText = new JTextField(8);
		time = new JLabel("Enter time (military format: HHMM):");
		startTime = new JLabel(
			 "Start time of appointment (military format: HHMM):");
		startTimeText = new JTextField(4);
		endTime = new JLabel(
			 "End time of appointment (military format: HHMM):");
		endTimeText = new JTextField(4);
		name = new JLabel("Enter name of appointment:");
		nameText = new JTextField(30);
		
		calendarTitle = new JLabel("Calendar found in/will be added to:");
		//if calendar is selected
		calendar = new JLabel("Calendar file(add) / Calendar name(remove):");
		calendarText = new JTextField(30);

		//adds all to 3rd panel
		searchPanel.add(calendarTitle);
		searchPanel.add(calendar);
		searchPanel.add(calendarText);
		searchPanel.add(search);
		searchPanel.add(searchText);
		searchPanel.add(date);
		searchPanel.add(dateText);
		searchPanel.add(time);
		searchPanel.add(startTime);
		searchPanel.add(startTimeText);
		searchPanel.add(endTime);
		searchPanel.add(endTimeText);
		searchPanel.add(name);
		searchPanel.add(nameText);
		searchPanel.add(addButton);
		searchPanel.add(removeButton);
		searchPanel.add(printButton);
		searchPanel.add(searchButton);
		
		//adds all buttons to listener
		searchButton.addActionListener(searchHandler);
		addButton.addActionListener(searchHandler);
		removeButton.addActionListener(searchHandler);
		printButton.addActionListener(searchHandler);
		 
		// add panels to content pane
		contents.add(optionPanel);
		contents.add(radioPanel);
		contents.add(searchPanel);
		
		//sets all pane visibilities to true
		optionPanel.setVisible(true);
		radioPanel.setVisible(true);
		searchPanel.setVisible(true);
		
		//method that sets all 2nd and 3rd pane buttons, etc visibility to false
		visibility();
		
		//set size and sets gui visibility to true
		setSize(1200, 400);
		setVisible(true);
	}

	/****************************************************************/
	/* Method: redoCalendarButtons		                            */
	/* Purpose: if calendar added/removed, changes radio button opts*/
	/* Parameters:													*/
	/*  RadioButtonHandler choices Handler: to re-add calendar		*/
	/* 										buttons to listener		*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void redoCalendarButtons(RadioButtonHandler choicesHandler)
	{
		//calendarTreeButtons of length database size
		calendarTreeButtons = new JRadioButton[database.size()];
		//indexing variable: index
		for(int index=0; index<database.size(); index++)
		{
			calendarTreeButtons[index] = new JRadioButton(
				 database.getCalendars().get(index).getTitle());
			radioPanel.add(calendarTreeButtons[index]);
			choices.add(calendarTreeButtons[index]);
			calendarTreeButtons[index].addItemListener(choicesHandler);
		}
	}
	
	/****************************************************************/
	/* Method: visibility				                            */
	/* Purpose: sets all 2nd and 3rd pane components to false		*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	public void visibility()
	{
		//indexing variable: index
		for(int index=0; index<calendarTreeButtons.length; index++)
		{
			calendarTreeButtons[index].setVisible(false);
		}
		calendarTitle.setVisible(false);
		search.setVisible(false);
		searchText.setVisible(false);
		searchButton.setVisible(false);
		date.setVisible(false);
		dateText.setVisible(false);
		time.setVisible(false);
		startTime.setVisible(false);
		startTimeText.setVisible(false);
		endTime.setVisible(false);
		endTimeText.setVisible(false);
		name.setVisible(false);
		nameText.setVisible(false);
		calendar.setVisible(false);
		calendarText.setVisible(false);
		addButton.setVisible(false);
		removeButton.setVisible(false);
		printButton.setVisible(false);
		calendarButton.setVisible(false);
		appointmentButton.setVisible(false);
		phraseButton.setVisible(false);
		dateTimeButton.setVisible(false);
	}
	 
	/****************************************************************/
	/* Inner Class: OptionButtonHandler	                            */
	/* Purpose: deals with buttons added to this listener (1st pane)*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	private class OptionButtonHandler implements ActionListener
	{
		//sets for constructor because there's kinda a recursive method
		RadioButtonHandler choicesHandler;
		/****************************************************************/
		/* Method: SearchButtonHandler constructor                      */
		/* Purpose: creates SearchButtonHandler w/ a RadioButtonHandler	*/
		/* Parameters:													*/
		/* 		RadioButtonHandler choicesHandler:						*/
		/* 							so we know what choice is selected	*/
		/* Returns: n/a												    */
		/****************************************************************/
		public OptionButtonHandler(RadioButtonHandler choicesHandler) 
		{
			this.choicesHandler = choicesHandler;
		}
		
		/****************************************************************/
		/* Method: actionPerformed				                        */
		/* Purpose: deals with first pane button pressing				*/
		/* Parameters:													*/
		/* 		ActionEvent buttonPress:		A button was pressed	*/
		/* Returns: n/a												    */
		/****************************************************************/
		public void actionPerformed(ActionEvent buttonPress)
		{
			visibility();
			//prints off whole database
			if(buttonPress.getSource() == optionButtons[0])
			{
				database.getCalendars().printInOrder();
			}
			//prints off calendar
			else if(buttonPress.getSource() == optionButtons[1])
			{
				//indexing variable: index
				for(int index=0; index<calendarTreeButtons.length; index++)
				{
					calendarTreeButtons[index].setVisible(true);
				}
				printButton.setVisible(true);
			}
			//search phrase or time/date
			else if(buttonPress.getSource() == optionButtons[2])
			{		
				phraseButton.setVisible(true);
				dateTimeButton.setVisible(true);
				//search phrase
				if(phraseButton.isSelected())
				{
					search.setVisible(true);
					searchText.setVisible(true);
					searchButton.setVisible(true);
				}
				//search time and date
				else if(dateTimeButton.isSelected())
				{
					date.setVisible(true);
					dateText.setVisible(true);
					time.setVisible(true);
					startTimeText.setVisible(true);
					searchButton.setVisible(true);
				}
			}
			// add/remove calendar/appointment
			else if(buttonPress.getSource() == optionButtons[3])
			{
				calendarButton.setVisible(true);
				appointmentButton.setVisible(true);
				//appointment
				if(appointmentButton.isSelected())
				{
					calendarTitle.setVisible(true);
					calendarText.setVisible(true);
					date.setVisible(true);
					dateText.setVisible(true);
					startTime.setVisible(true);
					startTimeText.setVisible(true);
					endTime.setVisible(true);
					endTimeText.setVisible(true);
					name.setVisible(true);
					nameText.setVisible(true);
					addButton.setVisible(true);
					removeButton.setVisible(true);
				}
				//calendar
				else if(calendarButton.isSelected())
				{
					calendar.setVisible(true);
					calendarText.setVisible(true);
					addButton.setVisible(true);
					removeButton.setVisible(true);
				}
			}
			//restore to original database
			else if(buttonPress.getSource() == optionButtons[4])
			{
				database.restoreDatabase();
				redoCalendarButtons(choicesHandler);
				JOptionPane.showMessageDialog(null, 
						"Database restored",
						"Restored Database", JOptionPane.OK_OPTION);
			}
			//write database to files
			else if(buttonPress.getSource() == optionButtons[5])
			{
				Prog5.writeToDisk(database);
			}
		}
	}
	
	/****************************************************************/
	/* Inner Class: RadioButtonHandler	                            */
	/* Purpose: deals with buttons added to this listener (2nd Pane)*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	private class RadioButtonHandler implements ItemListener
	{
		/****************************************************************/
		/* Method: itemStateChanged				                        */
		/* Purpose: deals with 2nd pane radio button selection			*/
		/* Parameters:													*/
		/* 		ItemEvent radioClick:		Radio button selected		*/
		/* Returns: n/a												    */
		/****************************************************************/
		public void itemStateChanged(ItemEvent radioClick)
		{
			visibility();
			//appointment is selected
			if(appointmentButton.isSelected())
			{
				calendarTitle.setVisible(true);
				calendarText.setVisible(true);
				calendarButton.setVisible(true);
				appointmentButton.setVisible(true);
				date.setVisible(true);
				dateText.setVisible(true);
				startTime.setVisible(true);
				startTimeText.setVisible(true);
				endTime.setVisible(true);
				endTimeText.setVisible(true);
				name.setVisible(true);
				nameText.setVisible(true);
				addButton.setVisible(true);
				removeButton.setVisible(true);
			}
			//calendar is selected
			else if(calendarButton.isSelected())
			{
				calendarButton.setVisible(true);
				appointmentButton.setVisible(true);
				calendar.setVisible(true);
				calendarText.setVisible(true);
				addButton.setVisible(true);
				removeButton.setVisible(true);
			}
			//phrase is selected
			else if(phraseButton.isSelected())
			{
				phraseButton.setVisible(true);
				dateTimeButton.setVisible(true);
				search.setVisible(true);
				searchText.setVisible(true);
				searchButton.setVisible(true);
			}
			//date/time is selected
			else if(dateTimeButton.isSelected())
			{
				phraseButton.setVisible(true);
				dateTimeButton.setVisible(true);
				date.setVisible(true);
				dateText.setVisible(true);
				time.setVisible(true);
				startTimeText.setVisible(true);
				searchButton.setVisible(true);
			}
			//a calendar radio button is selected (specific calendar)
			else
			{
				//indexing variable: index
				for(int index=0; index<calendarTreeButtons.length; index++)
				{
					calendarTreeButtons[index].setVisible(true);
				}
				printButton.setVisible(true);
			}
		}
	}
	
	/****************************************************************/
	/* Inner Class: SearchButtonHandler	                            */
	/* Purpose: deals with buttons added to this listener (3rd Pane)*/
	/* Parameters:													*/
	/* Returns: n/a												    */
	/****************************************************************/
	private class SearchButtonHandler implements ActionListener
	{
		//sets for constructor because there's kinda a recursive method
		RadioButtonHandler choicesHandler;
		/****************************************************************/
		/* Method: SearchButtonHandler constructor                      */
		/* Purpose: creates SearchButtonHandler w/ a RadioButtonHandler	*/
		/* Parameters:													*/
		/* 		RadioButtonHandler choicesHandler:						*/
		/* 							so we know what choice is selected	*/
		/* Returns: n/a												    */
		/****************************************************************/
		public SearchButtonHandler(RadioButtonHandler choicesHandler) 
		{
			this.choicesHandler = choicesHandler;
		}
		
		/****************************************************************/
		/* Method: actionPerformed			 		                    */
		/* Purpose: deals with 3rd pane buttons. Meat of GUI is here	*/
		/* Parameters:													*/
		/* 		ActionEvent buttonPress: 		A button is pressed		*/
		/* Returns: n/a												    */
		/****************************************************************/
		public void actionPerformed(ActionEvent buttonPress)
		{
			//if search is pressed
			if(buttonPress.getSource() == searchButton)
			{
				//if phrase radio button is selected
				if(phraseButton.isSelected())
				{
					database.searchAPhrase(searchText.getText());
				}
				//if dateTime radio Button is selected
				else if(dateTimeButton.isSelected())
				{
					database.searchATime(
							dateText.getText(), startTimeText.getText());
				}
			}
			//if add button is pressed
			else if(buttonPress.getSource() == addButton)
			{
				//if calendar radio button is selected
				if(calendarButton.isSelected())
				{
					database.addACalendar(calendarText.getText());
					redoCalendarButtons(choicesHandler);
				}
				//if appointment radio button is selected
				else if(appointmentButton.isSelected())
				{
					//found is if calendar chosen is found
					Boolean found = false;
					
					//string array for creating an appointment
					String[] appointmentInfo = new String[4];
					appointmentInfo[0] = dateText.getText(); 
					appointmentInfo[1] = startTimeText.getText();
					appointmentInfo[2] = endTimeText.getText(); 
					appointmentInfo[3] = nameText.getText();
					
					//indexing variable: index
					for(int index=0; index<database.getCalendars().size(); 
							index++)
					{
						if(database.getCalendars().get(index)
								.getTitle().toLowerCase().equals(
								calendarText.getText().toLowerCase()))
						{
							database.addAnAppointment(
									database.getCalendars().get(index), 
									appointmentInfo);
							found = true;
						}
					}
					
					if(found == false)
					{
						JOptionPane.showMessageDialog(null, 
								"Calendar not found");
					}
				}
			}
			//if remove button is pressed
			else if(buttonPress.getSource() == removeButton)
			{
				//found is for if calendar is found
				Boolean found = false;
				//if calendar radio button is selected
				if(calendarButton.isSelected())
				{
					//indexing variable: index
					for(int index=0; index<calendarTreeButtons.length; index++)
					{
						if(calendarText.getText().toLowerCase().equals(
								database.getCalendars().get(index)
								.getTitle().toLowerCase()))
						{
							database.removeACalendar(
									database.getCalendars().get(index));
							found = true;
							redoCalendarButtons(choicesHandler);
						}
					}
					if(found == false)
					{
						JOptionPane.showMessageDialog(null, 
								"Calendar not found");
					}
				}
				//if appointment radio button is selected
				else if(appointmentButton.isSelected())
				{	
					//string array to create an appointment
					String[] appointmentInfo = new String[4];
					appointmentInfo[0] = dateText.getText(); 
					appointmentInfo[1] = startTimeText.getText();
					appointmentInfo[2] = endTimeText.getText(); 
					appointmentInfo[3] = nameText.getText();
					
					//indexing variable: index
					for(int index=0; index<database.getCalendars().size(); 
							index++)
					{
						if(database.getCalendars().get(index)
								.getTitle().toLowerCase().equals(
								calendarText.getText().toLowerCase()))
						{
							found = database.removeAnAppointment(
									database.getCalendars().get(index), 
									appointmentInfo);
							redoCalendarButtons(choicesHandler);
						}
					}
					if(found == false)
					{
						JOptionPane.showMessageDialog(null, 
								"Appointment not found");
					}
				}
			}
			//if print button is selected
			else if(buttonPress.getSource() == printButton)
			{
				//indexing variable: index
				for(int index=0; index<calendarTreeButtons.length; index++)
				{
					//finds specific calendar selected
					if(calendarTreeButtons[index].isSelected())
					{
						JOptionPane.showMessageDialog(null, 
								database.getCalendars().get(index).toString(),
								"Printed Calendar", JOptionPane.OK_OPTION);
					}
				}
			}
		}
	}
}
