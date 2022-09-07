// numerical integration lower sum (JP)
#include <iostream>  

using namespace std;

// declare prototype of polynomial second order
// unfortunatley to allow more generic function
// declartions and parameters like p0+p1*x+p2*x*x
// we need to learn a bit more about C++
// so lets just declare p0,p1,p2 as const global
// (again we will see that classes and other
// C++ functionality will allow more flexibilty
// later on)

// simple f(x)=x^2
const double p0=0;
const double p1=0;
const double p2=1;

double pol2(double x);

// declare prototype of lower sum of pol2 with fixed stepsize
double lowerSum(double a, double b, int stepsize);
double upperSum(double a, double b, int stepsize);

int main( )
{ 
  int stepsize=10000;

  // simple values to check and compare to
  // analytical solution = 1/3

  double a=0;
  double b=1;

  const double analyticalSolution=1/3.;

  cout<<endl;
  cout<<" Calculate the lower sum of f(x)=x^2 [a,b]=["
      <<a<<","<<b<<"] with fixed stepsize = "<<stepsize<<endl;

  cout<<endl;
  cout<<" Analytical Solution = "<<analyticalSolution<<endl;

  double numericalSolution=lowerSum(a,b,stepsize);
  double numericalSolution2=upperSum(a,b,stepsize);
   
  cout<<" Numerical Solution  = "<<numericalSolution<<endl;
  cout<<" Numerical Solution2  = "<<numericalSolution2<<endl;

  // loop to achieve a certain precision
  // in the inclass excercise we realized that a simple difference
  // based on one sum, for example lower sum, is not working as
  // we expected. So remember: lower sum < Intgeral < upper sum
  // so to achieve the prescision we want use the sums we just
  // define delta as the difference between lower and upper sum
  // as on potential solution. Of course this is not a very efficient
  // approach hence different methods are more approperiate/faster

  double epsilon=0.001;
  double delta=99;

  int loopstepsize=10;
  do
    {
      double myUpperSum=upperSum(a,b,loopstepsize);
      double myLowerSum=lowerSum(a,b,loopstepsize);
      delta=myUpperSum-myLowerSum;

      // DEBUG Output
      cout<<loopstepsize<<" "<<myUpperSum<<" "<<myLowerSum<<" "<<delta<<endl;
      
      loopstepsize++;
    }
  while (delta>epsilon);
  
  cout<<endl;
return 0;
}

double pol2(double x)
{
  return p0+p1*x+p2*x*x;
}

double lowerSum(double a, double b, int stepsize)
{
  double lSum=0;				
  const double deltaX=(b-a)/(double) stepsize;
  //DEBUG:
  //cout<<" DEBUG: deltaX = "<<deltaX<<endl;

  // lower sum, so start at a, meaning for loop 
  // with starting point zero (for example)
  // x*_i=a+deltaX*i;
  // only true for monotonic functions ;-)
  // (implement for own check/function
  // to ensure that you calculate the lower sum
  // for all functions ...)

  for (int i=0;i<stepsize;i++)
    {
      double xi=a+deltaX*i;
      lSum += (pol2(xi)*deltaX);
    }

  return lSum;
}

double upperSum(double a, double b, int stepsize)
{
  double uSum=0;				
  const double deltaX=(b-a)/(double) stepsize;
  //DEBUG:
  //cout<<" DEBUG: deltaX = "<<deltaX<<endl;

  // lower sum, so start at a, meaning for loop 
  // with starting point zero (for example)
  // x*_i=a+deltaX*i;
  // only true for monotonic functions ;-)
  // (implement for own check/function
  // to ensure that you calculate the lower sum
  // for all functions ...)

  for (int i=0;i<stepsize;i++)
    {
      double xi=a+deltaX*(i+1);
      uSum += (pol2(xi)*deltaX);
    }

  return uSum;
}
