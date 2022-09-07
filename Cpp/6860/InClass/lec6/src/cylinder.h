// This is the header file of cylinder class (in-class 6)

#ifndef CYLINDER_H
#define CYLINDER_H

#include "circle.h"

class cylinder : public circle

{
  	private:

  	double h;

  	public:

  	// constructor
  	cylinder(); //default
  	cylinder(double mR, double mh);

  	// destructor
  	virtual ~cylinder() {};

  	// Setter functions
  	void SetH(double mH) {h=mH;}
  
  	// Getter functions
  	double GetH() {return h;}

  	//our member functions
  	void Print();
	double Eval();
};

#endif //CYLINDER_H
