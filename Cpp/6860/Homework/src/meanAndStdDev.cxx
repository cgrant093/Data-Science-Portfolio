
#include "stdlib.h"
#include <iostream>  
#include <cmath>

using namespace std;

void calculations(int arrayA[], double &mean, double &stdDev, int row);
void print(int arrayA[], int row);

int main( )
{ 
	int row=0;	
	
	// asks user for size
	cout<<"\n\nWhat size array would you like (int)?   ";
	cin>>row;

	int *arrayA = new int[row];
	
	// for random number
	int random=0;
	srand(time(NULL)); 

	// fills in every array slot with a random
	// number between 1 and 50
	for(int i=0;i<row;i++)
	{
		random = 1+rand()%50; 
		arrayA[i] = random;
	}
	
	// prints out array
	print(arrayA,row);

	double stdDev = 0;
	double mean = 0;
	
	// calculates stdDev and mean of array
	calculations(arrayA, mean, stdDev, row);
	
	// prints out those two calculations
	cout<<"The standard deviation:  "
		<<stdDev<<endl;
	cout<<"The mean is:  "	
		<<mean<<endl<<endl<<endl;

	//deletes dynamic variable
	delete arrayA;	
	
	return 0;
}

void calculations(int arrayA[], double &mean, double &stdDev, const int row)
{
	//sum is for finding mean
	int sum = 0;
	//is for finding sum under square root for standard Deviation calculation
	double stdSum = 0;
 
	//finds mean
	for(int i=0;i<row;i++)
	{
		sum += arrayA[i];
	}
	
	mean = (double)sum/(double)row;
	
	//finds standard deviation
	for(int i=0;i<row;i++)
	{
		stdSum += pow(((double)arrayA[i]-mean),2);
	}
	
	stdDev = sqrt(stdSum/(double)row);
}

//prints out array
void print(int arrayA[], const int row)
{
	cout<<"\n\nThe array is:\n[";
	for(int i=0;i<row;i++)
	{
		if(i==row-1)
		{
			cout<<arrayA[i]<<"]\n\n";	
		}
		else
		{
			cout<<arrayA[i]<<", ";
		}
	}
}


