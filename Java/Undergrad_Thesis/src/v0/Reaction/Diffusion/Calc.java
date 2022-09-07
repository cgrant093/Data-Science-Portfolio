package Reaction.Diffusion;

// Calculations/Math class

public class Calc 
{	
	public static double[][][] addMatrices(double[][][] gridA, double[][][] gridB)
	{
		//System.out.println(gridA[G.center][G.center][0]);
		//System.out.println(gridB[G.center][G.center][1]);
		
		double[][][] grid = new double[RD.rowNum][RD.rowNum][2];
		
		for(int i=0; i<RD.rowNum; i++)
		{
			for(int j=0; j<RD.rowNum; j++)
			{
				for (int k=0; k<2; k++)
				{
					grid[i][j][k] = gridA[i][j][k] + gridB[i][j][k];
				}
			}
		}
		return grid;
	}
	
	public static double distance(double x1, double x2, double y1, double y2)
	{
		return Math.sqrt((Math.pow((y2-y1), 2) + Math.pow((x2-x1), 2))) * RD.delX; // basically the distance formula
	}
	
	public static double[][][] scalarMult(double[][][] grid, double scalar) 
	{
		double[][][] scaledGrid = new double[RD.rowNum][RD.rowNum][2];

	    for (int i=0; i<RD.rowNum; i++) 
	    {
	        for (int j=0; j<RD.rowNum; j++) 
	        {
	        	for (int k=0; k<2; k++)
	        	{
	        		scaledGrid[i][j][k] = scalar*grid[i][j][k];
	        	}
	        }
	    }
	    return scaledGrid;
	}

	public static double sumMatrix(double[][][] grid, int k)
	{
		double sum = 0;
		
		for (int i=0; i<RD.rowNum; i++) 
	    {
	        for (int j=0; j<RD.rowNum; j++) 
	        {
	        	sum = sum + grid[i][j][k];
	        }
	    }
		return sum;
	}
}
