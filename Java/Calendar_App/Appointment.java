/****************************************************************/
/* Cody Grant                                                   */
/* Login ID: gran7276                                           */
/* CS-102, Winter 2016                                          */
/* Programming Assignment 5                                     */
/* Appointment class: Holds appointment objects 				*/
/* 						& related quality checks				*/
/****************************************************************/

package project5;

public class Appointment 
{
	//Appointments are made of a date, start time, end time, and name
	private String date;
	private String startTime;
	private String endTime;
	private String name;
	
	/****************************************************************/
	/* Method: Appointment Constructor                              */
	/* Purpose: Makes an appointment object out of 5 pieces of info */
	/* Parameters:													*/
	/*  String[] parsedLine:	holds 4 other pieces of info        */
	/* Returns: n/a												    */
	/****************************************************************/
	public Appointment(String[] parsedLine)
	{
		//calls correctInfo to check if all information is possible information		
		if (correctInfo(parsedLine) == true)
		{
			this.date = parsedLine[0];
			this.startTime = parsedLine[1];
			this.endTime = parsedLine[2];
			this.name = parsedLine[3];
		}
		else
		{
			this.name = null;
		}
	}
	
	/****************************************************************/
	/* Method: correctInfo                             				*/
	/* Purpose: checks if all information is possible information 	*/
	/* Parameters:													*/
	/*  String[] parsedLine:	holds 4 other pieces of info        */
	/* Returns: boolean:		is the info correct?			    */
	/****************************************************************/
	private boolean correctInfo(String[] parsedLine) 
	{
		//finds number of objects in array
		int lineLength = parsedLine.length;
		
		//makes sure everything is correct before making an appointment
		if(lineLength == 4
				&& parsedLine[0].length() == 8 
				&& parsedLine[1].length() == 4 
				&& parsedLine[2].length() == 4
				&& parsedLine[3] != null)
		{
			if(goodDate(parsedLine[0]) == true &&
					goodTime(parsedLine[1], parsedLine[2]) == true)
			{
				return true;
			}
		}
		return false;
	}
	
	/****************************************************************/
	/* Method: goodTime                            					*/
	/* Purpose: checks if start and end times are possible 			*/
	/* Parameters:													*/
	/* 	String start:			startTime     						*/
	/*  String end:				endTime       						*/
	/* Returns: boolean:		is the time possible?			    */
	/****************************************************************/
	private boolean goodTime(String start, String end) 
	{
		//startTime made into integer from start String
		int startTime = Integer.parseInt(start);
		//endTime made into integer from end String
		int endTime = Integer.parseInt(end);
		
		//makes sure the times are good
		if((Integer.parseInt(start.substring(2)) > 59) 
				|| (Integer.parseInt(end.substring(2)) > 59))
		{
			return false;
		}
		if(startTime < endTime)
		{
			if (startTime > 2359 || startTime < 0000)
			{
				return false;
			}
			else if(endTime > 2359 || endTime < 0000)
			{
				return false;
			}
			return true;
		}
		return false;
	}
	
	/****************************************************************/
	/* Method: goodDate                             				*/
	/* Purpose: checks if date is possible 							*/
	/* Parameters:													*/
	/* 	String string:			calendar name	      				*/
	/* Returns: boolean:		is the date possible?			    */
	/****************************************************************/
	private boolean goodDate(String string) 
	{
		//year made into integer from some of date String
		int year = Integer.parseInt(string.substring(0,4));
		//month made into integer from some of date String
		int month = Integer.parseInt(string.substring(4,6));
		//day made into integer from some of date String
		int day = Integer.parseInt(string.substring(6,8));
		
		//only returns true if it works in our convoluted calendar
		if (year<=9999 && year >=0)
		{
			if(month > 0 && month <= 12)
			{
				if (day<=31 && day>0)
				{
					if((month == 6 || month == 8 
							|| month == 9 || month == 11))
					{
						if(day<=30)
						{
							return true;
						}
						return false;
					}
					else if(month == 2)
					{
						if(year%4 == 0 && year%2000 != 0 && day<=29)
						{
							return true;
						}
						else if(day<=28)
						{
							return true;
						}
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}

	/****************************************************************/
	/* Method: toString                            					*/
	/* Purpose: overrides toString method to print off appointments */
	/* 			in a specific format 								*/
	/* Parameters:													*/
	/* Returns: specifically formatted appointment information		*/
	/****************************************************************/
	public String toString()
	{
		return "\n" + this.date.substring(0,4) + "-"
				+ this.date.substring(4,6) + "-" + this.date.substring(6,8)
				+ " (" + this.startTime + "-" + this.endTime + ") "
				+ this.name; 
	}
	
	/****************************************************************/
	/* Method: toString2                          					*/
	/* Purpose: makes a different toString for writing to file		*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String toString2()
	{
		return this.date + "/" + this.startTime + "/" + 
				this.endTime + "/" + this.name;
	}
	
	/****************************************************************/
	/* Method: compareTime                             				*/
	/* Purpose: verifies if the time the user input falls in between*/
	/* 			a specific appointment start and end time			*/
	/* Parameters:													*/
	/*  String chosenTime:				user selected time	        */
	/* Returns: boolean:	does it fall under the correct time		*/
	/****************************************************************/
	public boolean compareTime(String chosenTime)
	{
		//startTime made into integer from startTime String variable
		int startTime = Integer.parseInt(this.startTime);
		//endTime made into integer from endTime String variable
		int endTime = Integer.parseInt(this.endTime);
		//time made into integer from user's chosen time
		int time = Integer.parseInt(chosenTime);
		
		if(time<=endTime && time>=startTime)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/****************************************************************/
	/* Method: getDate                      		    			*/
	/* Purpose: gets private date variable for other classes		*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String getDate()
	{
		return this.date;
	}
	
	/****************************************************************/
	/* Method: getStartTime		                          			*/
	/* Purpose: gets private start time variable for other classes	*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String getStartTime()
	{
		return this.startTime;
	}
	
	/****************************************************************/
	/* Method: getEndTime		                          			*/
	/* Purpose: gets private end time variable for other classes	*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String getEndTime()
	{
		return this.endTime;
	}
	
	/****************************************************************/
	/* Method: getName			                          			*/
	/* Purpose: gets private name variable for other classes		*/
	/* Parameters:													*/
	/* Returns: String											    */
	/****************************************************************/
	public String getName()
	{
		return this.name;
	}
}

