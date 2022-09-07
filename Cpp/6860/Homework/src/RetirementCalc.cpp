

#include "stdlib.h"
#include <iostream>	
#include <sstream> 	//num to string
#include <fstream> 	//writing to file
#include <iomanip> 	//setprecision

using namespace std;

// writes my string output to a text file
void to_file(string output, string fileTitle);

// used to convert (mainly) numbers to string format
template <typename F>
string to_string(F f);

int main(int argc, char* argv[])
{
	// so people know how to run program properly
	if(argc != 7)
	{
		cout << "\nMore arguements required to the program!\n"	
			"\nFirst: initial salary\n"
			"Second: salary invested per year (percentage)\n"
			"Third: salary raise invested per year (percentage)\n"
			"Fourth: annual return per year (percentage)\n"
			"Fifth: years spent investing \n"
			"Sixth: yearly fee from investment firm (percentage)\n"
			"(percentage: chose 10%? then input 10) Thank you!\n\n";

		return -1;
	}
	
	// current salary
	double salary=atof(argv[1]);
	// how much of your salary to invest per year (percentage)
	double investmentPercent=atof(argv[2]);
	// percentage of salary raise per year
	double salaryRaise=atof(argv[3]);
	// annual return rate
	double interestRate=atof(argv[4]);
	// years spent investing
	double years=atof(argv[5]);
	// yearly fee from people handling your money
	double yearlyFee=atof(argv[6]);

	// current amount you have saved
	double fund=0;

	// title for the txt file
	string fileTitle = "Salary"+to_string(salary)+"+"+
				to_string(salaryRaise)+"percIncreasePerYr"+
				"_Investing"+to_string(investmentPercent)+
				"percSalaryPerYr-"+to_string(yearlyFee)+
				"feePercPerYr_Length"+to_string(years)+
				"yrs_with"+to_string(interestRate)
				+"percGrowthPerYr.txt";
	string output = "\n\n";

	int interestFlux = 0;
	double adjInterest = 0;

	for(int i=0;i<years;i++)
	{
		// creates a range from -2 to 2
		interestFlux = (rand() % 5) - 2;
		// adds flutuations to interest to better model real life
		adjInterest = interestRate + (double)interestFlux;

		// compound interest formula
		// adds the amount you add in that year
		// takes away your yearly fee
		fund = (fund*(1+(adjInterest/100))
			+ (salary*(investmentPercent/100))) * (1-(yearlyFee/100));
		
		// adds to output string. Will print out string when complete
		if(i+1==years)
		{
			output = output + "\nTotal Retiremend fund is: "
				+to_string(fund)+"\n\n";
		}

		else
		{
			output = output +"Fund after "+
				to_string(i+1)+" years: "+
				to_string(fund)+" with "+
				to_string(adjInterest)+
				" percent return\n";
		}

		// salary increase
		salary = salary*(1+(salaryRaise/100));
	}
	
	//outputs in terminal
	cout << output;
	//outputs into text file
	to_file(output,fileTitle);

	return 0;
}

// writes my string output to a text file
void to_file(string output, string fileTitle)
{
	ofstream file;
	file.open(fileTitle.c_str());
		
	if (!file)
	{
		cout<<"Couldn't open file."<<endl;
	}
	else 
	{
		file << output;
		file.close();
		cout<<"Data written to file!\n\n";	
	}

	
}

// used to convert (mainly) numbers to string format
template <typename F>
string to_string(F f)
{
	stringstream ss;
	ss << fixed << setprecision(2) << f;
	return ss.str();
}
