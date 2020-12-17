// Gökhan Demir 26562 CS307 Operating Systems HW3 Sabancı University 2020-2021 Fall
#include <iostream>
#include <pthread.h>
#include <stdio.h>
#include <unistd.h>
#include <string>
#include <stdlib.h>
#include <ctime> // Needed for producing different random numbers.
#include <queue>
#include <semaphore.h>
using namespace std;

#define NUM_THREADS 10
#define MEMORY_SIZE 150

struct node
{
	int id;
	int size;
};

queue<node> myqueue; // shared queue
pthread_mutex_t sharedLock = PTHREAD_MUTEX_INITIALIZER; // mutex
pthread_t server; // server thread handle
sem_t semlist[NUM_THREADS]; // thread semaphores

int thread_message[NUM_THREADS]; // thread memory information
char memory[MEMORY_SIZE]; // memory size

int done=0, full=0; // done is a number of finished threads and full is a number of filled ones.

void my_malloc(int thread_id, int size) // This function will add the struct to the queue.
{
  /*This function should be called by a thread with a size value. It will gain access to the shared
  queue and write the requesting thread_id and the amount.*/
  node n1; // New node object called n1.
	n1.size=size;
  n1.id=thread_id;
  myqueue.push(n1); // New node called n1 will be added to the shared queue.
}

void * server_function(void *) // This function should grant or decline a thread depending on memory size.
{
    while(true)
    {
      while(!myqueue.empty())
      {
        node* request = new node();
        request = &myqueue.front();
        myqueue.pop();
        if((MEMORY_SIZE - full)< request->size)
        {
          thread_message[request->id] = -1; // Not enough memory.
        }
        else
        {
          thread_message[request->id] = full; // Enough memory.
        }
        sem_post(&semlist[request->id]);
      }
      if(10==done) // When all thread requests are handled, server thread should terminate.
      {
        break; // Server thread is terminating.
      }
    }
    return 0;
}

void * thread_function(void * id) // This function will create a random number and call my_malloc.
{
  // This function runs as a thread.
  pthread_mutex_lock(&sharedLock); // Locking.
  int* pointerOfID=(int *)id;
  int number=rand()%(MEMORY_SIZE/6) + 1; // The generated number should be between 1 and (MEMORY_SIZE / 6). 1 and 25 are included here.
  my_malloc(*pointerOfID,number);
  sem_wait(&semlist[*pointerOfID]);

  // Then fill the memory with ids or give an error prompt.
  if(thread_message[*pointerOfID] == -1)
  {
    cout << "Thread " << *pointerOfID << ": Not enough memory" << endl; // Error prompt.
  }
  else
	{
		for(int i=full; i < number + full; i++)
    {
      memory[i] = '1'; // Since memory can be allocated, corresponding indexes are set to 1. 
    }
    full+= number;
	}
  pthread_mutex_unlock(&sharedLock); // Unlocking.
  return 0;
}

void init()
{
  pthread_mutex_lock(&sharedLock);
	for (int i = 0; i < NUM_THREADS; i++) // Initializes semaphores.
	{
    sem_init(&semlist[i],0,0);
  }
	for (int i = 0; i < MEMORY_SIZE; i++)	// Filling memory array.
  {
    memory[i] = '0';
  }
  pthread_create(&server,NULL,server_function,NULL); // Starts server.
	pthread_mutex_unlock(&sharedLock);
}

void dump_memory()
{
  // This function is used to test memory allocation. It will print the entire contents of the memory array onto the console.
  cout << "Whole memory:" << endl; // You need to print the whole memory array here.
  for (int i=0; i < MEMORY_SIZE; i++)
  {
    cout << memory[i];
  }
}

int main(int argc, char* argv[])
{
  srand(time(NULL)); // Not to have same results all the time.

  int thread_ID[NUM_THREADS]; // You need to create a thread ID array here.
  for (int x=0;x<NUM_THREADS;x++)
  {
    thread_ID[x]=x;
  }

 	init();	// call init
  
  // You need to create threads with using thread ID array, using pthread_create().
  pthread_t thread[NUM_THREADS];
  for (int b=0; b<NUM_THREADS; b=b+1)
  {
    pthread_create(&thread[b],NULL,thread_function,(void *)&thread_ID[b]);
    // Since threads' status of working consecutively is not stated intentionally, I am not joining threads here.
  }

  pthread_mutex_lock(&sharedLock);
  done++;
  pthread_mutex_unlock(&sharedLock);

 	// You need to join the threads.
  for (int c=0; c<NUM_THREADS; c=c+1)
	{
    pthread_join(thread[c], NULL);
  }
  
 	dump_memory(); // This will print out the memory.
  
 	printf("\nMemory Indexes:\n" );
 	for (int i = 0; i < NUM_THREADS; i++)
 	{
 		printf("[%d]" ,thread_message[i]); // This will print out the memory indexes.
 	}
 	printf("\nTerminating...\n");
  return 0;
 }