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
	cout<<" vecNd: default constructor called"<<endl;
	x = new double[dim];
	x[0]=-123;
	x[1]=-123;	
}

//user input dimension constructor
vecNd::vecNd(int mdim)
{
	dim = mdim;
	cout<<" vecNd: constructor of size "<<dim<<" called"<<endl;
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
	cout<<" vecNd: default destructor called"<<endl;
	delete x;
}


vecNd vecNd::operator+(vecNd  &v)
{		
	if(dim != v.dim)
	{
		return vecNd();
	}

	vecNd y = vecNd(v.dim);
	
	for(int i=0;i<y.dim;i++)
	{
		y.x[i]= x[i] + v.x[i];
	}

	return y;
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


