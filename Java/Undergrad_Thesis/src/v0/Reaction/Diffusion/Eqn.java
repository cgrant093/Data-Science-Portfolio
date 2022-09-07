package Reaction.Diffusion;

// Differential Equation class

public class Eqn 
{
	//protected static double D = 0.1; // diffusion coefficient,
	protected static double Du = 0.045;
	protected static double Dv = 0.1;
	protected static double alpha = -0.899;
	protected static double beta = -0.91;
	protected static double gamma = -alpha;
	protected static double r2 = 2;
	protected static double r3 = 3.5;
		
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
	
	public static double rate(double[][][] grid, int i, int j, int k, double D, double A, double B, double r, double R)
	{
		double diff = diffusion(grid, i, j, k, D);
		double uTerm = uTerm(grid, i, j, A);
		double vTerm = vTerm(grid, i, j, B);
		double uvTerm = uvTerm(grid, i, j, r);
		double uv2Term = uv2Term(grid, i, j, R);
		
		double dRho = diff + uTerm + vTerm + uvTerm + uv2Term;
		
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
	
	public static double uTerm(double[][][] grid, int i, int j, double A) 
	{		
		double dRho = (A)*(grid[i][j][0]);///Math.pow(RD.delX,2);
					
		return dRho;
	}
	
	public static double vTerm(double[][][] grid, int i, int j, double B) 
	{	
		double dRho = (B)*(grid[i][j][1]);///Math.pow(RD.delX,2);
					
		return dRho;
	}
	
	public static double uvTerm(double[][][] grid, int i, int j, double r) 
	{	
		double dRhoU = grid[i][j][0];
		double dRhoV = grid[i][j][1];
		double dRho = (r)*(dRhoU*dRhoV);///Math.pow(RD.delX,2);
					
		return dRho;
	}
	
	public static double uv2Term(double[][][] grid, int i, int j, double R) 
	{	
		double dRhoU = grid[i][j][0];
		double dRhoV = grid[i][j][1];
		double dRho = (R)*(dRhoU*Math.pow(dRhoV,2));///Math.pow(RD.delX,2);
					
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
							kGrid[i][j][k] = rate(grid, i, j, k, Du, alpha, 1, (-r2), (-alpha*r3));					
						}
						
						if (k == 1)
						{
							kGrid[i][j][k] = rate(grid, i, j, k, Dv, gamma, beta, r2, (alpha*r3));					
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
