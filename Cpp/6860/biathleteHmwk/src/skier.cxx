#include "skier.h"

#include <iostream>
#include <cmath>

using namespace std;

skier::skier(double mS)
{
	if (mS>=0)
	{
  		s=mS;
	}
	else
	{
		cout<<"Speed should be positive!"<<endl;
		s=0;
	}
}

void skier::Print()
{
  cout<<" Skier class with average speed = "<<s<<endl;
}

