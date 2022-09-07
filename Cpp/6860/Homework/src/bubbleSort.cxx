
#include "stdlib.h"
#include <iostream>  
#include <cmath>

using namespace std;

//size of matrix
const int globRow=20;

void bubbleSortSux(int arrayA[]);
void swap(int &a, int &b);
void print(int arrayA[]);

int main( )
{ 
	//creates a static matrix
	int arrayA[globRow];
	
	//for random variable
	int random=0;
	srand(time(NULL)); 
		
	// fills in every array slot with a random
	// number between 1 and 100
	for(int i=0;i<globRow;i++)
	{
		random = 1+rand()%100; 
		arrayA[i] = random;
	}
	
	// prints out unsorted array
	cout<<"\n\nThe unsorted array is:";
	print(arrayA);

	// sorts via the gross bubble sort
	bubbleSortSux(arrayA);

	// prints out the sorted array
	cout<<"\n\nThe sorted array is:";
	print(arrayA);
	
	cout<<endl;

	return 0;
}

void bubbleSortSux(int arrayA[])
{
	//looks at all but last element
	for(int i=0;i<globRow-1;i++)
	{
		//changes this so you don't look
		// at the sorted elements at the 
		// end of the array
		for(int j=0;j<globRow-i-1;j++)
		{		
			if(arrayA[j]>arrayA[j+1])
			{
				swap(arrayA[j], arrayA[j+1]);
			}
		}
	}
}

//swap by reference method from class
void swap(int &a, int &b)
{
	int temp=a;
	a=b;
	b=temp;
}

//print method
void print(int arrayA[])
{
	cout<<"\n[";
	for(int i=0;i<globRow;i++)
	{
		if(i==globRow-1)
		{
			cout<<arrayA[i]<<"]\n\n";	
		}
		else
		{
			cout<<arrayA[i]<<", ";
		}
	}
}


