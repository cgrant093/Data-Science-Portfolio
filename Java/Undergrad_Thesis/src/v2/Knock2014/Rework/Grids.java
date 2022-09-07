package Knock2014.Rework;

public class Grids 
{
	// Initialization of Grids
	
	protected static double[][] baseGrid; // Grid, tells us if box is inside or outside the circle
	protected static double[][] pip3Grid; // Grid, for protein PIP3
	protected static double[][] ptenGrid; // Grid, for protein PTEN
	protected static double[][] xGrid; // Grid, for protein PIP3
	protected static double[][] yGrid; // Grid, for protein PTEN
		
	public static void gridState(int rowNum, int center)
	{
		double distance; // sets up variable distance
			
		for (int i=0; i<rowNum; i++) 
		{
			for (int j=0; j<rowNum; j++)
			{
				distance = Calc.distance(i, center, j, center);
				
				if (distance <= RD.radius - RD.delX) // one array box away from edge
				{
					Grids.baseGrid[i][j] = 1; // sets to 1 if on the inside of circle
				}
					
				else
				{
					Grids.baseGrid[i][j] = 0; // 0 if outside circle
				}
			}		
		}
	}
		
	public static double neighborCellPercent(int i, int j)
	{
		double neighborSum = Grids.baseGrid[i-1][j] + Grids.baseGrid[i+1][j] + Grids.baseGrid[i][j-1] + Grids.baseGrid[i][j+1];
		double mult = neighborSum/4;
		return mult;
	}
		
	public static double[][] separation(double[][][] gridA, double[][] grid, int k)
	{	
		for (int i=0; i<RD.rowNum; i++)
		{	
			for (int j=0; j<RD.rowNum; j++)
			{
				grid[i][j] = gridA[i][j][k];
			}
		}
		return grid;
	}
}
