// This is the header file of circle class (in-class 6)

#ifndef CIRCLE_H
#define CIRCLE_H

class circle
{
  private:

  double R;

  public:

  // constructor
  circle() {R=0;}; //default
  circle(double mR);

  // destructor
 	virtual ~circle() {};

  // Setter functions
  void SetR(double mR) {R=mR;}
  
  // Getter functions
  double GetR() {return R;}

  //our member functions
  double Eval();
  void Print();
};

#endif //CIRCLE_H
