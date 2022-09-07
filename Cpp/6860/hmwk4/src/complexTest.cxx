// Demonstrating vec2dclass5.cxx
// implemention via our vec2d class
// implemenntation using a modular
// approach, via header and source
// file of the class

#include "complex.h"
#include <iostream>  
#include <cmath>

using namespace std;

void print(const complex &v, std::string name);

int main( )
{ 
	complex a; //default constructor
	a.setComponent(0,4.2);
	a.setComponent(1,1.2);
	print(a, "a"); //prints vector a
	
	// finds and prints length of a
	cout<<endl<<"The length of complex a is: "<<a.Length()<<"\n\n";

	complex b(a); //copy constructor
	print(b, "b (copy constructor)");
	
	// various operator overloading tests
	b += a;
	print(b, "b+=a");

	complex c = a * b;
	print(c, "c=a*b");
	
	complex d = a + c;
	print(d, "d=a+c");
	
	complex e = d - b;
	print(e, "e=d-b");
	
	e -= c;
	print(e, "e-=c");
	
	complex f = e/a;
	print(f, "f=e/a");

	// testing conjugate method
	complex g = f.conj();
	print(g, "conj(f)");

	cout<<endl<<"Program end"<<endl;
}

// small print function to make output and main look nice
void print(const complex &v, string name)
{
	cout<<endl;
	cout<<"Complex Number "<<name<<":"<<endl;
	v.Print();
	cout<<endl;	
}
