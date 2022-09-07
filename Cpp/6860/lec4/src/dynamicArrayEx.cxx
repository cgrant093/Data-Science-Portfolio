
#include "stdlib.h"
#include <iostream> 
#include <cmath> 

using namespace std;

void PrintGame(int player1[], int player2[], int N);
void analyzeGames(int player1[], int player2[], int nThrows, int &win, int &lose, 				int&tie);

int main( )
{ 
  	int win=0; 
	int lose=0; 
	int tie=0;

  	int nThrows=0;
  	cout<<endl;
  	cout<<"Number of times to play= ";
  	cin>>nThrows;

  	int *player1 = new int[nThrows];
	int *player2 = new int[nThrows];
  
  
  	srand(time(NULL)); 

	int randValue1 = 0;     
	int randValue2 = 0;

  	for (int i = 0; i < nThrows ; i++)
    	{
      		randValue1 = 1+rand()%6;     
      		randValue2 = 1+rand()%6;  
      		player1[i]=randValue1;
      		player2[i]=randValue2;
    	}

  	PrintGame(player1,player2,nThrows);

	analyzeGames(player1,player2,nThrows,win,lose,tie);
  

  	// free up the memory!!!
  	delete [] player1;
  	delete [] player2;
 
  	cout<<endl;
  	return 0;
}



void PrintGame(int player1[], int player2[], int nThrows)
{
  	cout<<"\nFight!\n\n";

  	// so start loop at zero seems to be better
  	for (int i = 0; i < nThrows; i++)
    	{
     		cout<<"Round "<<i+1<<": Player 1 = "<<player1[i]<<" vs. Player 2 = "
			<<player2[i]<<endl;
   	}  
}

void analyzeGames(int player1[], int player2[], int nThrows, int &win, int &lose, int&tie)
{
	for (int i = 0; i < nThrows; i++)
    	{
     		if(player1[i]>player2[i])
		{
			win++;
		}
		else if(player1[i]<player2[i])
		{
			lose++;
		}
		else
		{
			tie++;
		}
   	} 

	cout<<"\n\nResults:\n\n"
		<<"Player 1 won "<<win<<" times.\n"
		<<"Player 2 won "<<lose<<" times.\n"
		<<"They tied "<<tie<<" times.\n\n";
}
