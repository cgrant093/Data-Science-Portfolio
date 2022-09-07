
#include "stdlib.h"
#include <cmath>
#include <iostream>
#include <iomanip>

using namespace std;

//creates a PI constant that has many decimal places
const double PI = atan(1.0) * 4;
//creates a gravity constant (m/s/s)
const double g = 9.8;
//creates constant for speed of light (m/s)
const double c = 299792458;

// Method that takes degrees from user and converts into radians
// The calculation is just long enough, that it doesn't need to be in main
double radianCalc(int degrees)
{
	// creates a double for the radians in degrees
	double totalRad = (double)degrees*PI / 180;
	// returns a value between [0,2*PI)
	return fmod(totalRad, (2 * PI));
}

double rangeCalc(double radian, double velocity)
{
	//this version of the formula takes the initial position of y to be zero
	//you can find this formula by taking the equations of motion for x and y 
	//solving for t in the y equation, substitute that into the x equation
	// Can be found on page XXX in Taylor's Classical Mechanics textbook
	return pow(velocity, 2)*sin(2 * radian) / g;
}


int main()
{
	//user input for angle in degree (not in radians)
	double degree;
	//user input for initial velocity in m/s
	double vel0;

	//asks user for an int value for their chosen degree
	cout << "Please enter the angle in degrees (not radians):\n";
	cin >> degree;

	//converts degrees to radians
	double radian = radianCalc(degree);

	//if the user tries to shoot it into the ground
	if (radian < 0 || radian > 2*PI)
	{
		cout << "This is not a subterrain missile. Good-bye!";
		return 0;
	}

	//asks user for an int value for their chosen degree
	cout << "\nPlease enter the initial velocity (in m/s):\n";
	cin >> vel0;
	
	//if the user tries to shoot the projectile at 
	//a speed with a negative magnitude
	//The range formula is negative anyway so it should be fine.
	if (vel0 < 0)
	{
		cout << "I'll make that magnitude positive for you!";
		return 0;
	}

	//if the user is curious about speeds greater than speed of light.
	//It shouldn't hurt the program, but I do warn them that it isn't correct
	if (vel0 > c)
	{
		cout << "\nI see, you're curious about the unphysical!";
	}

	double range = rangeCalc(radian, vel0);
	cout << "\nThe projectile lands in " << range << " meters.\n\n";

	return 0;
}

