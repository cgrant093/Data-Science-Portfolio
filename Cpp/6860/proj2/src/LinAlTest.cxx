// Demonstrating vec2dclass5.cxx
// implemention via our vec2d class
// implemenntation using a modular
// approach, via header and source
// file of the class

#include "vecNd.h"
#include "matrixNM.h"
#include <iostream>  
#include <cmath>

using namespace std;

int main( )
{ 
	vecNd a(3); //default constructor
	vecNd b(3); //make size 2
	
	a.setComponent(0,4.2);
	a.setComponent(1,1.2);
	a.setComponent(2,0.7);
	b.setComponent(0,3.4);
	b.setComponent(1,2);
	b.setComponent(2,0);

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

	cout<<endl;
	cout<<"Vector product axb:"<<endl;
	(a*b).Print();	
	cout<<endl;

	matrixNM M(3,3);
	M.setRowCol(0, 0, 1);
	M.setRowCol(0, 1, 3);
	M.setRowCol(0, 2, 3);
	M.setRowCol(1, 0, 1);
	M.setRowCol(1, 1, 4);
	M.setRowCol(1, 2, 3);
	M.setRowCol(2, 0, 1);
	M.setRowCol(2, 1, 3);
	M.setRowCol(2, 2, 4);
	
	cout<<endl;
	cout<<"Matrix M:"<<endl;
	M.Print();	
	cout<<endl;
	
	cout<<endl;
	cout<<"Transposed Matrix M:"<<endl;
	M.transpose().Print();	
	cout<<endl;
	
	cout<<endl;
	cout<<"Determinant of Matrix M:"<<endl;
	cout<<M.det(M.rowSize())<<endl;	

	cout<<endl;
	cout<<"Transposed Matrix M times Matrix M:"<<endl;
	((M.transpose())*M).Print();	
	cout<<endl;

	cout<<endl;
	cout<<"Matrix M times Transposed Matrix M:"<<endl;
	(M*(M.transpose())).Print();	
	cout<<endl;

}



