// Gökhan Demir's first CS307 homework in Fall 2020-2021, Student ID: 26562
#include <iostream>
#include <pthread.h> // for POSIX threads.
#include <cstdlib> // for rand()
#include <ctime>
using namespace std;

int MatrixM[2][50]; // First of all, I created a global MatrixM with size 2 (rows) x 50 (columns) and each of the cells of the matrix will represent a seat in the airplane. It is empty now. It will be filled with 0s in main() firstly.
int seatCount = 0; // It has to be 0 initially, I will increment it. Global variable.
int turn = 0; // It has to be 0 initially. Global variable.

int random (int); // Prototype

void *travelagency1(void*)
{
  while(seatCount<100)
  {
    int x = random(seatCount);
    while (turn!=0){} // Different than Thread 2.
    cout << "Agency 1 Entered Critical Region" << endl;
    if (x <= 50 && MatrixM[0][x-1]==0) // Condition for x <= 50, namely in the first row.
    {
      MatrixM[0][x-1]=1; // Since this is the Thread 1, it will fill with 1.
      seatCount=seatCount+1; // seatCount is incremented.
      cout << "Seat Number " << x << "  is reserved by Agency 1" << endl;
    }
    else if(x > 50 && MatrixM[1][x-51]==0)
    {
      MatrixM[1][x-51]=1; // Since this is Thread 1, it will fill with 1.
      seatCount=seatCount+1; // seatCount is incremented.
      cout << "Seat Number " << x << "  is reserved by Agency 1" << endl;
    }
    // Nothing for else.
    cout << "Agency 1 Exit Critical Region" << endl << "\n";
    turn = 1; // turn has to be 1 now.
  }
  return 0;
}

void *travelagency2(void*)
{
  while (seatCount<100)
  {
    int y = random(seatCount);
    while (turn!=1){} // Different than Thread 1.
    cout << "Agency 2 Entered Critical Region" << endl;
    if (y <= 50 && MatrixM[0][y-1]==0) // Condition for y <= 50, namely in the first row.
    {
      MatrixM[0][y-1]=2; // Since this is Thread 2, it will fill with 2.
      seatCount=seatCount+1; // seatCount is incremented.
      cout << "Seat Number " << y << "  is reserved by Agency 2" << endl;
    }
    else if(y > 50 && MatrixM[1][y-51]==0)
    {
      MatrixM[1][y-51]=2; // Since this is Thread 2, it will fill with 2.
      seatCount=seatCount+1; // seatCount is incremented.
      cout << "Seat Number " << y << "  is reserved by Agency 2" << endl;
    }
    // Nothing for else.
    cout << "Agency 2 Exit Critical Region" << endl << "\n";
    turn = 0; // turn has to be 1 now.
  }
  return 0;
}

int main()
{
	srand(time(NULL));
  /* This is the main thread which initiates 2 other threads called TravelAgency1 and TravelAgency2
  together with a 2-D array of fixed size representing the seats in a plane. Note that this
  2-D array representing the seats is a shared data structure, so the travel agency threads can
  access and update it. Accessing a shared data structure may cause a race condition. If the
  flight is full, meaning that the shared 2-D array is fully marked, then travel agency threads
  should terminate and main thread should print the seats to the screen.*/

  pthread_t TravelAgency1, TravelAgency2; // There will be one thread per agency simulating the activities of that agency.

  for (int row=0;row<2;row++) // All the cells will initially be marked as 0 by main thread.
  {
    for (int column=0;column<50;column++) // Nested loop, I have 50 columns in MatrixM.
    {
      MatrixM[row][column]=0; // Initially all elements in the matrix are equal to 0.
    }
  }

  pthread_create(&TravelAgency1,NULL,travelagency1,NULL); // This is how TravelAgency1 is created by me.
  pthread_create(&TravelAgency2,NULL,travelagency2,NULL); // This is how TravelAgency2 is created by me.

  pthread_join(TravelAgency1,NULL); // I used join here for waiting TravelAgency1 to finish.
  pthread_join(TravelAgency2,NULL); // I used join here for waiting TravelAgency2 to finish.

  for (int row=0;row<2;row++) // In the end, when Main thread sees that MatrixM is full, MatrixM will be printed on the console.
  {
    for (int column=0;column<50;column++) // Nested loop, I have 50 columns in MatrixM.
    {
      cout << MatrixM[row][column] << " "; // Printing content of the row. 50 components.
    }
    cout << endl; // Goint to the next line when a row finishes.
  }
  return 0; // My program ends here.
}

int random(int seatCount)
{
  int numberr1 = rand() % 100 + 1; // Numbers from 1 to 100(included).
  return numberr1;
}