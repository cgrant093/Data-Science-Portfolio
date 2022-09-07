#include <stdio.h>
#include <iostream>

#include <gsl/gsl_errno.h>
#include <gsl/gsl_matrix.h>
#include <gsl/gsl_odeiv2.h>

#include "TAxis.h"
#include "TGraph.h"
#include "TCanvas.h"
#include "TLegend.h"

using namespace std;

const double a = 0.25;
const double b = 0.12;
const double c = 0.0025;
const double d = 0.0013;

int dfunc(double t, const double x[], double f[], void *params_ptr);
void graph(int n, double t[], double x1[], double x2[]);

int main()
{
	const int dim = 2; // number of diff eqns

	int status; // status of driver function

	const double eps_abs = 1.e-8;	//absolute error
	const double eps_rel = 1.e-10; //relative error

	double myparams[4];
	double x[dim];	// current solution vector
	double t, tnext;	// current and next indep var
	double tmin, tmax, delta_t; // range and stepsize for output
	double h = 1.e-6; // starting stepsize for ode solver

	gsl_odeiv2_system ode_system; // struct with dfunc func, etc

	cout<<"\nThis program numberically solves the PredPrey model\n\n";

	//load values into ode_system struct
	ode_system.function = dfunc;
	ode_system.dimension = dim;
	
	myparams[0] = a;
	myparams[1] = b;
	myparams[2] = c;
	myparams[3] = d;

	ode_system.params = myparams;
	tmin = 0.0;
	tmax = 200.0;
	delta_t = 2;

	// initial values for x1 and x2
	x[0] = 125;
	x[1] = 47;
	
	// creating matrices to plot later
	int len = (tmax-tmin)/delta_t;
	double tgraph[len];
	double x1[len]; //rabbit pop
	double x2[len]; //wolf pop

	int i=0;
	
	tgraph[i] = 0;
	x1[i] = x[0];
	x2[i] = x[1];	

	gsl_odeiv2_driver *drv = gsl_odeiv2_driver_alloc_y_new (&ode_system, 		
							gsl_odeiv2_step_rkf45, h, eps_abs, eps_rel);
	// Runge-Kutta-Fehlberg (4,5) method: gsl_odeiv2_step_rkf45
	
	tnext = tmin + delta_t;
	while(tnext < tmax)
	{

		status = gsl_odeiv2_driver_apply (drv, &t, tnext, x);
		if (status != GSL_SUCCESS)
		{	
			cout<<"error\n";	
			printf("Error: status = %d \n", status);
			break;	
		}
		//print at t=tnext
		//cout<<t<<", "<<x[0]<<", "<<x[1]<<endl;
		

		i++;

		tgraph[i]=tnext;
		x1[i] = x[0];
		x2[i] = x[1];

		tnext += delta_t;
	}

	graph(len, tgraph, x1, x2);

	gsl_odeiv2_driver_free (drv);

	return 0;
}

int dfunc(double t, const double x[], double f[], void *params_ptr)
{
	f[0] = a*x[0] - c*x[0]*x[1];
	f[1] = -b*x[1] + d*x[0]*x[1];
	return GSL_SUCCESS;
}

void graph(int n, double t[], double x1[], double x2[])
{
	TCanvas *c1 = new TCanvas("c1","Preditor Prey Model", 200, 10, 600, 400);
	c1->SetGrid();

	TGraph *gr1 = new TGraph (n, t, x1);
	gr1->SetLineColor(4);
	gr1->SetMarkerStyle(3);
	gr1->SetTitle("Preditor Prey Model");
	gr1->GetXaxis()->SetTitle("Time");
	gr1->GetYaxis()->SetTitle("Population");
	gr1->Draw("AC*");
	
	TGraph *gr2 = new TGraph (n, t, x2);
	gr2->SetMarkerStyle(4);
	gr2->SetLineColor(2);
	gr2->Draw("CP");

	TLegend *leg = new TLegend(0.99,0.8,0.85,0.9);
	leg->AddEntry(gr1,"Rabbits","lp");
	leg->AddEntry(gr2,"Wolves","lp");
	leg->Draw();

	gPad->Update();
	gPad->Modified();
	
	gPad->SaveAs("PredPrey.png");
}



