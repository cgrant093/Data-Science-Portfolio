#include "cylinder.h"

#include <iostream>
#include <cmath>

using namespace std;

cylinder::cylinder():circle()
{
	h=0;
}

cylinder::cylinder(double mR, double mH):circle(mR)
{
  h=mH;
}

void cylinder::Print()
{
  cout<<" Cylinder Class derived from Circle Class:"<<endl;
  cout<<" R = "<<GetR()<<endl;
  cout<<" h = "<<h<<endl;
}

double cylinder::Eval()
{
	return h*circle::Eval();
}
