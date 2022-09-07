#include "sphere.h"

#include <iostream>
#include <cmath>

using namespace std;

void sphere::Print()
{
  	cout<<" Sphere Class derived from circle class:";
	cout<<" R = " << GetR()<<endl;
}

double sphere::Eval()
{
	return 4*GetR()*circle::Eval()/3;
}
