#include "circle.h"

#include <iostream>
#include <cmath>

using namespace std;

const double PI = 4*atan(1);

circle::circle(double mR)
{
  	R=mR;
}

void circle::Print()
{
  	cout<<" Circle Class with Radius = "<<R<<endl;
}

double circle::Eval()
{
	return PI*pow(R,2);
}
