// This is the header file
// of our complex class


//definition of our complex class
class complex
{
	private:

	double *x;
	int dim;

	public:

	// Setter functions
	void setComponent(int index, double mx) {x[index]=mx;}

	// Getter functions
	double getComponent(int index) const {return x[index];}

	// constructor
	complex();
	complex(const complex &c);

	// destructor
	~complex();

	//operators
	complex operator = (const complex &v);
	complex operator += (const complex &v);
	complex operator + (const complex &v);
	complex operator -= (const complex &v);
	complex operator - (const complex &v);
	complex operator * (const complex &v);
	complex operator / (const complex &v);

	//our member functions
	double absSq() const;
	complex conj() const;
	void Print() const;
	double Length() const;
	int size() const;
};
 

