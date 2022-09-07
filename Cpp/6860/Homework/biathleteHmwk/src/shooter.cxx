#include "shooter.h"

#include <iostream>
#include <cmath>

using namespace std;


shooter::shooter(double mA)
{
	if(mA>=0 && mA<=1)
	{
  		a=mA;
	}
	else
	{
		cout<<"Not a good accuracy, some value between 0 and 1!"<<endl;
		a=0;
	}
}

void shooter::Print()
{
  	cout<<" Shooter class with accuracy = "<<a<<endl;
}
