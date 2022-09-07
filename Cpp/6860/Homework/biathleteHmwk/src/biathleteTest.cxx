#include <iostream>
#include <cmath>

#include "shooter.h"
#include "skier.h"
#include "biathlete.h"

using namespace std;

int main( )
{
	biathlete bi;
	bi.Print();

	biathlete bi2(.70, 35);
	bi2.Print();

}
