package fnWhiteNoise.noNegatives;

import org.opensourcephysics.frames.*; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//main class, RD stands for Reaction-Diffusion model

public class RD
{	
	// Initialization of various variables
	
	protected static double radius = 5; //units = microns
	protected static double PIP3 = 10; //microMolar concentration of protein PIP3
	protected static double PTEN = 10; //microMolar concentration of protein PTEN
	protected static double time = 5; // seconds
	protected static double delT = .01; // delta time .01 seconds
			
	// Calculated variables
			
	protected static double delX = 3*Math.sqrt(Eqn.Dw*delT); //units = microns
	protected static int rowNum = (int)(2*radius/delX); // sets row and column number
	protected static int center = (rowNum-1)/2; // sets a box to be declared the origin of the circle
		
	public static void main(String[] args) throws Exception
	{
		// Initialization of the Variables 
		
		// Matrices are [row][column]
		Grids.baseGrid = new double[rowNum][rowNum]; // makes the state grid (2D) with rowNum dimensions		
		Grids.pip3Grid = new double[rowNum][rowNum]; 
		Grids.ptenGrid = new double[rowNum][rowNum];
		double[][][] concentration = new double[rowNum][rowNum][2];
		
		// Gridwork... haha
		
		Grids.gridState(rowNum, center); 
		int cell = (int)(Calc.sumMatrix2D(Grids.baseGrid));
		
		for (int i=0; i<rowNum; i++)
		{
			for (int j=0; j<rowNum; j++)
			{
				concentration[i][j][0] = PIP3/cell;
				concentration[i][j][1] = PTEN/cell;
			}
		}
		
		for (int t=1; t < (int)(time/delT); t++) 
		{
			System.out.println(t);
			concentration = Eqn.update(concentration);
			
			if ((t+1) % 100 == 0) // print off every 10 delT's
			{	
				String s = iterationText(t);
				
				writeToFile(concentration, s);
			}
			
			/*if ((t+1) % 2000 == 0)
			{
				Grids.pip3Grid = Grids.separation(concentration, Grids.pip3Grid, 0);
				Grids.ptenGrid = Grids.separation(concentration, Grids.ptenGrid, 1);
				
				Scalar2DFrame frameA = new Scalar2DFrame("x (microns)", "y (microns)", "PIP3 at " + (t+1)*delT + " seconds"); 
				Scalar2DFrame frameB = new Scalar2DFrame("x (microns)", "y (microns)", "PTEN at " + (t+1)*delT + " seconds"); 
				
				frameA.setVisible(true);
				//frameA.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				frameB.setVisible(true);
				//frameB.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				
				graph(Grids.pip3Grid, frameA);
				graph(Grids.ptenGrid, frameB);
			}*/
		}
		
		System.out.println(rowNum + " " + center + " " + radius);
	}
	
	public static void graph(double[][] grid, Scalar2DFrame frame)
	{
		frame.setAll(grid, -5, 5, -5, 5); 
	}
	
	public static String iterationText(int t)
	{
		int n = (int)((t+1)*delT); // n is in seconds
		
		String s = "";
		
		if (n < 10)
		{
			s = "000" + n;
		}
		else if (n < 100)
		{
			s = "00" + n;
		}
		else if (n < 1000)
		{
			s = "0" + n;
		}
		else
		{
			s = "" + n;
		}
		
		return s;
	}
	
	public static void writeToFile(double[][][] grid, String s) throws IOException
	{
		FileWriter fw1 = null;
		FileWriter fw2 = null;
		
		File newTextFile1 = new File("C:\\Users\\Cody Dody Flody Rody\\Desktop\\Thesis\\FN with noise\\Run 4\\PIP3_" + s + ".txt");
		File newTextFile2 = new File("C:\\Users\\Cody Dody Flody Rody\\Desktop\\Thesis\\FN with noise\\Run 4\\PTEN_" + s + ".txt");
		
		fw1 = new FileWriter(newTextFile1);
		fw2 = new FileWriter(newTextFile2);
		
		for (int i=0; i<rowNum; i++)
		{
			String pip3 = "";
			String pten = "";
			
			for (int j=0; j<rowNum; j++)
			{	
				pip3 = pip3 + grid[i][j][0] + " ";			
				pten = pten + grid[i][j][1] + " ";						
			}
			
	        fw1.write(pip3 + "\r\n");
	        fw2.write(pten + "\r\n");
		}
		
		fw1.close();
		fw2.close();
	}
}
