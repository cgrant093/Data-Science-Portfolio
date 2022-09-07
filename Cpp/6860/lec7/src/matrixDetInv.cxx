#include <stdio.h>
#include <gsl/gsl_matrix.h>
#include <gsl/gsl_linalg.h>
#include <gsl/gsl_cblas.h>
#include <gsl/gsl_blas.h>
#include <iostream>

using namespace std;

void setMatrixM(gsl_matrix &m);
void printMatrix(gsl_matrix &m, int &n);

int main()
{
	//dimension of matrix
	int n=3;
	// signum
	int s;

	// define all used matrices
	gsl_matrix *m = gsl_matrix_alloc(n,n);
	gsl_matrix *inverse = gsl_matrix_alloc(n,n);
	gsl_matrix *mult = gsl_matrix_alloc(n,n);
	gsl_permutation *perm = gsl_permutation_alloc(n);

	gsl_matrix_set(m, 0, 0, 1);
	gsl_matrix_set(m, 0, 1, 3);
	gsl_matrix_set(m, 0, 2, 3);
	gsl_matrix_set(m, 1, 0, 1);
	gsl_matrix_set(m, 1, 1, 4);
	gsl_matrix_set(m, 1, 2, 3);
	gsl_matrix_set(m, 2, 0, 1);
	gsl_matrix_set(m, 2, 1, 3);
	gsl_matrix_set(m, 2, 2, 4);

	cout<<endl;
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			cout<<" "<<gsl_matrix_get(m, i, j);	
		}
		cout<<endl;
	}
	cout<<endl;

	//make LU decomp
   gsl_linalg_LU_decomp(m, perm, &s);

	//invert matrix
	gsl_linalg_LU_invert(m, perm, inverse);

	cout<<endl;
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			cout<<" "<<gsl_matrix_get(inverse, i, j);	
		}
		cout<<endl;
	}
	cout<<endl;

	//reset m, because it gets messed up while making inverse
	gsl_matrix_set(m, 0, 0, 1);
	gsl_matrix_set(m, 0, 1, 3);
	gsl_matrix_set(m, 0, 2, 3);
	gsl_matrix_set(m, 1, 0, 1);
	gsl_matrix_set(m, 1, 1, 4);
	gsl_matrix_set(m, 1, 2, 3);
	gsl_matrix_set(m, 2, 0, 1);
	gsl_matrix_set(m, 2, 1, 3);
	gsl_matrix_set(m, 2, 2, 4);

	gsl_blas_dgemm(CblasNoTrans, CblasNoTrans, 1.0, inverse, m, 0.0, mult);

	cout<<endl;
	for(int i=0;i<n;i++)
	{
		for(int j=0;j<n;j++)
		{
			cout<<" "<<gsl_matrix_get(mult, i, j);	
		}
		cout<<endl;
	}
	cout<<endl;

	return 0;
}



