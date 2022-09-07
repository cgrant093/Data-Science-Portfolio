// This is the header file
// of our vecNd class


//definition of our vecNd class
class vecNd
{
	private:

	double *x;
	int dim;

	public:

	// constructor
	vecNd();
	vecNd(int mdim); 
	vecNd(const vecNd &c);

	// destructor
	~vecNd();

	// Setter functions
	void setComponent(int index, double mx) {x[index]=mx;}

	// Getter functions
	double getComponent(int index) const {return x[index];}

	//operators
	vecNd operator -= (vecNd &);
	vecNd operator - (vecNd &);
	vecNd operator += (vecNd &);
	vecNd operator + (vecNd &);
	vecNd operator = (vecNd &);
	vecNd operator * (vecNd &); //vector product

	//our member functions
	void Print();
	double Length();
	int size() const;
};
 

