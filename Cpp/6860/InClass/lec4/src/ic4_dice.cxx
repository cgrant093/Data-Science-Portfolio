// ic4 dice game
#include <iostream>  

using namespace std;

void PrintGame(int player1[], int player2[], int N);
void Analysis(int player1[], int player2[], int N, int &win, int &loose, int &tie);

int main( )
{ 
  int win=0; int loose=0; int tie=0;

  int nThrows=0;
  cout<<endl;
  cout<<" Number of times to play= ";
  cin>>nThrows;

  int *player1 = new int[nThrows];
  int *player2 = new int[nThrows];
  
  srand(time(NULL)); 

  for (int i = 0; i < nThrows ; i++)
    {
      int randValue1 = 1+rand()%6;     
      int randValue2 = 1+rand()%6;  
      player1[i]=randValue1;
      player2[i]=randValue2;
    }

  PrintGame(player1,player2,nThrows);
  Analysis(player1,player2,nThrows, win, loose, tie);

  cout<<endl;
  cout<<" Win = "<<win<<endl;
  cout<<" Loss = "<<loose<<endl;
  cout<<" Ties = "<<tie<<endl;
  cout<<endl;

  // free up the memory!!!
  delete [] player1;
  delete [] player2;
 
  cout<<endl;
  return 0;
}

void Analysis(int player1[], int player2[], int N, int &win, int &loose, int &tie)
{
  for (int i = 0; i < N ; i++)
    {
      if (player1[i]>player2[i])
	{
	  win++;
	}
      else if (player1[i]<player2[i])
	{
	  loose++;
	}
      else
	{
	  tie++;
	}
    }
	  
}

void PrintGame(int player1[], int player2[], int N)
{
  cout<<endl;
  cout<<"Print the Game:"<<endl;
  cout<<endl;
  // so start loop at zero seems to be better
  for (int i = 0; i < N ; i++)
    {
      cout<<"#"<<i+1<<" Player 1 = "<<player1[i]<<" vs. Player 2 = "<<player2[i]<<endl;
   }  
}
