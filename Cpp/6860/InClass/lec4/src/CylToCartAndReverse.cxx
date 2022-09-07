
#include "stdlib.h"
#include <iostream>  
#include <cmath>

using namespace std;

const double PI=4*atan(1);

void cartesian(double &x, double &y, double &r, double &phi);
void cylindrical(double &x, double &y, double &r, double &phi);

void convertCylToCart(const double &r, const double &phi, double &x, double &y);
void convertCartToCyl(const double &x, const double &y, double &r, double &phi);

void renormPhi(double &phi);

int main( )
{ 
	string choice = "x";
	cout<<"Would you like to convert cylindrical coordinates to cartesian?\n"
		<<"Or cartisian to cylindrical?\nInput starting coordinates\n"
		<<"(x for cartisian and r for cylindrical):   ";
	cin>>choice;

	double x=-99;
	double y=-99;
	double r=-99;
	double phi=-99;
	
	if(choice=="x")
	{
		cartesian(x,y,r,phi);
	}
	else if(choice=="r")
	{
		cylindrical(x,y,r,phi);
	}
	else	
	{
		cout<<"You did not pick one of the two choices!"<<endl;
	}
	
	return 0;
}

void cartesian(double &x, double &y, double &r, double &phi)
{
	cout<<"\n\nPlease choose the x value: ";
	cin>>x;
	cout<<"\nPlease choose the y value: ";
	cin>>y;

	convertCartToCyl(x,y,r,phi);
	
	cout<<"\nWith cartesian values x="<<x<<" and y="
		<<y<<", the cylindrical values are r="
		<<r<<" and phi="<<phi<<endl;
}

void cylindrical(double &x, double &y, double &r, double &phi)
{
	cout<<"\n\nPlease choose the r value: ";
	cin>>r;
	cout<<"\nPlease choose the phi value (in radians): ";
	cin>>phi;

	renormPhi(phi);

	convertCylToCart(r,phi,x,y);
	
	cout<<"\nWith cylindrical values r="<<r<<" and phi="
		<<phi<<", the cartesian values are x="
		<<x<<" and y="<<y<<endl;
}

void convertCylToCart(const double &r, const double &phi, double &x, double &y)
{
	x=r*cos(phi);
	y=r*sin(phi);
}

void convertCartToCyl(const double &x, const double &y, double &r, double &phi)
{
	r=sqrt((x*x)+(y*y));
	phi=atan2(y,x);
}

void renormPhi(double &phi)
{
	while(phi>=(2*PI))
	{	
		phi=phi-(2*PI);
	}
}

