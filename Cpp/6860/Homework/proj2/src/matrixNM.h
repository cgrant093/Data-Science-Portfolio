// This is the header file
// of our matrixNM class

#include <vector>

using namespace std;

//definition of our vecNd class
class matrixNM
{
	private:

	vector< vector< double > > x;
	int ndim;
	int mdim;

	public:

	// constructor
	matrixNM();
	matrixNM(int dim); //for making identity matrices;
	matrixNM(int myNdim, int myMdim); 
	matrixNM(const matrixNM &c);

	// destructor
	~matrixNM();

	// Setter functions
	double setRowCol(int r, int c, double mx) {return x[r][c] = mx;}

	// Getter functions
	double getRowCol(int r, int c) {return x[r][c];}

	//operators
	matrixNM operator -= (const matrixNM &v);
	matrixNM operator - (const matrixNM &v);
	matrixNM operator += (const matrixNM &v);
	matrixNM operator + (const matrixNM &v);
	matrixNM operator = (const matrixNM &v);
	matrixNM operator * (const matrixNM &v); //multiply two matrices

	//our member functions
	matrixNM constMult (double &c); //multiply by constant factor
	void Print() const;
	matrixNM transpose() const;
	double det(int dim) const;
	int rowSize() const;
	int colSize() const;
};
 

