#include "biathlete.h"

#include <iostream>
#include <cmath>

using namespace std;

void biathlete::Print()
{
  	cout<<"\n\n Biathlete derived class:\n";
	cout<<"  with accuracy = "<<GetA()<<endl;
	cout<<"  and avg speed = "<<GetS()<<endl<<endl;
}


