#include <iostream>
#include <fstream>
using namespace std;

int main()
{
	ifstream rfile;
	int count=0;
	rfile.open("loremipsum.txt");
	if(rfile.is_open())
	{
		char ch;
		while (rfile >> ch) // while (rfile >> noskipws >> ch)
		{
			if(ch=='a')
			{
				count++;
			}
		}
	}
	cout << count << endl;
	return 0;
}