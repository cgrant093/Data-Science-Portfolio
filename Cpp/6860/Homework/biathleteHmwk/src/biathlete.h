// This is the header file of BIATHLETE class (in-class 6)

#ifndef BIATHLETE_H
#define BIATHLETE_H

#include "shooter.h"
#include "skier.h"

class biathlete : public shooter, public skier 

{
  private:

  public:

  // constructor
  biathlete() : shooter(), skier() {};
	biathlete(double mA, double mS) : shooter(mA), skier(mS) {};

  // destructor
  virtual ~biathlete() {};

  //our member functions
  void Print();
};

#endif //BIATHLETE_H
