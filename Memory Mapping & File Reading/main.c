#include <stdio.h>

#pragma warning(suppress : 4996)
int main()
{
	char file_name[15] = "loremipsum.txt";
	FILE* fp;
	
	fp = fopen(file_name, "r");
	
	if (fp == NULL) 
	{
		printf("Could not open file %s",file_name);
		return 0;
	}
	char c;
	int count = 0;
	for (c = getc(fp); c != EOF; c = getc(fp))
	{
		// Increment count for this character 
		if (c == 'a')
			count++;
	}
	printf("Count is %i\n", count);
	return 0;
}