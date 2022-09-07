// This is the header file of skier class (in-class 6)

#ifndef SKIER_H
#define SKIER_H

class skier

{
  private:

  double s; //speed
	// average speed in mph

  public:

  // constructor
  skier() {s=0;}; //default
  skier(double mS);

  // destructor
  virtual ~skier() {};

  // Setter functions
  void SetS(double mS) {s=mS;}
  
  // Getter functions
  double GetS() {return s;}

  //our member functions
  void Print();
};

#endif //SKIER_H
