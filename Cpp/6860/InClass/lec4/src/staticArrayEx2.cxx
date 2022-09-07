
#include "stdlib.h"
#include <iostream>  
#include <cmath>

using namespace std;

const int globRow=3;

int* addVectors(const int arrayA[], const int arrayB[], int arrayC[]);
int* multScalar(const int &scalarK, const int arrayA[], int arrayD[]);

int main( )
{ 
	const int scalarK=3;

	int arrayA[globRow] = {24,36,4};
	int arrayB[globRow] = {26,14,46};
	int *arrayC = new int[globRow];
	int *arrayD = new int[globRow];

	arrayC = addVectors(arrayA, arrayB, arrayC);

	cout<<"\n[24,36,4] + [26,14,46] = \n["
		<<arrayC[0]<<", "
		<<arrayC[1]<<", "
		<<arrayC[2]<<"]\n\n"
		<<scalarK<<" * ["
		<<arrayA[0]<<", "
		<<arrayA[1]<<", "
		<<arrayA[2]<<"] = \n";

	arrayD = multScalar(scalarK, arrayA, arrayD);

	cout<<"["<<arrayD[0]<<", "
		<<arrayD[1]<<", "
		<<arrayD[2]<<"]\n\n";

	delete arrayC;
	delete arrayD;

	return 0;
}

int* addVectors(const int arrayA[], const int arrayB[], int arrayC[])
{

	for(int i=0;i<globRow;i++)
	{
		arrayC[i]=arrayA[i]+arrayB[i];
	}
	
	return arrayC;	
}

int* multScalar(const int &scalarK, const int arrayA[], int arrayD[])
{

	for(int i=0;i<globRow;i++)
	{
		arrayD[i]=scalarK*arrayA[i];
	}

	return arrayD;
}





