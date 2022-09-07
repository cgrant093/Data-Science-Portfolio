package vary2parameters;

import java.lang.Math;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TwoParameters 
{

	public static void main(String[] args) throws IOException
	{
		
		double[] variable = new double[16]; // 16 is number of parameters, lambda components, and periods
		
		//set variables
		double p0 = variable[2] = 1;
		double A = variable[6] = 1;
		double kt1 = variable[8] = 3.9; //rt1
		double kt2 = variable[9] = 0.049; //kt

		double rp0 = variable[4] = 1*1;
		double rp1 = variable[5] = 1*1;
		double kx = variable[3] = 1*0.1;
		double kp = variable[7] = 1*0.01;
		
		//changing variables
		double rx0 = variable[0] = 0.1;
		double rx2 = variable[1] = 0.01;
		
		
		//title of text file
		//Make sure you change it so it makes sense (200*variable[x] only goes with the 2 variables that are changing)
		// and I like to place those two variables in the front
	    String result = "rx0_" + variable[0] + "-" + 200*variable[0] + " rx2_" + variable[1] + "-" + 200*variable[1] + 
	    		" rp0_" + variable[4] + " rp1_" + variable[5] + 
	    		" kp_" + variable[7]   + " kx_" + variable[3]  + 
	    		" rt1_" + variable[8] + " kt_" + variable[9] + " p0_" + variable[2] + " A_" + variable[6];
		
	    //creates file **remember to change the folder every time you run!!!
		FileWriter fw1 = null;
		File newTextFile1 = new File("C:\\Thesis Results\\vary2para\\" + result + ".txt");
		fw1 = new FileWriter(newTextFile1);
		
		//makes sure to not get stuck in newton's method
		int q = 0;
		
		//number of iterations
		int n = 1;
		
		//make sure you change these to to correctly reflect the variables that are changing
		//the first for loop will be the vertical variable
		//the second is the horizontal variable
		for(rx0 = variable[0]; rx0 <= 200*variable[0]; rx0 += variable[0])
		{	
			for(rx2 = variable[1]; rx2 <= 200*variable[1]; rx2 += variable[1])
			{
				System.out.println(n);
				n++;
				
				//System.out.println("Fixed points:");
				
				double e = 1;
				double v = 1; //p*
				
				q = 0;
				
				
				while(e > 0.01)
				{	
					//System.out.println(v);
					
					double f = -Math.log(kx*kt1*Math.pow(v,2)*(A+v)*kp/(kt2*(rp0*A+rp0*v+rp1*v)
							*(rx0+rx2*Math.pow(kt1,2)*Math.pow(v,4)*Math.pow((A+v),2)*Math.pow(kp,2)
							/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),2)))))*p0;
					
					//System.out.println("f is " + f);
					
					double fprime = -(2*kx*kt1*v*(A+v)*kp/(kt2*(rp0*A+rp0*v+rp1*v)*(rx0+rx2*Math.pow(kt1,2)*Math.pow(v,4)*Math.pow((A+v),2)*Math.pow(kp,2)/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),2))))
							-kx*kt1*Math.pow(v,2)*(A+v)*kp*(rp0+rp1)/(kt2*Math.pow((rp0*A+rp0*v+rp1*v),2)*(rx0+rx2*Math.pow(kt1,2)*Math.pow(v,4)*Math.pow((A+v),2)*Math.pow(kp,2)/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),2))))
							+kx*kt1*Math.pow(v,2)*kp/(kt2*(rp0*A+rp0*v+rp1*v)*(rx0+rx2*Math.pow(kt1,2)*Math.pow(v,4)*Math.pow((A+v),2)*Math.pow(kp,2)/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),2))))
							-kx*kt1*Math.pow(v,2)*(A+v)*kp*(4*rx2*Math.pow(kt1,2)*Math.pow(v,3)*Math.pow((A+v),2)*Math.pow(kp,2)/(Math.pow(kt2,2)
							*Math.pow((rp0*A+rp0*v+rp1*v),2))-2*rx2*Math.pow(kt1,2)*Math.pow(v,4)*Math.pow((A+v),2)*Math.pow(kp,2)*(rp0+rp1)
							/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),3))+2*rx2*Math.pow(kt1,2)*Math.pow(v,4)*(A+v)*Math.pow(kp,2)/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),2)))
							/(kt2*(rp0*A+rp0*v+rp1*v)*Math.pow((rx0+rx2*Math.pow(kt1,2)*Math.pow(v,4)*Math.pow((A+v),2)*Math.pow(kp,2)/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),2))),2)))*kt2*(rp0*A+rp0*v+rp1*v)
							*(rx0+rx2*Math.pow(kt1,2)*Math.pow(v,4)*Math.pow((A+v),2)*Math.pow(kp,2)/(Math.pow(kt2,2)*Math.pow((rp0*A+rp0*v+rp1*v),2)))*p0/(kx*kt1*Math.pow(v,2)*(A+v)*kp);
					
					//System.out.println("fprime is " + fprime);
					
					double vnew = v - (f / fprime);
					
					e = Math.abs(v - vnew);
					
					//System.out.println(vnew);
					
					//System.out.println("e is " + e);
					
					v = vnew;
					
					//System.out.println ("v is " + v);
					q++;
					
					if(q >= 1000)
					{
						e = 0.001;
						v = -1;
					}
				}	
				
				
				
				
				//System.out.println (v);
				
				double u = kt1*Math.pow(v,2)*(A+v)*kp/(kt2*(rp0*A+rp0*v+rp1*v)); //x*
				double w = (rp0*A+rp0*v+rp1*v)/((A+v)*kp*v); //t*
				
				variable[10] = v;
				variable[11] = u;
				variable[12] = w;
				
				//System.out.println(v + "\n" + u + "\n" + w + "\n");
				
				double[] variable2 = new double[16];
				double[] variable3 = new double[16];
				
				for (int i=0; i<16; i++)
				{
					variable2[i] = variable[i];
					variable3[i] = variable[i];
				}
		
				// DELTA = 18*a*b*c*d-4*b^3*d + b^2*c^2 - 4*a*c^3 - 27*a^2*d^2
				
				double alpha = -1; //a
				double beta;   //b
				double gamma;  //c
				double eta;    //d
				
				double delta;

				double sqroot;
				double firstTerm;
				
				if (v>0 && w>0 && u>0)
				{
					beta = 2*rx2*u*Math.exp(-v/p0)-kp*w-kt2*u-kx+rp1*A/Math.pow((A+v),2);
					
					gamma = 2*rx2*u*Math.exp(-v/p0)*(kt2*u+kp*w+rp1*A/Math.pow((A+v),2))
							-kx*kt2*u-kp*v*kt1-kx*kp*w-kt2*kp*w*u
							+(kt2*u+kx)*rp1*A/Math.pow((A+v),2);
					
					eta = 2*rx2*u*Math.exp(-v/p0)*(kt2*kp*w*u+kp*v*kt1
							+rp1*A*kt2*u/Math.pow((A+v),2))
							-Math.exp(-v/p0)*kt2*kp*w*u*(rx2*Math.pow(u,2)
							-rx0)/p0-kx*kp*(kt1*v+w*v*kt2)
							+kx*rp1*A*kt2*u/Math.pow((A+v),2);
					
					delta = 18*alpha*beta*gamma*eta 
							- 4*Math.pow(beta,3)*eta 
							+ Math.pow(beta,2)*Math.pow(gamma,2) 
							- 4*alpha*Math.pow(gamma,3) 
							- 27*Math.pow(alpha,2)*Math.pow(eta,2);
					
					
					if (delta < 0)
					{
						sqroot = Math.pow(2*Math.pow(beta, 3) - 9*alpha*beta*gamma + 27*Math.pow(alpha, 2)*eta, 2)
								- 4*Math.pow(Math.pow(beta, 2) - 3*alpha*gamma, 3);
						firstTerm = 2*Math.pow(beta, 3) - 9*alpha*beta*gamma + 27*Math.pow(alpha, 2)*eta;
						
						if (sqroot > 0)
						{
							//2 complex lambdas
							variable3[13] = (-beta/(3*alpha)) + Math.cbrt((firstTerm + Math.sqrt(sqroot))/2)/(6*alpha) + Math.cbrt((firstTerm - Math.sqrt(sqroot))/2)/(6*alpha);
							variable2[13] = variable[13];
							//Real Lambda Component (same for both lambdas)
							
							variable3[14] = Math.sqrt(3)*Math.cbrt((firstTerm + Math.sqrt(sqroot))/2)/(6*alpha) - Math.sqrt(3)*Math.cbrt((firstTerm - Math.sqrt(sqroot))/2)/(6*alpha);
							//First Imaginary Lambda Component
							
							variable2[14] = Math.sqrt(3)*Math.cbrt((firstTerm - Math.sqrt(sqroot))/2)/(6*alpha) - Math.sqrt(3)*Math.cbrt((firstTerm + Math.sqrt(sqroot))/2)/(6*alpha);
							//Second Imaginary Lambda Component
							
							//period
							variable3[15] = 2*3.14159265/(variable3[14]);
							variable2[15] = 2*3.14159265/(variable2[14]);
							
							//System.out.println("Lambda 1: " + variable3[13] + " + " + variable3[14] + "i\nLambda 2: " + variable2[13] + " + " + variable2[14] +"i");
							
							variable3[0] = rx0;
							variable3[1] = rx2;
							variable3[2] = p0;
							variable3[3] = kx;
							variable3[4] = rp0;
							variable3[5] = rp1;
							variable3[6] = A;
							variable3[7] = kp;
							variable3[8] = kt1;
							variable3[9] = kt2;
							
							variable2[0] = rx0;
							variable2[1] = rx2;
							variable2[2] = p0;
							variable2[3] = kx;
							variable2[4] = rp0;
							variable2[5] = rp1;
							variable2[6] = A;
							variable2[7] = kp;
							variable2[8] = kt1;
							variable2[9] = kt2;

							
							

							if((variable2[14] > 0) || (variable3[14] > 0))
							{
								if((variable2[14] > 0))
								{
									fw1.write(variable2[15] + "\t");
								}
								else if((variable3[14] > 0))
								{
									fw1.write(variable3[15] + "\t");
								}
							}
							else
							{
								fw1.write(0.0 + "\t");
							}
							
						}			
					}
					else
					{
						System.out.println("Delta is greater than or equal to 0... :(");
						fw1.write(0.0 + "\t");
					}
				}
				else
				{
					System.out.println("u, v, or w is less than or equal to zero... which is lame");
					fw1.write(0.0 + "\t");
				}
			}
			fw1.write("\r\n");
		}
		fw1.close();
	}
}
