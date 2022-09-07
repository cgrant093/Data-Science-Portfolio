package fnWhiteNoise.noNegatives;

import java.util.Random;

//Differential Equation class

public class Eqn 
{
	protected static double Dv = 0.045;
	protected static double Dw = 0.1;
	protected static double a = 0.7;
	protected static double b = 0.8;
	protected static double iExt = 0.5;
	protected static double tau = 12.5;
	protected static double v3 = 1;
	protected static Random rand = new Random();
	
	public static double[][][] update(double[][][] grid)
	{
		double[][][] k1 = new double[RD.rowNum][RD.rowNum][2];
		double[][][] k2 = new double[RD.rowNum][RD.rowNum][2];
		double[][][] k3 = new double[RD.rowNum][RD.rowNum][2];
		double[][][] k4 = new double[RD.rowNum][RD.rowNum][2];
		
		double[][][] h1 = new double[RD.rowNum][RD.rowNum][2];
		double[][][] h2 = new double[RD.rowNum][RD.rowNum][2];
		double[][][] h3 = new double[RD.rowNum][RD.rowNum][2];
		
		double[][][] dRho = new double[RD.rowNum][RD.rowNum][2];
		double[][][] dRhoA = new double[RD.rowNum][RD.rowNum][2];
		double[][][] dRhoB = new double[RD.rowNum][RD.rowNum][2];
		
		
		k1 = kUpdate(k1, grid);
		h1 = hUpdate(h1, k1, grid);
		k2 = kUpdate(k2, grid);
		h2 = hUpdate(h2, k2, grid);		
		k3 = kUpdate(k3, grid);
		h3 = hUpdate(h3, k3, grid);	
		k4 = kUpdate(k4, grid);
		
		
		dRhoA = Calc.addMatrices(k1, Calc.scalarMult(k2, 2));
		dRhoB = Calc.addMatrices(Calc.scalarMult(k3, 2), k4);
		dRho = Calc.scalarMult(Calc.addMatrices(dRhoA, dRhoB), (RD.delT/6));
		
		grid = Calc.addMatrices(grid, dRho);
		
		for (int k=0; k<2; k++)
		{
			System.out.println(Calc.sumMatrix(grid, k));
		}
		
		return grid;
	}
	
	public static double rate(double[][][] grid, int i, int j, int k, double D, double A, double B, double I, double T, double V3, double N)
	{
		double diff = diffusion(grid, i, j, k, D);
		double vTerm = vTerm(grid, i, j);
		double wTerm = wTerm(grid, i, j, B);
		double v3Term = v3Term(grid, i, j, V3);
		
		double dRho = (diff/T) + (vTerm/T) + (wTerm/T) + v3Term + (A/T) + I + (0.1*N/Math.sqrt(RD.delT));
		
		return dRho;
	}
	
	public static double diffusion(double[][][] grid, int i, int j, int k, double D) 
	{	
		double mult = Grids.neighborCellPercent(i, j);
		double dRhoX = grid[i+1][j][k] - (mult)*2*grid[i][j][k] + grid[i-1][j][k];
		double dRhoY = grid[i][j+1][k] - (mult)*2*grid[i][j][k] + grid[i][j-1][k];
		double dRho = (D)*(dRhoX + dRhoY)/Math.pow(RD.delX,2);
					
		return dRho;
	}
	
	public static double vTerm(double[][][] grid, int i, int j) 
	{		
		double dRho = grid[i][j][0];
					
		return dRho;
	}
	
	public static double wTerm(double[][][] grid, int i, int j, double B) 
	{	
		double dRho = (B)*(grid[i][j][1]);
					
		return dRho;
	}
	
	public static double v3Term(double[][][] grid, int i, int j, double V3) 
	{	
		double dRhoV = grid[i][j][0];
		double dRho = (V3)*(Math.pow(dRhoV,3))/3;
					
		return dRho;
	}
	
	public static double[][][] hUpdate(double[][][] hGrid, double[][][] kGrid, double[][][] grid)
	{
		for (int i=0; i<RD.rowNum; i++)
		{
			for (int j=0; j<RD.rowNum; j++)
			{	
				for (int k=0; k<2; k++)
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
	
	public static double[][][] kUpdate(double[][][] kGrid, double[][][] grid)
	{
		for (int i=0; i<RD.rowNum; i++)
		{
			for (int j=0; j<RD.rowNum; j++)
			{	
				for (int k=0; k<2; k++)
				{	
					if (Grids.baseGrid[i][j] == 1)
					{
						if (k == 0)
						{
							kGrid[i][j][k] = rate(grid, i, j, k, Dv, 0, -1, iExt, 1, -v3, rand.nextGaussian());					
						}
						
						if (k == 1)
						{
							kGrid[i][j][k] = rate(grid, i, j, k, Dw, a, -b, 0, tau, 0, 0);					
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
