// This is the header file of cylinder class (in-class 6)

#ifndef SPHERE_H
#define SPHERE_H

#include "circle.h"

class sphere : public circle

{
  private:

  public:

  // constructor
  sphere():circle(){};
	sphere(double mR):circle(mR){};

  // destructor
  virtual ~sphere() {};

  //our member functions
  void Print();
	double Eval();
};

#endif //SPHERE_H
