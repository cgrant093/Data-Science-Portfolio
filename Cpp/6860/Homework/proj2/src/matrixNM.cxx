// Implementation of our matrixNM class

#include "matrixNM.h"

#include <vector>
#include <iostream>  
#include <cmath>

using namespace std;


//default constructor
matrixNM::matrixNM()
{
	ndim = mdim = 2;	

	vector< vector<double> > temp(ndim,vector<double>(mdim,-123));	
	x = temp;
}

//identity matrix constructor
matrixNM::matrixNM(int dim)
{
	ndim=mdim=dim;
	
	vector< vector<double> > temp(dim,vector<double>(mdim,0));
	x=temp;
	
	for(int i=0;i<dim;i++)
	{
		for(int j=0;j<dim;j++)
		{
			if(i==j)
			{
				x[i][j] = 1;
			}	
		}
	}
}

//user input dimension constructor
matrixNM::matrixNM(int myNdim, int myMdim)
{
	ndim = myNdim;
	mdim = myMdim;
	
	vector< vector<double> > temp(ndim,vector<double>(mdim,-123));
	x = temp;
}

//copy constructor
matrixNM::matrixNM(const matrixNM &c)
{	
	ndim = c.ndim;
	mdim = c.mdim;

	vector< vector<double> > temp(ndim,vector<double>(mdim));
	x = temp;

	for (int i=0;i<ndim;i++)
	{
		for (int j=0;j<mdim;j++)
		{
			x[i][j] = c.x[i][j];
		}
	}
}

//destructor
matrixNM::~matrixNM() {}


matrixNM matrixNM::operator-(const matrixNM  &v)
{		
	if((ndim != v.ndim) || (mdim != v.mdim))
	{
		return matrixNM(); 
	}	

	*this -= v;

	return *this;
}

matrixNM matrixNM::operator-=(const matrixNM &v)
{
	if((ndim != v.ndim) || (mdim != v.mdim))
	{
		return matrixNM(); 
	}
	
	for (int i=0;i<ndim;i++)
	{
		for (int j=0;j<mdim;j++)
		{
			x[i][j] -= v.x[i][j];
		}	
	}

	return *this;
}


matrixNM matrixNM::operator+(const matrixNM  &v)
{		
	if((ndim != v.ndim) || (mdim != v.mdim))
	{
		return matrixNM(); 
	}	

	*this += v;

	return *this;
}

matrixNM matrixNM::operator+=(const matrixNM &v)
{
	if((ndim != v.ndim) || (mdim != v.mdim))
	{
		return matrixNM(); 
	}
	
	for (int i=0;i<ndim;i++)
	{
		for (int j=0;j<mdim;j++)
		{
			x[i][j] += v.x[i][j];
		}	
	}

	return *this;
}

matrixNM matrixNM::operator=(const matrixNM &v)
{
	*this = v;

	return *this;
}

//multiply NxM and MxK matrices
matrixNM matrixNM::operator*(const matrixNM &v) 
{
	if(mdim != v.ndim)
	{
		return matrixNM();
	}

	matrixNM y = matrixNM(ndim, v.mdim);
	
	for(int i=0;i<y.ndim;i++) //y row
	{
		for(int j=0;j<y.mdim;j++) //y col
		{
			double sum = 0;
			for(int k=0;k<mdim;k++) //internal dim for x*v
			{
				sum += x[i][k]*v.x[k][j];
			}
			y.x[i][j] = sum;
		}
	}

	return y;
}

// multiply by constant factor
matrixNM matrixNM::constMult(double &c) 
{
	for (int i=0;i<ndim;i++)
	{
		for (int j=0;j<mdim;j++)
		{
			x[i][j] *= c;
		}
	}
	
	return *this;
}


//prints n-dimensions
void matrixNM::Print() const 
{
	cout<<"matrixNN Clas:"<<endl;
 	cout<<" dimension = "<<ndim;
	cout<<"x"<<mdim<<endl;
 
 	for (int i=0;i<ndim;i++)
   {
   	for (int j=0;j<mdim;j++)
		{
			cout<<x[i][j]<<" ";
		}
      cout<<endl;
   }
  	cout<<endl;
}

//transpose matrix
matrixNM matrixNM::transpose() const 
{
	matrixNM T = matrixNM(mdim, ndim);
	
	for (int i=0;i<ndim;i++)
	{
		for (int j=0;j<mdim;j++)
		{
			T.x[i][j] = x[j][i];
		}
	}

	return T;
}

//determinant
double matrixNM::det(int dim) const 
{	
	if(ndim!=mdim)
	{
		cout<<"\n\nNEEDS TO BE SQUARE MATRIX TO USE det()!!!\n\n";
		return 0;
	}

	if(dim==2)
	{
		return (x[0][0]*x[1][1]-x[0][1]*x[1][0]);
	}
	
	double sum = 0;
	
	for(int i=0;i<dim;i++)//going by x-column
	{
		matrixNM y = matrixNM(dim-1,dim-1);
	
		for(int l=1;l<dim;l++) //x&y-row
		{
			int k=0; //running counter
			for(int j=0;j<dim;j++) //x&y-column
			{
				if(j!=i)
				{
					y.x[l-1][k]=x[l][j];
					k++;
				}
			}
		}
		sum += pow(-1,2+i)*x[0][i]*y.det(dim-1);
	}

	return sum;
}

//size of matrix (NxM-dimension)
int matrixNM::rowSize() const
{
	return ndim;
}

int matrixNM::colSize() const
{
	return mdim;
}





