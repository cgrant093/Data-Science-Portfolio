// This is the header file of shooter class (in-class 6)

#ifndef SHOOTER_H
#define SHOOTER_H

class shooter
{
  private:

  double a; //accuracy
	//scale from 0 to 1

  public:

  // constructor
  shooter() {a=0;}; //default
  shooter(double mA);

  // destructor
 	virtual ~shooter() {};

  // Setter functions
  void SetA(double mA) {a=mA;}
  
  // Getter functions
  double GetA() {return a;}

  //our member functions
  void Print();
};

#endif //SHOOTER_H
