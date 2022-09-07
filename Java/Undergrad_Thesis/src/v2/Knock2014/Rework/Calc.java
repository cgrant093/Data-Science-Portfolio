package Knock2014.Rework;

//Calculations/Math class

public class Calc 
{	
	public static double[][][] addMatrices(double[][][] gridA, double[][][] gridB)
	{
		double[][][] grid = new double[RD.rowNum][RD.rowNum][2];
		
		for(int i=0; i<RD.rowNum; i++)
		{
			for(int j=0; j<RD.rowNum; j++)
			{
				for (int k=0; k<4; k++)
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
	        	for (int k=0; k<4; k++)
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
	
	public static double sumMatrix2D(double[][] grid)
	{
		double sum = 0;
		
		for (int i=0; i<RD.rowNum; i++) 
	    {
	        for (int j=0; j<RD.rowNum; j++) 
	        {
	        	sum = sum + grid[i][j];
	        }
	    }
		return sum;
	}
}
