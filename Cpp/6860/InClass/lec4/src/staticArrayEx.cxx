
#include "stdlib.h"
#include <iostream>  
#include <cmath>

using namespace std;

const int globRow=3;

void addVectors(int arrayA[], const int arrayB[]);
void multScalar(const int &scalarK, int arrayA[]);

int main( )
{ 
	const int scalarK=3;

	int arrayA[globRow]= {24,36,4};
	int arrayB[globRow]= {26,14,46};

	addVectors(arrayA, arrayB);

	cout<<"\n[24,36,4] + [26,14,46] = \n["
		<<arrayA[0]<<", "
		<<arrayA[1]<<", "
		<<arrayA[2]<<"]\n\n"
		<<scalarK<<" * ["
		<<arrayA[0]<<", "
		<<arrayA[1]<<", "
		<<arrayA[2]<<"] = \n";

	multScalar(scalarK, arrayA);

	cout<<"["<<arrayA[0]<<", "
		<<arrayA[1]<<", "
		<<arrayA[2]<<"]\n\n";

	return 0;
}

void addVectors(int arrayA[], const int arrayB[])
{
	for(int i=0;i<globRow;i++)
	{
		arrayA[i]=arrayA[i]+arrayB[i];
	}	
}

void multScalar(const int &scalarK, int arrayA[])
{
	for(int i=0;i<globRow;i++)
	{
		arrayA[i]=scalarK*arrayA[i];
	}
}





