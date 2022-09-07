// Implementation of our vecNd class
// now we have to include our header
// file !!!

#include "vecNd.h"

#include <iostream>  
#include <cmath>

using namespace std;


//default constructor
vecNd::vecNd()
{
	dim = 2;

	x = new double[dim];
	x[0]=-123;
	x[1]=-123;	
}

//user input dimension constructor
vecNd::vecNd(int mdim)
{
	dim = mdim;

	x = new double[dim];
	for (int i=0;i<dim;i++)
	{
		x[i]=-123;
	}
}

//copy constructor
vecNd::vecNd(const vecNd &c)
{	
	dim = c.dim;
	x = new double[dim];
	for (int i=0;i<dim;i++)
	{
		x[i] = c.x[i];
	}
}

//destructor
vecNd::~vecNd() 
{
	delete x;
}


vecNd vecNd::operator-(vecNd  &v)
{		
	if(dim != v.dim)
	{
		return vecNd();
	}

	*this -= v;

	return *this;
}

vecNd vecNd::operator-=(vecNd &v)
{
	if(dim != v.dim)
	{
		return vecNd(); 
	}
	
	for(int i=0;i<v.dim;i++)
	{
		x[i] -= v.x[i];
	}

	return *this;
}

vecNd vecNd::operator+(vecNd  &v)
{		
	if(dim != v.dim)
	{
		return vecNd();
	}

	*this += v;

	return *this;
}

vecNd vecNd::operator+=(vecNd &v)
{
	if(dim != v.dim)
	{
		return vecNd(); 
	}
	
	for(int i=0;i<v.dim;i++)
	{
		x[i] += v.x[i];
	}

	return *this;
}

vecNd vecNd::operator=(vecNd &v)
{
	if(dim != v.dim)
	{
		delete [] x;
      
		dim=v.dim;
      x = new double[dim];
	}
	

	for(int i=0;i<dim;i++)
		{
			x[i] = v.x[i];
		}

	return *this;
}

vecNd vecNd::operator*(vecNd &v)
{
	if(dim != 3)
	{
		return vecNd();
	}

	vecNd y = vecNd(3);
	
	y.x[0] = (x[1]*v.x[2]) - (x[2]-v.x[1]);
	y.x[1] = (x[2]*v.x[0]) - (x[0]-v.x[2]);
	y.x[2] = (x[0]*v.x[1]) - (x[1]-v.x[0]);

	return y;
}



//prints n-dimensions
void vecNd::Print()
{
	for (int i=0;i<dim;i++)
	{
		cout<<"Element "<<i<<" = "<<x[i]<<endl;
	}
}

//length for n-dimensions
double vecNd::Length()
{
	double doubSum=0;
	for (int i=0;i<dim;i++)
	{
		doubSum+=pow(x[i],2);
	}
	
	return sqrt(doubSum);
}

//size of vec (n-dimension)
int vecNd::size() const
{
	return dim;
}



