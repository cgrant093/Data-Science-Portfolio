
#include "stdlib.h"
#include <cmath>
#include <iostream>

using namespace std;

// simple f(x)=x^2
const double p0=0;
const double p1=0;
const double p2=1;

// precision constant
const double epsilon=pow(10,-4);

double pol2(double x);
double trapSum(double a, double b, int stepsize);
double simpsonSum(double a, double b, int stepsize);


int main()
{
	// starting stepsize
	int stepsize=1;

	// range of integration
	double a=0;
	double b=1;

	// so I can compare between do-while steps
	double trapSumOld=0;
	double trapSumNew=0;
	double simpSumOld=0;
	double simpSumNew=0;

	// difference calculated between old and new
	double delta=99;
	// sets loop step size to 1
	int loopstepsize=stepsize;

	// do-while for simpson's rule
	do
	{
		simpSumNew=simpsonSum(a,b,loopstepsize);
		delta=fabs(simpSumNew-simpSumOld);
		loopstepsize++;
		simpSumOld = simpSumNew;
	}
	while (delta>epsilon);
	
	// to save how many steps it took for comparison
	double simpSS = loopstepsize-1;

	// resets difference and loop step size
	delta=99;
	loopstepsize=stepsize;

	// trapezoidal rule do-while loop
	do
	{
		trapSumNew=trapSum(a,b,loopstepsize);
		delta=fabs(trapSumNew-trapSumOld);
		loopstepsize++;
		trapSumOld = trapSumNew;
	}
	while (delta>epsilon);
	
	// saves step number for comparison.
	double trapSS = loopstepsize-1;

	// prints out convergent value
	cout<<"\n\nNumerical integration for f(x)=x^2 in the range [0,1]:\n\n"
		<<"The Simpson's rule found a value of: "
		<<simpSumNew<<" in "<<simpSS<<" step(s).\n"
		<<"The trapezoidal rule found a value of: "
		<<trapSumNew<<" in "<<trapSS<<" step(s).\n\n";

	// if/else statements to tell which one converged faster
	if (simpSS<trapSS)
	{
		cout<<"Simpson's Rule converges faster.\n\n";
	}
	else if (trapSS>simpSS)
	{
		cout<<"Trapezoidal Rule converges faster.\n\n";
	}
	else
	{
		cout<<"They converge at the same rate.\n\n";
	}

	cout << endl;

	return 0;
}

// simple second order polynomial
double pol2(double x)
{	
	return p0+p1*x+p2*x*x;
}


// via the Trapezoidal Rule
double trapSum(double a, double b, int stepsize)
{
	double trapSum=0;
	double deltaX=(b-a)/(double)stepsize;
	double xstart=0;
	double xend=0;

	for (int i=0;i<stepsize;i++)
	{
		xstart=a+deltaX*i;
		xend=xstart+deltaX;
		trapSum += (deltaX/2)*(pol2(xstart)+pol2(xend));
	}

	return trapSum;
}

// via Simpson's Rule
double simpsonSum(double a, double b, int stepsize)
{
	double simpSum=0;
	double deltaX=(b-a)/(double)stepsize;
	double xstart=0;
	double xend=0;

	for (int i=0;i<stepsize;i++)
	{
		xstart=a+deltaX*i;
		xend=xstart+deltaX;
		simpSum += ((xend-xstart)/6)*
			(pol2(xstart)+4*pol2((xstart+xend)/2)+pol2(xend));
	}
	
	return simpSum;
}

