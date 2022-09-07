package Knock2014.Rework;

import java.util.Random;

//Differential Equation class

public class Eqn 
{
	protected static Random rand1 = new Random();
	protected static Random rand2 = new Random();
	
	public static double[][][] update(double[][][] grid, int rowNum, double delX, double D1, double D2, double D3, double k1, double k2, double k4, double k5, double k6, double A1, double P0, double X0, double a, double b, double alpha, double beta, double epsilon)
	{
		double[][][] ku1 = new double[rowNum][rowNum][4];
		double[][][] ku2 = new double[rowNum][rowNum][4];
		double[][][] ku3 = new double[rowNum][rowNum][4];
		double[][][] ku4 = new double[rowNum][rowNum][4];
		
		double[][][] h1 = new double[rowNum][rowNum][4];
		double[][][] h2 = new double[rowNum][rowNum][4];
		double[][][] h3 = new double[rowNum][rowNum][4];
		
		double[][][] dRho = new double[rowNum][rowNum][4];
		double[][][] dRhoA = new double[rowNum][rowNum][4];
		double[][][] dRhoB = new double[rowNum][rowNum][4];
		
		
		ku1 = kUpdate(ku1, grid, delX, rowNum, D1, D2, D3, k1, k2, k4, k5, k6, A1, P0, X0, a, b, alpha, beta, epsilon);
		h1 = hUpdate(h1, ku1, grid, rowNum);
		ku2 = kUpdate(ku2, grid, delX, rowNum, D1, D2, D3, k1, k2, k4, k5, k6, A1, P0, X0, a, b, alpha, beta, epsilon);
		h2 = hUpdate(h2, ku2, grid, rowNum);		
		ku3 = kUpdate(ku3, grid, delX, rowNum, D1, D2, D3, k1, k2, k4, k5, k6, A1, P0, X0, a, b, alpha, beta, epsilon);
		h3 = hUpdate(h3, ku3, grid, rowNum);	
		ku4 = kUpdate(ku4, grid, delX, rowNum, D1, D2, D3, k1, k2, k4, k5, k6, A1, P0, X0, a, b, alpha, beta, epsilon);
		
		
		dRhoA = Calc.addMatrices(ku1, Calc.scalarMult(ku2, 2));
		dRhoB = Calc.addMatrices(Calc.scalarMult(ku3, 2), ku4);
		dRho = Calc.scalarMult(Calc.addMatrices(dRhoA, dRhoB), (RD.delT/6));
		
		grid = Calc.addMatrices(grid, dRho);
		
		for (int k=0; k<2; k++)
		{
			System.out.println(Calc.sumMatrix(grid, k));
		}
		
		return grid;
	}
	
	public static double ratePIP3(double[][][] grid, int i, int j, int k, double D, double C1, double C2, double A, double N, double delX, double A1)
	{
		double diff = diffusion(grid, i, j, k, D, delX);	// diffusion
		double hill = hill1(grid, i, j, C1, A1);		// hill function for pip3
		double inter = interaction(grid, i, j, C2);	// pip3*pten
		double Y = Y(grid, i, j, A);				// interference from Y 
		
		double dRho = diff + hill - inter + Y;
		
		return dRho;
	}
	
	public static double ratePTEN(double[][][] grid, int i, int j, int k, double D, double C4, double C5, double C6, double B, double delX, double P0, double A1)
	{
		double diff = diffusion(grid, i, j, k, D, delX);
		double hill = diffhill(grid, i, j, C4, P0);
		double hill2 = hill1(grid, i, j, C6, A1);
		double Y = Y(grid, i, j, B);
		double bigTerm = combo(grid, i, j, C5, hill2, Y, P0);
		
		double dRho = diff + hill - bigTerm;
		
		return dRho;
	}
	
	public static double rateX(double[][][] grid, int i, int j, int k, double D, double E, double A, double B, double N, double delX, double X0)
	{
		double diff = diffusion(grid, i, j, k, D, delX);
		double bigTerm = combo2(grid, i, j, E, A, B, X0);
		
		double dRho = diff + bigTerm + N;
		
		return dRho;
	}
	
	public static double rateY(double[][][] grid, int i, int j, int k, double D, double E, double delX)
	{
		double diff = diffusion(grid, i, j, k, D, delX);
		double combo = combo3(grid, i, j, E);
		
		double dRho = diff + combo;
		
		return dRho;
	}
	
	public static double diffusion(double[][][] grid, int i, int j, int k, double D, double delX) 
	{	
		double mult = Grids.neighborCellPercent(i, j);
		double dRhoX = grid[i+1][j][k] - (mult)*2*grid[i][j][k] + grid[i-1][j][k];
		double dRhoY = grid[i][j+1][k] - (mult)*2*grid[i][j][k] + grid[i][j-1][k];
		double dRho = (D)*(dRhoX + dRhoY)/Math.pow(delX,2);
					
		return dRho;
	}
	
	public static double hill1(double[][][] grid, int i, int j, double C, double A1) 
	{		
		double square = grid[i][j][0]*grid[i][j][0];
		double dRho = C*(square)/(A1 + square);
					
		return dRho;
	}
	
	public static double diffhill(double[][][] grid, int i, int j, double C, double P0) 
	{		
		double square = (grid[i][j][1] - P0)*(grid[i][j][1] - P0);
		double dRho = C*(square)/(P0 + square);
					
		return dRho;
	}
	
	public static double combo(double[][][] grid, int i, int j, double C, double hill, double Y, double P0) 
	{		
		double dRho = (grid[i][j][1] - P0)*(C + hill + Y);
					
		return dRho;
	}
	
	public static double combo2(double[][][] grid, int i, int j, double E, double A, double B, double X0) 
	{		
		double coeff = E*(grid[i][j][2])*(X0 - grid[i][j][2]);
		double dRho = coeff*(grid[i][j][2] - ((grid[i][j][3] + B)/A));
					
		return dRho;
	}
	
	public static double combo3(double[][][] grid, int i, int j, double E) 
	{		
		double dRho = E*(grid[i][j][2] - grid[i][j][3]);
					
		return dRho;
	}
	
	public static double interaction(double[][][] grid, int i, int j, double C) 
	{	
		double dRho = C*(grid[i][j][0])*(grid[i][j][1]);
					
		return dRho;
	}
	
	public static double Y(double[][][] grid, int i, int j, double C) 
	{	
		double dRho = C*(grid[i][j][3]);
					
		return dRho;
	}
	
	public static double[][][] hUpdate(double[][][] hGrid, double[][][] kGrid, double[][][] grid, int rowNum)
	{
		for (int i=0; i<rowNum; i++)
		{
			for (int j=0; j<rowNum; j++)
			{	
				for (int k=0; k<4; k++)
				{	
					if (Grids.baseGrid[i][j] == 1)
					{
						hGrid[i][j][k] = grid[i][j][k] + (RD.delT/2)*kGrid[i][j][k];					
					}
					else if (Grids.baseGrid[i][j] == 0)
					{
						grid[i][j][k] = 0;
					}
				}
			}
		}
		return hGrid;
	}
	
	public static double[][][] kUpdate(double[][][] kGrid, double[][][] grid, double delX, int rowNum, double D1, double D2, double D3, double k1, double k2, double k4, double k5, double k6, double A1, double P0, double X0, double a, double b, double alpha, double beta, double epsilon)
	{
		for (int i=0; i<rowNum; i++)
		{
			for (int j=0; j<rowNum; j++)
			{	
				for (int k=0; k<4; k++)
				{	
					if (Grids.baseGrid[i][j] == 1)
					{
						if (k == 0)
						{
							kGrid[i][j][k] = ratePIP3(grid, i, j, k, D1, k1, k2, alpha, rand1.nextGaussian(), delX, A1);					
						}
						
						if (k == 1)
						{
							kGrid[i][j][k] = ratePTEN(grid, i, j, k, D2, k4, k5, k6, beta, delX, P0, A1);					
						}
						
						if (k == 2)
						{
							kGrid[i][j][k] = rateX(grid, i, j, k, D2, epsilon, a, b, rand2.nextGaussian(), delX, X0);					
						}
						
						if (k == 3)
						{
							kGrid[i][j][k] = rateY(grid, i, j, k, 5*D2, epsilon, delX);					
						}
					}
					else if (Grids.baseGrid[i][j] == 0)
					{
						grid[i][j][k] = 0;
					}
				}
			}
		}
		return kGrid;
	}
}
