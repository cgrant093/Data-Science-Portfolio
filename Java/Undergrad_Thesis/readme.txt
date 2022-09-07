Firstly, I would ask your forgiveness if your eyes peer upon this code. 
It was fairly early in my coding career and the lack of experience shows.
Very inefficient proceedures and little comments. 
There are pieces of it that are even hard for me to read.

This work was done over ~2 years, 2014-2016, 
and most of the work was performed only during the semester,
since my adviser and I had trouble communicating remotely.

The thesis was studying a specific protein interaction found inside slime mold cells.
When two specific proteins inside the cell has specific concentration amounts, 
various periodic interactions would take place over time.
We constructed a three-body model (the two proteins, and a mysterious third species)
to try to simulate and recreate some of these interactions.
More details can be read in Thesis word document.

As for the code, since it was over a couple years with various breaks, 
the code took on many versions:

- v0-2 is my take at trying to numerically solve those coupled differential equations
using linear stability analysis. There are files for my own matrix class, etc. 
The different version are using previous works to modify the solver.

- v3 used Maple to symbolically solve for the Jacobian, I believe?
I then use numerical analysis (in Java) to solve for the remaining pieces.

- v4-5 are taking a different approach. There are something like 10 parameters,
which was one of the difficult parts about solving the differential equations.
We decided to find parameter sets in the 10D space that would result in 
stable eigenvalues that give off periodic motion.

We set some of these parameters with reported experimental measurements.
- v4 sets 2 parameters, and then tries to vary the remaining 8 with nested for loops.
Talk about O(n^8) inefficient programming!
- v5 sets 8 parameters, and varies two at a time, then will go back 
and vary different pairs. 

This is where the project left my hands due to graduating and what I wrote the thesis on. 
From what I heard, some future student continued from where I left off.
