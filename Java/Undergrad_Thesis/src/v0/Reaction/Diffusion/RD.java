package Reaction.Diffusion;

import java.util.Random;
import org.opensourcephysics.frames.*;

// main class, RD stands for Reaction-Diffusion model

public class RD
{	
	// Initialization of various variables
	
	protected static double radius= 5; //units = microns
	protected static double PIP3 = 10; //microMolar concentration of protein PIP3
	protected static double PTEN = 10; //microMolar concentration of protein PTEN
	protected static double time = 5; // seconds
	protected static double delT = .001; // delta time is 1 second
			
	// Calculated variables
			
	protected static double delX = 3*Math.sqrt(Eqn.Dv*delT); //units = microns
	protected static int rowNum = (int)(2*radius/delX); // sets row and column number
	protected static int center = (rowNum-1)/2; // sets a box to be declared the origin of the circle
		
	public static void main(String[] args)
	{
		// Initialization of the Variables 
		
		Grids.baseGrid = new double[rowNum][rowNum]; // makes the state grid (2D) with rowNum dimensions		
		Grids.pip3Grid = new double[rowNum][rowNum];
		Grids.ptenGrid = new double[rowNum][rowNum];
		double[][][] concentration = new double[rowNum][rowNum][2];
		
		Scalar2DFrame frameA = new Scalar2DFrame("x (microns)", "y (microns)", "PIP3"); 
		Scalar2DFrame frameB = new Scalar2DFrame("x (microns)", "y (microns)", "PTEN"); 
		
		frameA.setVisible(true);
		frameA.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frameB.setVisible(true);
		frameB.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
		// Gridwork... haha
		
		
		Grids.gridState(rowNum, center); 
		
		Random rand = new Random();
		
		for (int i=0; i<rowNum; i++)
		{
			for (int j=0; j<rowNum; j++)
			{
				concentration[i][j][0] = rand.nextDouble() * PIP3/86225;
				concentration[i][j][1] = rand.nextDouble() * PTEN/86225;
			}
		}
		
		for (int t=1; t < (int)(RD.time/RD.delT); t++) 
		{
			System.out.println(t);
			concentration = Eqn.update(concentration);
		}
		
		Grids.pip3Grid = Grids.separation(concentration, Grids.pip3Grid, 0);
		Grids.ptenGrid = Grids.separation(concentration, Grids.ptenGrid, 1);
		
		graph(Grids.pip3Grid, frameA);
		graph(Grids.ptenGrid, frameB);
		
		System.out.println(rowNum + " " + center + " " + radius);
	}
	
	public static void graph(double[][] grid, Scalar2DFrame frame)
	{
		frame.setAll(grid, -5, 5, -5, 5); 
	}
	
}
