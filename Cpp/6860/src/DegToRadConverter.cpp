
#include "stdlib.h"
#include <cmath>
#include <iostream>
#include <iomanip>

using namespace std;

//creates a PI constant that has many decimal places
const double PI = atan(1.0) * 4; 

// Method that takes degrees from user and converts into radians
// The calculation is just long enough, that it doesn't need to be in main
double radianCalc(int degrees)
{
	// creates a double for the radians in degrees
	double totalRad = (double)degrees*PI / 180;
	// returns a value between [0,2*PI)
	return fmod(totalRad,(2 * PI));
}

int main()
{
	//variable that will store the user's chosen degree
	int degrees;

	//asks user for an int value for their chosen degree
	cout << "Please enter the degrees:\n";
	cin >> degrees;

	cout << "\nThe radial distance in radians is:\n";
	// sets the output to only have 2 values after the decimal place
	cout << fixed << setprecision(2) << radianCalc(degrees);
	cout << "\n\n";

	return 0;
}

