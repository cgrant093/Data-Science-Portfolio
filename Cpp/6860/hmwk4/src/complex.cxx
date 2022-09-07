// Implementation of our vecNd class
// now we have to include our header
// file !!!

#include "complex.h"
#include <iostream>  
#include <cmath>

using namespace std;


//default constructor
complex::complex()
{
	// size always 2: 0) real component.. 1) imaginary
	dim = 2;
	cout<<" complex: default constructor called"<<endl;
	x = new double[dim];
	x[0]=-123; //real part
	x[1]=-123; //imaginary part
}

//copy constructor
complex::complex(const complex &c)
{	
	// size always 2: 0) real component.. 1) imaginary
	dim = 2;
	cout<<" complex: copy constructor called"<<endl;
	x = new double[dim];
	for (int i=0;i<dim;i++)
	{
		x[i] = c.x[i];
	}
}

//destructor
complex::~complex() 
{
	cout<<" complex: default destructor called"<<endl;
	delete x;
}


// overriding operators
complex complex::operator=(const complex &v)
{
	for(int i=0;i<dim;i++)
	{
		x[i] = v.x[i];
	}

	return *this;
}

complex complex::operator+=(const complex &v)
{
	for(int i=0;i<v.dim;i++)
	{
		x[i] += v.x[i];
	}

	return *this;
}

complex complex::operator+(const complex  &v)
{		
	*this += v;

	return *this;
}

complex complex::operator-=(const complex &v)
{
	for(int i=0;i<v.dim;i++)
	{
		x[i] -= v.x[i];
	}

	return *this;
}

complex complex::operator-(const complex &v)
{
	*this -= v;

	return *this;
}

complex complex::operator*(const complex &v)
{
	complex y;
	
	// foil the two complex numbers together
	y.x[0] = (x[0]*v.x[0]) - (x[1]*v.x[1]);
	y.x[1] = (x[0]*v.x[1]) + (x[1]*v.x[0]);

	return y;
}

complex complex::operator/(const complex &v)
{
	double denom = v.absSq(); // this should only have a real part!

	// if denom is approximately zero, throw an exception
	if (fabs(denom) < 1e-10) 
	{
		cerr << "TRYING TO DIVIDE BY ZERO!!!" << endl;
		throw(-1);
	}

	complex y=*this * v.conj();
	y.x[0] /= denom;
	y.x[1] /= denom;

	return y;
}


// for when multiply a complex number
// by its complex conjugate
// should only get the real part of the new vector
// imaginary part is zero
double complex::absSq() const
{
	return x[0]*x[0] + x[1]*x[1];
}

//conjugate
complex complex::conj() const
{
	complex y;
	y.x[0] = x[0];
	y.x[1] = -x[1];

	return y;
}

//prints n-dimensions
void complex::Print() const
{
	// makes output nice
	cout<<"Complex number: "<<x[0];
	if (x[1] < 0)
	{
		cout<<" - "<<fabs(x[1])<<"i";
	}
	else
	{
		cout<<" + "<<x[1]<<"i";
	}
	cout<<endl;
}

//length for n-dimensions
double complex::Length() const
{
	return sqrt(this->absSq());
}

//size of vec (n-dimension)
int complex::size() const
{
	// should always be 2
	return dim;
}


