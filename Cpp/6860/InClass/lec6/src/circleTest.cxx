#include <iostream>
#include <cmath>

#include "sphere.h"
#include "cylinder.h"
#include "circle.h"

using namespace std;

int main( )
{
	cylinder cyl(1.56,3.2);
  	cyl.Print();
	cout<<endl<<cyl.Eval();
  	cout<<endl<<endl;

	sphere sph(1.56);
	sph.Print();
	cout<<endl<<sph.Eval();
	cout<<endl<<endl;
}
