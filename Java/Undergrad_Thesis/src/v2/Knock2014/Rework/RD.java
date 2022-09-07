package Knock2014.Rework;

import org.opensourcephysics.frames.*; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

//main class, RD stands for Reaction-Diffusion model

public class RD
{	
	// Initialization of various variables
	
	protected static double radius = 5; //units = microns
	protected static double PIP3 = 10; //microMolar concentration of protein PIP3
	protected static double PTEN = 10; //microMolar concentration of protein PTEN
	protected static double X = 10; //microMolar concentration of protein X
	protected static double Y = 10; //microMolar concentration of protein Y
	protected static double time = 5; // seconds
	protected static double delT = .01; // delta time .01 seconds
			
	// Calculated variables
	
	public static void main(String[] args) throws Exception
    {
		double[] variable = new double[16]; // 16 is number of parameters
		
		variable = readTextFile(variable);

		double D1 = variable[1];
		double D2 = variable[2];
		double D3 = variable[3];
		double k1 = variable[4];
		double k2 = variable[5];
		double k4 = variable[6];
		double k5 = variable[7];
		double k6 = variable[8];
		double A1 = variable[9];
		double P0 = variable[10];
		double X0 = variable[11];
		double a = variable[12];
		double b = variable[13];
		double alpha = variable[14];
		double beta = variable[15];
		double epsilon = variable[16];
		
		// Initialization of the Variables 
		
		double delX = 3*Math.sqrt(D2*delT); //units = microns
		int rowNum = (int)(2*radius/delX); // sets row and column number
		int center = (rowNum-1)/2; // sets a box to be declared the origin of the circle
		
		// Matrices are [row][column]
		Grids.baseGrid = new double[rowNum][rowNum]; // makes the state grid (2D) with rowNum dimensions		
		Grids.pip3Grid = new double[rowNum][rowNum]; 
		Grids.ptenGrid = new double[rowNum][rowNum];
		Grids.xGrid = new double[rowNum][rowNum]; 
		Grids.yGrid = new double[rowNum][rowNum];
		double[][][] concentration = new double[rowNum][rowNum][4];
		
		// Gridwork
		
		Grids.gridState(rowNum, center); 
		int cell = (int)(Calc.sumMatrix2D(Grids.baseGrid));
		
		for (int i=0; i<rowNum; i++)
		{
			for (int j=0; j<rowNum; j++)
			{
				concentration[i][j][0] = PIP3/cell;
				concentration[i][j][1] = PTEN/cell;
				concentration[i][j][2] = X/cell;
				concentration[i][j][3] = Y/cell;
			}
		}
		
		for (int t=1; t < (int)(time/delT); t++) 
		{
			System.out.println(t);
			concentration = Eqn.update(concentration, rowNum, delX, D1, D2, D3, k1, k2, k4, k5, k6, A1, P0, X0, a, b, alpha, beta, epsilon);
			
			if ((t+1) % 100 == 0) // print off every 10 delT's
			{	
				String s = iterationText(t);
				
				writeToFile(concentration, s, rowNum);
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
	
	public static void writeToFile(double[][][] grid, String s, int rowNum) throws IOException
	{
		FileWriter fw1 = null;
		FileWriter fw2 = null;
		
		File newTextFile1 = new File("C:\\Users\\Cody Dody Flody Rody\\Desktop\\Thesis\\knock2014\\Run 1\\PIP3_" + s + ".txt");
		File newTextFile2 = new File("C:\\Users\\Cody Dody Flody Rody\\Desktop\\Thesis\\knock2014\\Run 1\\PTEN_" + s + ".txt");
		
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
	
	public static double[] readTextFile(double[] variable) throws IOException
	{
		try
		{
			Scanner file = new Scanner(new File("dataFile.txt"));
			
			int i = 0;
			
			while (file.hasNextDouble( ))
			{
				Double parameter = file.nextDouble( );
				variable[i] = parameter;
				i = i+1;
			}
			
			file.close();
		}
		
		catch (FileNotFoundException fnfe)
		{
			System.out.println("Unable to find dataFile.txt, exiting");
		}
		
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		
		return variable;
	}
}
