// Demonstrating vec2dclass5.cxx
// implemention via our vec2d class
// implemenntation using a modular
// approach, via header and source
// file of the class

#include "vecNd.h"
#include <iostream>  
#include <cmath>

using namespace std;

int main( )
{ 
	vecNd a; //default constructor
	vecNd b(2); //make size 2
	
	a.setComponent(0,4.2);
	a.setComponent(1,1.2);
	b.setComponent(0,3.4);
	b.setComponent(1,2);

	cout<<endl;
	cout<<"Vector a:"<<endl;
	a.Print();
	cout<<endl;

	cout<<endl<<"The length of vec a is: ";
	cout<<a.Length()<<endl;

	cout<<endl;
	cout<<"Vector b:"<<endl;
	b.Print();
	cout<<endl;

	vecNd c(a); //copy constructor

	cout<<endl;
	cout<<"Vector c (copy of a):"<<endl;
	c.Print();
	cout<<endl;
	
	c = b;

	cout<<endl;
	cout<<"new Vector c (equals op copy of b):"<<endl;
	c.Print();
	cout<<endl;

	vecNd d(a + b); //copy of (a+b) constructor

	cout<<endl;
	cout<<"Vector d (a + b):"<<endl;
	d.Print();
	cout<<endl;
	
	d += c;
	
	cout<<endl;
	cout<<"new Vector d += c:"<<endl;
	d.Print();
	cout<<endl;	

	vecNd e(3);
	
	e = d;
	
	cout<<endl;
	cout<<"Vector e = d:"<<endl;
	e.Print();
	cout<<endl;	

	cout<<" Program end!"<<endl;
}
