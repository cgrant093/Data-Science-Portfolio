

#include "stdlib.h"
#include <cmath>
#include <iostream>

using namespace std;

// All values that should be constant
const double PI=4*atan(1);

const double value1=0;
const double value2=PI/4;
const double value3=PI/2;
const double value4=3*PI/4;
const double value5=PI;

// so I can be lazy and use a for loop to fill in all the derivatives.
const double radianValues[] = {value1,value2,value3,value4,value5};

int main()
{
	double stepsize=1;

	cout<<"\n\nFinding the derivatives of sin(x) at known radians: "
		<<value1<<", "<<value2<<", "<<value3<<", "<<value4<<", and "
		<<value5<<endl<<endl;

	cout<<"Please enter the stepsize (in radians) you'd like to use.\n"
		<<"For the best results, you should choose a value <= 0.01:\n";
	cin>>stepsize;
	
	// the approx derivative array to store all 5 values
	double derivative[5];
	// the exact derivative array to store all 5 values
	double exactDeriv[5];
	
	// runs through and finds all derivatives
	for (int i=0;i<5;i++)
	{
		derivative[i]=(sin(radianValues[i]+stepsize)
				-sin(radianValues[i])) / stepsize;
		exactDeriv[i]=cos(radianValues[i]);
	}

	// prints out results
	cout<<"\nThe approximate derivative of sin(0) is "<<derivative[0]<<endl
		<<"The exact derivative is "<<exactDeriv[0]<<endl<<endl
		<<"The approximate derivative of sin(PI/4) is "<<derivative[1]<<endl
		<<"The exact derivative is "<<exactDeriv[1]<<endl<<endl
		<<"The approximate derivative of sin(PI/2) is "<<derivative[2]<<endl
		<<"The exact derivative is "<<exactDeriv[2]<<endl<<endl
		<<"The approximate derivative of sin(3*PI/4) is "<<derivative[3]<<endl
		<<"The exact derivative is "<<exactDeriv[3]<<endl<<endl
		<<"The approximate derivative of sin(PI) is "<<derivative[4]<<endl
		<<"The exact derivative is "<<exactDeriv[4]<<endl<<endl<<endl;

	return 0;
}
